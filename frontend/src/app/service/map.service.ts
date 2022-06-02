import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import {Coordinate} from "../model/coordinate";

@Injectable()
export class MapService {

  private nearestNodeUrl = 'http://localhost:8083/nearest-node';
  private startupUrl = 'http://localhost:8083/startup';

  constructor(private http: HttpClient) { }

  getNearestNode(latitude: number, longitude: number): Observable<any> {
    const url = this.nearestNodeUrl + '?latitude=' + latitude + '&longitude=' + longitude;
    console.log(url);
    return this.http
      .get(url)
      .pipe(retry(1), catchError(this.handleError));
  }

  // Error handling
  handleError(error: any) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    // window.alert(errorMessage);
    return throwError(() => {
      return errorMessage;
    });
  }
}
