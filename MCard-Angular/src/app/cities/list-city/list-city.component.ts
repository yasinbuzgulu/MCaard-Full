import {Component, OnInit} from '@angular/core';
import {ICity} from "../../model/city.model";
import Swal from "sweetalert2";
import {Router} from "@angular/router";
import {CityService} from "../../service/city.service";
import {HttpResponse} from "@angular/common/http";
import {ICityOpportunity} from "../../model/city-opportunity.model";
import {CityOpportunityService} from "../../service/city-opportunity.service";

@Component({
  selector: 'app-list-city',
  templateUrl: './list-city.component.html',
  styleUrls: ['./list-city.component.css']
})
export class ListCityComponent implements OnInit {
  cityData: ICity[] | null | undefined;
  cityName: string | undefined;
  cityOpportunities: ICityOpportunity[] | null | undefined;

  constructor(private router: Router, private cityService: CityService, private cityOpportunityService: CityOpportunityService) {
  }

  ngOnInit(): void {
    this.cityService.getAllCities().subscribe((allData: HttpResponse<ICity[]>) => {
      this.cityData = allData.body;
    });
    this.cityOpportunityService.getAllCityOpportunities().subscribe((allData: HttpResponse<ICityOpportunity[]>) => {
      this.cityOpportunities = allData.body;
    });
  }

  deleteCity(city_id: number) {
    this.cityService.deleteCity(city_id).subscribe((result) => {
      this.router.navigateByUrl('/cities', {skipLocationChange: true}).then(() => {
        this.router.navigate(['ListCityComponent']);
      });
    });
    Swal.fire('Successful', 'City is deleted!', 'success');
  }

}
