import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../service/security/token-storage.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {LoginRequest} from "../model/login-request";
import {ToastrService} from "ngx-toastr";
import {BookService} from "../service/book.service";
import {SecurityService} from "../service/security/security.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  public isSignIn: boolean = false;
  public name: string = '';
  token: string;
  userName: string;
  roles: [];
  types: string;
  errorMap: any;
  loginFrom = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required, Validators.maxLength(50), Validators.minLength(6)]),
    remember: new FormControl(),
  });
  constructor(private toastr: ToastrService,
              private bookService: BookService,
              private securityService: SecurityService,
              private route: Router,
              private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    if(this.tokenStorageService.getToken()){
      this.isSignIn = true;
      this.name = this.tokenStorageService.getUser().username;
      this.roles = this.tokenStorageService.getUser().roles;
    }

  }

  logOut() {
    this.tokenStorageService.signOut();
    this.route.navigateByUrl('/home').then(() => {
      window.location.reload()
    })
  }

  onSubmit() {
    if (this.loginFrom.valid) {
      const username = this.loginFrom.value.username;
      const password = this.loginFrom.value.password;
      const signInSubmitForm: LoginRequest = {username, password};
      this.securityService.signIn(signInSubmitForm).subscribe(
        next => {
          if (this.loginFrom.value.remember) {
            this.tokenStorageService.saveTokenLocal(next.token);
            this.tokenStorageService.saveUserLocal(next);
          } else {
            this.tokenStorageService.saveTokenSession(next.token)
            this.tokenStorageService.saveUserSession(next);
          }
          this.userName = this.tokenStorageService.getUser().username;
          this.roles = this.tokenStorageService.getUser().roles;
          this.isSignIn = true;
          this.toastr.success("Đăng nhập thành công", "Chúc mừng")
          this.loginFrom.reset();

          this.roles.forEach(role => {
            if (role === 'ROLE_USER') {
              this.route.navigateByUrl('/home').then(() => {
                window.location.reload()
              });
            } else {
              this.route.navigateByUrl('/home').then();
              this.ngOnInit();
            }
          })
        },
        error => {
          console.log(error);
          if (error.error?.errorMap) {
            if(error.error?.errorMap?.notExists){
              this.toastr.warning(error.error.errorMap['notExists'], "Lỗi Đăng Nhập");
            }else if(error.error?.errorMap?.isVerification){
              this.toastr.warning(error.error.errorMap['isVerification'],"Lỗi Đăng nhập");
            }
            else{
              this.errorMap = error.error.errorMap;
            }
          } else{
            this.toastr.warning("Sai mật khẩu, vui lòng thử lại!", "Lỗi Đăng Nhập");
          }
        }
      )

    }
  }
}
