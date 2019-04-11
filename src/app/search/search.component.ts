import { Component, OnInit } from '@angular/core';
import { SearchService } from 'src/app/service/search.service';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  public getSearchResults: any[];
  
  constructor(private searchService: SearchService, private route:ActivatedRoute,private router:Router) { }

  public goToDetails(news){
    console.log("selected news",news);
    news.isFromWatchList = false;
    this.searchService.getNews(news);
    this.router.navigate(["/news"]);
  }

  ngOnInit() {
    this.searchService.getSearchResults(this.route.snapshot.params['searchQuery']).subscribe((res:any) => this.getSearchResults=res.articles);
    
  }
}

