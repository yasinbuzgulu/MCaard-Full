import { Component, OnInit } from '@angular/core';
import {ICity} from "../../model/city.model";
import {IOpportunity} from "../../model/opportunity.model";
import {Router} from "@angular/router";
import {OpportunityService} from "../../service/opportunity.service";
import {HttpResponse} from "@angular/common/http";
import Swal from "sweetalert2";

@Component({
  selector: 'app-list-opportunity',
  templateUrl: './list-opportunity.component.html',
  styleUrls: ['./list-opportunity.component.css']
})
export class ListOpportunityComponent implements OnInit {
  opportunityData: IOpportunity[] | null | undefined;
  name: string | undefined;

  constructor(private router: Router, private opportunityService:OpportunityService) { }

  ngOnInit(): void {
    this.opportunityService.getAllOpportunities().subscribe((allData: HttpResponse<IOpportunity[]>) => {
      this.opportunityData = allData.body;
    });
  }

  deleteOpportunity(opportunityId: number) {
    this.opportunityService.deleteOpportunity(opportunityId).subscribe((result) => {
      this.router.navigateByUrl('/opportunities', {skipLocationChange: true}).then(() => {
        this.router.navigate(['ListOpportunityComponent']);
      });
    });
    Swal.fire('Successful', 'Opportunity is deleted!', 'success');
  }

}
