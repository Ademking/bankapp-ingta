import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountsComponent } from './accounts/accounts.component';
import { ClientsComponent } from './clients/clients.component';
import { EmployeesComponent } from './employees/employees.component';
import { GroupsComponent } from './groups/groups.component';
import { MainComponent } from './main/main.component';
import { ServicesComponent } from './services/services.component';

const routes: Routes = [
  {
    path: '', component: MainComponent,
    children: [
      { path: '', redirectTo: 'clients', pathMatch: 'full' },
      { path: 'clients', component: ClientsComponent },
      { path: 'employees', component: EmployeesComponent },
      { path: 'groups', component: GroupsComponent },
      { path: 'accounts', component: AccountsComponent },
      { path: 'services', component: ServicesComponent },

    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
