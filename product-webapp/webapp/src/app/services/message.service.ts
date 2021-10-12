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
    return this.http.post(`http://localhost:8095/chat-messages/${message.senderName}/${message.reciverName}`, message);
  }

  GetAllMessage(message: Message): Observable<any>{
    return this.http.get(`http://localhost:8095/chat-messages/${message.senderName}/${message.reciverName}`);
  }

  deleteMessages(message: Message): Observable<any>{
    return this.http.delete(`http://localhost:8095/chat-messages/${message.senderName}/${message.reciverName}`);
  }
}



export class Message{
  public senderName: string;
  public reciverName: string;
  public messageBody: string;


constructor(senderName: string, reciverName: string, messageBody: string){
  this.senderName = senderName;
  this.reciverName = reciverName;
  this.messageBody = messageBody;

  }
}
