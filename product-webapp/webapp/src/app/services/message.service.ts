import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  message: Message[] = [];

  constructor(private http: HttpClient) { }

  // SendMessage(senderId: any, reciverId: any, senderName: any, reciverName: any, message: any): Observable<any>{
  //   return this.http.post(`http://localhost:8080chat-messages/${senderId}/${reciverId}`, {
  //     reciverId,
  //     reciverName,
  //     message
  //   })
  // }


  SendMessage(message: Message): Observable<any>{
    return this.http.post(`http://localhost:8095/chat-messages/${message.senderName}/${message.reciverName}`, message);
  }

  GetAllMessage(message: Message): Observable<any>{
    return this.http.get(`http://localhost:8095/chat-messages/${message.senderName}/${message.reciverName}`);
  }

  // SendMessage(message: Message): Observable<any>{
  //   return this.http.post('http://localhost:8080/chat-messages/'+message.sender+'/'+message.reciver, message)
  // }

  deleteMessages(message: Message): Observable<any>{
    return this.http.delete(`http://localhost:8095/chat-messages/${message.senderName}/${message.reciverName}`);
  }
}



export class Message{
  // public sender: string;
  // public reciver: string;
  public senderName: string;
  public reciverName: string;
  public messageBody: string;

  // constructor( sender: string, reciver: string, senderName: string, reciverName: string, messageBody: string){
    constructor(senderName: string, reciverName: string, messageBody: string){
    // this.sender = sender;
    // this.reciver = reciver;
    this.senderName = senderName;
    this.reciverName = reciverName;
    this.messageBody = messageBody;

  }
}
