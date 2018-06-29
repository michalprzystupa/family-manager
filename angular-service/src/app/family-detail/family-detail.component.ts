import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { DetailsService }  from '../details.service';
import { Family } from '../Family';

@Component({
  selector: 'app-family-detail',
  templateUrl: './family-detail.component.html',
  styleUrls: ['./family-detail.component.css']
})
export class FamilyDetailComponent implements OnInit {
	childTitles: string[] = ['pesel', 'first name', 'second name', 'sex'];
	family: Family;

  constructor(private route: ActivatedRoute, private detailsService: DetailsService) { }

  ngOnInit() {
		this.getFamily();
  }

	getFamily(): void {
		const id = +this.route.snapshot.paramMap.get('id');
	  this.detailsService.getFamily(id)
	    .subscribe(family => this.family = family);
	}
}
