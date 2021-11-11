import { Component, OnInit } from '@angular/core';
import {ICity} from "../../model/city.model";
import {ICityOpportunity} from "../../model/city-opportunity.model";
import {Router} from "@angular/router";
import {CityOpportunityService} from "../../service/city-opportunity.service";
import {HttpResponse} from "@angular/common/http";
import Swal from "sweetalert2";

@Component({
  selector: 'app-list-city-opportunity',
  templateUrl: './list-city-opportunity.component.html',
  styleUrls: ['./list-city-opportunity.component.css']
})
export class ListCityOpportunityComponent implements OnInit {
  cityOpportunities: ICityOpportunity[] | null | undefined;
  cityName: string | undefined;
  name: string | undefined;

  constructor(private  router:Router, private cityOpportunityService:CityOpportunityService) { }

  ngOnInit(): void {
    this.cityOpportunityService.getAllCityOpportunities().subscribe((allData: HttpResponse<ICityOpportunity[]>) => {
      this.cityOpportunities = allData.body;
    });
  }

  deleteCityOpportunity(cityOpportunity_id: number) {
    this.cityOpportunityService.deleteCityOpportunity(cityOpportunity_id).subscribe((result) => {
      this.router.navigateByUrl('/city-opportunities', {skipLocationChange: true}).then(() => {
        this.router.navigate(['ListCityOpportunityComponent']);
      });
    });
    Swal.fire('Successful', 'City & Opportunity is deleted!', 'success');
  }

}
