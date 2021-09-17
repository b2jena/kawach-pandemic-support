import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Comment } from '../model/comment';
import { ContentService } from '../service/content.service';

@Component({
  selector: 'app-view-content',
  templateUrl: './view-content.component.html',
  styleUrls: ['./view-content.component.css']
})
export class ViewContentComponent implements OnInit {

  constructor(private contentService: ContentService) {
  }

  ngOnInit(): void {
    this.contentService.findContent(this.id).subscribe(
      response => {
        this.retrievedContent = response;
        this.base64Data = this.retrievedContent.thumbnail.picByte;
        this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
      });
      this.retrievedContent.uploadedOn = formatDate(this.retrievedContent.uploadedOn, 'yyyy-MM-dd', 'en-US');
  }

  id: number=1;
  retrievedImage: any;
  base64Data: any;
  retrievedContent: any;
  name!: string;
  comment!: string;

  onUploadComment(): void {
    this.retrievedContent.comments.push(new Comment(this.name, this.comment));
    this.contentService.modifyContent(this.retrievedContent).subscribe();
  }
}
