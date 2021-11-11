import {Component, OnInit} from '@angular/core';
import {ApplicantService} from '../../service/applicant.service';
import {Router} from "@angular/router";
import {IApplicant} from "../../model/applicant.model";
import {HttpResponse} from "@angular/common/http";
import Swal from 'sweetalert2';

@Component({
  selector: 'app-list-applicant',
  templateUrl: './list-applicant.component.html',
  styleUrls: ['./list-applicant.component.css']
})
/**
 * Applicant listeme işleminin yapıldığı sınıf
 */
export class ListApplicantComponent implements OnInit {
  name: string | undefined;
  surname: string | undefined;

  constructor(private applicantService: ApplicantService, private router: Router) {
  }

  applicantData: IApplicant[] | null = [];

  ngOnInit(): void {
    this.applicantService.getAllApplicants().subscribe((allData: HttpResponse<IApplicant[]>) => {
      this.applicantData = allData.body;
    });
  }

  deleteApplicant(applicant_id: number) {
    this.applicantService.deleteApplicant(applicant_id).subscribe((result) => {
      this.router.navigateByUrl('/applicants', {skipLocationChange: true}).then(() => {
        this.router.navigate(['ListApplicantComponent']);
      });
    });
    Swal.fire('Successful', 'Applicant is deleted!', 'success');
  }
}
