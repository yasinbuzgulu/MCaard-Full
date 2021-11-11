import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AddApplicantComponent} from './applicants/add-applicant/add-applicant.component';
import {EditApplicantComponent} from './applicants/edit-applicant/edit-applicant.component';
import {ListApplicantComponent} from './applicants/list-applicant/list-applicant.component';
import {ListCardComponent} from "./cards/list-card/list-card.component";
import {AddCardComponent} from "./cards/add-card/add-card.component";
import {EditCardComponent} from "./cards/edit-card/edit-card.component";
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {ProfileComponent} from "./profile/profile.component";
import {BoardUserComponent} from "./list-user/board-user.component";
import {AddCityComponent} from "./cities/add-city/add-city.component";
import {ListCityComponent} from "./cities/list-city/list-city.component";
import {EditCityComponent} from "./cities/edit-city/edit-city.component";
import {AddOpportunityComponent} from "./opportunities/add-opportunity/add-opportunity.component";
import {ListOpportunityComponent} from "./opportunities/list-opportunity/list-opportunity.component";
import {AddCityOpportunityComponent} from "./city-opportunities/add-city-opportunity/add-city-opportunity.component";
import {ListCityOpportunityComponent} from "./city-opportunities/list-city-opportunity/list-city-opportunity.component";
import {EditOpportunityComponent} from "./opportunities/edit-opportunity/edit-opportunity.component";
import {EditCityOpportunityComponent} from "./city-opportunities/edit-city-opportunity/edit-city-opportunity.component";

const routes: Routes = [
  {
    path: 'applicant-addition',
    component: AddApplicantComponent
  },
  {
    path: 'applicant-edition/:id',
    component: EditApplicantComponent
  },
  {
    path: 'applicant-list',
    component: ListApplicantComponent
  },
  {
    path: 'card-list',
    component: ListCardComponent
  },
  {
    path: 'card-addition',
    component: AddCardComponent
  },
  {
    path: 'card-edition/:id',
    component: EditCardComponent
  },
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'users', component: BoardUserComponent},
  {path: 'applicant-addition', component: AddApplicantComponent},
  {path: 'applicant-list', component: ListApplicantComponent},
  {path: 'card-addition', component: AddCardComponent},
  {path: 'card-list', component: ListCardComponent},
  {path: 'city-addition', component: AddCityComponent},
  {path: 'city-list', component: ListCityComponent},
  {
    path: 'city-edition/:id',
    component: EditCityComponent
  },
  {path: 'opportunity-addition', component: AddOpportunityComponent},
  {path: 'opportunity-list', component: ListOpportunityComponent},
  {
    path: 'opportunity-edition/:id',
    component: EditOpportunityComponent
  },
  {path: 'cityOpportunity-addition', component: AddCityOpportunityComponent},
  {path: 'cityOpportunity-list', component: ListCityOpportunityComponent},
  {
    path: 'cityOpportunity-edition/:id',
    component: EditCityOpportunityComponent
  },
  {path: '', redirectTo: 'home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
