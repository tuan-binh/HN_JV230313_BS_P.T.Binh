package ra.bussiness.config;

public class Validate {

   //   validate không được để trống
   public static boolean checkSpace(String str) {
      if (str.trim().equals("")) {
         System.err.println("Không được để trắng hoặc bỏ trống");
         return false;
      }
      return true;
   }

   //   validate description
   public static boolean checkDescription(String str) {
      if (str.length() < 10) {
         System.err.println("Nhập vào mô tả có độ dài lơn hơn 10");
         return false;
      }
      return true;
   }

   //   validate importPrice
   public static boolean checkImportPrice(double importPrice) {
      if (importPrice <= 0) {
         System.err.println("Vui lòng nhập lại giá nhập lớn hơn 0");
         return false;
      }
      return true;
   }

   //   validate exportPrice
   public static boolean checkExportPrice(double exportPrice, double importPrice) {
      if (importPrice * 1.2 >= exportPrice) {
         System.err.println("Vui lòng nhập lại (giá nhập < giá xuất)");
         return false;
      }
      return true;
   }
}
