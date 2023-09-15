import {
  Component,
  OnInit,
  ViewChild,
  OnDestroy,
  AfterViewInit,
} from '@angular/core';
import {
  UntypedFormGroup,
  UntypedFormBuilder,
  UntypedFormControl,
  Validators,
} from '@angular/forms';

import { Observable } from 'rxjs';
import { environment } from 'environments/environment';
import { JwtHelperService } from '@auth0/angular-jwt';
import { MatButton } from '@angular/material/button';
import { MatProgressBar } from '@angular/material/progress-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { AppLoaderService } from 'app/shared/services/app-loader/app-loader.service';
import { JwtAuthService } from 'app/shared/services/auth/jwt-auth.service';
import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';
import { Subject } from 'rxjs';
import { Fondo } from './fondo.model';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss'],
})
export class SigninComponent implements OnInit, AfterViewInit, OnDestroy {
  @ViewChild(MatProgressBar) progressBar: MatProgressBar;
  @ViewChild(MatButton) submitButton: MatButton;
  private messageChange = new Subject<string>;
  signinForm: UntypedFormGroup;
  errorMsg = '';
  username:string;
  password:string;
  idfondoSelected:string;
  fondoIndex:Fondo[];
  fondos: Fondo[] = [{'idfondo':'1','fondo':'FONDO CAPITAL EMPRENDEDOR'},{'idfondo':'2','fondo':'FONDO POPULAR'},{'idfondo':'3','fondo':'FONDO MYPE'},{'idfondo':'4','fondo':'FONDO PEREZ HIDALGO'}];;




  private _unsubscribeAll: Subject<any>;


  constructor(
    private fb: UntypedFormBuilder,
    private jwtAuth: JwtAuthService,
    private egretLoader: AppLoaderService,
    private router: Router,
    private route: ActivatedRoute,
    private _snackBar: MatSnackBar,
  ) {
    this._unsubscribeAll = new Subject();

  }
  ejecuto(event){
    this.idfondoSelected=event.value
  }
  ngOnInit() {
    const password = new UntypedFormControl('', Validators.required);


    this.signinForm = new UntypedFormGroup({
      username: new UntypedFormControl(this.username, Validators.required),
      password: new UntypedFormControl(this.password, Validators.required),
      rememberMe: new UntypedFormControl(true),
      idfondoSelected: new UntypedFormControl(this.idfondoSelected, Validators.required),
    });
  }

  onSubmit() {
    if (!this.signinForm.invalid) {
      // do what you wnat with your data
     // console.log(this.signinForm.value);
    }
  }
  ngAfterViewInit() {
    //this.autoSignIn();
  }

  ngOnDestroy() {
    this._unsubscribeAll.next(1);
    this._unsubscribeAll.complete();
  }

  signin() {

    if (this.signinForm.invalid) { return; }

    const signinData = this.signinForm.value;
    console.log("ingresando")
   // this.submitButton.disabled = true;
    //this.progressBar.mode = 'indeterminate';
    this.jwtAuth.signin(this.username, this.password).subscribe(
      (response) => {
        const helper = new JwtHelperService();

        let token = JSON.stringify(response);
        sessionStorage.setItem(this.jwtAuth.JWT_TOKEN, token);

        let tk = JSON.parse(sessionStorage.getItem(this.jwtAuth.JWT_TOKEN));
        const decodedToken = helper.decodeToken(tk.jwtToken);
        this.jwtAuth.JWT_TOKEN = tk.jwtToken;
        this.jwtAuth.user={'id': '5b700c45639d2c0c54b354ba',
        displayName: this.username,
        role: 'SA'}
        const resul=this.fondos.find(x => x.idfondo == this.idfondoSelected);
        this.jwtAuth.fondo = this.idfondoSelected;
        this.jwtAuth.desfondo = resul.fondo;
        this._snackBar.open("Accediendo", 'INFO', {duration: 3000, horizontalPosition: 'right', verticalPosition: 'top'});
        this.router.navigate(['reporte']);
      },
      (err) => {
        //this.submitButton.disabled = false;
       // this.progressBar.mode = 'determinate';
       this._snackBar.open("Verificar datos", 'INFO', {duration: 3000, horizontalPosition: 'right', verticalPosition: 'top'});
      }
    );
 }

  autoSignIn() {
    if (this.jwtAuth.return === '/') {
      return;
    }
    this.egretLoader.open(
      `Automatically Signing you in! \n Return url: ${this.jwtAuth.return.substring(
        0,
        20
      )}...`,
      { width: '320px' }
    );
    setTimeout(() => {
      this.signin();

      this.egretLoader.close();
    }, 2000);
  }


  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }
}
