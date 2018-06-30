import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { BackendURLs } from './BackendURLs';
import { Family } from './Family';

@Injectable({
  providedIn: 'root'
})
export class DetailsService {

  constructor(private http: HttpClient) { }

  getFamily(id: number): Observable<Family> {
    return this.http.get<Family>(BackendURLs.FAMILIES_URL + id);
  }
}
