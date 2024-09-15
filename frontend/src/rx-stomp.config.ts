import { RxStompConfig } from '@stomp/rx-stomp';

export const StompConfig: RxStompConfig = {
  brokerURL: 'ws://localhost:8080/ws',

  heartbeatIncoming: 0, // Typical value 0 - disabled
  heartbeatOutgoing: 20000, // Typical value 20000 - every 20 seconds

  reconnectDelay: 200,

  // Will log diagnostics on console
  // It can be quite verbose, not recommended in production
  // Skip this key to stop logging to console
  debug: (msg: string): void => {
    console.log(msg);
  },
};
