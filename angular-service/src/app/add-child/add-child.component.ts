import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

import { FamilyCreatorService } from '../family-creator.service'
import { Child } from '../Child';

@Component({
  selector: 'app-add-child',
  templateUrl: './add-child.component.html',
  styleUrls: ['./add-child.component.css']
})
export class AddChildComponent implements OnInit {
	child: Child;

	numberOfChildren: number;

  constructor(
		private familyCreator: FamilyCreatorService,
		private router: Router
	) { }

  ngOnInit() {
  	this.numberOfChildren = 0;
		this.clearChild();
		this.familyCreator.cleanChildren();
  }

	clearChild() {
		this.child = new Child();
	}

	addChild() {
		this.familyCreator.addChild(this.child);
		this.clearChild();
		this.numberOfChildren += 1;
	}

	createFamily() {
		this.familyCreator.createFullFamily().subscribe(familyId => {
			this.familyCreator.cleanChildren();
			this.router.navigate(['detail/' + familyId]) });
	}
}