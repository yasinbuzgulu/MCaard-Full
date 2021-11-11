import {Component, OnInit} from '@angular/core';
import {Location} from '@angular/common';
import {ApplicantService} from '../../service/applicant.service';
import {ActivatedRoute} from '@angular/router';
import {IApplicant} from "../../model/applicant.model";
import Swal from "sweetalert2";

@Component({
  selector: 'app-edit-applicant',
  templateUrl: './edit-applicant.component.html',
  styleUrls: ['./edit-applicant.component.css']
})
/**
 * Applicant güncelleme işlemlerinin yapıldığı sınıf
 */
export class EditApplicantComponent implements OnInit {
  applicant: IApplicant = {};

  constructor(private applicantService: ApplicantService, private activatedRoute: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    const id = parseInt(this.activatedRoute.snapshot.paramMap.get('id')!, 10);
    this.applicantService.getApplicantById(id).subscribe(applicant => this.applicant = applicant);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.applicant) {
      this.applicantService.updateApplicantData(this.applicant.id, this.applicant)
        .subscribe(() => this.goBack());
    }
    Swal.fire('Successful', 'Applicant is edited!', 'success');

  }

}
