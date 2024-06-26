import './ChatApp.css'
import { useContext, useEffect, useRef, useState } from 'react'
import UserContext from '../../contexts/UserContext'
import {
  collection,
  onSnapshot,
  query,
  where,
  orderBy,
  limit,
} from 'firebase/firestore'
import { FirebaseDb } from '../../configs/Firebase'
import ChatListItem from './ChatListItem'
import { authApi, endpoints } from '../../configs/Apis'
import * as qs from 'qs'

const List = () => {
  const authApis = authApi
  const [currentUser] = useContext(UserContext)
  const [chats, setChats] = useState([])
  const [chatsFilter, setChatsFilter] = useState([])
  const [chatUsers, setChatUsers] = useState([])
  const [unsub, setUnsub] = useState(null)
  const [hasMore, setHasMore] = useState(true)
  const containerRef = useRef(null)

  const loadChatsLength = 10

  const fetchChats = () => {
    if (!hasMore) return

    if (unsub) unsub()

    const nextChatsLength = chats.length + loadChatsLength

    const chatsRef = collection(FirebaseDb, 'chats')
    const q = query(
      chatsRef,
      orderBy('lastMessage.createdAt', 'desc'),
      limit(nextChatsLength)
    )
    const unSubcribe = onSnapshot(q, (snapshot) => {
      const data = snapshot.docs.map((doc) => ({
        ...doc.data(),
        id: doc.id,
      }))
      if (data.length < nextChatsLength) setHasMore(false)

      setChats(data)
    })

    const unsubRef = () => unSubcribe
    setUnsub(unsubRef)
  }

  useEffect(() => {
    return () => {
      if (unsub) unsub()
    }
  }, [])

  useEffect(() => {
    fetchChats()
    return () => unsub
  }, [currentUser.id])

  useEffect(() => {
    const getChatUsers = async () => {
      if (!chats.length) return

      const ids = chats.map((chat) => chat.user)
      try {
        const res = await authApis.get(endpoints['get-chat-users'], {
          params: { ids },
          paramsSerializer: (params) => {
            return qs.stringify(params, { arrayFormat: 'repeat' })
          },
        })
        setChatUsers(res.data)
      } catch (err) {
        console.error(err)
      }
    }

    getChatUsers()
    setChatsFilter(chats)
  }, [chats])

  const searchChats = (kw) => {
    const chatsFilter = chats.filter((chat) => {
      const otherUser = chatUsers.find((e) => chat.user === e.id)
      return otherUser.fullName.toLowerCase().includes(kw.toLowerCase())
    })

    setChatsFilter(chatsFilter)
  }

  const handleScroll = () => {
    if (containerRef.current) {
      const { scrollTop, clientHeight, scrollHeight } = containerRef.current
      if (Math.abs(scrollHeight - (scrollTop + clientHeight)) <= 1) {
        fetchChats()
      }
    }
  }

  return (
    <div className='list'>
      <div className='chatList' onScroll={handleScroll} ref={containerRef}>
        <div className='search'>
          <div className='searchBar'>
            <img src='/search.png' alt='' />
            <input
              type='text'
              placeholder='Tìm kiếm'
              onChange={(e) => searchChats(e.target.value)}
            />
          </div>
        </div>
        <div>
          {chatsFilter.map((chat) => (
            <ChatListItem
              key={chat.id}
              chat={chat}
              otherUser={chatUsers.find((e) => chat.user === e.id)}
            />
          ))}
        </div>
      </div>
    </div>
  )
}

export default List
