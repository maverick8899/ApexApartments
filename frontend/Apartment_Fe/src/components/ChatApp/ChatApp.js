import './ChatApp.css'
import { createContext, useContext, useEffect, useState } from 'react'
import ChatMessages from './ChatMessages'
import List from './List'
import UserContext from '../../contexts/UserContext'
import { FirebaseDb } from '../../configs/Firebase'
import {
  addDoc,
  collection,
  doc,
  onSnapshot,
  orderBy,
  query,
  setDoc,
  where,
} from 'firebase/firestore'

const ChatAppContext = createContext()
export { ChatAppContext }

const ChatApp = () => {
  const [chatId, setChatId] = useState(null)
  const [otherUser, setOtherUser] = useState(null)

  const [currentUser] = useContext(UserContext)
  const isNormalUser = currentUser.role == 'user'

  useEffect(() => {
    let unsubscribe
    if (isNormalUser) {
      unsubscribe = fetchChatFornormalUser()
    }
    return () => unsubscribe && unsubscribe()
  }, [isNormalUser])

  const fetchChatFornormalUser = () => {
    const chatsRef = collection(FirebaseDb, 'chats')
    const q = query(chatsRef, where('user', '==', currentUser.id))
    const unsubscribe = onSnapshot(q, (snapshot) => {
      if (snapshot.empty) {
        const newChatRef = doc(chatsRef)
        setDoc(newChatRef, {
          user: currentUser.id,
          lastMessage: {
            createdAt: null,
            text: '',
            uid: null,
          },
        })
        setChatId(newChatRef.id)
        setOtherUser({
          id: 'admin',
          fullName: 'Admin',
          avatar: '/admin_avatar.png',
        })
      } else {
        setChatId(snapshot.docs[0].id)
        setOtherUser({
          id: 'admin',
          fullName: 'Admin',
          avatar: '/admin_avatar.png',
        })
      }
    })
    return unsubscribe
  }

  return (
    <ChatAppContext.Provider
      value={{ chatId, setChatId, otherUser, setOtherUser }}
    >
      <div className='container-chat-app'>
        {!isNormalUser && <List />}
        <ChatMessages />
      </div>
    </ChatAppContext.Provider>
  )
}
export default ChatApp
