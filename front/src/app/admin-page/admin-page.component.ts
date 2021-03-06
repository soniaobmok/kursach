import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { BackendService } from '../services/backend.service';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  public id;
  public name;
  public description;

  constructor(private _be: BackendService) { }

  ngOnInit() {
  }

  public add() {
    if(this.name, this.description) {
      this._be.addBarber(this.name, 1, this.description).subscribe(
        () => alert("Saved!"),
        () => alert("Saved!")
      )
    } else {
      alert("Not all fields are filled!")
    }
  }

  public update() {
    if(this.id && (this.name || this.description)) {
      this._be.updateBarber(this.id, this.name, 1, this.description).subscribe(
        () => alert("Updated!"),
        () => alert("Updated!")
      )
    } else {
      alert("Fill at least one filled!")
    }
  }

  public delete() {
    if (this.id) {
      this._be.deleteBarber(this.id).subscribe(
        () => alert("Deleted!")
      )
    } else {
      alert("Fill id field!")
    }
  }
}
