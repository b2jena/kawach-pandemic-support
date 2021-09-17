import { Component, OnInit } from '@angular/core';
import { ContentService } from 'src/app/service/content.service';

@Component({
  selector: 'app-all-contents',
  templateUrl: './all-contents.component.html',
  styleUrls: ['./all-contents.component.css']
})
export class AllContentsComponent implements OnInit {

  retrievedContent: Array<any> = [];

  constructor(private contentService: ContentService) { }

  ngOnInit(): void {
    this.contentService.findAllContents().subscribe(
      response => {
        this.retrievedContent = response;
        this.retrievedContent.forEach((content, index) => {
          this.retrievedContent[index].image = 'data:image/jpeg;base64,' + content.thumbnail.picByte;
        });
      });
  }

}
