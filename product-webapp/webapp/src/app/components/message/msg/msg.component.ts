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

  nameFormControl = new FormControl('', [
    Validators.required,
  ]);

  message: string;
  reciverName: string;
  messageArray: any = [];
  socket: any;
  doctorEmail: String;

  user: Message = new Message('paitent', 'doctor', '');
  constructor(private messageService: MessageService, private route: ActivatedRoute, private router: Router) {}
  ngOnInit(): void {
      setInterval(() => {
        this.GetMessage();
    }, 2000);

    const email = this.route.snapshot.params.name;
    const splitEmail = email.split("@")
    this.doctorEmail = splitEmail[0];

  }
  SendMessage(){
    this.messageService.SendMessage(this.user).subscribe(
      () => {
        // window.location.reload();
        this.user.messageBody = '';
      });
    }

  GetMessage() {
    this.messageService.GetAllMessage(this.user).subscribe((data: any) => {
      this.messageArray = data;
    });
  }
  deleteMessage(a: any, b: any){
    this.router.navigateByUrl("/sos");
    this.messageService.deleteMessages(this.user).subscribe( () => {
    });
  }
}
