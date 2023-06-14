import jdk.nashorn.internal.runtime.regexp.joni.Config;
import ra.bussiness.config.ConfigScanner;
import ra.bussiness.config.Validate;
import ra.bussiness.controller.BookController;
import ra.bussiness.modal.Book;

import java.sql.SQLOutput;

public class Main {
   static BookController bookController = new BookController();

   public static void main(String[] args) {
      int choose;
      while (true) {
         System.out.println("**************** JAVA-HACKATHON-05-BASIC-MENU ***************");
         System.out.println("1. Thêm sách vào thư viện");
         System.out.println("2. Hiển thị sách có trong thư viện");
         System.out.println("3. Sắp xếp sách theo lợi nhuận");
         System.out.println("4. Xóa sách khỏi thư viện");
         System.out.println("5. Tìm kiếm sách theo tên hoặc mô tả");
         System.out.println("6. Thay đổi thông tin sách theo mã sách");
         System.out.println("7. Thoát");
         System.out.println("*************************************************************");
         System.out.print("Nhập lựa chọn của bạn: ");
         choose = ConfigScanner.scanner().nextInt();
         switch (choose) {
            case 1:
               // nhập vào thông tin sách
               addNewBook();
               break;
            case 2:
               // hiển thị sách có trong thư viện
               showListBook();
               break;
            case 3:
               // sắp sếp sách theo lợi nhuận
               sortByInterest();
               break;
            case 4:
               // xóa sách khỏi thư viện
               deleteBook();
               break;
            case 5:
               // tìm kiếm tương đối theo tên hoặc mô tả
               searchBook();
               break;
            case 6:
               // thay đổi thông tin sách
               updateBook();
               break;
            case 7:
               // out progress
               System.out.println("~~ Cảm ơn bạn đã sử dụng chương trình ~~");
               System.exit(0);
               break;
            default:
               System.err.print("Vui lòng nhập lại (1 -> 7): ");
               break;
         }
      }
   }
   // Phương thức làm việc với chương trình

   // add new book to library
   public static void addNewBook() {
      System.out.print("Nhập vào sô lượng bạn muốn nhập: ");
      int n = ConfigScanner.scanner().nextInt();
      for (int i = 0; i < n; i++) {
         System.out.println("Sản phẩm thứ " + i + 1);
         Book book = new Book();
         book.setBookId(bookController.getNewId());
         // check name
         while (true) {
            System.out.print("Nhập tên buốn sách: ");
            String name = ConfigScanner.scanner().nextLine();
            if (Validate.checkSpace(name)) {
               book.setBookName(name);
               break;
            }
         }
         // check author
         while (true) {
            System.out.print("Nhập tên tác giả: ");
            String author = ConfigScanner.scanner().nextLine();
            if (Validate.checkSpace(author)) {
               book.setAuthor(author);
               break;
            }
         }
         // check description
         while (true) {
            System.out.print("Nhập mô tả tên sách: ");
            String description = ConfigScanner.scanner().nextLine();
            if (Validate.checkSpace(description) && Validate.checkDescription(description)) {
               book.setDescription(description);
               break;
            }
         }
         // check importPrice
         while (true) {
            System.out.print("Nhập vào giá nhập: ");
            double importPrice = ConfigScanner.scanner().nextDouble();
            if (Validate.checkImportPrice(importPrice)) {
               book.setImportPrice(importPrice);
               break;
            }
         }
         // check exportPrice
         while (true) {
            System.out.print("Nhập vào giá xuất: ");
            double exportPrice = ConfigScanner.scanner().nextDouble();
            if (Validate.checkExportPrice(exportPrice, book.getImportPrice())) {
               book.setExportPrice(exportPrice);
               break;
            }
         }
         bookController.save(book);
      }
   }

   // show all book in the library
   public static void showListBook() {
      if (bookController.getSize() == 0) {
         System.err.println("~~ Thư viện không có sách ~~");
         return;
      }
      for (Book book : bookController.getAll()) {
         if (book != null) {
            System.out.println(book);
         }
      }
      System.out.println("--------------------------------");
   }

   // sort list book
   public static void sortByInterest() {
      bookController.sortByInterest();
      showListBook();
   }

   // delete item book in the library
   public static void deleteBook() {
      System.out.print("Bạn muốn xóa sách có id là: ");
      int id = ConfigScanner.scanner().nextInt();
      bookController.delete(id);
   }

   // search book
   public static void searchBook() {
      System.out.print("Nhập vào tên sách hoặc mô tả: ");
      String text = ConfigScanner.scanner().nextLine();
      // chuyển về chữ thường hết
      text.toLowerCase();
      // check để hiển thì có sách hay không có sách
      boolean found = false;
      if (text.trim().equals("")) {
         System.err.println("~~ Bạn đã không nhập gì ~~");
      } else {
         for (Book book : bookController.getAll()) {
            if (book != null) {
               // check xem có tồn tại trong list book không
               if (book.getBookName().toLowerCase().contains(text) || book.getDescription().toLowerCase().contains(text)) {
                  System.out.println(book);
                  found = true;
               }
            }
         }
         // hiển thị là không có sách nếu found = false
         if (!found) {
            System.out.println("~~ Không có quyển sách bạn muốn tìm ~~");
         }
      }
   }

   // update book
   public static void updateBook() {
      System.out.print("Bạn muốn sửa sách có id là: ");
      int id = ConfigScanner.scanner().nextInt();
      Book book = bookController.findById(id);
      if (book == null) {
         System.err.println("~~ Trong thư viện không có sách này ~~");
         return;
      }
      // sửa tên
      while (true) {
         System.out.printf("Sửa tên (tên cũ: %s): ", book.getBookName());
         String name = ConfigScanner.scanner().nextLine();
         if (Validate.checkSpace(name)) {
            book.setBookName(name);
            break;
         }
      }
      // sửa tên tác giả
      while (true) {
         System.out.printf("Sửa tên tác giả (tên cũ: %s): ", book.getAuthor());
         String author = ConfigScanner.scanner().nextLine();
         if (Validate.checkSpace(author)) {
            book.setAuthor(author);
            break;
         }
      }
      //sủa mô tả
      while (true) {
         System.out.printf("Sửa mô tả mới (mô tả cũ: %s): ", book.getDescription());
         String description = ConfigScanner.scanner().nextLine();
         if (Validate.checkSpace(description) && Validate.checkDescription(description)) {
            book.setDescription(description);
            break;
         }
      }
      // sửa giá nhập
      while (true) {
         System.out.printf("Nhập vào giá nhập mới (giá cũ: %f): ", book.getImportPrice());
         double importPrice = ConfigScanner.scanner().nextDouble();
         if (Validate.checkImportPrice(importPrice)) {
            book.setImportPrice(importPrice);
            break;
         }
      }
      // sửa giá xuất
      while (true) {
         System.out.printf("Nhập vào giá xuất mới (giá cũ: %f): ", book.getExportPrice());
         double exportPrice = ConfigScanner.scanner().nextDouble();
         if (Validate.checkExportPrice(exportPrice, book.getImportPrice())) {
            book.setExportPrice(exportPrice);
            break;
         }
      }
      book.setInterest(book.getImportPrice(), book.getExportPrice());
      bookController.save(book);
   }
}