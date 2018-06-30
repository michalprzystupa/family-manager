import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { BackendURLs } from './BackendURLs';
import { Family } from './Family';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) { }

  search(pesel: string, firstName: string, secondName: string, sex: string): Observable<Family[]>{
		const params = { 'pesel': pesel, 'firstName': firstName, 'secondName': secondName, 'sex': sex }

		return this.http.get<Family[]>(BackendURLs.FAMILIES_URL, { headers: {}, params });
  }
}