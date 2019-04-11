import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SearchService } from 'src/app/service/search.service';
import { AuthService } from 'src/app/service/auth.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public title = 'News';
  public searchQuery="";
  public isUserLoggedIn = false;
  userId: string;
  password: string;
  loading: string;
  constructor(private router: Router,private authsvc: AuthService,private search:SearchService) {
	
  }
  Search(){
    console.log("search word",this.searchQuery);
    const link = ["/search/"+this.searchQuery];
    console.log("link",link);
    this.ngOnInit();
    this.router.navigate(link);
    this.ngOnInit();
  }
  reload(){
    window.location.reload();
  }
  loginUser() {
    this.authsvc.login(this.userId, this.password).
      subscribe(response => {
        if (response["token"]) {
          this.authsvc.setToken(response["token"]);
          this.authsvc.setUserId(this.userId);
          this.ngOnInit();
          this.router.navigate(['/home']);
          this.ngOnInit();
        }
      }, error => {
        if (error.status === 401) {
          alert('Username or password is incorrect.');
        }
      });
  }
  ngOnInit(){
    this.isUserLoggedIn = localStorage.getItem("jwt_token") === null ? false : true;
	}
}
