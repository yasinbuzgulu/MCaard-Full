import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {IApplicant} from "../model/applicant.model";
import {ICard} from "../model/card.model";
import {ICityOpportunity} from "../model/city-opportunity.model";

@Injectable({
  providedIn: 'root'
})
export class CardService {
  url = 'http://localhost:8080/cards';

  constructor(private http: HttpClient) {
  }

  getAllCards(): Observable<HttpResponse<ICard[]>> {
    return this.http.get<ICard[]>(this.url, {
      observe: "response"
    });
  }

  saveCardData(data: {
    expiryDate: string; cityOpportunity: ICityOpportunity[]; price: number;
    cardOpportunityYear: number; applicant: IApplicant
  }): Observable<ICard[]> {
    return this.http.post<ICard[]>(this.url, data);
  }

  deleteCard(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }

  getCardById(id: number): Observable<ICard> {
    return this.http.get<ICard>(`${this.url}/${id}`);
  }

  updateCardData(id: number | undefined, data: ICard): Observable<ICard[]> { debugger
    return this.http.put<ICard[]>(`${this.url}/${id}`, data);
  }
}
