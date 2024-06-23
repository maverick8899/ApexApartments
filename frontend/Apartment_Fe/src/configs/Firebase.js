import { initializeApp } from 'firebase/app'
import { getAuth } from 'firebase/auth'
import { getFirestore } from 'firebase/firestore'

const firebaseConfig = {
  apiKey: "AIzaSyC2TbIlAlHOAorQ6bxHXWJyHyfPvp_Xcqo",
  authDomain: "chatapprealtime-c9591.firebaseapp.com",
  projectId: "chatapprealtime-c9591",
  storageBucket: "chatapprealtime-c9591.appspot.com",
  messagingSenderId: "18194633761",
  appId: "1:18194633761:web:9b59b58bc3256144b6ff60",
  measurementId: "G-CK0M118GL6"
};

const app = initializeApp(firebaseConfig);

export const FirebaseAuth = getAuth()
export const FirebaseDb = getFirestore()