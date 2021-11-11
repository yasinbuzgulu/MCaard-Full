import {Injectable} from '@angular/core';
import {AbstractControl, ValidatorFn} from "@angular/forms";
import {ApplicantService} from "../service/applicant.service";
import {HttpResponse} from "@angular/common/http";
import {IApplicant} from "../model/applicant.model";
import Swal from "sweetalert2";
import {CityService} from "../service/city.service";
import {OpportunityService} from "../service/opportunity.service";
import {CityOpportunityService} from "../service/city-opportunity.service";
import {ICity} from "../model/city.model";
import {IOpportunity} from "../model/opportunity.model";
import {ICityOpportunity} from "../model/city-opportunity.model";

@Injectable({
  providedIn: 'root'
})
export class CustomValidationService {

  applicantData: IApplicant[] | null = [];
  cityData: ICity[] | null = [];
  opportunityData: IOpportunity[] | null = [];
  cityOpportunityData: ICityOpportunity[] | null = [];
  opportunitiesBaseOnCity: ICityOpportunity[] | undefined;
  cityOpportunities: ICityOpportunity[] | null = [];

  constructor(private applicantService: ApplicantService, private cityService: CityService,
              private opportunityService: OpportunityService, private cityOpportunityService: CityOpportunityService) {
  }

  validateCitizenshipNumber(): ValidatorFn {

    return (control: AbstractControl) => {
      this.applicantService.getAllApplicants().subscribe((allData: HttpResponse<IApplicant[]>) => {
        this.applicantData = allData.body;
      });
      ////////////////////////////T.C Number validation//////////////////////////////////////////////
      let i;
      let isValid = control.value % 2 === 0; // tek sayı olamaz
      let isValidLength = control.value.toString().length === 11;  // 11 hane olmalı
      let totalX = 0;
      for (i = 0; i < 10; i++) {  // ilk 10 hane toplanır
        totalX += Number(control.value.toString().substr(i, 1));
      }
      // ilk 10 hanenin toplamının son hanesi ile T.C numarasının son hanesi aynı olmalıdır.
      const isRuleX = totalX % 10 == control.value.toString().substr(10, 1);

      let totalY1 = 0;
      let totalY2 = 0;
      for (i = 0; i < 10; i += 2) {
        totalY1 += Number(control.value.toString().substr(i, 1)); // ilk 10 haneden çiftleri topla
      }
      for (i = 1; i < 10; i += 2) {
        totalY2 += Number(control.value.toString().substr(i, 1)); // ilk 10 haneden tekleri topla
      }
      //  ilk10 haneden çifler toplamının 7 katından, tekler toplamı çıkarıldığında
      //  çıkan sonucun son hanesi ile T.C numarasının 10. hanesi aynı olmalıdır.
      const isRuleY = ((totalY1 * 7) - totalY2) % 10 == control.value.toString().substr(9, 0);

      let isExist; // daha önce girilmiş bir T.C mi kontrolü
      if (this.applicantData?.some(e => e.citizenNumber === control.value)) {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Entered citizenship number is used before!',
          footer: '<a> Please enter new number or contact with administrator</a>'
        });
        isExist = false;
      } else {
        isExist = true;
      }

