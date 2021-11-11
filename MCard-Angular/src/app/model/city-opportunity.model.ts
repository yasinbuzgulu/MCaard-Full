import {ICity} from "./city.model";
import {IOpportunity} from "./opportunity.model";

export interface ICityOpportunity {
  id? : number;
  city? : ICity;
  opportunity?: IOpportunity;
  perYearPrice?: number;
  // topLimitYearValue: number;
}
