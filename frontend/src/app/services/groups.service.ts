import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GroupsService {

  env = environment;
  constructor(private http: HttpClient) { }

  getGroups() {
    return this.http.get(`${this.env.apiUrl}/groups`)
  }

  saveGroup(group: any) {
    return this.http.post(`${this.env.apiUrl}/groups`, group)
  }

  deleteGroup(id: number) {
    return this.http.delete(`${this.env.apiUrl}/groups/${id}`)
  }

  updateGroup(group: any) {
    return this.http.put(`${this.env.apiUrl}/groups/${group.codeGroupe}`, group)
  }
}
