import { Component, OnInit } from '@angular/core';
import { SearchService } from 'src/app/service/search.service';
import { NewsService } from "src/app/service/news.service";
import { ActivatedRoute } from '@angular/router';
import { map } from 'rxjs/operators';
import { switchMap } from 'rxjs/operators';


@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  public news;

  public isFromWatchlist = false;

  public title:string;

  public checkNews:object;

  constructor(private searchService: SearchService,private newsService:NewsService,private route: ActivatedRoute) { }

  updateWatchList(   
    title :string,
    description:string,
    url:string,
    urlToImage:string,
    id: number,userType:string){
      this.newsService.updateWatchList(        
        title,
        description,
        url,
        urlToImage,
        id,
        userType
      ).subscribe((response:any)=>{
        this.ngOnInit();
        alert('Updated Sucussfully');
      });
  }
  public deleteNews(title){
    console.log("title",title);
    this.newsService.removeNewsFromWatchList(title).subscribe((response:any)=>{
      this.ngOnInit();
    });
  }
  

  ngOnInit() {
    this.news = this.searchService.loadNews();
    this.newsService.isNewsAvailable(this.news.title).subscribe((response:any) => {
      console.log("respone of isAvailable",JSON.parse(response._body));
      this.isFromWatchlist=JSON.parse(response._body);
    });
    console.log("news obj in news component",this.news);
    // this.route.params
    //   .pipe(map(params => params.title))
    //   .pipe(switchMap(title => this.newsService.checkWatchList(this.news.title)))
    //   .subscribe(response =>{
    //     if(response=== undefined){
    //       this.isWatchlist=false;
    //     }
    //   })
    // this.checkNews = this.newsService.checkWatchList(this.news.title);
    // console.log('checknews',this.checkNews);
  }

}
