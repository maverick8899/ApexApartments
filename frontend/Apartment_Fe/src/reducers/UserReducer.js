import Cookies from 'js-cookie'

const UserReducer = (currentState, action) => {
  switch (action.type) {
    case 'login': {
      return { ...action.payload }
    }
    case 'logout':
      Cookies.remove('token')
      Cookies.remove('user')
      return null
  }

  return currentState
}

export default UserReducer
