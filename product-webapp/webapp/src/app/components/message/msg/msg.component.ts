import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Message, MessageService } from 'src/app/services/message.service';
@Component({
  selector: 'app-msg',
  templateUrl: './msg.component.html',
  styleUrls: ['./msg.component.css']
})
export class MsgComponent implements OnInit {
  message: string;
  reciverName: string;
  messageArray: any = [];
  socket: any;
  user: Message = new Message('paitent', 'doctor', '');
  constructor(private messageService: MessageService, private route: ActivatedRoute, private router: Router) {}
  ngOnInit(): void {
      setInterval(() => {
        this.GetMessage();
    }, 2000);

  }
  SendMessage(){
    this.messageService.SendMessage(this.user).subscribe(
      data => {});
  }
  GetMessage() {
    this.messageService.GetAllMessage(this.user).subscribe(data => {
      this.messageArray = data;
    });
  }
  deleteMessage(a: any, b: any){
    this.router.navigateByUrl("/sos");
    this.messageService.deleteMessages(this.user).subscribe( data => {
      // this.messageArray = data;
    });
  }
}
