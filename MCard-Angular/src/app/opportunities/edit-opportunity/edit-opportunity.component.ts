import {Component, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {ICityOpportunity} from "../../model/city-opportunity.model";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {CityOpportunityService} from "../../service/city-opportunity.service";
import {HttpResponse} from "@angular/common/http";
import Swal from "sweetalert2";
import {IOpportunity} from "../../model/opportunity.model";
import {OpportunityService} from "../../service/opportunity.service";

@Component({
  selector: 'app-edit-opportunity',
  templateUrl: './edit-opportunity.component.html',
  styleUrls: ['./edit-opportunity.component.css']
})
export class EditOpportunityComponent implements OnInit {
  opportunity: IOpportunity = {};
  opportunityForm!: FormGroup;
  opportunitiesBaseOnOpportunity: ICityOpportunity[] | undefined = [];
  cityOpportunities: ICityOpportunity[] | null = [];

  constructor(private activatedRoute: ActivatedRoute, private opportunityService: OpportunityService, private location: Location,
              private cityOpportunityService: CityOpportunityService) {
  }

  ngOnInit(): void {
    const id = parseInt(this.activatedRoute.snapshot.paramMap.get('id')!, 10);
    this.opportunityService.getOpportunityById(id).subscribe(opportunity => this.opportunity = opportunity);
    this.fetchAllCityOpportunities();
  }

  get name() {
    return this.opportunityForm.get('name')!;
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
    this.opportunitiesBaseOnOpportunity = this.cityOpportunities?.filter(co => co.opportunity?.name === value);
  }

  save(): void {
    if (this.opportunity) {
      this.changeInCityOpportunity(this.opportunity.name?.toUpperCase());
      this.opportunity.name = this.opportunity.name?.toUpperCase();
      this.opportunityService.updateOpportunityData(this.opportunity.id, this.opportunity)
        .subscribe(() => this.goBack());
    }
    Swal.fire('Successful', 'Opportunity is edited!', 'success');

  }
}
