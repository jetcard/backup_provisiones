import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { RiesgosService } from '../../../services/reportes/riesgos.service';//loader.service.ts
import { finalize } from 'rxjs/operators';

@Injectable()
export class LoaderInterceptor implements HttpInterceptor {

  constructor(private loaderService: RiesgosService) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
     this.loaderService.show();

     return next.handle(request).pipe(
           finalize(() => this.loaderService.hide()),
     );
  }
}
