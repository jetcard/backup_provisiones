import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
} from '@angular/common/http';

@Injectable()
export class CorsInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    // Agregar la cabecera 'Access-Control-Allow-Origin'
    const modifiedReq = req.clone({
      setHeaders: {
        'Access-Control-Allow-Origin': 'http://localhost:4250/',
      },
    });

    return next.handle(modifiedReq);
  }
}