import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from 'auth.service';
import { Observable  } from 'rxjs';
import { JwtAuthService } from 'app/shared/services/auth/jwt-auth.service';
import { environment } from 'environments/environment';
@Injectable({
  providedIn: 'root'
})
export class ProvisionesService {
  private apiUrl = `${environment.apiURL}/reporte-provisiones`;

  private apiUrl2 = `${environment.apiURL}/reporte-provisiones-excel`;

  private reporteServices = btoa('user:b4530586-15c6-43cf-8c1d-960f426f1986');
  // Agregar credenciales para otros servicios si es necesario

  constructor(private http: HttpClient, private authService: AuthService,private serv :JwtAuthService ) { }

  getReporteServices(): string {
    return this.reporteServices;
  }

  // Agregar métodos para obtener credenciales de otros servicios si es necesario

  enviarAuthServiceComoJson() {
    // Convertir el objeto AuthService a JSON
    const authServiceJson = JSON.stringify(this);

    // Agregar el JSON como parámetro de consulta en la URL
    const params = new HttpParams().set('authServiceJson', authServiceJson);

    // Realizar la solicitud GET con los parámetros de consulta
    this.http.get(this.apiUrl, { params }).subscribe(
      (data) => {
        console.log('Respuesta del servidor:', data);
      },
      (error) => {
        console.error('Error en la solicitud:', error);
      }
    );
  }


  listPageable(p: number, s: number){
    const token = JSON.parse(sessionStorage.getItem("JWT_TOKEN"));
    console.log("getreporteNUEVO"+this.serv.fondo)
    return this.http.get<any>(`${this.apiUrl}/pageable?page=${p}&size=${s}`);
  }

  obtenerDatos(): Observable<any> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getReporteServices()}`
    });
    //return this.http.get<any>(`${this.apiUrl}/datos`, { headers });
    return this.http.get<any>(`${this.apiUrl}`, { headers });
  }

  getReporteProvision(fechaFormateada: String) {
    const token = JSON.parse(sessionStorage.getItem("JWT_TOKEN"));
    console.log("fechaFormateada "+fechaFormateada)
    console.log("getreporte this.serv.fondo "+this.serv.fondo)
    //const url = `${this.apiUrl}/${this.serv.fondo}/${fechaFormateada}`;
    const url = `${this.apiUrl}?fechaProceso=${fechaFormateada}`;
    console.log("reporte de calculo: "+url)
    return this.http.get<string>(url,{
      headers: new HttpHeaders().set('Authorization', `Bearer ${token.jwtToken}`).set('Content-Type', 'application/json')
    });
  }


  /**
   * export excel categories
   */
    exportCategories(fechaFormateada: String){
      const token = JSON.parse(sessionStorage.getItem("JWT_TOKEN"));
      const headers = new HttpHeaders({
        Authorization: `Basic ${this.authService.getReporteServices()}`,
      });
      const url = `${this.apiUrl2}/exportar-provisiones/excel?fechaProceso=${fechaFormateada}`;
      return this.http.get(url, {
        headers: new HttpHeaders().set('Authorization', `Bearer ${token.jwtToken}`).set('Content-Type', 'application/json'),
        responseType: 'blob'
      });
    }

    /*
  exportaCategories(): Observable<any>{
    const token = JSON.parse(sessionStorage.getItem("JWT_TOKEN"));
    const headers = new HttpHeaders({
      Authorization: `Basic ${this.authService.getReporteServices()}`,
    });
    const url = `${this.apiUrl}/provision/export/excel`;
    return this.http.get<string>(url, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${token.jwtToken}`).set('Content-Type', 'application/json')
    });
  }*/



}
