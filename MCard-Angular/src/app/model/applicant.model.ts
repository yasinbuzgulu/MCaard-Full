/**
 * Applicsnt ın interface modeli
 */
export interface IApplicant {
  id? : number;
  name? : string;
  surname? : string;
  birthDate? : string;
  citizenNumber? : number;
  typeBasedOnAge? : string;
  typeBasedOnEducation? : string;
}
