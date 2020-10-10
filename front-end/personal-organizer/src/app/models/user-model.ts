import { Address } from './address-model';
import { UserType } from './user-type-model';

export interface User {
    id: string;
    name: string;
    lastName: string;
    age: number;
    email: string;
    username: string;
    password: string;
    address: Address;
    userType: UserType;
}