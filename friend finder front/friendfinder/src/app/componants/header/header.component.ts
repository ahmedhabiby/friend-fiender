import { Component, OnInit } from '@angular/core';
import {SecurityService} from '../../service/security/security.service';
import {SearchService} from "../../service/search/search.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private securityService :SecurityService,private _searchService: SearchService) { }

  ngOnInit(): void {
  }

  logout() {
    this.securityService.logout();
  }
  isUserLogin():boolean{

    if(!this.securityService.isUserLogin()){
      return false
    }
    return true
  }
  onSearch(event:Event):void{
    const input:HTMLInputElement = event.target as HTMLInputElement;
    this._searchService.setSearch(input.value)
  }
}
