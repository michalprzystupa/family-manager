import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable, of } from 'rxjs';

import { Family } from './Family';

@Injectable({
  providedIn: 'root'
})
export class DetailsService {

  constructor(private http: HttpClient) { }

  getFamily(id: number): Observable<Family> {
    return this.http.get<Family>('http://localhost:8081/families/' + id);
  }
}
