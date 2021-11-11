import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'cityTableFilter'
})
export class FilterCityTablePipe implements PipeTransform {

  transform(list: any[], value: string) {
    return value ? list.filter(item => item.cityName.includes(value.toUpperCase())) : list;
  }
}
