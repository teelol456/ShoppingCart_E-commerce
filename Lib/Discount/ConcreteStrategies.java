package Lib.Discount;
import Lib.*;

public class ConcreteStrategies{
    /**
     * กลยุทธ์การคิดราคาแบบปกติ (ไม่มีส่วนลด)
     */
    public static class DefaultPricingStrategy implements DiscountStrategy{
        /**
         * คำนวณราคารวมของสินค้าตามราคาปกติ (ไม่ลดราคา)
         *
         * @param item รายการสินค้าในตะกร้า
         * @return ราคาสินค้า × จำนวน
         */
        @Override
        public double calculatePrice(CartItem item) {
            return item.getProduct().getPrice() * item.getQuantity();
        }
    } 
    /**
     * กลยุทธ์ส่วนลดแบบ "ซื้อ 1 แถม 1" (BOGO - Buy One Get One Free)
     * โดยจะคิดเงินเฉพาะครึ่งหนึ่งของจำนวนสินค้า 
     */  
    public static class BogoDiscountStrategy implements DiscountStrategy{
        /**
         * คำนวณราคารวมโดยใช้โปรโมชั่นซื้อ 1 แถม 1
         * เช่น ถ้าซื้อ 3 ชิ้น จะจ่ายเพียง 2 ชิ้น
         *
         * @param item รายการสินค้าในตะกร้า
         * @return ราคาสินค้าหลังหักจำนวนที่แถม
         */
        @Override
        public double calculatePrice(CartItem item) {
            return (item.getProduct().getPrice() * item.getQuantity()) - (item.getProduct().getPrice() * Math.ceil(item.getQuantity() / 2));
        }
    }
    /**
     * กลยุทธ์ส่วนลดแบบซื้อจำนวนมาก (Bulk Discount)
     * ถ้าจำนวนสินค้ามากกว่าหรือเท่ากับขั้นต่ำที่กำหนด จะได้ส่วนลดเป็นเปอร์เซ็นต์
     */
    public static class BulkDiscountStrategy implements DiscountStrategy{
        private int minQuantity;
        private double discountPercentage;
         /**
         * สร้างกลยุทธ์ส่วนลดแบบจำนวนมาก
         *
         * @param minQuantity จำนวนขั้นต่ำที่ต้องซื้อถึงจะได้ส่วนลด
         * @param discountPercentage เปอร์เซ็นต์ส่วนลด (เช่น 0.10 = 10%)
         */
        public BulkDiscountStrategy(int minQuantity, double discountPercentage) {
            this.minQuantity = minQuantity;
            this.discountPercentage = discountPercentage;
        }
        /**
         * คำนวณราคาสินค้าหลังหักส่วนลดแบบ Bulk
         * หากจำนวนสินค้า >= จำนวนขั้นต่ำ จะลดราคาตามเปอร์เซ็นต์ที่กำหนด
         *
         * @param item รายการสินค้าในตะกร้า
         * @return ราคาหลังหักส่วนลด หากมีสิทธิ์ได้รับ
         */
        @Override
        public double calculatePrice(CartItem item) {
            if(item.getQuantity() >= minQuantity){
                return ((item.getProduct().getPrice() * item.getQuantity()) - ((item.getProduct().getPrice() * item.getQuantity()) * discountPercentage));
            }
            return item.getProduct().getPrice() * item.getQuantity();
        }
    }
}
