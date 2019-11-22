import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {LoginService} from 'src/app/_services';
import {first} from "rxjs/operators";

@Component({ templateUrl: 'login.component.html', styleUrls: ['login.component.css']})
export class LoginComponent implements OnInit {
  loading = false;
  loginForm: FormGroup;
  submitted = false;

  constructor(private formBuilder: FormBuilder, private router: Router, private loginService: LoginService) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    }, );
  }

  // convenience getter for easy access to form fields
  get f() { return this.loginForm.controls; }

  public onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.loginService.login(this.loginForm.value)
      .pipe(first())
      .subscribe(
        data => {
          // this.alertService.success('Registration successful', true);
          this.router.navigate(['/home']);
        },
        error => {
          // this.alertService.error(error);
          this.loading = false;
        });

    alert('SUCCESS!! :-)\n\n' + JSON.stringify(this.loginForm.value));

    alert('SUCCESS!! :-)\n\n' + JSON.stringify(this.loginForm.value));
  }
}
