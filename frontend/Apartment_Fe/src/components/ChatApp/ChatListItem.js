import vi from 'javascript-time-ago/locale/vi'
import TimeAgo from 'javascript-time-ago'
import { useContext } from 'react'
import UserContext from '../../contexts/UserContext'
import { ChatAppContext } from './ChatApp'

const ChatListItem = ({ chat, otherUser }) => {
  const [currentUser] = useContext(UserContext)
  const { chatId, setChatId, setOtherUser } = useContext(ChatAppContext)

  const isActive = chatId === chat.id

  TimeAgo.addLocale(vi)
  const timeAgo = new TimeAgo('vi-VN')

  const handleActiveChat = () => {
    setChatId(chat.id)
    setOtherUser(otherUser)
  }

  const formatLengthMessage = (msg) => {
    if (!msg) return ''

    if (msg.length > 20) {
      return msg.slice(0, 20) + '...'
    }
    return msg
  }

  return (
    <div
      className={isActive ? 'chat-list-item active' : 'chat-list-item'}
      onClick={handleActiveChat}
    >
      {otherUser && (
        <>
          <img
            src={otherUser.avatar || '/default_avatar.png'}
            alt={otherUser.fullName}
          />
          <div className='texts'>
            <span>{otherUser.fullName}</span>
            {chat.lastMessage.text && (
              <p>
                {chat.lastMessage.uid === currentUser.id ? 'Bạn: ' : ''}
                {formatLengthMessage(chat.lastMessage.text)} -{' '}
                {chat.lastMessage.createdAt &&
                  timeAgo.format(chat.lastMessage.createdAt.toDate())}
              </p>
            )}
          </div>
        </>
      )}
    </div>
  )
}

export default ChatListItem