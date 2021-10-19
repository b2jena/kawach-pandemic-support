import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  message: Message[] = [];

  constructor(private http: HttpClient) { }

  /*This is to pass the message data from front end to backend with the help of a post request*/
  SendMessage(message: Message): Observable<any>{
    return this.http.post(`/api/v1/chat-messages/${message.senderName}/${message.reciverName}`, message);
  }

  /*This is to fetching all the message data from backend with the help of a get request*/
  GetAllMessage(message: Message): Observable<any>{
    return this.http.get(`/api/v1/chat-messages/${message.senderName}/${message.reciverName}`);
  }

  /*This is to deleting all the message data from backend with the help of a delete request*/
  deleteMessages(message: Message): Observable<any>{
    return this.http.delete(`/api/v1/chat-messages/${message.senderName}/${message.reciverName}`);
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
