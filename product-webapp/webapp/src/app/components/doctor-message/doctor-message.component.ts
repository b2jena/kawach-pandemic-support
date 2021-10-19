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
  messageArray: any = [];
  user: Message = new Message('doctor', 'paitent', '');
  constructor(private messageService: MessageService, private route: ActivatedRoute) {
  }

/*This Method is triggered when the page initializes, here GetMessage method is called in the interval of every 1 seconds*/

  ngOnInit(): void {
      setInterval(() => {
        this.GetMessage();
    }, 1000);
    }

/*This Method is responsible for Sending the message and storing the message details in mongoDB data base*/

  SendMessage(){
    this.messageService.SendMessage(this.user).subscribe(
      () => {
        this.user.messageBody = '';
      } );
  }

/*This Method is responsible for fetching the message details from mongoDB data base and showing it in chat window*/

  GetMessage() {
    this.messageService.GetAllMessage(this.user).subscribe(data => {
      this.messageArray = data;
    });
  }
}
