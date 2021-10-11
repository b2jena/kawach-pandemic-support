import { User } from './../../../services/login.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Message, MessageService } from 'src/app/services/message.service';
import {io } from 'socket.io-client';
import { interval, Subscription } from 'rxjs';

@Component({
  selector: 'app-msg',
  templateUrl: './msg.component.html',
  styleUrls: ['./msg.component.css']
})
export class MsgComponent implements OnInit {

  message: string = '';
  reciverName: string= '';
  messageArray: any = [];
  socket: any;
  private updateSubscription: Subscription;


  constructor(private messageService: MessageService, private route: ActivatedRoute) {
    // this.socket = io(`http://localhost:8080/`);
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      console.log(params)
      // this.socket.on('refreshPage', () => {
      // this.GetMessage();
      this.user.reciverName = params.name;
      console.log(this.user.reciverName);
      // this.updateSubscription = interval(1000).subscribe((val) => {
      //   this.updateStatus();
      // })
      // })

      setInterval(() => {
        this.GetMessage();
    }, 10000);
    })
  }



  // user: Message = new Message('', '', this.message);

  user: Message = new Message('paitent', '', this.message);


  SendMessage(){
    this.user.senderName = localStorage.getItem("paitentEmail");
    console.log(this.user.senderName);
    this.messageService.SendMessage(this.user).subscribe(
      data => {
        console.log(data);
      }
    );
    // this.GetMessage();
    console.log(this.messageArray)
  }

  GetMessage() {
    this.messageService.GetAllMessage(this.user).subscribe(data => {
      this.messageArray = data;
      // console.log(data);
      // console.log(this.messageArray);
    });
  }


  deleteMessage(a: any, b: any){
    console.log(a);
    console.log(b);
    this.messageService.deleteMessages(this.user).subscribe( data =>{
      this.messageArray=data;
      console.log(data);
    });
  }
}
