import {Category} from "./category";

export interface Book {
  bookId: number;
  bookName: string;
  bookImage: string;
  bookPrice: string;
  bookTranslator: string;
  bookPublishingCompany: string;
  bookNumberOfPages: string;
  bookSize: string;
  bookAuthor: string;
  bookDiscount: string;
  bookAmount: string;
  bookReleaseDate:string;
  bookDescription: string;
  category: Category;
}
