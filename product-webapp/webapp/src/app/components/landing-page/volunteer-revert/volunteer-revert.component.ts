import { CloseScrollStrategy } from '@angular/cdk/overlay';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';

@Component({
  selector: 'app-volunteer-revert',
  templateUrl: './volunteer-revert.component.html',
  styleUrls: ['./volunteer-revert.component.css']
})
export class VolunteerRevertComponent implements OnInit {

  constructor(private httpService: HttpClient) { }

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  volunteers: Volunteer[] = [];
  displayedColumns: string[] = ['volunteerEmailId', 'score', 'level'];
  dataSource = new MatTableDataSource<Volunteer>([]);
  // dataSource = this.volunteers;



  ngOnInit(): void {
    this.getVolunteers();
  }

  getVolunteers(){
    this.httpService.get<Volunteer[]>('http://localhost:8686/api/v1/volunteers').subscribe(
      (response) => {
        console.log(response);
        // this.volunteers = response;

        this.volunteers = response;
        this.volunteers.sort((a, b) => b.score - a.score);
        this.dataSource.data = this.volunteers;
        this.dataSource.paginator = this.paginator;

      }
    );
  }



}

export class Volunteer{
  private volunteerEmailId: string;
  public score: number;
  private level: string;

  constructor(volunteerEmailId: string, score: number, level: string){
    this.volunteerEmailId = volunteerEmailId;
    this.score = score;
    this.level = level;
  }
}
