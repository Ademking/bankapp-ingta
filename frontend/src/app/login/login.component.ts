import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HotToastService } from '@ngneat/hot-toast';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email: string = 'admin@email.com'; // for the demo
  password: string = '123456';

  constructor(private toastService: HotToastService, private router: Router, private loginService: LoginService) { }

  ngOnInit(): void {
    localStorage.clear()
  }


  login(e: any) {
    e.preventDefault();
    this.loginService.login({
      email: this.email,
      password: this.password
    }).subscribe({
      next: (data: any) => {
        console.log(data);
        if (data.connected) {
          this.toastService.success('Login Successful', {
            position: 'top-center'
          });
          localStorage.setItem('connected', 'true');
          this.router.navigate(['/dashboard']);
        } else {
          this.toastService.error('Wrong email or password', {
            position: 'top-center'
          });
        }
      },
      error: (err) => {
        this.toastService.error('There was an error logging in', {
          position: 'top-center'
        });
        console.error(err);
      }
    });
  }
}
