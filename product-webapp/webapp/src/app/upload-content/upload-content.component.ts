import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Content } from '../model/content';
import { ContentService } from '../service/content.service';

@Component({
  selector: 'app-upload-content',
  templateUrl: './upload-content.component.html',
  styleUrls: ['./upload-content.component.css']
})
export class UploadContentComponent implements OnInit {

  counter: number;
  selectedFile!: File;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message!: string;
  imageName: any;
  uploadContent: Content;
  domains: string[] = ['Cooking', 'Interior Decorating', 'Home Furnishing', 'Electronic Inventions'];

  username: string = localStorage.getItem("username");

  constructor(private contentService: ContentService, private router: Router) {
    this.uploadContent = new Content();
    this.uploadContent.likes = 0;
    this.uploadContent.shares = 0;
    this.counter = 0;
  }
  ngOnInit(): void { }

  onFileChanged(event: any): void {
    this.selectedFile = event.target.files[0];
  }
  onUploadImage(): void {
    console.log(this.selectedFile);
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);
    this.contentService.addImage(uploadImageData).subscribe((response) => {
        if (response.status === 201) {
          this.message = 'Image uploaded successfully';
        } else {
          this.message = 'Image not uploaded successfully';
        }
      });
  }

  onUploadContent(): void {
    this.onUploadImage();
    // this.uploadContent.contentId = ++this.counter;
    this.uploadContent.uploadedBy= localStorage.getItem("username");
    this.contentService.addContent(this.uploadContent).subscribe((response) => {
      if (response.status === 201) {
        this.message = 'Content uploaded successfully';
        this.router.navigate(['/home']);
      } else {
        this.message = 'Content not uploaded successfully';
      }
    });
  }
}
