import React from 'react';

function PingButton() {
    const url = 'http://localhost:8080/ping'

  const handlePing = async () => {
    try {
      const response = await fetch(url);
      const text = await response.text();
      alert(text);
    } catch (error) {
      alert('Failed to reach server: ' + error.message);
    }
  };

  return (
    <button onClick={handlePing}>
      Ping Backend
    </button>
  );
}

export default PingButton;