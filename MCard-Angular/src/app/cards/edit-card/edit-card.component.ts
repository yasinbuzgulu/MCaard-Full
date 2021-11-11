import {Component, OnInit} from '@angular/core';
import {IApplicant} from "../../model/applicant.model";
import {ApplicantService} from "../../service/applicant.service";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {ICard} from "../../model/card.model";
import {CardService} from "../../service/card.service";
import {HttpResponse} from "@angular/common/http";
import {ICity} from "../../model/city.model";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {ICityOpportunity} from "../../model/city-opportunity.model";
import {CityOpportunityService} from "../../service/city-opportunity.service";
import {CityService} from "../../service/city.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-edit-card',
  templateUrl: './edit-card.component.html',
  styleUrls: ['./edit-card.component.css']
})
/**
 * Card güncelleme işlemlerinin yapıldığı sınıf
 */
export class EditCardComponent implements OnInit {
  card: ICard | undefined;
  applicants: IApplicant[] | null | undefined;
  cities: ICity[] | null | undefined;
  myOpportunity: (number | undefined)[] | undefined;
  myCityId: number | undefined;
  opportunitiesBaseOnCity: ICityOpportunity[] | undefined = [];
  cityOpportunities: ICityOpportunity[] | null = [];
  cardForm!: FormGroup;
  opportunity = new FormControl(); //You might have already
  form: FormGroup | undefined;

  constructor(private cardService: CardService, private activatedRoute: ActivatedRoute,
              private location: Location, private applicantService: ApplicantService,
              private cityOpportunityService: CityOpportunityService,
              private cityService: CityService, private fb: FormBuilder) {
    this.form = this.fb.group({
      checkArray: this.fb.array([])
    })
  }

  /**
   * Başlangıçta seçili kart bilgilerinin ayarlanma işlemi ve farklı seçenekler seçilebilmesi için tüm verilerin çekilme yapılır.
   */
  ngOnInit(): void {

    const id = parseInt(this.activatedRoute.snapshot.paramMap.get('id')!, 10);
    this.cardService.getCardById(id).subscribe(card => {
      this.card = card;
      this.myCityId = card.cityOpportunity.map(c => c.city?.id)[0];
      this.myOpportunity = card.cityOpportunity.map(c => c.id);
      this.arrangeCity(card.cityOpportunity.map(city => city.city?.id)[0]);
      this.opportunity.setValue(card.cityOpportunity);
    });

    this.fetchAllApplicants();
    this.fetchAllCityOpportunities();
    this.fetchAllCities();

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

  arrangeCity(value: number | undefined) {
    this.opportunitiesBaseOnCity = this.cityOpportunities?.filter(co => co.city?.id === value);
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

  goBack(): void {
    this.location.back();
  }

  save(): void {
    // this.card?.cityOpportunity = this.cardForm.get('opportunities')?.value;
    if (this.card) {

      debugger;
      this.cardService.updateCardData(this.card.id, this.card)
        .subscribe(() => this.goBack());
    }
    Swal.fire('Successful', 'Card is edited!', 'success');
  }

}
