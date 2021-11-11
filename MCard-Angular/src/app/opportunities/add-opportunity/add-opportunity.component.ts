import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import Swal from "sweetalert2";
import {OpportunityService} from "../../service/opportunity.service";
import {IOpportunity} from "../../model/opportunity.model";
import {HttpResponse} from "@angular/common/http";
import {CustomValidationService} from "../../Validation/custom-validation.service";

@Component({
  selector: 'app-add-opportunity',
  templateUrl: './add-opportunity.component.html',
  styleUrls: ['./add-opportunity.component.css']
})
export class AddOpportunityComponent implements OnInit {
  opportunityForm!: FormGroup;
  opportunity = {
    name: ''
  };
  opportunities: IOpportunity[] | null | undefined;

  constructor(private opportunityService: OpportunityService, private customValidationService:CustomValidationService) {
  }

  ngOnInit(): void {
    this.opportunityForm = new FormGroup({
      name: new FormControl(this.opportunity.name, [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(43),
        Validators.pattern('^[A-Za-z]+$'),
        this.customValidationService.validateOpportunity(),
      ])
    });
  }

  get name() {
    return this.opportunityForm.get('name')!;
  }

  checkExistence() {
    this.opportunityService.getAllOpportunities().subscribe((allData: HttpResponse<IOpportunity[]>) => {
      this.opportunities = allData.body;
    });
  }

  saveData() {
    this.checkExistence();
    this.opportunityForm.value.name = this.opportunityForm.value.name.toUpperCase();
    this.opportunityService.saveOpportunityData(this.opportunityForm.value).subscribe((result) => {
      this.opportunityForm.reset({});
    });
    Swal.fire('Successful', 'Opportunity is saved!', 'success');
  }

}
