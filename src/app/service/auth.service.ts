import { Injectable } from "@angular/core";
import { Http, Headers, Jsonp } from "@angular/http";
import * as jwt_decode from "jwt-decode";
export const TOKEN_NAME: string = "jwt_token";
export const USER_ID: string = "user_id";
export const USER_TYPE: string = "user_type";
import { map } from 'rxjs/operators';
import { Observable } from "rxjs/internal/Observable";
import { retryWhen } from "rxjs/internal/operators/retryWhen";


@Injectable()
export class AuthService {

    constructor(private http: Http) { }
    private headers = new Headers({
        "Content-Type": "application/json", "Accept": "application/json"
    });
    private serviceUrl = "http://localhost:8089/api/userservice/";
    userId: string = "";
    userType: string= "";
    userLogin = true;

    getToken(): string {
        return localStorage.getItem(TOKEN_NAME);
    }
    setToken(token: string): void {
        localStorage.setItem(TOKEN_NAME, token);
    }

    setUserId(userId: string) {
        localStorage.setItem(USER_ID,userId);
    }
    getUserId() {
        return localStorage.getItem(USER_ID);
    }

    setUserType(userType: string){
        localStorage.setItem(USER_TYPE,userType);
    }



    getUserType(){
        return localStorage.getItem(USER_TYPE);
    }

    deleteToken(): void {
        localStorage.removeItem(TOKEN_NAME);
    }
    getTokenExpirationDate(token: string): Date {
        const decoded: any = jwt_decode(token);
        if (decoded.exp === undefined) return null;
        const date = new Date(0);
        date.setUTCSeconds(decoded.exp);
        return date;
    }

    isTokenExpired(token?: string): boolean {
        if (!token) token = this.getToken();
        if (!token) return true;
        const date = this.getTokenExpirationDate(token);
        if (date === undefined || date === null) return false;
        return !(date.valueOf() > new Date().valueOf());
    }

    getUserTypeByUserId(){
        const userId = this.getUserId();
        console.log("userId in call",userId)
        const url = this.serviceUrl + "usertype/"+userId;
        return this.http.get(url, {headers:this.headers})
        .toPromise().then((res:any) => this.setUserType(res._body));
    }


    login(user_Id: string, password: string): Observable<string> {
        const url = this.serviceUrl + "login/";
        this.setUserId(user_Id);
        console.log("userId",user_Id);
        this.getUserTypeByUserId();
        
        const json = JSON.stringify({ userId: user_Id, password: password });
        return this.http.post(url, json, { headers: this.headers }).
            pipe(map(res => res.json()));
    }

    registerUser(userId: string, password: string, fname: string, lname: string,usertype: string) {
        const url = this.serviceUrl + "register/";
        const json = JSON.stringify({ userId: userId, password: password, firstname: fname, lastname: lname, userType: usertype });
        console.log("user json",json);
        return this.http.post(url, json, { headers: this.headers }).toPromise()
            .then(res => res.json()).catch((err) => { return "Unable to fetch token"; });
    }
    private handleError(error: any) {
        // console.error('An error occurred', error); // for demo purposes only
    }

}