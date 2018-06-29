import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule }    from '@angular/common/http';

import { SearchFamilyComponent } from './search-family/search-family.component';
import { AddFatherComponent } from './add-father/add-father.component';
import { AddChildComponent } from './add-child/add-child.component';
import { FamilyDetailComponent } from './family-detail/family-detail.component';

const routes: Routes = [
    { path: '', redirectTo: '/search', pathMatch: 'full' },
    { path: 'search', component: SearchFamilyComponent },
    { path: 'add-father', component: AddFatherComponent },
    { path: 'add-child', component: AddChildComponent },
    { path: 'detail/:id', component: FamilyDetailComponent }
];

@NgModule({
  exports: [ RouterModule ],
  imports: [ RouterModule.forRoot(routes),
    HttpClientModule ]
})
export class AppRoutingModule { }