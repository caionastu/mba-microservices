import { LocationFilter } from './../filters/location-filter';
import { Distance } from './../models/distance-model';
import { UserFilter } from './../filters/user-filter';
import { HttpParams, HttpHeaders, HttpClient } from '@angular/common/http';
import { User } from './../models/user-model';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class UserService {

    private baseUrl: string = 'http://localhost:8082/users';

    constructor(private httpClient: HttpClient){}

    get(userFilter: UserFilter): Observable<User[]> {
        let httpParams: HttpParams = new HttpParams();

        httpParams = userFilter.page ? httpParams.append('page', userFilter.page.toString())  : httpParams.append('page', '0');
        httpParams = userFilter.pageSize ? httpParams.append('size', userFilter.pageSize.toString())  : httpParams.append('size', '20');
        
        httpParams = userFilter.name ? httpParams.append('name', userFilter.name) : httpParams;
        httpParams = userFilter.userType ? httpParams.append('userType', userFilter.userType.toString()) : httpParams;
        
        if(userFilter.location){
            httpParams = httpParams.append('location.distance', userFilter.location.distance.toString());
            httpParams = httpParams.append('location.lat', userFilter.location.lat.toString());
            httpParams = httpParams.append('location.lng', userFilter.location.lng.toString());
        }

        let httpHeaders: HttpHeaders = new HttpHeaders();
        httpHeaders.append('Content-Type', 'application/json');


        return this.httpClient.get<User[]>(this.baseUrl, { params: httpParams, headers: httpHeaders });
    }
}