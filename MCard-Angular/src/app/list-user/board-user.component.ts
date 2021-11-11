import {Component, OnInit} from '@angular/core';
import {UserService} from "../service/user.service";
import {HttpResponse} from "@angular/common/http";
import Swal from "sweetalert2";
import {Router} from "@angular/router";
import {IUser} from "../model/user.model";
import {TokenStorageService} from "../service/token-storage.service";

@Component({
  selector: 'app-board-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.css']
})

export class BoardUserComponent implements OnInit {
  users: IUser[] | null | undefined;
  currentUser: any;

  constructor(private userService: UserService, private router: Router, private token: TokenStorageService) {
  }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();

    this.userService.getAllUsers().subscribe((allData: HttpResponse<IUser[]>) => {
      this.users = allData.body;
    });
  }

  deleteUser(user_id: number, username: string) {
    if (username !== this.currentUser.username) {
      this.userService.deleteUser(user_id).subscribe((result) => {
        this.router.navigateByUrl('/users', {skipLocationChange: true}).then(() => {
          this.router.navigate(['BoardUserComponent']);
        });
      });
      Swal.fire('Successful', 'User is deleted!', 'success');
    }
      else {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Admin can not delete himself!',
        // footer: '<a href=""> Ek işlem (bilgilendirme vs) için yönlendirme yapılabilir</a>'
      })    }
  }
}
