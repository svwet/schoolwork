import { Component, OnInit } from '@angular/core';
import {ListService} from "./list.service";
import {User} from "./user";

@Component({
  selector: 'app-list',
  templateUrl: 'list.page.html',
  styleUrls: ['list.page.scss']
})
export class ListPage implements OnInit {
  private users: User[];

  constructor(public listService: ListService){
  }

  ngOnInit() {
    this.listService.getGoodBeers().subscribe(
        value => this.users = value
    );
    this.listService.getGoodBeers();
  }
}
