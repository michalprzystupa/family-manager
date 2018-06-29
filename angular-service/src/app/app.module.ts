import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { AppRoutingModule } from './/app-routing.module';
import { SearchFamilyComponent } from './search-family/search-family.component';
import { FamilyDetailComponent } from './family-detail/family-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationBarComponent,
    SearchFamilyComponent,
    FamilyDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
