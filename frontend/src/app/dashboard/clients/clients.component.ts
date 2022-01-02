import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { HotToastService } from '@ngneat/hot-toast';
import { environment } from '../../../environments/environment';
import { ClientsService } from 'src/app/services/clients.service';
@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {

  env = environment;
  listClients: any[] = [];

  constructor(private toastService: HotToastService, private ClientService: ClientsService) { }

  ngOnInit(): void {
    this.getClients()
  }

  showAddModal: boolean = false;
  showEditModal: boolean = false;
  fullname: string = '';
  tmpfullname: string = '';
  tmpCodeClient: any;

  submitClient() {

    if (this.fullname === '') {
      this.toastService.error('Please write a name', {
        position: 'top-center'
      });
      return;
    }

    this.ClientService.saveClient({ nomClient: this.fullname }).subscribe(
      {
        next: (data: any) => {
          console.log(data);
          this.showAddModal = false;
          this.toastService.success('Client Created successfully', {
            position: 'top-center'
          });
          this.showAddModal = false;
          this.getClients();
          this.fullname = '';
        },
        error: (err) => {
          this.toastService.error('There was an error creating the client', {
            position: 'top-center'
          });
          console.error(err);
         },
      }
    );
  }


  getClients(){
    this.ClientService.getClients().subscribe({
      next: (data: any) => {
        this.listClients = data;
        console.log(data);
      },
      error: (err) => {
        this.toastService.error('There was an error getting the clients', {
          position: 'top-center'
        });
        console.error(err);
      },
    });
    }


    deleteClient(id: any) {

      let clientName = this.listClients.find(x => x.codeClient === id).nomClient;

      if (confirm(`Are you sure you want to delete ${clientName} ?`)) {
        this.ClientService.deleteClient(id).subscribe({
          next: (data: any) => {
            this.toastService.success('Client Deleted successfully', {
              position: 'top-center'
            });
            this.getClients();
          },
          error: (err: any) => {
            this.toastService.error('There was an error deleting the client', {
              position: 'top-center'
            });
            console.error(err);
           },
        });
      }



    }

    openEditModal(id: any) {
      this.showEditModal = true;
      this.tmpfullname = this.listClients.find(x => x.codeClient === id).nomClient;
      this.tmpCodeClient = id;
    }

    UpdateClient(){
      this.ClientService.updateClient({ codeClient: this.tmpCodeClient, nomClient: this.tmpfullname }).subscribe({
        next: (data: any) => {
          this.toastService.success('Client Updated successfully', {
            position: 'top-center'
          });
          this.getClients();
        },
        error: (err: any) => {
          this.toastService.error('There was an error updating the client', {
            position: 'top-center'
          });
          console.error(err);
         },
      });
    }

}
