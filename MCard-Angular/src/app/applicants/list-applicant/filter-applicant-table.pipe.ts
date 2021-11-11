import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterApplicantTable'
})
export class FilterApplicantTablePipe implements PipeTransform {

  transform(list: any[], value: string) {
    return value ? list.filter(item => item.name.includes(value.toUpperCase()) | item.surname.includes(value.toUpperCase())
       ) : list;
  }
}
