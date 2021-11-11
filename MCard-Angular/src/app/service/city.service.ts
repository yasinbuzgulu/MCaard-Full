// import {Injectable} from '@angular/core';
// import {HttpClient, HttpResponse} from "@angular/common/http";
// import {Observable} from "rxjs";
// import {ICity} from "../model/city.model";
// import {map} from "rxjs/operators";
// import {IApplicant} from "../model/applicant.model";
//
// @Injectable({
//   providedIn: 'root'
// })
// export class CityService {
//
//   url = 'http://localhost:8080/cities';
//
//   constructor(private http: HttpClient) {
//   }
//
//   saveCityData(data: ICity): Observable<ICity[]> {
//     console.log(data);
//     return this.http.post<ICity[]>(this.url, data);
//   }
//
//   getAllCities(): Observable<ICity[]> {
//     return this.http.get<ICity[]>(this.url, {
//       observe: "response"
//     })
//       .pipe(
//         map((data: any) => {
//             return data.body._embedded.cities;
//           }
//         )
//       );
//   }
//
//   deleteCity(id: number): Observable<ICity[]> {
//     return this.http.delete<ICity[]>(`${this.url}/${id}`);
//   }
//
//   getCityById(id: number): Observable<ICity> {
//     return this.http.get<ICity>(`${this.url}/${id}`);
//   }
//
//   updateCityData(id: number | undefined, data: ICity): Observable<ICity[]> {
//     return this.http.put<ICity[]>(`${this.url}/${id}`, data);
//   }
//
// }
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
import {ICity} from "../model/city.model";

@Injectable({
  providedIn: 'root'
})
export class CityService {
  url = 'http://localhost:8080/cities';

  constructor(private http: HttpClient) {
  }

  getAllCities(): Observable<HttpResponse<ICity[]>> {
    return this.http.get<ICity[]>(this.url, {
      observe: "response"
    });
  }

  saveCityData(data: ICity): Observable<ICity[]> {
    return this.http.post<ICity[]>(this.url, data);
  }

  deleteCity(id: number): Observable<ICity[]> {
    return this.http.delete<ICity[]>(`${this.url}/${id}`);
  }

  getCityById(id: number): Observable<ICity> {
    return this.http.get<ICity>(`${this.url}/${id}`);
  }

  updateCityData(id: number | undefined, data: ICity): Observable<ICity[]> {
    return this.http.put<ICity[]>(`${this.url}/${id}`, data);
  }
}
