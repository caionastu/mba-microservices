import { sanitizeIdentifier } from '@angular/compiler';
import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { PoMenuItem } from '@po-ui/ng-components';
import { MenuItem } from 'primeng/api';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class AppComponent implements OnInit {

  menuItems: Array<PoMenuItem>;

  isCollapsed = false;

  ngOnInit() {
    this.menuItems = [
      {label: "Explorar", link: '/'},
      {label: "Sobre", action: this.constructionPage.bind(this)},
      {label: "Contato", action: this.constructionPage.bind(this)},
      // {label: "Cadastro", link: '/register'}
    ]
  }

  private constructionPage(): void {
    alert('Página em construção');
  }

}
