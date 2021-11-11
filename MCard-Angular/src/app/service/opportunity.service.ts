import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {IOpportunity} from "../model/opportunity.model";

@Injectable({
  providedIn: 'root'
})
export class OpportunityService {

  url = 'http://localhost:8080/opportunities';

  constructor(private http: HttpClient) {
  }

  // getAllOpportunities(): Observable<IOpportunity[]> {
  //   return this.http.get<IOpportunity[]>(this.url);
  // }

  getAllOpportunities(): Observable<HttpResponse<IOpportunity[]>> {
    return this.http.get<IOpportunity[]>(this.url, {
      observe: "response"
    });
  }

  saveOpportunityData(data: IOpportunity): Observable<IOpportunity[]> {
    console.log(data);
    return this.http.post<IOpportunity[]>(this.url, data);
  }

  deleteOpportunity(id: number): Observable<IOpportunity[]> {
    return this.http.delete<IOpportunity[]>(`${this.url}/${id}`);
  }

  getOpportunityById(id: number): Observable<IOpportunity> {
    return this.http.get<IOpportunity>(`${this.url}/${id}`);
  }

  updateOpportunityData(id: number | undefined, data: IOpportunity): Observable<IOpportunity[]> {
    return this.http.put<IOpportunity[]>(`${this.url}/${id}`, data);
  }
}
