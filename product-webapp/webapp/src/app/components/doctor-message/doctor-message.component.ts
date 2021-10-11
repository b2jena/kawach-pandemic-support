import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Message, MessageService } from 'src/app/services/message.service';

@Component({
  selector: 'app-doctor-message',
  templateUrl: './doctor-message.component.html',
  styleUrls: ['./doctor-message.component.css']
})
export class DoctorMessageComponent implements OnInit {
  message: string = '';
  reciverName: string= 'Paitent';
  messageArray: any = [];
  socket: any;


  constructor(private messageService: MessageService, private route: ActivatedRoute) {
    // this.socket = io(`http://localhost:8080/`);
  }

  ngOnInit(): void {
    // this.route.params.subscribe(params => {
    //   console.log(params)

      // this.socket.on('refreshPage', () => {
      // this.GetMessage();
      // return this.reciverName = params.name;
      // this.updateSubscription = interval(1000).subscribe((val) => {
      //   this.updateStatus();
      // })
      // })

      setInterval(() => {
        this.GetMessage();
    }, 1000);
    }



  user: Message = new Message('', 't', this.message);

  SendMessage(){
    this.user.senderName = this.messageArray[0].senderName;
    this.user.reciverName = localStorage.getItem("loggedIn");
    console.log(this.user.senderName);
    this.messageService.SendMessage(this.user).subscribe(
      data => {
        console.log(data);
      }
    );
    this.GetMessage();
    console.log(this.messageArray)
  }

  GetMessage() {
    this.messageService.GetAllMessage(this.user).subscribe(data => {
      this.messageArray = data;
      console.log(data);
      console.log(this.messageArray);
    });
  }
}
