import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Message, MessageService } from 'src/app/services/message.service';


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



  constructor(private messageService: MessageService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.user.reciverName = params.name;
      setInterval(() => {
        this.GetMessage();
    }, 1000);
    })
  }

  user: Message = new Message('paitent', '', this.message);

  SendMessage(){
    this.user.senderName = localStorage.getItem("paitentEmail");
    console.log(this.user.senderName);
    this.messageService.SendMessage(this.user).subscribe(
      data => {});

    console.log(this.messageArray)
  }

  GetMessage() {
    this.messageService.GetAllMessage(this.user).subscribe(data => {
      this.messageArray = data;
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
