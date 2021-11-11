import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from "rxjs";
import {IApplicant} from "../model/applicant.model";

@Injectable({
  providedIn: 'root'
})
export class ApplicantService {
  url = 'http://localhost:8080/applicants';

  constructor(private http: HttpClient) {
  }

  getAllApplicants(): Observable<HttpResponse<IApplicant[]>> {
    return this.http.get<IApplicant[]>(this.url, {
      observe: "response"
    });
  }

  saveApplicantData(data: IApplicant): Observable<IApplicant[]> {
    console.log(data);
    return this.http.post<IApplicant[]>(this.url, data);
  }

  deleteApplicant(id: number): Observable<IApplicant[]> {
    return this.http.delete<IApplicant[]>(`${this.url}/${id}`);
  }

  getApplicantById(id: number): Observable<IApplicant> {
    return this.http.get<IApplicant>(`${this.url}/${id}`);
  }

  updateApplicantData(id: number | undefined, data: IApplicant): Observable<IApplicant[]> {
    return this.http.put<IApplicant[]>(`${this.url}/${id}`, data);
  }
}
