import { initializeApp } from 'firebase/app'
import { getFirestore } from 'firebase/firestore'
import { setDoc, doc } from 'firebase/firestore'

const firebaseConfig = {
  apiKey: process.env.FIREBASE_API_KEY,
  authDomain: process.env.FIREBASE_AUTH_DOMAIN,
  projectId: process.env.FIREBASE_PROJECT_ID,
  storageBucket: process.env.FIREBASE_STORAGE_BUCKET,
  messagingSenderId: process.env.FIREBASE_MESSAGING_SENDER_ID,
  appId: process.env.FIREBASE_APP_ID,
}

const app = initializeApp(firebaseConfig)
const db = getFirestore(app)

export default async function write(password: string) {
  console.log('Writing password to database')
  try {
    await setDoc(doc(db, 'passwords', password), {
      password: password,
    })
    console.log('Document written')
  } catch (e) {
    console.error('Error adding document: ', e)
  }
}