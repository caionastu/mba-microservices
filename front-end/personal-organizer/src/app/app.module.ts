import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { PoModule } from '@po-ui/ng-components';
import { PoToasterComponent } from '@po-ui/ng-components/lib/services/po-notification/po-toaster/po-toaster.component';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { CardModule } from 'primeng/card';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { MenubarModule } from 'primeng/menubar';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './pages/register/register.component';
import { SearchComponent } from './pages/search/search.component';
import { ImageService } from './services/image-service';
import { UserService } from './services/user-service';


@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,
    RegisterComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    RouterModule.forRoot([]),
    MenubarModule,
    // Implementar o LazyLoading dos módulos

    //Search Component
    CardModule,
    InputTextModule,
    CalendarModule,
    DropdownModule,
    ButtonModule,
    PoModule
  ],
  providers: [UserService, ImageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
