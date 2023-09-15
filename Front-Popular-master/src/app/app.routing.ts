import { Routes } from '@angular/router';
import { AdminLayoutComponent } from './shared/components/layouts/admin-layout/admin-layout.component';
import { AuthLayoutComponent } from './shared/components/layouts/auth-layout/auth-layout.component';
import { AuthGuard } from './shared/guards/auth.guard';
///import { RiesgoComponent } from './views/reporte/riesgo/riesgo.component';
import { AppBlankComponent } from './views/others/app-blank/app-blank.component';

export const rootRouterConfig: Routes = [
  {
    path: '',
    redirectTo: 'sessions/signin',
    pathMatch: 'full'
    // redirectTo: 'others/blank',
    // pathMatch: 'full'
  },
  {
    path: '',
    component: AuthLayoutComponent,
    children: [
      {
        path: 'sessions',
        loadChildren: () => import('./views/sessions/sessions.module').then(m => m.SessionsModule),
        data: { title: 'Session' }
      }
    ]
  },
  {
    path: '',
    component: AdminLayoutComponent,
   // canActivate: [AuthGuard],
    children: [
      {
        path: 'profile',
        loadChildren: () => import('./views/profile/profile.module').then(m => m.ProfileModule),
        data: { title: 'Profile', breadcrumb: 'PROFILE' }
      },
      {
        path: 'blank',
        loadChildren: () => import('./views/others/app-blank/app-blank.module').then(m => m.AppBlankModule),
       data: { title: 'reporteriesgo' }
      },
      {
        path: 'others',
        loadChildren: () => import('./views/others/others.module').then(m => m.OthersModule),
        data: { title: 'Others', breadcrumb: 'OTHERS' }
      },

      {
        path: 'reporte',
        loadChildren: () => import('./views/reporte/reporte.module').then(m => m.ReporteModule),
        data: { title: 'Reporte', breadcrumb: 'Reporte' }
      },
      {
        path: 'search',
        loadChildren: () => import('./views/search-view/search-view.module').then(m => m.SearchViewModule)
      }
    ]
  },
  {
    path: '**',
    redirectTo: 'sessions/404'
  }
];
