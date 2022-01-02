import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HotToastService } from '@ngneat/hot-toast';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  constructor(private router: Router, private toastService: HotToastService) { }

  ngOnInit(): void {
  }

  logout() {
    this.toastService.success('Logout Successful. See you soon!');
    localStorage.clear();
    this.router.navigate(['/']);
  }

}
