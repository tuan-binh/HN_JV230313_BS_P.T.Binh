package ra.bussiness.service;

import ra.bussiness.modal.Book;

public class BookService {

   private Book[] listBook = new Book[100];
   private int size = 3;

//   Contructor


   public BookService() {
      listBook[0] = new Book(1, "Đắc nhân tâm", "hai dang", "đây là description 1", 1200, 2000, true);
      listBook[1] = new Book(2, "Nhà giả kim", "hai dang", "đây là description 2", 1400, 2000, true);
      listBook[2] = new Book(3, "Tuổi trẻ", "hai dang", "đây là description 3", 1000, 2000, true);
   }

   //   get list book
   public Book[] getAll() {
      return listBook;
   }

   //   get size
   public int getSize() {
      return size;
   }
//   public save(add)

   public void save(Book book) {
      if (findById(book.getBookId()) == null) {
         if (size == 100) {
            System.err.println("~~ Danh sách đã đầy ~~");
            return;
         }
         for (int i = 0; i < listBook.length; i++) {
            if (listBook[i] == null) {
               listBook[i] = book;
               size++;
               break;
            }
         }
         System.out.println("~~ Thêm thành công ~~");
      } else {
         for (int i = 0; i < listBook.length; i++) {
            if (listBook[i].getBookId() == book.getBookId()) {
               listBook[i] = book;
               break;
            }
         }
         System.out.println("~~ Sửa thành công ~~");
      }
   }

   //   delete book
   public void deleteBook(int id) {
      if (findById(id) != null) {
         for (int i = 0; i < listBook.length; i++) {
            if (listBook[i].getBookId() == id) {
               listBook[i] = null;
               size--;
               break;
            }
         }
         System.out.println("~~ Xóa thành công ~~");
      } else {
         System.err.println("~~ Sách này không có ~~");
      }
   }

   //   find book by id
   public Book findById(int id) {
      for (Book book : listBook) {
         if (book != null && book.getBookId() == id) {
            return book;
         }
      }
      return null;
   }

   //   get new Id
   public int getNewId() {
      int idMax = 0;
      for (Book b : listBook) {
         if (b != null && idMax < b.getBookId()) {
            idMax = b.getBookId();
         }
      }
      return idMax + 1;
   }

   public void sortByInterest() {
      for (int i = 0; i < listBook.length; i++) {
         for (int j = i + 1; j < listBook.length; j++) {
            if (listBook[i] != null && listBook[j] != null) {
               if (listBook[i].getInterest() > listBook[j].getInterest()) {
                  Book temp = listBook[i];
                  listBook[i] = listBook[j];
                  listBook[j] = temp;
               }
            }
         }
      }
   }
}
