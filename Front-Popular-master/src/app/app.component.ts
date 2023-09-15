import { Component, OnInit, AfterViewInit, Renderer2 } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router, NavigationEnd, ActivatedRoute, ActivatedRouteSnapshot } from '@angular/router';

import { RoutePartsService } from './shared/services/route-parts.service';

import { filter } from 'rxjs/operators';
import { UILibIconService } from './shared/services/ui-lib-icon.service';
import { ThemeService } from './shared/services/theme.service';
import { LayoutService } from './shared/services/layout.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MatDivider } from '@angular/material/divider';
///import { SocialAuthService, GoogleLoginProvider, SocialUser } from '@abacritt/angularx-social-login';

///import { MaterialModule } from './app/mimodule/material.module';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  ///styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewInit {
  appTitle = 'Egret';
  pageTitle = '';
  users: any;
  ///socialUser!: SocialUser;
  isLoggedin: boolean = false;  
  constructor(
    public title: Title,
    private router: Router,
    private activeRoute: ActivatedRoute,
    private routePartsService: RoutePartsService,
    private iconService: UILibIconService,
    private layoutService: LayoutService,
    private http: HttpClient,
    ///private socialAuthService: SocialAuthService
  ) {
    iconService.init()
  }

  ngOnInit(): void {
    this.changePageTitle();
    /*this.socialAuthService.authState.subscribe((user) => {
      this.socialUser = user;
      this.isLoggedin = (user != null);
      console.log(this.socialUser);
    });*/
  }

  ngAfterViewInit() {
  }

  changePageTitle() {
    this.router.events.pipe(filter(event => event instanceof NavigationEnd)).subscribe((routeChange) => {
      const routeParts = this.routePartsService.generateRouteParts(this.activeRoute.snapshot);
      if (!routeParts.length) {
        return this.title.setTitle(this.appTitle);
      }
      // Extract title from parts;
      this.pageTitle = routeParts
        .reverse()
        .map((part) => part.title)
        .reduce((partA, partI) => { return `${partA} > ${partI}` });
      this.pageTitle += ` | ${this.appTitle}`;
      this.title.setTitle(this.pageTitle);
    });
  }
}
