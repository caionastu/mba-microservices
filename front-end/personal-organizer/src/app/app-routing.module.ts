import { RegisterComponent } from './pages/register/register.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchComponent } from './pages/search/search.component';

const routes: Routes = [
  { path: 'register', component: RegisterComponent },
  { path: '', component: SearchComponent, pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
