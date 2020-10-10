import { User } from './../../models/user-model';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { SelectItem } from 'primeng/api';
import { UserService } from './../../services/user-service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {

  public raiosDeAlcance: SelectItem[];
  public professionals: User[];

  constructor(private httpClient: HttpClient,
    private userService: UserService) { }

  ngOnInit(): void {

    this.raiosDeAlcance = [
      { label: "1 km", value: 1 },
      { label: "5 km", value: 5 },
      { label: "15 km", value: 15 },
      { label: "25 km", value: 25 },
      { label: "35 km", value: 35 },
      { label: "50 km", value: 50 },
    ]

    this.userService.get({page: 0, pageSize: 20}).subscribe(users => this.professionals = users)
    navigator.geolocation.getCurrentPosition((position) => console.log(position))
  }

}