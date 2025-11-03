import { useState } from "react";

function Registration() {
    let url = 'http://ec2-54-234-94-174.compute-1.amazonaws.com:8080/register'


  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');

    async function register() {
        const registrationDto = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName
        };

        try {
            const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(registrationDto)
            });
            const data = await response.json();
            console.log('Registration successful:', data);
        } catch (error) {
            console.error('Registration failed:', error);
        }
    }

  return (
    <div className="auth-form">
      <h2>Register</h2>
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
      <div className="form-group">
        <label>First Name</label>
        <input 
          type="text"
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
        />
      </div>
      <div className="form-group">
        <label>Last Name</label>
        <input 
          type="text"
          value={lastName}
          onChange={(e) => setLastName(e.target.value)}
        />
      </div>
      <button className="btn btn-register" onClick={register}>Register</button>
    </div>
  );
}

export default Registration