      if (isValid && isValidLength && isRuleX && isRuleY && isExist) {
        return null;
      } else {
        return {'numericValueCheck': 'failed'};
      }
    }
  }

  validateBirthDate(): ValidatorFn {

    return (control: AbstractControl) => {
      this.applicantService.getAllApplicants().subscribe((allData: HttpResponse<IApplicant[]>) => {
        this.applicantData = allData.body;
      });
      let inputBirthDate = new Date(control.value.toString()); // girilen string doğum tarihi Date formatına çevrilir
      let currentDate = new Date(); // anlık date alınır
      let time = currentDate.getTime() - inputBirthDate.getTime(); // zaman farkı alınır
      let days = time / (1000 * 3600 * 24); // days 0 dan küçükse girilen tarih anlık tarihi aşmıştır

      let yearDifference = currentDate.getFullYear() - inputBirthDate.getFullYear();// yıl farkı alınır
      let yearLimit = 150;

      if ((days > 0) && (yearDifference < yearLimit)) {
        return null;
      } else {
        return {'numericValueCheck': 'failed'};
      }
    }
  }

  validateCity(): ValidatorFn {

    return (control: AbstractControl) => {
      this.cityService.getAllCities().subscribe((allData: HttpResponse<ICity[]>) => {
        this.cityData = allData.body;
      });

      let isExist = false; // daha önce girilmiş bir şehir mi kontrolü
      if (this.cityData?.some(e => e.cityName === control.value.toUpperCase())) {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Entered city is used before!',
          footer: '<a> Please enter new city or contact with administrator</a>'
        });
        isExist = false;
      } else {
        isExist = true;
      }

      if (isExist) {
        return null;
      } else {
        return {'numericValueCheck': 'failed'};
      }
    }
  }

  validateOpportunity(): ValidatorFn {

    return (control: AbstractControl) => {
      this.opportunityService.getAllOpportunities().subscribe((allData: HttpResponse<IOpportunity[]>) => {
        this.opportunityData = allData.body;
      });

      let isExist = false; // daha önce girilmiş bir olanak mı kontrolü
      if (this.opportunityData?.some(e => e.name === control.value.toUpperCase())) {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Entered opportunity is used before!',
          footer: '<a> Please enter new opportunity or contact with administrator</a>'
        });
        isExist = false;
      } else {
        isExist = true;
      }

      if (isExist) {
        return null;
      } else {
        return {'numericValueCheck': 'failed'};
      }
    }
  }

  validateCityOpportunity(): ValidatorFn {
    return (control: AbstractControl) => {
      this.cityOpportunityService.getAllCityOpportunities().subscribe((allData: HttpResponse<IOpportunity[]>) => {
        this.cityOpportunityData = allData.body;
      });

      let isExist = false; // daha önce girilmiş bir şehir mi kontrolü
      if (this.cityOpportunityData?.some(e => e.opportunity?.name === control.value.name)) {
        if (this.cityOpportunityData?.some(is => is.city?.cityName === this.validateCityOpportunityCityName().toString())) {
          alert("varmısşsşsşs");
        }
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Wubba lubba dub dub!',
          footer: '<a> Please enter new city or contact with administrator</a>'
        });
        isExist = false;
      } else {
        isExist = true;
      }

      if (isExist) {
        return null;
      } else {
        return {'numericValueCheck': 'failed'};
      }
    }
  }

  validateCityOpportunityCityName(): ValidatorFn {
    return (control: AbstractControl) => {
      // this.cityOpportunityService.getAllCityOpportunities().subscribe((allData: HttpResponse<IOpportunity[]>) => {
      //   this.cityOpportunityData = allData.body;
      // });

      return control.value.cityName;
      // let isExist = false; // daha önce girilmiş bir şehir mi kontrolü
      // if (this.cityOpportunityData?.some(e => e.city?.cityName === control.value.cityName)) {
      //   // Swal.fire({
      //   //   icon: 'error',
      //   //   title: 'Oops...',
      //   //   text: 'Wubba lubba dub dub!',
      //   //   footer: '<a> Please enter new city or contact with administrator</a>'
      //   // });
      //   isExist = false;
      // } else {
      //   isExist = true;
      // }
      // return control.value.id;
      // // if (isExist) {
      // //   return null;
      // // } else {
      // //   return {'numericValueCheck': 'failed'};
      // // }
    }
  }

  checkExistence(cityName: any, opportunityName: any) {
    this.cityOpportunityService.getAllCityOpportunities().subscribe((allData: HttpResponse<IOpportunity[]>) => {
      this.cityOpportunityData = allData.body;
      let isExist = false; // daha önce girilmiş bir şehir mi kontrolü
      if (this.cityOpportunityData?.some(e => e.city?.cityName === cityName) &&
        this.cityOpportunityData?.some(opp => opp.opportunity?.name === opportunityName) ) {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Wubba lubba dub dub!',
          footer: '<a> Please enter new city or contact with administrator</a>'
        });
          isExist = false;
        } else {
          isExist = true;
        }
        if (isExist) {
          return null;
        } else {
          return {'numericValueCheck': 'failed'};
          alert("aşlsddişsa");
        }

    });






  }
}
