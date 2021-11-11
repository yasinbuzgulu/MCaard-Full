// import {Injectable} from '@angular/core';
// import {HttpClient} from "@angular/common/http";
// import {Observable} from "rxjs";
// import {map} from "rxjs/operators";
// import {IApplicant} from "../model/applicant.model";
//
// const API_URL = 'http://localhost:8080/api/test/';
//
// @Injectable({
//   providedIn: 'root'
// })
// export class UserService {
//   url = 'http://localhost:8080/users';
//
//   constructor(private http: HttpClient) {
//   }
//
//   getPublicContent(): Observable<any> {
//     return this.http.get(API_URL + 'all', {responseType: 'text'});
//   }
//
//   getUsers(): Observable<any> {
//     return this.http.get<any>(this.url, {
//       observe: "response"
//     })
//       .pipe(
//         map((data: any) => {
//             return data.body._embedded.users;
//           }
//         )
//       );
//   }
//
//
//   deleteUser(username: any): Observable<any> {
//     return this.http.delete<any>(`${this.url}/${username}`);
//   }
// }
import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from "rxjs";
import {IUser} from "../model/user.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  url = 'http://localhost:8080/users';

  constructor(private http: HttpClient) {
  }

  getAllUsers(): Observable<HttpResponse<IUser[]>> {
    return this.http.get<IUser[]>(this.url, {
      observe: "response"
    });
  }

  saveUserData(data: IUser): Observable<IUser[]> {
    console.log(data);
    return this.http.post<IUser[]>(this.url, data);
  }

  deleteUser(id: number): Observable<IUser[]> {
    return this.http.delete<IUser[]>(`${this.url}/${id}`);
  }

  getUserById(id: number): Observable<IUser> {
    return this.http.get<IUser>(`${this.url}/${id}`);
  }

  updateUserData(id: number | undefined, data: IUser): Observable<IUser[]> {
    return this.http.put<IUser[]>(`${this.url}/${id}`, data);
  }
}
