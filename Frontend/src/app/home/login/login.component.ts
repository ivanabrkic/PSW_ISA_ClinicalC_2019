import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {first} from 'rxjs/operators';
import { LoginService } from 'src/app/modules/shared/services/login-and-register-service/login.service';

@Component({ templateUrl: 'login.component.html', styleUrls: ['login.component.css']})
export class LoginComponent implements OnInit {
  loading = false;
  loginForm: FormGroup;
  submitted = false;

  constructor(private formBuilder: FormBuilder, private router: Router, private loginService: LoginService) { }

  ngOnInit() {
    // this.loginService.odjava();
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
          if (data.aktivnostNaloga === false) {
            alert('Nalog jos nije aktiviran!');
            return;
          }
          alert('Uspešno ste se ulogovali!! :-)\n\n');
          console.log(data);

          if (data.prvoLogovanje === true && data.tipKorisnika !== 'Pacijent') {
            this.router.navigate(['/promenaLozinke']);
          } else
            if (data.tipKorisnika === 'Pacijent') {
              this.router.navigate(['/pacijentPregled']);
            } else if (data.tipKorisnika === 'Lekar') {
              this.router.navigate(['/lekarPregled']);
            } else if (data.tipKorisnika === 'Medicinska sestra') {
              this.router.navigate(['/medicinskaSestra']);
            } else if (data.tipKorisnika === 'Administrator klinike') {
              this.router.navigate(['/administratorklinikepregled']);
            } else if (data.tipKorisnika === 'Administrator klinickog centra') {
              this.router.navigate(['/administratorKc']);
            } else {
              this.router.navigate(['/welcome']);
            }
          },
          error => {
            alert('Pogrešan email ili lozinka!! :-)\n\n');
            this.loading = false;
        });


  }
}
