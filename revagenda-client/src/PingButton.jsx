import React from 'react';

function PingButton() {
    const url = 'http://ec2-50-17-99-29.compute-1.amazonaws.com:8090/ping'

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