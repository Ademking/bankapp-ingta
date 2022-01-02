import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { HotToastService } from '@ngneat/hot-toast';
import { environment } from '../../../environments/environment';
import { EmployeesService } from 'src/app/services/employees.service';
import { GroupsService } from 'src/app/services/groups.service';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {

  env = environment;
  listEmp: any[] = [];
  listGroups: any[] = [];

  constructor(private toastService: HotToastService, private EmployeesService: EmployeesService, private GroupService: GroupsService) { }

  ngOnInit(): void {
    this.getEmps()
    this.getGroups();
  }

  showAddModal: boolean = false;
  showEditModal: boolean = false;
  fullname: string = '';
  tmpfullname: string = '';
  tmpcodeEmploye: any;
  groupId: string = '';
  tmpGroupId: string = '';

  getGroups(){
    this.GroupService.getGroups().subscribe({
      next: (data: any) => {
        this.listGroups = data;
        console.log(data);
      }
    });
  }


  submitEmp() {


    if (this.groupId === '') {
      this.toastService.error('Please select a group', {
        position: 'top-center'
      });
      return;
    }

    if (this.fullname === '') {
      this.toastService.error('Please write a name', {
        position: 'top-center'
      });
      return;
    }

    this.EmployeesService.saveEmp({ nomEmploye: this.fullname }, this.groupId).subscribe(
      {
        next: (data: any) => {
          console.log(data);
          this.showAddModal = false;
          this.toastService.success('Employee Created successfully', {
            position: 'top-center'
          });
          this.showAddModal = false;
          this.getEmps();
          this.fullname = '';
          this.groupId = '';

        },
        error: (err) => {
          this.toastService.error('There was an error creating the Employee', {
            position: 'top-center'
          });
          this.showAddModal = false;
          console.error(err);
         },
      }
    );
  }


  getEmps(){
    this.EmployeesService.getEmps().subscribe({
      next: (data: any) => {
        this.listEmp = data;
        console.log(data);
      },
      error: (err: any) => {
        this.toastService.error('There was an error getting the clients', {
          position: 'top-center'
        });
        console.error(err);
      },
    });
    }


    deleteEmp(id: any) {

      let empName = this.listEmp.find(x => x.codeEmploye === id).nomEmploye;

      if (confirm(`Are you sure you want to delete ${empName} ?`)) {
        this.EmployeesService.deleteEmp(id).subscribe({
          next: (data: any) => {
            this.toastService.success('Employee Deleted successfully', {
              position: 'top-center'
            });
            this.getEmps();
          },
          error: (err: any) => {
            this.toastService.error('There was an error deleting the employee', {
              position: 'top-center'
            });
            console.error(err);
           },
        });
      }

    }

    openEditModal(id: any, groupId: any) {
      this.showEditModal = true;
      this.tmpfullname = this.listEmp.find(x => x.codeEmploye === id).nomEmploye;
      this.tmpcodeEmploye = id;
      this.tmpGroupId = groupId;
    }

    UpdateEmp(){
      this.EmployeesService.updateEmp({ codeEmploye: this.tmpcodeEmploye, nomEmploye: this.tmpfullname }, this.tmpGroupId).subscribe({
        next: (data: any) => {
          this.toastService.success('Employee Updated successfully', {
            position: 'top-center'
          });
          this.getEmps();
        },
        error: (err: any) => {
          this.toastService.error('There was an error updating the emp', {
            position: 'top-center'
          });
          console.error(err);
         },
      });
    }

}
