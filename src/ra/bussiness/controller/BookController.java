package ra.bussiness.controller;

import ra.bussiness.modal.Book;
import ra.bussiness.service.BookService;

public class BookController {
   private BookService bookService = new BookService();

   public Book[] getAll() {
      return bookService.getAll();
   }

   public int getSize() {
      return bookService.getSize();
   }

   public void save(Book book) {
      bookService.save(book);
   }

   public void delete(int id) {
      bookService.deleteBook(id);
   }

   public Book findById(int id) {
      return bookService.findById(id);
   }

   public int getNewId() {
      return bookService.getNewId();
   }

   public void sortByInterest() {
      bookService.sortByInterest();
   }
}
