import { Component, OnInit, Input, NgZone } from '@angular/core';
import { MapsAPILoader } from '@agm/core';

@Component({
  selector: 'app-mapa-profil',
  templateUrl: './mapa-profil.component.html',
  styleUrls: ['./mapa-profil.component.css']
})
export class MapaProfilComponent implements OnInit {

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
