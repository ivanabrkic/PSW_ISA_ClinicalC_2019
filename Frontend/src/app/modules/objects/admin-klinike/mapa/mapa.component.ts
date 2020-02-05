import { Component, AfterViewInit, ViewChild, ElementRef, OnInit, NgZone, Input, OnChanges } from
  '@angular/core';
import { MapsAPILoader, MouseEvent } from '@agm/core';


@Component({
  selector: 'app-mapa',
  templateUrl: './mapa.component.html',
  styleUrls: ['./mapa.component.css']
})

export class MapaComponent implements OnInit, OnChanges {


  geoCoder: google.maps.Geocoder;
  public latitude: number;
  longitude: number;
  zoom: number;

  @Input() public address: string;

  results: any


  constructor(private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone) { }

  ngOnInit() {
    this.mapsAPILoader.load().then(() => {
      this.geoCoder = new google.maps.Geocoder();
      this.setOtherLocation()
    });
  }

  ngOnChanges(changes: import("@angular/core").SimpleChanges): void {
    this.mapsAPILoader.load().then(() => {
      this.geoCoder = new google.maps.Geocoder();
      this.setOtherLocation()
    });
  }

  private setOtherLocation() {
    var self = this
    this.geoCoder.geocode({ 'address': this.address }, function (results, status) {
      if (status == 'OK') {
        self.latitude = results[0].geometry.location.lat();
        self.longitude = results[0].geometry.location.lng();
        self.zoom = 15;

      } else {
        alert('Geocode was not successful for the following reason: ' + status);
      }
    });
  }

}
