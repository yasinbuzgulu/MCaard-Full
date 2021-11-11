import {Component, OnInit} from '@angular/core';
import {CardService} from "../../service/card.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {IApplicant} from "../../model/applicant.model";
import {ApplicantService} from "../../service/applicant.service";
import {HttpResponse} from "@angular/common/http";
import {CityOpportunityService} from "../../service/city-opportunity.service";
import {ICityOpportunity} from "../../model/city-opportunity.model";
import {CityService} from "../../service/city.service";
import {ICity} from "../../model/city.model";
import {DatePipe} from "@angular/common";
import Swal from "sweetalert2";

@Component({
  selector: 'app-add-card',
  templateUrl: './add-card.component.html',
  styleUrls: ['./add-card.component.css'],
  providers: [DatePipe]
})
/**
 * Card ekleme işleminin yapıldığı sınıf
 */
export class AddCardComponent implements OnInit {
  /**
   *  Tüm verilerin çekilme işleminde kullanılır
   */
  applicants: IApplicant[] | null = [];
  cityOpportunities: ICityOpportunity[] | null = [];
  opportunitiesBaseOnCity: ICityOpportunity[] | undefined = [];
  cities: ICity[] | null = [];
  card: {
    expiryDate: string; cityOpportunity: ICityOpportunity[]; price: number;
    cardOpportunityYear: number; applicant: IApplicant
  } = {
    applicant: {}, price: 0, cityOpportunity: [],
    expiryDate: "", cardOpportunityYear: 0
  };

  cardForm!: FormGroup;

  constructor(private cardService: CardService, private applicantService: ApplicantService,
              private cityOpportunityService: CityOpportunityService,
              private cityService: CityService, private datePipe: DatePipe
  ) {
  }

  /**
   * Selection box ta listelenmesi için tüm verilerin başlangıçta çekilme işlemleri ve kontrol işlemleri
   */
  ngOnInit(): void {
    this.fetchAllApplicants();
    this.fetchAllCityOpportunities();
    this.fetchAllCities();

    this.cardForm = new FormGroup({
        applicant: new FormControl(this.card.applicant.name, Validators.required ),
        cardOpportunityYear: new FormControl(this.card.cardOpportunityYear, [
          Validators.required,
          Validators.minLength(1),
          Validators.min(0)
        ]),
        price: new FormControl(this.card.price, [
          Validators.required,
          Validators.max(3000),
          Validators.min(0),
        ]),
        opportunities: new FormControl(this.card.cityOpportunity, [
          Validators.required,
        ]),
        cityName: new FormControl([
          Validators.required,
        ]),
      },
    );
  }

  fetchAllApplicants() {
    this.applicantService.getAllApplicants().subscribe((res: HttpResponse<IApplicant[]>) => {
      this.applicants = res.body;
    });
  }

  fetchAllCityOpportunities() {
    this.cityOpportunityService.getAllCityOpportunities().subscribe((res: HttpResponse<ICityOpportunity[]>) => {
      this.cityOpportunities = res.body;
    });
  }

  fetchAllCities() {
    this.cityService.getAllCities().subscribe((allCities: HttpResponse<ICity[]>) => {
      this.cities = allCities.body;
    });
  }

  /**
   * Olanakların seçilen şehre göre listelenmesi için filtreleme/kontrol işleminin yapıldığı metod
   * @param value: selecttion box ta seçilen şehir
   */
  checkCity(value: ICity) {
    this.opportunitiesBaseOnCity = this.cityOpportunities?.filter(co => co.city?.cityName === value.cityName);
  }

  get applicant() {
    return this.cardForm.get('applicant')!;
  }

  get cityName() {
    return this.cardForm.get('cityName')!;
  }

  get opportunities() {
    return this.cardForm.get('opportunities')!;
  }

  get price() {
    return this.cardForm.get('price')!;
  }

  get cardOpportunityYear() {
    return this.cardForm.get('cardOpportunityYear')!;
  }

  /**
   * Son kullanma tarihi ayarlama
   */
  setExpiryDate() {
    let date = new Date();
    let additionYear = 5;
    date.setFullYear(date.getFullYear() + additionYear);
    return <string>this.datePipe.transform(date, 'mm/dd/yyyy HH:mm:ss');
  }

  adjustData() {
    this.card.applicant = this.cardForm?.get('applicant')?.value;
    this.card.price = this.cardForm.get('price')?.value;
    this.card.cardOpportunityYear = this.cardForm.get('cardOpportunityYear')?.value;
    this.card.cityOpportunity = this.cardForm.get('opportunities')?.value;
    this.card.expiryDate = this.setExpiryDate();
  }


  saveData() {
    this.adjustData();
    this.cardService.saveCardData(this.card).subscribe((result) => {
      this.cardForm.reset({});
    });
    Swal.fire('Successful', 'Card is saved!', 'success');

  }

}



