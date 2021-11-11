import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ApplicantService} from '../../service/applicant.service';
import {CustomValidationService} from "../../Validation/custom-validation.service";
import {IApplicant} from "../../model/applicant.model";
import Swal from "sweetalert2";

@Component({
  selector: 'app-add-applicant',
  templateUrl: './add-applicant.component.html',
  styleUrls: ['./add-applicant.component.css']
})
/**
 * Applicant ekleme işleminin yapıldığı sınıf
 */
export class AddApplicantComponent implements OnInit {
  applicant = {
    name: '', surname: '', birthDate: '', citizenNumber: 0,
    typeBasedOnAge: '',
    typeBasedOnEducation: ''
  };
  applicantForm!: FormGroup;
  applicants: IApplicant[] | null | undefined;

  constructor(private applicantService: ApplicantService, private customValidationService: CustomValidationService) {
  }

  /**
   * Applicant ekleme komponentinde ilk yapılacak kontrol işlemlerini içerir
   */
  ngOnInit(): void {

    this.applicantForm = new FormGroup({
        name: new FormControl(this.applicant.name, [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(43),
          Validators.pattern('^[A-Za-z]+$'),
        ]),
        surname: new FormControl(this.applicant.surname, [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(20),
          Validators.pattern('^[A-Za-z]+$')
        ]),
        birthDate: new FormControl(this.applicant.birthDate, [
          Validators.required,
          Validators.pattern('^([0-3]?[0-9])/([0-3]?[0-9])/((?:[0-9]{2})?[0-9]{2})$'),
          this.customValidationService.validateBirthDate(),
        ]),
        citizenNumber: new FormControl(this.applicant.citizenNumber, [
          Validators.required,
          Validators.min(0),
          this.customValidationService.validateCitizenshipNumber(),
        ]),
        typeBasedOnAge: new FormControl(this.applicant.typeBasedOnAge, [
          Validators.required,
        ]),
        typeBasedOnEducation: new FormControl(this.applicant.typeBasedOnAge, [
          Validators.required,
        ]),
      },
    );
  }

  get name() {
    return this.applicantForm.get('name')!;
  }

  get surname() {
    return this.applicantForm.get('surname')!;
  }

  get citizenNumber() {
    return this.applicantForm.get('citizenNumber')!;
  }

  get birthDate() {
    return this.applicantForm.get('birthDate')!;
  }

  get typeBasedOnAge() {
    return this.applicantForm.get('typeBasedOnAge')!;
  }

  get typeBasedOnEducation() {
    return this.applicantForm.get('typeBasedOnEducation')!;
  }

  // checkExistence() {
  //   this.applicantService.getAllApplicants().subscribe((res: HttpResponse<IApplicant[]>) => {
  //     this.applicants = res.body;
  //     console.log(this.applicants?.filter(cs => cs.citizenNumber === this.applicantForm.value.citizenNumber)
  //     );
  //   });
  // }

  adjustApplicant () {
    this.applicantForm.value.name = this.applicantForm.value.name.toUpperCase();
    this.applicantForm.value.surname = this.applicantForm.value.surname.toUpperCase();

  }

  saveData() {
    // this.checkExistence();
    this.adjustApplicant();
    this.applicantService.saveApplicantData(this.applicantForm.value).subscribe((result) => {
      this.applicantForm.reset({});
    });
    Swal.fire('Successful', 'Applicant is saved!', 'success');

  }

}
