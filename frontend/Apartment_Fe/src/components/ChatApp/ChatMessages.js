import {
  addDoc,
  collection,
  doc,
  onSnapshot,
  query,
  orderBy,
  serverTimestamp,
  updateDoc,
  limit,
} from 'firebase/firestore'
import { useContext, useEffect, useRef, useState } from 'react'
import { FirebaseDb } from '../../configs/Firebase'
import UserContext from '../../contexts/UserContext'
import vi from 'javascript-time-ago/locale/vi'
import TimeAgo from 'javascript-time-ago'
import { ChatAppContext } from './ChatApp'
import { Spinner } from 'react-bootstrap'

const ChatMessages = () => {
  const { chatId, otherUser } = useContext(ChatAppContext)
  const [messages, setMessages] = useState([])
  const [text, setText] = useState('')
  const [currentUser] = useContext(UserContext)
  const [unsub, setUnsub] = useState(null)
  const [hasMore, setHasMore] = useState(true)
  const [lastScrollPosition, setLastScrollPosition] = useState(null)

  const containerRef = useRef(null)

  const loadMessagesLength = 4

  TimeAgo.addLocale(vi)
  const timeAgo = new TimeAgo('vi-VN')

  useEffect(() => {
    return () => {
      if (unsub) unsub()
    }
  }, [])

  useEffect(() => {
    setMessages([])
    setHasMore(true)
  }, [chatId])

  useEffect(() => {
    fetchMessages()
  }, [chatId, hasMore])

  useEffect(() => {
    if (messages.length <= loadMessagesLength) scrollToEnd()
    else if (lastScrollPosition) scrollToLastPosition()
  }, [messages])

  const fetchMessages = () => {
    if (!chatId || !hasMore) return
    if (unsub) unsub()

    const nextMessagesLength = messages.length + loadMessagesLength

    const messagesRef = collection(FirebaseDb, 'chats', chatId, 'messages')
    const q = query(
      messagesRef,
      orderBy('createdAt', 'desc'),
      limit(nextMessagesLength)
    )
    const ubSubcribe = onSnapshot(q, (snapshot) => {
      const data = snapshot.docs.map((doc) => ({
        id: doc.id,
        ...doc.data(),
      }))
      if (data.length < nextMessagesLength) setHasMore(false)

      setMessages(data.reverse())
    })

    const unsubRef = () => ubSubcribe
    setUnsub(unsubRef)
  }

  const sendMessage = async (e) => {
    e.preventDefault()
    try {
      const chatDocRef = doc(FirebaseDb, 'chats', chatId)

      const messagesRef = collection(chatDocRef, 'messages')
      await addDoc(messagesRef, {
        text,
        createdAt: serverTimestamp(),
        uid: currentUser.id,
      })
      setText('')
      scrollToEnd()

      await updateDoc(chatDocRef, {
        lastMessage: {
          text,
          createdAt: serverTimestamp(),
          uid: currentUser.id,
        },
      })
    } catch (error) {
      console.error('Error sending message: ', error)
    }
  }

  const handleKeyDown = async (e) => {
    if (e.key === 'Enter') {
      e.preventDefault()
      await sendMessage(new Event('click'))
    }
  }

  const scrollToLastPosition = () => {
    if (containerRef.current && lastScrollPosition) {
      containerRef.current.scrollTo({
        top: containerRef.current.scrollHeight - lastScrollPosition,
      })
    }
    setLastScrollPosition(null)
  }

  const handleScroll = () => {
    if (containerRef.current) {
      const { scrollTop, scrollHeight } = containerRef.current
      if (scrollTop === 0) {
        setLastScrollPosition(scrollHeight)
        fetchMessages()
      }
    }
  }

  const scrollToEnd = () => {
    if (containerRef.current) {
      containerRef.current.scrollTo({
        top: containerRef.current.scrollHeight,
        behavior: 'smooth',
      })
    }
  }

  return (
    <div className='chat'>
      {chatId && otherUser && (
        <>
          <div className='top'>
            <div className='user'>
              <img src={otherUser.avatar || '/default_avatar.png'} alt='' />
              <div className='texts'>
                <span>{otherUser.fullName}</span>
              </div>
            </div>
          </div>
          <div className='center' onScroll={handleScroll} ref={containerRef}>
            {messages?.map((message) => (
              <div
                key={message.id}
                className={
                  message.uid === currentUser?.id
                    ? 'message own'
                    : 'message received'
                }
              >
                <div className='texts'>
                  {message.img && <img src={message.img} alt='' />}
                  <p>{message.text}</p>
                  <span>
                    {message.createdAt
                      ? timeAgo.format(message.createdAt.toDate())
                      : ''}
                  </span>
                </div>
              </div>
            ))}
          </div>
          <div className='bottom'>
            <textarea
              type='text'
              placeholder='Nhập tin nhắn...'
              value={text}
              onChange={(e) => setText(e.target.value)}
              onKeyDown={handleKeyDown}
            />
            <button className='sendButton' onClick={sendMessage}>
              Gửi
            </button>
          </div>
        </>
      )}
    </div>
  )
}

export default ChatMessages