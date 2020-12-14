import { User } from './../../models/user-model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ImageType } from 'src/app/models/image-tpye-model';
import { Image } from './../../models/image-model';
import { ImageService } from './../../services/image-service';
import { UserService } from './../../services/user-service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
  public images: Image[] = [];
  public profile: Image;
  public user: User;

  constructor(
    private imageService: ImageService,
    private route: ActivatedRoute,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.route.queryParamMap.subscribe((paramsMap) => {
      const userId = paramsMap.get('userId');

      this.imageService
        .get({
          userId: userId,
          imageType: ImageType.PORTFOLIO,
        })
        .subscribe((images) => (this.images = images));

      this.imageService
        .get({
          userId: userId,
          imageType: ImageType.PROFILE,
        })
        .subscribe((images) => (this.profile = images.shift()));

      this.userService.findById(userId).subscribe((user) => {
        this.user = user;
        console.log(user.address.location.distance);
      });
    });
  }

  public constructionPage(): void {
    alert('Página em construção');
  }
}
