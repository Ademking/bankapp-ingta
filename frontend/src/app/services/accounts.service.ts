import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AccountsService {

  env = environment;
  constructor(private http: HttpClient) { }

  getAccounts() {
    return this.http.get(`${this.env.apiUrl}/accounts`)
  }

  saveAccount(account: any, empId: any,  clientId: any,  accountType: any) {
    return this.http.post(`${this.env.apiUrl}/accounts/${empId}/${clientId}/${accountType}`, account)
  }

  deleteAccount(id: number, type: string) {
    return this.http.delete(`${this.env.apiUrl}/accounts/${id}/${type}`)
  }

  updateAccountCC(account: any, id: string, clientId: string, empId: string) {
    return this.http.put(`${this.env.apiUrl}/accounts/cc/${id}/${clientId}/${empId}`, account)
  }

  updateAccountCE(account: any, id: string, clientId: string, empId: string) {
    return this.http.put(`${this.env.apiUrl}/accounts/ce/${id}/${clientId}/${empId}`, account)
  }
}
