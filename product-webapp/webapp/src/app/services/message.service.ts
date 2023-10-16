import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  message: Message[] = [];

  constructor(private http: HttpClient) { }

  SendMessage(message: Message): Observable<any>{
    return this.http.post(`/api/v1/chat-messages/${message.senderName}/${message.receiverName}`, message);
  }

  GetAllMessage(message: Message): Observable<any>{
    return this.http.get(`/api/v1/chat-messages/${message.senderName}/${message.receiverName}`);
  }

  deleteMessages(message: Message): Observable<any>{
    return this.http.delete(`/api/v1/chat-messages/${message.senderName}/${message.receiverName}`);
  }
}



export class Message{
  public senderName: string;
  public receiverName: string;
  public messageBody: string;


constructor(senderName: string, receiverName: string, messageBody: string){
  this.senderName = senderName;
  this.receiverName = receiverName;
  this.messageBody = messageBody;

  }
}
