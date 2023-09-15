import { ErrorStateMatcher } from '@angular/material/core';
import { Validators, UntypedFormGroup, NgForm, FormGroupDirective, UntypedFormControl, AbstractControl, ValidationErrors } from "@angular/forms";
import { UntypedFormBuilder } from "@angular/forms";
import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-signup",
  templateUrl: "./signup.component.html",
  styleUrls: ["./signup.component.scss"]
})
export class SignupComponent implements OnInit {
  signupForm: UntypedFormGroup;

  lastName:string;
  username:string ;
  password:string ;
  constructor(private fb: UntypedFormBuilder) { }

  ngOnInit(): void {

    const password = new UntypedFormControl('', Validators.required);

    this.signupForm = this.fb.group(
      {
        lastName: ["", Validators.required],
        username: ["", Validators.required],
        email: ["", [Validators.required, Validators.email]],
        password: password,
        agreed: [false, Validators.required]

      }
    );
  }

  onSubmit() {
    if (!this.signupForm.invalid) {
      // do what you wnat with your data
      console.log(this.signupForm.value);
    }
  }
}
