import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {ICityOpportunity} from "../model/city-opportunity.model";

@Injectable({
  providedIn: 'root'
})
export class CityOpportunityService {

  url = 'http://localhost:8080/city-opportunities';

  constructor(private http: HttpClient) {
  }

  getAllCityOpportunities(): Observable<HttpResponse<ICityOpportunity[]>> {
    return this.http.get<ICityOpportunity[]>(this.url, {
      observe: "response"
    });
  }

  saveCityOpportunityData(data: ICityOpportunity): Observable<ICityOpportunity[]> {
    return this.http.post<ICityOpportunity[]>(this.url, data);
  }

  deleteCityOpportunity(id: number): Observable<ICityOpportunity[]> {
    return this.http.delete<ICityOpportunity[]>(`${this.url}/${id}`);
  }

  getOpportunityById(id: number): Observable<ICityOpportunity> {
    return this.http.get<ICityOpportunity>(`${this.url}/${id}`);
  }

  updateOpportunityData(id: number | undefined, data: ICityOpportunity): Observable<ICityOpportunity[]> {
    return this.http.put<ICityOpportunity[]>(`${this.url}/${id}`, data);
  }
}
