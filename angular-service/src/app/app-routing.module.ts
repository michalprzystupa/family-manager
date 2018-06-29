import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule }    from '@angular/common/http';

import { SearchFamilyComponent } from './search-family/search-family.component';
import { FamilyDetailComponent } from './family-detail/family-detail.component';

const routes: Routes = [
    { path: '', redirectTo: '/search', pathMatch: 'full' },
    { path: 'search', component: SearchFamilyComponent },
    { path: 'detail/:id', component: FamilyDetailComponent }
];

@NgModule({
  exports: [ RouterModule ],
  imports: [ RouterModule.forRoot(routes),
    HttpClientModule ]
})
export class AppRoutingModule { }