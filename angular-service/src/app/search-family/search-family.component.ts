import { Component, OnInit } from '@angular/core';

import { Family } from '../Family';
import { Father } from '../Father';
import { Child } from '../Child';
import { SearchService } from '../search.service';

@Component({
  selector: 'app-search-family',
  templateUrl: './search-family.component.html',
  styleUrls: ['./search-family.component.css']
})
export class SearchFamilyComponent implements OnInit {
	families: Family[];

	pesel: string = '';
	firstName: string = '';
	secondName: string = '';
	sex: string = '';

  constructor(private searchService: SearchService) { }

  ngOnInit() {
		this.search();
  }

	onPeselChangeTo(newPeselValue: string): void {
		this.pesel = newPeselValue;
		this.search();
	}

	onFirstNameChangeTo(newFirstNameValue: string): void {
		this.firstName = newFirstNameValue;
		this.search();
	}

	onSecondNameChangeTo(newSecondNameValue: string): void {
		this.secondName = newSecondNameValue;
		this.search();
	}

	onSexChangeTo(newSexValue: string): void {
		this.sex = newSexValue;
		this.search();
	}

	search(): void {
		this.searchService.search(this.pesel, this.firstName, this.secondName, this.sex)
		.subscribe(families => this.families = families);
	}
}