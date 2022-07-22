import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {BookService} from "../service/book.service";
import {ToastrService} from "ngx-toastr";
import {Book} from "../model/book";

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
  public id: string;
  public book = {} as Book;
  public books: Book[];
  public categoryId: number;

  constructor(private activatedRoute: ActivatedRoute,
              private bookService: BookService,
              private router: Router,
              private toastr: ToastrService) {
    this.id = this.activatedRoute.snapshot.paramMap.get('id');
  }

  ngOnInit(): void {
    this.getBookById(this.id)

  }

  public getBookById(id: string) {
    this.bookService.getBookById(id).subscribe(book => {
        this.book = book,
          this.categoryId = this.book.category.categoryId,
          console.log(this.categoryId)
      this.bookService.getAllBook({
        page:0,size:4,criterion:'category_id',valueSearch:this.categoryId
      }).subscribe(books => this.books = books['content'])
      },
      () => this.toastr.warning('Không tìm thấy dữ liệu tương ứng !', 'Thông báo', {
        timeOut: 3000,
        progressBar: true
      }))
  }
}
