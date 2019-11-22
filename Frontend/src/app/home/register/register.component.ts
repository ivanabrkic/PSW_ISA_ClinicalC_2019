import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

import { MustMatch } from 'src/app/_helpers/MustMatch';
import {Router} from '@angular/router';
import { first } from 'rxjs/operators';
import {RegisterService} from 'src/app/_services';

@Component({ templateUrl: 'register.component.html', styleUrls: [ 'register.component.css' ] })
export class RegisterComponent implements OnInit {
  loading = false;
  registerForm: FormGroup;
  submitted = false;

  constructor(private formBuilder: FormBuilder, private router: Router, private registerService: RegisterService) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      confirmpass: ['', Validators.required],
      jbo: ['', Validators.required],
      username: ['', Validators.required]
    },  {
      validator: MustMatch('password', 'confirmpass')
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  onSubmit() {

    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    this.loading = true;
    this.registerService.register(this.registerForm.value)
      .pipe(first())
      .subscribe(
        data => {
         // this.alertService.success('Registration successful', true);
          this.router.navigate(['/login']);
        },
        error => {
         // this.alertService.error(error);
          this.loading = false;
        });

    alert('SUCCESS!! :-)\n\n' + JSON.stringify(this.registerForm.value));
  }
}
