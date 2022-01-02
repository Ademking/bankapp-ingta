import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClientsService {

  env = environment;
  constructor(private http: HttpClient) { }

  getClients() {
    return this.http.get(`${this.env.apiUrl}/clients`)
  }

  saveClient(client: any) {
    return this.http.post(`${this.env.apiUrl}/clients`, client)
  }

  deleteClient(id: number) {
    return this.http.delete(`${this.env.apiUrl}/clients/${id}`)
  }

  updateClient(client: any) {
    return this.http.put(`${this.env.apiUrl}/clients/${client.codeClient}`, client)
  }
}
