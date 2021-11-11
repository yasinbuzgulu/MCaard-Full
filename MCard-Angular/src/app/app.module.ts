import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthInterceptor} from "./helper/auth.interceptor";
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AddApplicantComponent} from './applicants/add-applicant/add-applicant.component';
import {EditApplicantComponent} from './applicants/edit-applicant/edit-applicant.component';
import {ListApplicantComponent} from './applicants/list-applicant/list-applicant.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {MatInputModule} from "@angular/material/input";
import {HomeComponent} from './home/home.component';
import {MatMenuModule} from "@angular/material/menu";
import {MatButtonModule} from "@angular/material/button";
import {AddCityOpportunityComponent} from './city-opportunities/add-city-opportunity/add-city-opportunity.component';
import {ListCardComponent} from './cards/list-card/list-card.component';
import {AddCardComponent} from './cards/add-card/add-card.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {EditCardComponent} from './cards/edit-card/edit-card.component';
import {MatIconModule} from "@angular/material/icon";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {ProfileComponent} from './profile/profile.component';
import {BoardUserComponent} from './list-user/board-user.component';
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import { AddCityComponent } from './cities/add-city/add-city.component';
import { ListCityComponent } from './cities/list-city/list-city.component';
import { EditCityComponent } from './cities/edit-city/edit-city.component';
import { ListOpportunityComponent } from './opportunities/list-opportunity/list-opportunity.component';
import { AddOpportunityComponent } from './opportunities/add-opportunity/add-opportunity.component';
import { ListCityOpportunityComponent } from './city-opportunities/list-city-opportunity/list-city-opportunity.component';
import { EditOpportunityComponent } from './opportunities/edit-opportunity/edit-opportunity.component';
import { EditCityOpportunityComponent } from './city-opportunities/edit-city-opportunity/edit-city-opportunity.component';
import { FilterCityTablePipe } from './cities/list-city/filter-city-table.pipe';
import { FilterCardTablePipe } from './cards/list-card/filter-card-table.pipe';
import { FilterOpportunityTablePipe } from './opportunities/list-opportunity/filter-opportunity-table.pipe';
import { FilterCityOpportunityTablePipe } from './city-opportunities/list-city-opportunity/filter-city-opportunity-table.pipe';
import { FilterApplicantTablePipe } from './applicants/list-applicant/filter-applicant-table.pipe';

@NgModule({
  declarations: [
    AppComponent,
    AddApplicantComponent,
    EditApplicantComponent,
    ListApplicantComponent,
    HomeComponent,
    AddCityOpportunityComponent,
    ListCardComponent,
    AddCardComponent,
    EditCardComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    BoardUserComponent,
    AddCityComponent,
    ListCityComponent,
    EditCityComponent,
    ListOpportunityComponent,
    AddOpportunityComponent,
    ListCityOpportunityComponent,
    EditOpportunityComponent,
    EditCityOpportunityComponent,
    FilterCityTablePipe,
    FilterCardTablePipe,
    FilterOpportunityTablePipe,
    FilterCityOpportunityTablePipe,
    FilterApplicantTablePipe,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatInputModule,
    MatMenuModule,
    MatButtonModule,
    FormsModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatAutocompleteModule,
    MatButtonToggleModule

  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}

  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
