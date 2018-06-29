import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { Family } from './Family';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) { }

  search(pesel: string, firstName: string, secondName: string, sex: string): Observable<Family[]>{
		const headers = {};
		const params = { 'pesel': pesel, 'firstName': firstName, 'secondName': secondName, 'sex': sex }

		return this.http.get<Family[]>('http://localhost:8081/families', { headers, params });
  }
}