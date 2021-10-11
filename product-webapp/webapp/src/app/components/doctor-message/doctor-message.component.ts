import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Message, MessageService } from 'src/app/services/message.service';

@Component({
  selector: 'app-doctor-message',
  templateUrl: './doctor-message.component.html',
  styleUrls: ['./doctor-message.component.css']
})
export class DoctorMessageComponent implements OnInit {
  message: string;
  reciverName: string;
  messageArray: any = [];
  socket: any;


  user: Message = new Message('', '', '');
  constructor(private messageService: MessageService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
      setInterval(() => {
        this.GetMessage();
    }, 1000);
    }





  SendMessage(){
    this.user.senderName = this.messageArray[0].senderName;
    this.user.reciverName = localStorage.getItem('loggedIn');
    this.messageService.SendMessage(this.user).subscribe(
      data => {
      }
    );
    this.GetMessage();
  }

  GetMessage() {
    this.messageService.GetAllMessage(this.user).subscribe(data => {
      this.messageArray = data;
      console.log(data);
      console.log(this.messageArray);
    });
  }
}
