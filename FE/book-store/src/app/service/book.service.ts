import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Book} from "../model/book";
import {Category} from "../model/category";
import {environment} from "../../environments/environment";
const API_URL = `${environment.apiUrl}`;
@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) { }

  public getAllBook(request):Observable<Book[]>{
    const params = request;
    return this.http.get<Book[]>(`${API_URL}/api/book`,{params})
  }

  public getAllCategory():Observable<Category[]>{
    return this.http.get<Category[]>(`${API_URL}/api/book/category`)
  }
  public getBookById(id: String):Observable<Book>{
    return this.http.get<Book>(`${API_URL}/api/book/${id}`)
  }
}
