import { TestBed } from '@angular/core/testing';

import { ProvisionesService } from './provisiones.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
@Injectable({
  providedIn: 'root'
})
export class reportesProvisionService {
  constructor(
    private http: HttpClient
  ) { }
  getAllProvision() {
    return this.http.get<reportesProvisionService[]>('http://localhost:8085/proyecto-reportes/api/reporte-provisiones')
  }

}

describe('ProvisionesService', () => {
  let service: ProvisionesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProvisionesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
