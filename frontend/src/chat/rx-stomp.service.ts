import { Injectable } from '@angular/core';
import { RxStomp } from '@stomp/rx-stomp';
import { StompConfig } from '../rx-stomp.config';

@Injectable({
  providedIn: 'root',
  useFactory: rxStompServiceFactory,
})
export class RxStompService extends RxStomp {
  constructor() {
    super();
  }
}

export function rxStompServiceFactory() {
  const rxStomp = new RxStompService();
  rxStomp.configure(StompConfig);
  rxStomp.activate();
  return rxStomp;
}
