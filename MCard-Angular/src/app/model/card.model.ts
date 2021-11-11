import {IApplicant} from "./applicant.model";
import {ICityOpportunity} from "./city-opportunity.model";

export interface ICard {
  id : number;
  price : number;
  expiryDate : string;
  applicant : IApplicant;
  cityOpportunity : ICityOpportunity [];
  cardOpportunityYear : number;

}
