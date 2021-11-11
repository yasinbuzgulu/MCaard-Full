import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterCityOpportunityTable'
})
export class FilterCityOpportunityTablePipe implements PipeTransform {

  transform(list: any[], value: string) {
    return value ? list.filter(item => item.city.cityName.includes(value.toUpperCase()) | item.opportunity.name.includes(value.toUpperCase())  ) : list;
  }

}
