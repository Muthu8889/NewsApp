import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LogoutComponent } from "src/app/logout/logout.component";
import { RegisterComponent } from "src/app/register/register.component";
import { LoginComponent } from "src/app/login/login.component";
import { HomeComponent } from "src/app/home/home.component";
import { AuthGuard } from "src/app/service/auth-guard.service";
import { NewsComponent } from "src/app/news/news.component";
import { WatchlistComponent } from "src/app/watchlist/watchlist.component";
import { EditComponent } from "src/app/edit/edit.component";
import { SearchComponent } from "src/app/search/search.component";

const routes: Routes = [
	{ path: "home", component: HomeComponent, canActivate: [AuthGuard] },
	{ path: "news", component: NewsComponent, canActivate: [AuthGuard] },
	{ path: "watchlist", component: WatchlistComponent, canActivate: [AuthGuard] },
	{ path: "edit", component: EditComponent, canActivate: [AuthGuard] },
	{ path: "search/:searchQuery", component: SearchComponent, canActivate: [AuthGuard] },
	{ path: "login", component: LoginComponent },
	// { path: " ", redirectTo: '/login', pathMatch: 'full' },
	{ path: "register", component: RegisterComponent },
	{ path: "logout", component: LogoutComponent },
];
@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule { }
