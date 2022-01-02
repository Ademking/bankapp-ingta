import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { ClientsComponent } from './clients/clients.component';
import { MainComponent } from './main/main.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { EmptyComponent } from './empty/empty.component';
import { EmployeesComponent } from './employees/employees.component';
import { GroupsComponent } from './groups/groups.component';
import { AccountsComponent } from './accounts/accounts.component';
import { ServicesComponent } from './services/services.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { FooterComponent } from './footer/footer.component';

@NgModule({
  declarations: [
    ClientsComponent,
    MainComponent,
    SidebarComponent,
    EmptyComponent,
    EmployeesComponent,
    GroupsComponent,
    AccountsComponent,
    ServicesComponent,
    FooterComponent
  ],
  imports: [
    HttpClientModule,
    FormsModule,
    CommonModule,
    DashboardRoutingModule,

  ]
})
export class DashboardModule { }
