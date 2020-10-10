import { UserType } from './../models/user-type-model';
import { LocationFilter } from './location-filter';
import { PageFilter } from './page-filter';
export interface UserFilter extends PageFilter {
    name?: string;
    userType?: UserType;
    location?: LocationFilter;
}