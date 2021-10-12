export const environment = {
  production: true,
  wsEndpoint: 'wss://kawach.stackroute.io:443/ws/',
  RTCPeerConfiguration: {
    iceServers: [
      {
        urls: 'turn:turnserver:3478',
        username: 'user',
        credential: 'password'
      }
    ]
  }
};