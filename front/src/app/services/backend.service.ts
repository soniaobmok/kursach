import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { map, tap, concatMap } from "rxjs/operators";
import { HttpClient } from "@angular/common/http";
import { Barber, Auth, Roles, Feedback } from "../types";

@Injectable({
  providedIn: "root"
})
export class BackendService {
    static apiPath = "http://localhost:8080/";
    static eqPath = "barber";
    static bookingPath = "booking";
    static userPath = "user";
    static find = "find";
    static delete = "delete";
    static update = "update";
    static login = "login";
    static register= "register";
    static feedback="feedback";

    constructor(private _http: HttpClient) {}

    private auth: Auth = {
        user: {
            id: null, 
            name: "", 
            email: "", 
            password: "", 
            role: Roles.client
        },
        token: ""
    };
    
    public isLoggedIn(): boolean {
        return !!this.auth.token;
    }

    public isAdmin(): boolean {
        return this.auth.user.role === Roles.admin;
    }

    public logout() {
        this.auth.token = "";
    }

    public login(email, password) {
        const url = `${BackendService.apiPath}${BackendService.userPath}/${BackendService.login}`;
        return this._http.post(url, { email, password },
            { headers: this.getBasicHeaders() }
        ).pipe(
            tap((res: Auth) => this.auth = res)
        );
    }

    private getBasicHeaders() {
        const headers = {
          'Content-type': 'application/json',
          'X-Requested-With': "", 
          'XMLHttpRequest': "",
          "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3"
        };
        return headers;
    }
    
    private getAuthHeaders() {
        const headers = this.getBasicHeaders();
        if (this.isLoggedIn) {
          headers["Authorization"] = `Bearer ${this.auth.token}`;
        }
        return headers;
    }

    public register(name, email, password) {
        const url = `${BackendService.apiPath}${BackendService.userPath}/${BackendService.register}`;
        return this._http.post(url, {name, email, password},
            { headers: this.getBasicHeaders() }
        ).pipe(
            tap((res: Auth) => this.auth = res)
        );
    }


    public getBarberList() {
        const url = `${BackendService.apiPath}${BackendService.eqPath}`;
        return this._http.get(url,
          { headers: this.getBasicHeaders() }
        )
    }

    public getBarberDetails(id: string) {
        const url = `${BackendService.apiPath}${BackendService.eqPath}/${id}`;
        return this._http.get(url,
          { headers: this.getBasicHeaders() }
        )
    }

    public addBarber(name: string,
        rating: number, description: string) {
        const url = `${BackendService.apiPath}${BackendService.eqPath}`;
        return this._http.post(url, {name, rating, description},
            { headers: this.getAuthHeaders() },
        )
    }
    
    public deleteBarber(id: number) {
        const url = `${BackendService.apiPath}${BackendService.eqPath}/${id}`;
        return this._http.delete(url,
          { headers: this.getAuthHeaders() }
        )
    }

    public updateBarber(id: number, name: string,
            rating: number, description: string) {
        const url = `${BackendService.apiPath}${BackendService.eqPath}/${id}`;

        return this._http.put(url, {name, rating, description},
            { headers: this.getAuthHeaders() }
          )
    }

    public addBooking(barberId) {
        const date = '05/07/2020 20:00:00';
        const url = `${BackendService.apiPath}${BackendService.bookingPath}`;
        return this._http.post(url, {touristId:  this.auth.user.id.toString(), barberId, date},
            { headers: this.getAuthHeaders() },
        )
    }

    public getBookingsList() {
        const url = (this.isLoggedIn() && this.isAdmin()) ? 
        `${BackendService.apiPath}${BackendService.bookingPath}` :
        `${BackendService.apiPath}${BackendService.bookingPath}/${BackendService.userPath}/${this.auth.user.id}`;
        
        return this._http.get(url,
            { headers: this.getAuthHeaders() }
        );
    }

    public getBooking(id) {
        const url = `${BackendService.apiPath}${BackendService.bookingPath}/${id}`;
        return this._http.get(url,
            { headers: this.getAuthHeaders() }
        );
    }

    public getFeedbacksByBarberList(id) {
        const url = `${BackendService.apiPath}${BackendService.feedback}`;
        return this._http.get(url,
            { headers: this.getBasicHeaders() }
        ).pipe(
            map((res: Array<Feedback>) => res.filter(f => {
                return Number(f.barberId) ===  Number(id);
            }))
        );
    }

    public postFeedback(bookingId, text, rating, barberId) {
        const date = '05/07/2020 20:00:00';
        const url = `${BackendService.apiPath}${BackendService.feedback}`;
        return this._http.post(url, {bookingId, text, rating, date, barberId},
            { headers: this.getAuthHeaders() }
        );
    }

}
  