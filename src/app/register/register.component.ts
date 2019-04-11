import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
// import { AlertService } from './../service/alert.service';
import { AuthService } from '../service/auth.service';
import { User } from '../User';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  
})
export class RegisterComponent implements OnInit {
 
  lname: string;
  userId: string;
  fname: string;
  password: string;
  usertype: string;
  
  constructor(private authsvc:AuthService, private router: Router ) {}
  
  public ngOnInit() {}
  
  user=new User();
   registerUser() {
    console.log("usertype",this.usertype);
    this.authsvc.registerUser(this.userId,this.password,this.fname,this.lname,this.usertype).then
      (obj=>{
        alert("Registered Sucessfully")
      });
      
  }
}