import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, NgForm } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
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
  selectedFile: any = null;
  changeImage = false;

  constructor(private uploadService: UploadFileService, private snackBar: MatSnackBar){}

  showSnackbar(content: string, action: string) {
    const snack = this.snackBar.open(content, action, {
      duration: 2000,
      verticalPosition: 'top', // Allowed values are  'top' | 'bottom'
      horizontalPosition: 'center', // Allowed values are 'start' | 'center' | 'end' | 'left' | 'right'
    });
    snack.afterDismissed().subscribe(() => {
      console.log('This will be shown after snackbar disappeared');
    });
    snack.onAction().subscribe(() => {
      console.log('This will be called when snackbar button clicked');
    });
  }

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
      } else if (event instanceof HttpResponse) {
        this.showSnackbar('File Successfully Uploaded', 'x');
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
