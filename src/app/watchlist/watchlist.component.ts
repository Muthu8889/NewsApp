import { Component, OnInit } from '@angular/core';
import { NewsService } from 'src/app/service/news.service';
import { SearchService } from 'src/app/service/search.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
export const USER_TYPE: string = "user_type";


@Component({
  selector: 'app-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {

  public watchList:any[];

  public isAdmin: boolean;

  private userType = localStorage.getItem(USER_TYPE);



  constructor(private newsService: NewsService,private auth:AuthService,private searchService:SearchService,private router:Router) { }

  ngOnInit() {
      this.newsService.getWatchList().then(res => {
        if(res){
          console.log("watchlist before",res);
          this.watchList = res;
          console.log("watchlist after",this.watchList);
        }
      });
      if(this.userType === "user"){
        this.isAdmin = true;
      }
      if(this.userType === "admin"){
        this.isAdmin = false;
      }
  }
  public deleteNews(title){
    console.log("title",title);
    this.newsService.removeNewsFromWatchList(title).subscribe((respone:any)=>{
        this.refreshNews();
    }
    );
  }

  public refreshNews(){
    this.newsService.getWatchList().then(res => {
      if(res){
        console.log("watchlist before",res);
        this.watchList = res;
        console.log("watchlist after",this.watchList);
      }
    });
  }
  public goToDetails(news){
    console.log("selected news",news);
    news.isFromWatchList = true;
    this.searchService.getNews(news);
    this.router.navigate(["/news"]);
  }

  public editNews(news){
    this.searchService.getNews(news);
    this.router.navigate(["/edit"]);  
  }

}
