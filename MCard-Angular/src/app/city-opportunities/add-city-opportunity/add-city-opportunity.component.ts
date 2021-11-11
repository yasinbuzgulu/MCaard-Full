import {Component, OnInit} from '@angular/core';
import {ICity} from "../../model/city.model";
import {CityService} from "../../service/city.service";
import {OpportunityService} from "../../service/opportunity.service";
import {IOpportunity} from "../../model/opportunity.model";
import {CityOpportunityService} from "../../service/city-opportunity.service";
import {HttpResponse} from "@angular/common/http";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import Swal from "sweetalert2";
import {CustomValidationService} from "../../Validation/custom-validation.service";
import {ICityOpportunity} from "../../model/city-opportunity.model";

@Component({
  selector: 'app-add-city-opportunity',
  templateUrl: './add-city-opportunity.component.html',
  styleUrls: ['./add-city-opportunity.component.css']
})
export class AddCityOpportunityComponent implements OnInit {
  cityOpportunityForm!: FormGroup;
  cities: ICity[] | null = [];
  opportunities: IOpportunity[] | null = [];
  cityOpportunityData: { city: ICity; opportunity: IOpportunity; perYearPrice: number; } = {
    city: {}, opportunity: {}, perYearPrice: 0
  }
  cityOpportunities: ICityOpportunity[] | null = [];

  constructor(private cityService: CityService, private opportunityService: OpportunityService,
              private cityOpportunityService: CityOpportunityService, private customValidationService: CustomValidationService) {
  }


  ngOnInit(): void {

    this.fetchAllOpportunities();
    this.fetchAllCities();

    this.cityOpportunityForm = new FormGroup({
        city: new FormControl(this.cityOpportunityData.city.cityName, [
          Validators.required,
          // this.customValidationService.validateCityOpportunityCityName(),
        ]),
        opportunity: new FormControl(this.cityOpportunityData.opportunity.name, [
          Validators.required,
          // this.customValidationService.validateCityOpportunity(),
        ]),
        perYearPrice: new FormControl(this.cityOpportunityData.perYearPrice, [
          Validators.required,
          Validators.max(3000),
          Validators.min(0),
        ]),
      },
    );
  }

  fetchAllOpportunities() {
    this.opportunityService.getAllOpportunities().subscribe((res: HttpResponse<IOpportunity[]>) => {
      this.opportunities = res.body;
    });
  }

  fetchAllCities() {
    this.cityService.getAllCities().subscribe((allCities: HttpResponse<ICity[]>) => {
      this.cities = allCities.body;
    });
  }

  get city() {
    return this.cityOpportunityForm.get('city')!;
  }

  get opportunity() {
    return this.cityOpportunityForm.get('opportunity')!;
  }

  get perYearPrice() {
    return this.cityOpportunityForm.get('perYearPrice')!;
  }

  adjustData() {
    this.cityOpportunityData.city = this.cityOpportunityForm?.get('city')?.value;
    this.cityOpportunityData.opportunity = this.cityOpportunityForm.get('opportunity')?.value;
    this.cityOpportunityData.perYearPrice = this.cityOpportunityForm.get('perYearPrice')?.value;
  }

  saveData() {
    this.adjustData();
    // if (this.customValidationService.checkExistence(this.cityOpportunityData.city.cityName, this.cityOpportunityData.opportunity.name) === null) {
    //   alert("hehir varmışş");
    // }
    this.cityOpportunityService.saveCityOpportunityData(this.cityOpportunityData).subscribe((result) => {
      this.cityOpportunityForm.reset({});
    });
    Swal.fire('Successful', 'City&Opportunity is saved!', 'success');
  }


}
