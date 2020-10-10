import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { PoMenuItem } from '@po-ui/ng-components';
import { MenuItem } from 'primeng/api';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class AppComponent implements OnInit {

  // menuItems: MenuItem[];
  menuItems: Array<PoMenuItem>

  ngOnInit() {
    // this.menuItems = [
    //   {
    //     label: 'Explorar',
    //     command: this.constructionPage.bind(this),
    //   },
    //   {
    //     label: 'Sobre',
    //     command: this.constructionPage.bind(this),
    //   },
    //   {
    //     label: 'Contato',
    //     command: this.constructionPage.bind(this),
    //   }
    // ]

    this.menuItems = [
      {label: "Explorar", action: this.constructionPage.bind(this)},
      {label: "Sobre", action: this.constructionPage.bind(this)},
      {label: "Contato", action: this.constructionPage.bind(this)}
    ]
  }

  private constructionPage(): void {
    alert('Página em construção');
  }

}
