import { Component, OnInit, Input } from '@angular/core';
import { BackendService } from '../services/backend.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-barber-card',
  templateUrl: './barber-card.component.html',
  styleUrls: ['./barber-card.component.css']
})
export class BarberCardComponent implements OnInit {

  public item$;
  public image;
  @Input() id: string; 
  @Input() date: string; 

  constructor(private _be: BackendService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.item$ = this._be.getBarberDetails(this.id);
  }
}
