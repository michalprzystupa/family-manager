import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

import { FamilyCreatorService } from '../family-creator.service'
import { Father } from '../Father'

@Component({
  selector: 'app-add-father',
  templateUrl: './add-father.component.html',
  styleUrls: ['./add-father.component.css']
})
export class AddFatherComponent implements OnInit {
	father: Father;

  constructor(
  	private familyCreator: FamilyCreatorService, 
  	private router: Router
  ) { }

  ngOnInit() {
  	this.father = new Father();
  }

  setFather() {
  	this.familyCreator.setFather(this.father);
  	this.router.navigate(['add-child']);
  }
}