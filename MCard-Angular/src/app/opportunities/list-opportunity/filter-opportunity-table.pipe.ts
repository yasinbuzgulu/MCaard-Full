import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'filterOpportunityTable'
})
export class FilterOpportunityTablePipe implements PipeTransform {

  transform(list: any[], value: string) {
    return value ? list.filter(item => item.name.includes(value.toUpperCase())) : list;
  }

}
