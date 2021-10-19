import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Message, MessageService } from 'src/app/services/message.service';
@Component({
  selector: 'app-msg',
  templateUrl: './msg.component.html',
  styleUrls: ['./msg.component.css']
})
export class MsgComponent implements OnInit {

  message: string;
  messageArray: any = [];
  doctorEmail: String;
  user: Message = new Message('paitent', 'doctor', '');
  constructor(private messageService: MessageService, private route: ActivatedRoute, private router: Router) {}

/*This Method is triggered when the page initializes, here GetMessage method is called in the interval of every 2 seconds
*and doctor email is extracting  from the url and stored it in doctor mail.*/

  ngOnInit(): void {
      setInterval(() => {
        this.GetMessage();
    }, 2000);

    const email = this.route.snapshot.params.name;
    const splitEmail = email.split("@")
    this.doctorEmail = splitEmail[0];

  }

/*This Method is responsible for Sending the message and storing the message details in mongoDB data base*/

  SendMessage(){
    this.messageService.SendMessage(this.user).subscribe(
      () => {
        this.user.messageBody = '';
      });
    }

/*This Method is responsible for fetching the message details from mongoDB data base and showing it in chat window*/

  GetMessage() {
    this.messageService.GetAllMessage(this.user).subscribe((data: any) => {
      this.messageArray = data;
    });
  }

/*This Method is responsible for deleting all the message from mongoDB data base*/

  deleteMessage(a: any, b: any){
    this.router.navigateByUrl("/sos");
    this.messageService.deleteMessages(this.user).subscribe( () => {
    });
  }
}
