import {AfterViewInit, Component} from '@angular/core';
import {MapService} from "../service/map.service"
import * as L from 'leaflet';
import {Coordinate} from "../model/coordinate";

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements AfterViewInit {
  private map: any;
  private clickCoordinate: Coordinate | undefined;
  private markerSource: any;
  private markerTarget: any;
  private sourceNodeId: number | undefined;
  private targetNodeId: number | undefined;

  constructor(public mapService: MapService) {
  }

  private onMapClick(e: { latlng: L.LatLngExpression; }): void {
    console.log("Clicked at " + e.latlng);
    var l = L.latLng(e.latlng);

    this.mapService
      .getNearestNode(l.lat, l.lng)
      .subscribe(c => {
        this.clickCoordinate = c;
        console.log(this.clickCoordinate);

        if (this.markerSource == null && this.markerTarget == null) {
          this.markerSource = L.marker([c.latitude, c.longitude]);
          this.map.addLayer(this.markerSource);
          this.sourceNodeId = c.nodeIndex;
        } else if (this.markerSource != null && this.markerTarget == null) {
          this.markerTarget = L.marker([c.latitude, c.longitude]);
          this.map.addLayer(this.markerTarget);
          this.targetNodeId = c.nodeIndex;

          this.mapService
            .getShortestPath(this.sourceNodeId, this.targetNodeId)
            .subscribe(geoJson => {
              L.geoJSON(geoJson).addTo(this.map);
            });

        } else if (this.markerSource != null && this.markerTarget != null) {
          this.map.removeLayer(this.markerSource);
          this.map.removeLayer(this.markerTarget);
          this.markerSource = null;
          this.markerTarget = null;
        }

      });
  };

  private initMap(): void {
    this.map = L.map('map', {
      center: [48.779044, 9.178734],
      zoom: 12
    });
    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 18,
      minZoom: 3,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });

    tiles.addTo(this.map);
    this.map.on('click', this.onMapClick.bind(this));
  };

  ngAfterViewInit(): void {
    this.initMap();
  }

}
