import { Component, OnInit } from '@angular/core';
import { HotToastService } from '@ngneat/hot-toast';
import { AccountsService } from 'src/app/services/accounts.service';
import { GroupsService } from 'src/app/services/groups.service';
import { environment } from '../../../environments/environment';
import { ClientsService } from 'src/app/services/clients.service';
import { EmployeesService } from 'src/app/services/employees.service';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {

  env = environment;
  listComptes: any[] = [];
  listGroups: any[] = [];
  tmpAccountId: any;

  constructor(private toastService: HotToastService, private ClientService: ClientsService, private AccountsService: AccountsService, private GroupService: GroupsService, private EmployeesService: EmployeesService, private GroupsService: GroupsService) { }

  ngOnInit(): void {
    this.getAccounts()
    this.getGroups();
    this.getEmps();
    this.getClient();
  }

  getEmps() {
    this.EmployeesService.getEmps().subscribe({
      next: (data: any) => {
        this.listEmp = data;
        console.log(data);
      }
    });
  }

  getClient() {
    this.ClientService.getClients().subscribe({
      next: (data: any) => {
        this.listClients = data;
        console.log(data);
      }
    });
  }




  keyPress(event: any) {
    const pattern = /[0-9\+\-\ ]/;

    let inputChar = String.fromCharCode(event.charCode);
    if (event.keyCode != 8 && !pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  showAddModal: boolean = false;
  showEditModal: boolean = false;
  fullname: string = '';
  tmpfullname: string = '';
  tmpcodeCompte: any;
  groupId: string = '';
  tmpGroupId: string = '';
  clientId: string = '';
  listClients: any[] = [];
  empId: string = '';
  listEmp: any[] = [];
  accountType: string = '';
  decouvert: any;
  balance: any;

  tmpempId: string = '';
  tmpclientId: string = '';
  tmpaccountType: string = '';
  tmpdecouvert: any;
  tmpbalance: any;
  getGroups() {
    this.GroupService.getGroups().subscribe({
      next: (data: any) => {
        this.listGroups = data;
        console.log(data);
      }
    });
  }


  submitAccount() {

    if (this.empId === '' || this.accountType === '' || this.balance === '') {
      this.toastService.error('Please fill all the fields', {
        position: 'top-center'
      });
      return;
    }



    this.AccountsService.saveAccount(
      {
        "solde": this.balance,
        "decouvert": this.decouvert
      },
      this.empId,
      this.clientId,
      this.accountType
    ).subscribe(
      {
        next: (data: any) => {
          console.log(data);
          this.showAddModal = false;
          this.toastService.success('Account Created successfully', {
            position: 'top-center'
          });
          this.showAddModal = false;
          this.getAccounts();
          this.empId = '';
          this.clientId = '';
          this.accountType = '';
          this.balance = '';
          this.decouvert = '';
        },
        error: (err: any) => {
          this.toastService.error('There was an error creating the Account', {
            position: 'top-center'
          });
          this.showAddModal = false;
          console.error(err);
        },
      }
    );
  }


  getAccounts() {
    this.AccountsService.getAccounts().subscribe({
      next: (data: any) => {
        this.listComptes = data;
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


  deleteEmp(id: any, type: string) {


    if (confirm(`Are you sure you want to delete this account?`)) {
      this.AccountsService.deleteAccount(id, type).subscribe({
        next: (data: any) => {
          this.toastService.success('Account Deleted successfully', {
            position: 'top-center'
          });
          this.getAccounts();
        },
        error: (err: any) => {
          this.toastService.error('There was an error deleting the Account', {
            position: 'top-center'
          });
          console.error(err);
        },
      });
    }

  }

  openEditModal(compte: any) {
    console.log(compte);

    this.showEditModal = true;
    this.tmpclientId = compte.client.codeClient;
    this.tmpempId = compte.employes.codeEmploye;
    this.tmpaccountType = compte.type_CPT;
    this.tmpbalance = compte.solde;
    if (this.tmpaccountType === 'CC') {
      this.tmpdecouvert = compte.decouvert;
    }
    this.tmpAccountId = compte.codeCompte;
    // this.tmpfullname = this.listComptes.find(x => x.codeCompte === id).nomEmploye;
    // this.tmpcodeCompte = id;
    // this.tmpGroupId = groupId;
  }

  updateAccount() {

    if (this.tmpaccountType == 'CC') {
      this.AccountsService.updateAccountCC({
        "solde": this.tmpbalance,
        "decouvert": this.tmpdecouvert,
      }, this.tmpAccountId, this.tmpclientId, this.tmpempId).subscribe({
        next: (data: any) => {
          this.toastService.success('Account Updated successfully', {
            position: 'top-center'
          });
          this.showEditModal = false;
          this.getAccounts();
        }
      })
    }
    else {
      this.AccountsService.updateAccountCE({
        "solde": this.tmpbalance,
      }, this.tmpAccountId, this.tmpclientId, this.tmpempId).subscribe({
        next: (data: any) => {
          this.toastService.success('Account Updated successfully', {
            position: 'top-center'
          });
          this.showEditModal = false;
          this.getAccounts();
        }
      })

    }


    // this.AccountsService.updateAccount({ codeCompte: this.tmpcodeCompte, nomEmploye: this.tmpfullname }, this.tmpGroupId).subscribe({
    //   next: (data: any) => {
    //     this.toastService.success('Employee Updated successfully', {
    //       position: 'top-center'
    //     });
    //     this.getEmps();
    //   },
    //   error: (err: any) => {
    //     this.toastService.error('There was an error updating the emp', {
    //       position: 'top-center'
    //     });
    //     console.error(err);
    //    },
    // });
  }

}

