import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  env = environment;
  constructor(private http: HttpClient) { }

  login(credentials: any) {
    return this.http.post(`${this.env.apiUrl}/login`, credentials)
  }
}
