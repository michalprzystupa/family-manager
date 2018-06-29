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

  constructor(
		private familyCreator: FamilyCreatorService,
		private router: Router
	) { }

  ngOnInit() {
		this.clearChild();
  }

	clearChild() {
		this.child = new Child();
	}

	addChild() {
		this.familyCreator.addChild(this.child);
		this.clearChild();
	}

	createFamily() {
		this.familyCreator.createFullFamily().subscribe(familyId => {
			this.familyCreator.cleanChildren();
			this.router.navigate(['detail/' + familyId]) });
	}
}
