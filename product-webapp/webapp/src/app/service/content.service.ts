import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Content } from '../model/content';

@Injectable({
  providedIn: 'root'
})
export class ContentService {

  constructor(private httpClient: HttpClient) { }

  public addImage(uploadImageData: any): Observable<any> {
    return this.httpClient.post('http://localhost:8085/upload/image/', uploadImageData, { observe: 'response' });
  }

  public addContent(uploadContent: Content): Observable<any> {
    return this.httpClient.post('http://localhost:8085/upload/', uploadContent, { observe: 'response' });
  }

  public findContent(id: any): Observable<any> {
    return this.httpClient.get('http://localhost:8085/contents/' + id);
  }

  public findAllContents(): Observable<any> {
    return this.httpClient.get('http://localhost:8085/contents/');
  }
  
  public modifyContent(uploadContent: any){
    return this.httpClient.put('http://localhost:8085/upload/', uploadContent, { observe: 'response' });
  }
}
