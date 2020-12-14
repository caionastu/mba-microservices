import { ImageType } from './../models/image-tpye-model';
import { HttpHeaders, HttpParams, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ImageFilter } from '../filters/image-filter';
import { Image } from '../models/image-model';

@Injectable()
export class ImageService {
    private baseUrl: string = 'http://localhost:8084/images'; // Substituir pelo ip do gateway

    constructor(private httpClient: HttpClient) { }

    get(filter: ImageFilter): Observable<Image[]> {
        let httpParams: HttpParams = new HttpParams();

        // TODO Tratar a páginação padrão no backend
        httpParams = filter.page ? httpParams.append('page', filter.page.toString()) : httpParams.append('page', '0');
        httpParams = filter.pageSize ? httpParams.append('size', filter.pageSize.toString()) : httpParams.append('size', '20');

        httpParams = httpParams.append('userId', filter.userId);
        httpParams = httpParams.append('imageType', filter.imageType);

        let httpHeaders: HttpHeaders = new HttpHeaders();
        httpHeaders.append('Content-Type', 'application/json');


        return this.httpClient.get<Image[]>(this.baseUrl, { params: httpParams, headers: httpHeaders });
    }
}