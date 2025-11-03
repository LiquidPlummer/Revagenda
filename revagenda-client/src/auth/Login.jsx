import React, { useState } from 'react';
import './AuthPage.css';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  return (
    <div className="auth-form">
      <h2>Login</h2>
      <div className="form-group">
        <label>Username</label>
        <input 
          type="text" 
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
      </div>
      <div className="form-group">
        <label>Password</label>
        <input 
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </div>
      <button className="btn btn-login">Login</button>
    </div>
  );
}

export default Login