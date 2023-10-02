import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProvisionComponent } from './provision/provision.component';
import { RiesgoComponent } from './riesgo/riesgo.component';
import { MatTableModule } from '@angular/material/table';

const routes: Routes = [
  {
    path: 'provisiones',
    component: ProvisionComponent,
    data: {
      title: 'Provision',
      breadcrumb: 'Provisiones'
    }
  },
  {
    path: 'riesgos',
    component: RiesgoComponent,
    data: {
      title: 'Riesgo',
      breadcrumb: 'Riesgos'
    }
  },
];


@NgModule({
  imports: [RouterModule.forChild(routes), MatTableModule
  ],
  exports: [RouterModule]
})
export class ReporteRoutingModule { }
