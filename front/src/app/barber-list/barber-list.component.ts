import { Component, OnInit } from '@angular/core';
import { BackendService } from '../services/backend.service';
import { Barber } from "../types";
import { map } from "rxjs/operators";

@Component({
  selector: 'app-barber-list',
  templateUrl: './barber-list.component.html',
  styleUrls: ['./barber-list.component.css']
})
export class BarberListComponent implements OnInit {

  public eq;
  public name = "";

  constructor(private _be: BackendService) { }

  public loadData() {
    this.eq = this._be.getBarberList();
  }

  public corresponds(name, type) {
    return (name.includes(this.name) || this.name === '') 
  }

  ngOnInit() {
    this.loadData();
  }
}
