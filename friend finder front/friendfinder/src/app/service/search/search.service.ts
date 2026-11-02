import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  private searchSource = new BehaviorSubject<string>(' ');
  search$ = this.searchSource.asObservable();

  setSearch(value: string) {
    this.searchSource.next(value);
  }
}
