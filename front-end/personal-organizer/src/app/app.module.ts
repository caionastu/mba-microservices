import { UserService } from './services/user-service';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { PoModule } from '@po-ui/ng-components';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { CardModule } from 'primeng/card';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { MenubarModule } from 'primeng/menubar';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SearchComponent } from './pages/search/search.component';
import { PoToasterComponent } from '@po-ui/ng-components/lib/services/po-notification/po-toaster/po-toaster.component';

@NgModule({
  declarations: [
    AppComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
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
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
