import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {LoginService} from 'src/app/_services/LoginAndRegister';
import {first} from 'rxjs/operators';

@Component({ templateUrl: 'login.component.html', styleUrls: ['login.component.css']})
export class LoginComponent implements OnInit {
  loading = false;
  loginForm: FormGroup;
  submitted = false;

  constructor(private formBuilder: FormBuilder, private router: Router, private loginService: LoginService) { }

  ngOnInit() {
    this.loginService.odjava();
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      lozinka: ['', [Validators.required, Validators.minLength(8)]],
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
          alert('Uspešno ste se ulogovali!! :-)\n\n');
          if (data.tipKorisnika === 'Pacijent') {
            this.router.navigate(['/pacijentPregled']);
            this.loginService.ulogovaniTip = 'Pacijent';
          } else if (data.tipKorisnika === 'Lekar') {
            this.router.navigate(['/lekarPregled']);
            this.loginService.ulogovaniTip = 'Lekar';
          } else if (data.tipKorisnika === 'Medicinska sestra') {
            this.router.navigate(['/medicinskaSestra']);
            this.loginService.ulogovaniTip = 'Medicinska sestra';
          } else if (data.tipKorisnika === 'Administrator klinike') {
            this.router.navigate(['/administratorPregled']);
            this.loginService.ulogovaniTip = 'Administrator klinike';
          } else if (data.tipKorisnika === 'Administrator klinickog centra') {
            this.router.navigate(['/administratorKc']);
            this.loginService.ulogovaniTip = 'Administrator klinickog centra';
          } else {
            this.router.navigate(['/welcome']);
          }
          LoginService.authenticated = true;

        },
        error => {
          alert('Pogrešan email ili lozinka!! :-)\n\n');
          this.loading = false;
        });


  }
}
