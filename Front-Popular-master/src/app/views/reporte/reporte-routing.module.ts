import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RiesgoComponent } from './riesgo/riesgo.component';
import { MatTableModule } from '@angular/material/table';

const routes: Routes = [
  {
    path: 'riesgo',
    component: RiesgoComponent,
    data: {
      title: 'Riesgo',
      breadcrumb: 'Provisiones'
    }
  },



];


@NgModule({
  imports: [RouterModule.forChild(routes), MatTableModule
  ],
  exports: [RouterModule]
})
export class ReporteRoutingModule { }
