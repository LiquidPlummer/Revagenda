import React, { useState } from 'react';
import Login from './Login';
import Registration from './Registration';

function AuthPage() {
  return (
    <div className="auth-page">
      <Login />
      <Registration />
    </div>
  );
}

export default AuthPage