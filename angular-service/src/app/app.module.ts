import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';

import { AppComponent } from './app.component';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { AppRoutingModule } from './/app-routing.module';
import { SearchFamilyComponent } from './search-family/search-family.component';
import { FamilyDetailComponent } from './family-detail/family-detail.component';
import { AddFatherComponent } from './add-father/add-father.component';
import { AddChildComponent } from './add-child/add-child.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationBarComponent,
    SearchFamilyComponent,
    FamilyDetailComponent,
    AddFatherComponent,
    AddChildComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
