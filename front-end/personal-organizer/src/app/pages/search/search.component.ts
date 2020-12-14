import { UserFilter } from './../../filters/user-filter';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { SelectItem } from 'primeng/api';
import { ImageType } from 'src/app/models/image-tpye-model';
import { UserView } from 'src/app/views/user-view';
import { ImageFilter } from './../../filters/image-filter';
import { ImageService } from './../../services/image-service';
import { UserService } from './../../services/user-service';
import { FormGroup } from '@angular/forms';
import { PoSelectOption } from '@po-ui/ng-components';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss'],
})
export class SearchComponent implements OnInit {
  public radiusOptions: PoSelectOption[];
  public radius: number;
  public userViews: UserView[];
  public form: FormGroup;
  public location: string;

  private filter: UserFilter;

  constructor(
    private userService: UserService,
    private imageService: ImageService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.radiusOptions = [
      { label: '1 km', value: 1 },
      { label: '5 km', value: 5 },
      { label: '10 km', value: 10 },
      { label: '20 km', value: 20 },
      { label: '30 km', value: 30 },
      { label: '50 km', value: 50 },
      { label: '100 km', value: 100 }
    ];

    // Filtar pela localização atual do usuário
    navigator.geolocation.getCurrentPosition((position) => {
      this.filter = {
        page: 0,
        pageSize: 20,
        location: {
          lat: position.coords.latitude,
          lng: position.coords.longitude,
          distance: 100,
        },
      };

      this.radius = 100;
      this.location = `${position.coords.latitude},${position.coords.longitude}`;
      this.findByFilter();
    });
  }

  public goToPerfil(id: string): void {
    this.router.navigate(['/register'], { queryParams: { userId: id } });
  }

  public search() {
    this.filter.location.distance = this.radius;

    const coordinates: string[] = this.location.split(',')

    this.filter.location.lat = Number.parseFloat(coordinates[0]);
    this.filter.location.lng = Number.parseFloat(coordinates[1]);
    this.findByFilter();
  }

  private findByFilter() {
    // Refatorar para usar os operadores do subscribe (flatMap, concat, etc)
    this.userService.findByFilter(this.filter).subscribe((users) => {
      this.userViews = UserView.from(users);
      this.userViews.forEach((view) => {
        let filter: ImageFilter = {
          userId: view.user.id,
          imageType: ImageType.PROFILE,
        };
        this.imageService.get(filter).subscribe((images) => {
          view.images = images;
        });
      });
    });
  }
}
