import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterCardTable'
})
export class FilterCardTablePipe implements PipeTransform {

  transform(list: any[], value: string) {
    return value ? list.filter(item => item.applicant.name.includes(value.toUpperCase())) : list;
  }

}
