import { Injectable } from "@angular/core";
import { Headers, Http, Jsonp } from "@angular/http";
import {NewsHttpClient} from './newshttpclient.service';
import { AuthService } from "src/app/service/auth.service";
export const USER_TYPE: string = "user_type";
@Injectable()
export class NewsService {
	private serviceUrl = "http://localhost:8080/api/newsservice/";
	private userType = localStorage.getItem(USER_TYPE);
	constructor(private http: NewsHttpClient,private auth:AuthService) { }
    addToWatchListMovie(title: string,description :string, url:string,
    urlToImage:string,id:number) {
		const uri = this.serviceUrl + 'news/';
		const userType = this.userType;
		const json = {
			title: title,
            description: description,
            url: url,
			urlToImage:urlToImage,
			userType:userType
        };
        console.log("executed",json); 
        return this.http.post(uri, json);
           
	}
	updateWatchList(title: string,description :string, url:string,
		urlToImage:string,id:number,userType:string) {
			const uri = this.serviceUrl + 'news/';
			const json = {
				id:id,
				title: title,
				description: description,
				url: url,
				urlToImage:urlToImage,
				userType:userType
			};
			console.log("executed",json); 
			return this.http.post(uri, json);
			   
		}

	getUserWatchList(){
		const url= this.serviceUrl + 'admin/'
		return this.http.get(url).toPromise().then(res => res.json(),
			err => err.json());
	}

	getWatchList() {
		const url = this.serviceUrl + 'allnews';
		console.log("user type",this.userType);
		return this.http.get(url).toPromise().then(res => res.json(),
			err => err.json());
	}
	checkWatchList(title: string) {
		console.log("title",title);
		const url = this.serviceUrl + 'news/' + title;
		return this.http.get(url).toPromise().then(res => (res ? res.json() : null))
			.catch(this.handleError);
	}

	isNewsAvailable(title){
		const uri = this.serviceUrl + 'isNewsAvailable/';
		const json = {
			title:title
		} 
        return this.http.post(uri, json);		
	}
	updateNews(title: string, description: string) {
		const url = this.serviceUrl + 'movie/';
		const uri = `${url}${title}`;
		const data = { description:description };
		return this.http.put(uri, data).toPromise().catch(this.handleError);
	}
	removeNewsFromWatchList(title: string) {
		const url = this.serviceUrl + 'news/' + title;
		console.log("url",url);
		return this.http.delete(url);
	}
	private handleError(error: any) {
		console.error('An error occurred', error); // for demo purposes only
	}
}