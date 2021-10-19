import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
providedIn: 'root'})
export class UploadFileService {
  constructor(private https: HttpClient) { }

  /* This post method is to upload the file in Amazon S3 Bucket */
  pushFileToStorage(file: File): Observable<HttpEvent<{}>> {
    const data: FormData = new FormData();
    data.append('file', file);
    const newRequest = new HttpRequest('POST', '/api/v1/resource/sos/createSos/upload', data, {
    reportProgress: true,
    responseType: 'text'
    });
    return this.https.request(newRequest);
}}
