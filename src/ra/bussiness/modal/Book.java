package ra.bussiness.modal;

public class Book {
   private int bookId;
   private String bookName;
   private String author;
   private String description;
   private double importPrice;
   private double exportPrice;
   private float interest;
   private boolean bookStatus = true;

   public Book() {
   }

   public Book(int bookId, String bookName, String author, String description, double importPrice, double exportPrice, boolean bookStatus) {
      this.bookId = bookId;
      this.bookName = bookName;
      this.author = author;
      this.description = description;
      this.importPrice = importPrice;
      this.exportPrice = exportPrice;
      this.interest = (float) (exportPrice - importPrice);
      this.bookStatus = bookStatus;
   }

   public int getBookId() {
      return bookId;
   }

   public void setBookId(int bookId) {
      this.bookId = bookId;
   }

   public String getBookName() {
      return bookName;
   }

   public void setBookName(String bookName) {
      this.bookName = bookName;
   }

   public String getAuthor() {
      return author;
   }

   public void setAuthor(String author) {
      this.author = author;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public double getImportPrice() {
      return importPrice;
   }

   public void setImportPrice(double importPrice) {
      this.importPrice = importPrice;
   }

   public double getExportPrice() {
      return exportPrice;
   }

   public void setExportPrice(double exportPrice) {
      this.exportPrice = exportPrice;
   }

   public float getInterest() {
      return interest;
   }

   public void setInterest(double importPrice,double exportPrice) {
      this.interest = (float) (exportPrice - importPrice);
   }

   public boolean isBookStatus() {
      return bookStatus;
   }

   public void setBookStatus(boolean bookStatus) {
      this.bookStatus = bookStatus;
   }

   @Override
   public String toString() {
      return "--------------------------------\n" +
              "ID: " + bookId + " | BookName: " + bookName + " | Author: " + author +
              "\nImportPrice: " + importPrice + " | ExportPrice: " + exportPrice + " | Interest: " + interest;
   }
}
