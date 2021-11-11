import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CityService} from "../../service/city.service";
import {ICity} from "../../model/city.model";
import Swal from "sweetalert2";
import {HttpResponse} from "@angular/common/http";
import {CustomValidationService} from "../../Validation/custom-validation.service";

@Component({
  selector: 'app-add-city',
  templateUrl: './add-city.component.html',
  styleUrls: ['./add-city.component.css']
})
export class AddCityComponent implements OnInit {
  cityForm!: FormGroup;
  city = {
    cityName: ''
  };
  cities: ICity[] | null | undefined;

  constructor(private cityService: CityService, private customValidationService:CustomValidationService) {
  }

  ngOnInit(): void {
    this.cityForm = new FormGroup({
      cityName: new FormControl(this.city.cityName, [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(43),
        Validators.pattern('^[A-Za-z]+$'),
        this.customValidationService.validateCity(),
      ])
    });
  }

  get cityName() {
    return this.cityForm.get('cityName')!;
  }

  checkExistence() {
    this.cityService.getAllCities().subscribe((allData: HttpResponse<ICity[]>) => {
      this.cities = allData.body;
    });
  }

  saveData() {
    this.checkExistence();
    this.cityForm.value.cityName = this.cityForm.value.cityName.toUpperCase();
    this.cityService.saveCityData(this.cityForm.value).subscribe((result) => {
      this.cityForm.reset({});
    });
    Swal.fire('Successful', 'City is saved!', 'success');

  }
}

