import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { AuthGuard } from 'src/app/service/auth-guard.service';
import { AuthService } from 'src/app/service/auth.service';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { LogoutComponent } from './logout/logout.component';
import { HomeComponent } from './home/home.component';
import { NewsHttpClient } from 'src/app/service/newshttpclient.service';
import { AppRoutingModule } from 'src/app/app-routing/app-routing.module';
import { SearchService } from 'src/app/service/search.service';
import { NewsComponent } from './news/news.component';
import { WatchlistComponent } from './watchlist/watchlist.component';
import { NewsService } from 'src/app/service/news.service';
import { EditComponent } from './edit/edit.component';
import { SearchComponent } from './search/search.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    LogoutComponent,
    HomeComponent,
    NewsComponent,
    WatchlistComponent,
    EditComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    RouterModule,
    AppRoutingModule
  ],
  providers: [AuthGuard,AuthService,NewsHttpClient,SearchService,NewsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
