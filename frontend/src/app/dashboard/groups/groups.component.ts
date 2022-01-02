import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { HotToastService } from '@ngneat/hot-toast';
import { environment } from '../../../environments/environment';
import { GroupsService } from 'src/app/services/groups.service';
@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.css']
})
export class GroupsComponent implements OnInit {

  env = environment;
  listGroups: any[] = [];

  constructor(private toastService: HotToastService, private GroupService: GroupsService) { }

  ngOnInit(): void {
    this.getGroups()
  }

  showAddModal: boolean = false;
  showEditModal: boolean = false;
  fullname: string = '';
  tmpfullname: string = '';
  tmpCodeGroup: any;

  submitGroup() {
    if (this.fullname === '') {
      this.toastService.error('Please write a name', {
        position: 'top-center'
      });
      return;
    }

    this.GroupService.saveGroup({ nomGroupe: this.fullname }).subscribe(
      {
        next: (data: any) => {
          console.log(data);
          this.showAddModal = false;
          this.toastService.success('Group Created successfully', {
            position: 'top-center'
          });
          this.getGroups();
          this.showAddModal = false;
        },
        error: (err) => {
          this.toastService.error('There was an error creating the group', {
            position: 'top-center'
          });
          console.error(err);
         },
      }
    );
  }


  getGroups(){
    this.GroupService.getGroups().subscribe({
      next: (data: any) => {
        this.listGroups = data;
        console.log(data);
      },
      error: (err) => {
        this.toastService.error('There was an error getting the groups', {
          position: 'top-center'
        });
        console.error(err);
      },
    });
    }


    deleteGroup(id: any) {

      let groupName = this.listGroups.find(x => x.codeGroupe === id).nomGroupe;

      if (confirm(`Are you sure you want to delete ${groupName} ?`)) {
        this.GroupService.deleteGroup(id).subscribe({
          next: (data: any) => {
            this.toastService.success('Group Deleted successfully', {
              position: 'top-center'
            });
            this.getGroups();
          },
          error: (err: any) => {
            this.toastService.error('There was an error deleting the group', {
              position: 'top-center'
            });
            console.error(err);
           },
        });
      }



    }

    openEditModal(id: any) {
      this.showEditModal = true;
      this.tmpfullname = this.listGroups.find(x => x.codeGroupe === id).nomGroupe;
      this.tmpCodeGroup = id;
    }

    UpdateGroup(){
      this.GroupService.updateGroup({ codeGroupe: this.tmpCodeGroup, nomGroupe: this.tmpfullname }).subscribe({
        next: (data: any) => {
          this.toastService.success('Group Updated successfully', {
            position: 'top-center'
          });
          this.getGroups();
        },
        error: (err: any) => {
          this.toastService.error('There was an error updating the group', {
            position: 'top-center'
          });
          console.error(err);
         },
      });
    }

}
