import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-zahtevi-registracija',
  templateUrl: './zahtevi-registracija.component.html',
  styleUrls: ['./zahtevi-registracija.component.css']
})
export class ZahteviRegistracijaComponent implements OnInit {
  items = []
  constructor() {
    this.items = [{id: 1, korIme: 'Tamara', email: 't.lazarevic@gmail.com', jbo: '0122477125262'},
      {id: 2, korIme: 'Ivana', email: 'ivn.bc@gmail.com', jbo: '0121991271262'},
      {id: 3, korIme: 'Nikola', email: 'n.milosevic0111@gmail.com', jbo: '0111997710262'}];
  }

  ngOnInit() {
  }

  onItemDeleted(index: number) {
    this.items.splice(index, 1);
  }
}
