import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class EmployeesService {


  env = environment;
  constructor(private http: HttpClient) { }

  getEmps() {
    return this.http.get(`${this.env.apiUrl}/employees`)
  }

  saveEmp(emp: any, groupId: string) {
    return this.http.post(`${this.env.apiUrl}/employees/${groupId}`, emp)
  }

  deleteEmp(id: number) {
    return this.http.delete(`${this.env.apiUrl}/employees/${id}`)
  }

  updateEmp(emp: any, groupId: string) {
    return this.http.put(`${this.env.apiUrl}/employees/${emp.codeEmploye}/${groupId}`, emp)
  }

}
