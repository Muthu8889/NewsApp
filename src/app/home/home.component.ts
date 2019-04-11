import { Component, OnInit } from '@angular/core';
import { SearchService } from 'src/app/service/search.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { NewsService } from 'src/app/service/news.service';
export const USER_TYPE: string = "user_type";


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public getTopNewsHeadlines: Object[]=[];
  private userType :string;


  constructor(private searchService: SearchService, private router:Router,private newsService:NewsService,private authsvc: AuthService) { }

  public goToDetails(news){
    console.log("selected news",news);
    news.isFromWatchList = false;
    this.searchService.getNews(news);
    this.router.navigate(["/news"]);
  }

  ngOnInit() {
    this.authsvc.getUserTypeByUserId();
    this.userType = localStorage.getItem(USER_TYPE);
    console.log("usertype",this.userType);
    if(this.userType === "admin"){
    this.searchService.getTopNewsHeadlines().subscribe(response =>  this.getTopNewsHeadlines = response.articles);
  }else{
    this.newsService.getUserWatchList().then(res => this.getTopNewsHeadlines=res);
  }
}
}
