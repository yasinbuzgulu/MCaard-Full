import {Component, OnInit} from '@angular/core';
import {ICity} from "../../model/city.model";
import {FormGroup} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {CityService} from "../../service/city.service";
import Swal from "sweetalert2";
import {Location} from "@angular/common";
import {ICityOpportunity} from "../../model/city-opportunity.model";
import {HttpResponse} from "@angular/common/http";
import {CityOpportunityService} from "../../service/city-opportunity.service";

@Component({
  selector: 'app-edit-city',
  templateUrl: './edit-city.component.html',
  styleUrls: ['./edit-city.component.css']
})
export class EditCityComponent implements OnInit {
  city: ICity = {};
  cityForm!: FormGroup;
  opportunitiesBaseOnCity: ICityOpportunity[] | undefined = [];
  cityOpportunities: ICityOpportunity[] | null = [];

  constructor(private activatedRoute: ActivatedRoute, private cityService: CityService, private location: Location,
              private cityOpportunityService:CityOpportunityService) {
  }

  ngOnInit(): void {
    const id = parseInt(this.activatedRoute.snapshot.paramMap.get('id')!, 10);
    this.cityService.getCityById(id).subscribe(city => this.city = city);
    console.log(this.city.cityName?.length);
    this.fetchAllCityOpportunities();
  }

  get cityName() {
    return this.cityForm.get('cityName')!;
  }

  fetchAllCityOpportunities() {
    this.cityOpportunityService.getAllCityOpportunities().subscribe((res: HttpResponse<ICityOpportunity[]>) => {
      this.cityOpportunities = res.body;
    });
  }

  goBack(): void {
    this.location.back();
  }

  changeInCityOpportunity(value: string | undefined) {
    this.opportunitiesBaseOnCity = this.cityOpportunities?.filter(co => co.city?.cityName === value);
  }

  save(): void {
    if (this.city) {
      this.changeInCityOpportunity(this.city.cityName?.toUpperCase());
      this.city.cityName = this.city.cityName?.toUpperCase();
      this.cityService.updateCityData(this.city.id, this.city)
        .subscribe(() => this.goBack());
    }
    Swal.fire('Successful', 'City is edited!', 'success');

  }
}
