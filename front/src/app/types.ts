export interface Barber {
    id: number;
    name: string;
    rating: number;
    description: string;
}

export interface User {
    id: number;
    name: string;
    email: string;
    password: string;
    role: Roles;
}

export interface Auth {
    user: User;
    token: string;
}

export interface Order {
    id: number;
    date: string;
    touristId: string;
    barberId: string;
    barber: Barber;
}

export interface Feedback {
    id: number;
    date: string;
    rating: number;
    text: string;
    barberId: number;
}

export enum Roles { 
    admin = "ROLE_ADMIN",
    client = "ROLE_CLIENT"
}