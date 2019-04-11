import { Injectable } from "@angular/core";
import { Http } from "@angular/http";
import { map } from 'rxjs/operators';
import { Observable } from "rxjs/internal/Observable";
import { Router } from "@angular/router";
import { ActivatedRoute } from "@angular/router";

@Injectable()
export class SearchService {
    public sharedSearchResult: object[] = [];
    public news:{
        title:string,
        description:string,
        url:string,
        urlToImage:string,
        name:string,
        id:number
    };
    public usertype: string;
    
    public newsUrl = "https://newsapi.org/v2/top-headlines?"+"country=us&"+"apiKey=e2c06d6c1a7141669b42dce092afc758";
    public searchUrl = "https://newsapi.org/v2/everything?q=bitcoin&from=2019-02-20&sortby=publishedat&apiKey=e2c06d6c1a7141669b42dce092afc758"
    constructor(private http:Http,private router:Router,private route:ActivatedRoute){}
    public getTopNewsHeadlines(){
        return this.http.get(this.newsUrl).pipe(map(response => response.json()));
    }
    getSearchResults(text: string){
        const url = "https://newsapi.org/v2/everything?q="+text+"&apiKey=e2c06d6c1a7141669b42dce092afc758";
        return this.http.get(url).pipe(map(response => response.json()));
        
    }
    public getNews(news){
        this.news=news;
        console.log("news in service",this.news);
    }
    public loadNews(){
        return this.news;
    }
}