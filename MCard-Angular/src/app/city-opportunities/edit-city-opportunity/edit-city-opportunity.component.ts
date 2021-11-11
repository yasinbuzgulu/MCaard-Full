import { Component, OnInit } from '@angular/core';
import {ICard} from "../../model/card.model";
import {ICityOpportunity} from "../../model/city-opportunity.model";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {ApplicantService} from "../../service/applicant.service";
import {CityOpportunityService} from "../../service/city-opportunity.service";
import {CityService} from "../../service/city.service";
import {HttpResponse} from "@angular/common/http";
import {ICity} from "../../model/city.model";
import {OpportunityService} from "../../service/opportunity.service";
import {IOpportunity} from "../../model/opportunity.model";
import Swal from "sweetalert2";

@Component({
  selector: 'app-edit-city-opportunity',
  templateUrl: './edit-city-opportunity.component.html',
  styleUrls: ['./edit-city-opportunity.component.css']
})
export class EditCityOpportunityComponent implements OnInit {
  cityOpportunity: ICityOpportunity | undefined;
  myCityId: number | undefined;
  myOpportunityId: number | undefined;
  cities: ICity[] | null | undefined;
  opportunities: IOpportunity[] | null | undefined;

  constructor(private activatedRoute: ActivatedRoute,
              private location: Location, private opportunityService: OpportunityService,
              private cityOpportunityService: CityOpportunityService,
              private cityService: CityService) { }

  ngOnInit(): void {
    const id = parseInt(this.activatedRoute.snapshot.paramMap.get('id')!, 10);
    this.cityOpportunityService.getOpportunityById(id).subscribe(cityOpportunity => {
      this.cityOpportunity = cityOpportunity;
      this.myCityId = cityOpportunity.city?.id;
      this.myOpportunityId = cityOpportunity.opportunity?.id;
    });
    this.fetchAllCities();
    this.fetchAllOpportunities()
  }

  fetchAllCities() {
    this.cityService.getAllCities().subscribe((allCities: HttpResponse<ICity[]>) => {
      this.cities = allCities.body;
    });
  }

  fetchAllOpportunities() {
    this.opportunityService.getAllOpportunities().subscribe((opportunities: HttpResponse<IOpportunity[]>) => {
      this.opportunities = opportunities.body;
    });
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.cityOpportunity) {

      this.cityOpportunityService.updateOpportunityData(this.cityOpportunity.id, this.cityOpportunity)
        .subscribe(() => this.goBack());
    }
    Swal.fire('Successful', 'City & Opportunity is edited!', 'success');
  }
}
