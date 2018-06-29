import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Father } from './Father';
import { Child } from './Child';

@Injectable({
  providedIn: 'root'
})
export class FamilyCreatorService {
	father: Father;
	children: Child[] = [];

  constructor(private http: HttpClient) { }

  setFather(father: Father) {
  	this.father = father;
  }

  addChild(child: Child) {
  	this.children.push(child);
  }

  cleanChildren() {
  	this.children = [];
  }

  createFamily(): Observable<Number> {
  	return this.http.post<Number>('http://localhost:8081/families', null);
  }

  createFather(familyId: number): Observable<Number> {
		return this.http.post('http://localhost:8081/families/' + familyId + '/father', this.father)
			.pipe(flatMap(_ => of(familyId)));
  }

  createChildren(familyId: number): Observable<Number> {
  	let result = of(familyId);
		this.children.forEach(
			child => result = result
				.pipe(flatMap(_ => this.http.post('http://localhost:8081/families/' + familyId + '/children', child)
				.pipe(flatMap(_ => of(familyId))))));
		return result;
  }

  createFullFamily(): Observable<Number> {
  	return this.createFamily()
  		.pipe(flatMap(id => {return this.createFather(id.valueOf())}))
  		.pipe(flatMap(id => { return this.createChildren(id.valueOf())}));
  }
}