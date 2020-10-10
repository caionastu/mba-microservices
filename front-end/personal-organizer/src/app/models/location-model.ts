import { Distance } from './distance-model';

export interface Location {
    lat: number;
    lng: number;
    distance: Distance;
}