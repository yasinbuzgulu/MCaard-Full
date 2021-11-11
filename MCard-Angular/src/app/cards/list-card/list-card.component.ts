import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {CardService} from "../../service/card.service";
import {ICard} from "../../model/card.model";
import {HttpResponse} from "@angular/common/http";
import Swal from "sweetalert2";

@Component({
  selector: 'app-list-card',
  templateUrl: './list-card.component.html',
  styleUrls: ['./list-card.component.css']
})
/**
 * Card listeleme işlemlerinin yapıldığı sınıf
 */
export class ListCardComponent implements OnInit {
  cardData: ICard[] | null = [];
  expiryDate: string | undefined;
  name: string | undefined;
  surname: string | undefined;
  definedYearList:number[] | undefined = [];
  priceList:any[] | undefined = [];
  totalFee:any[] | undefined = [];

  constructor(private cardService: CardService, private router: Router) {
  }

  ngOnInit(): void {
    this.cardService.getAllCards().subscribe((allData: HttpResponse<ICard[]>) => {
      this.cardData = allData.body;
      this.calculatePrice(this.cardData);
    });

  }

  calculatePrice(cardData: any[] | null) {
    this.definedYearList = cardData?.map(year => year.cardOpportunityYear);
    this.priceList = cardData?.map(price => price.cityOpportunity.map((pr: { perYearPrice: any; }) => pr.perYearPrice));
    //console.log(this.definedYearList);
   // console.log(this.priceList);
    // @ts-ignore
    for (let i = 0; i < this.priceList?.length; i++) {
        // @ts-ignore
      this.totalFee?.push((this.priceList[i].reduce((a: any, b: any) => a +b ,0) * this.definedYearList[i]));
    }
   // console.log(this.totalFee);
  }

  deleteCard(card_id: any) {
    this.cardService.deleteCard(card_id).subscribe((result) => {
      this.ngOnInit();
    });
    Swal.fire('Successful', 'Card is deleted!', 'success');
  }

}
