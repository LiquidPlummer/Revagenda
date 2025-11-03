import { useState } from 'react'
import './App.css'
import AuthPage from './auth/AuthPage'
import PingButton from './PingButton'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <AuthPage />
      <hr />
      <PingButton />
    </>
  )
}

export default App
