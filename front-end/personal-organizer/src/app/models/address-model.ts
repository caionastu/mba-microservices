import { Location } from './location-model';

export interface Address {
    street: string;
    district: string;
    number: number;
    city: string;
    country: string;
    cep: string;
    location: Location;
}