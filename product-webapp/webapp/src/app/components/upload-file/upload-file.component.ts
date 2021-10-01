import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UploadFileService } from 'src/app/services/upload-service.service';

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css']
})
export class UploadFileComponent implements OnInit {

  title = 'File-Upload-Save';
  selectedFiles: FileList |any;
  currentFileUpload: File |any;
  progress: { percentage: number } = { percentage: 0 };
  selectedFile = null;
  changeImage = false;
  constructor(private uploadService: UploadFileService, private snackBar: MatSnackBar){}

  change() {
    this.changeImage = true;
  }
  changedImage(event: any) {
    this.selectedFile = event.target.files[0];
  }
  upload() {
    this.progress.percentage = 0;
    this.currentFileUpload = this.selectedFiles.item(0);
    this.uploadService.pushFileToStorage(this.currentFileUpload).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        console.log(event);
        // this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
         this.snackBar.open('File Successfully Uploaded');
      }
      this.selectedFiles = undefined;
     }
    );
  }
  selectFile(event: any) {
    this.selectedFiles = event.target.files;
  }

  ngOnInit(): void {
  }

}
