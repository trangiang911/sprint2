import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ToastrService} from "ngx-toastr";
import {BookService} from "../service/book.service";
import {Book} from "../model/book";
import {Category} from "../model/category";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {SecurityService} from "../service/security/security.service";
import {TokenStorageService} from "../service/security/token-storage.service";
import {LoginRequest} from "../model/login-request";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  @ViewChild('valueDataList') valueDataList: ElementRef;
  @ViewChild('criterion') criterion: ElementRef;
  @ViewChild('valueInput') valueInput: ElementRef;
  public books: Book[];
  public categorys: Category[];
  public totalPages: number;
  public currentPage: number;
  public isHasContent = false;
  public isHiddenInput = false;
  public isHiddenSelect = true;
  public str: string;

  constructor(private toastr: ToastrService,
              private bookService: BookService,
              private securityService: SecurityService,
              private route: Router,
              private tokenStorageService: TokenStorageService) {
  }

  ngOnInit(): void {
    this.getAllBook();
    this.getAllCategory();

  }

  public getAllBook() {
    this.bookService.getAllBook({
      page: 0, size: 8, criterion: '', valueSeach: ''
    }).subscribe(books => {
      this.books = books['content'];
      this.currentPage = books['number'];
      this.totalPages = books['totalPages'];
    }, () => {
      this.books = null;
      this.isHasContent = true;
      this.toastr.warning('Không tìm thấy dữ liệu tương ứng !', 'Thông báo', {
        timeOut: 3000,
        progressBar: true
      });
    })
  }

  public getAllCategory() {
    this.bookService.getAllCategory().subscribe(categorys => this.categorys = categorys, () =>
      this.toastr.warning('Không tìm thấy dữ liệu tương ứng !', 'Thông báo', {
        timeOut: 3000,
        progressBar: true
      }))
  }

  changeOption(value: any) {
    this.isHiddenInput = value == "category_id" ? true : false;
    this.isHiddenSelect = value == "category_id" ? false : true;
  }

  search() {
    console.log(this.criterion.nativeElement.value)
    console.log(this.valueDataList.nativeElement.value);
    console.log(this.valueInput.nativeElement.value);
    this.str = this.criterion.nativeElement.value;
    switch (this.str) {
      case 'category_id':
        this.bookService.getAllBook({
          page: 0,
          size: 8,
          criterion: this.criterion.nativeElement.value,
          valueSearch: this.valueDataList.nativeElement.value
        }).subscribe(books => {
          this.books = books['content'];
          this.currentPage = books['number'];
          this.totalPages = books['totalPages'];
        }, () => {
          this.books = null;
          this.isHasContent = true;
          this.toastr.warning('Không tìm thấy dữ liệu tương ứng !', 'Thông báo', {
            timeOut: 3000,
            progressBar: true
          })
        })
          break;
        default:
          this.bookService.getAllBook({
            page: 0,
            size: 8,
            criterion: this.criterion.nativeElement.value,
            valueSearch: this.valueInput.nativeElement.value
          }).subscribe(books => {
            this.books = books['content'];
            this.currentPage = books['number'];
            this.totalPages = books['totalPages'];
          }, () => {
            this.books = null;
            this.isHasContent = true;
            this.toastr.warning('Không tìm thấy dữ liệu tương ứng !', 'Thông báo', {
              timeOut: 3000,
              progressBar: true
            })
          })
          break;
        }
    }

    previousPage()
    {
      const request = {};
      if ((this.currentPage) > 0) {
        request['page'] = this.currentPage - 1;
        request['size'] = 8;
        request['criterion'] = this.criterion.nativeElement.value;
        request['valueSearch'] = request['criterion'] == "category_id" ? this.valueDataList.nativeElement.value : this.valueInput.nativeElement.value
      }
      this.bookService.getAllBook(request).subscribe(books => {
        this.books = books['content'];
        this.currentPage = books['number'];
        this.totalPages = books['totalPages'];
      }, () => {
      })
    }

    nextPage()
    {
      const request = {};
      if ((this.currentPage + 1) < this.totalPages) {
        request['page'] = this.currentPage - 1;
        request['size'] = 8;
        request['criterion'] = this.criterion.nativeElement.value;
        request['valueSearch'] = request['criterion'] == "category_id" ? this.valueDataList.nativeElement.value : this.valueInput.nativeElement.value
      }
      this.bookService.getAllBook(request).subscribe(books => {
        this.books = books['content'];
        this.currentPage = books['number'];
        this.totalPages = books['totalPages'];
      }, () => {
      })
    }

}
