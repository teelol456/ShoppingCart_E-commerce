package Lib;
import java.util.*;

/**
 * เป็นคลาสที่ใช้แทนตะกร้าสินค้าของลูกค้า
 * โดยเก็บรายการสินค้าที่เพิ่มเข้ามา พร้อมเชื่อมโยงกับระบบสินค้า {@link ProductCatalog}
 * และระบบคำนวณราคา {@link PricingService}
 *
 * <p>รองรับการเพิ่ม ลบ ล้างตะกร้า รวมถึงการคำนวณราคารวมทั้งหมด</p>
 */
public class ShoppingCart {
    
    private ArrayList<CartItem> items = new ArrayList<>();
    private ProductCatalog catalog;
    private PricingService pricing;
    /**
     * ตรวจสอบความถูกต้องภายใน (Representation Invariant)
     * - items ต้องไม่เป็น null
     * - ต้องไม่มีสินค้าซ้ำกันในตะกร้า (ใช้ equals)
     */
    private void checkRep(){
        if (items == null) throw new RuntimeException("RI violate: items error");
        for(int i=0;i<items.size();i++){
            for(int j = i+1;j < items.size();j++){  
                if(items.get(i).equals(items.get(j))) throw new RuntimeException("RI violate: items error");
            }
        }
    }
     /**
     * สร้างออบเจ็กต์ตะกร้าสินค้าใหม่
     *
     * @param pricing บริการคำนวณราคา
     * @param catalog แค็ตตาล็อกสินค้า
     */
    public ShoppingCart(PricingService pricing,ProductCatalog catalog){
        this.pricing = pricing;
        this.catalog = catalog;
    }
    /**
     * คืนค่าจำนวนรายการสินค้าในตะกร้า (นับเป็นจำนวนชนิด)
     *
     * @return จำนวนชนิดของสินค้าที่อยู่ในตะกร้า
     */
    public int getItemCount(){
        return items.size();
    }
    /**
     * ล้างตะกร้าทั้งหมด
     * ทำให้รายการสินค้าหายไปทั้งหมด
     */
    public void clearCart(){
        items.clear();
    }
     /**
     * เพิ่มสินค้าเข้าในตะกร้า หากมีสินค้าเดิมอยู่แล้วจะเพิ่มจำนวนให้
     * หากไม่มีสินค้าเดิม จะเพิ่มรายการใหม่
     *
     * @param productId รหัสสินค้า
     * @param quantity จำนวนที่ต้องการเพิ่ม (ต้อง > 0)
     */
    public void addItem(String productId, int quantity){
        if(catalog.findById(productId) != null){
            boolean F = false;
            if(items.size() <= 0) items.add(new CartItem(catalog.findById(productId), quantity));
            else {
                for(CartItem i : items){
                    if(i.getProduct().getProductId().equals(productId)){
                        i.increasesQuantity(quantity);
                        F = true;
                        break;
                    }
                }
                if(!F && quantity > 0) items.add(new CartItem(catalog.findById(productId), quantity));
            }
        }
        checkRep();
    }
    /**
     * ลบสินค้าออกจากตะกร้า (ลบตาม productId)
     * หากไม่มีสินค้านั้น จะไม่เกิดการเปลี่ยนแปลง
     *
     * @param productId รหัสสินค้าที่ต้องการลบ
     */
    public void removeItem(String productId){
        for(int i=0;i<items.size();i++){  
                if(items.get(i).getProduct().getProductId().equals(productId)) items.remove(i);
            }
        checkRep();
    }
     /**
     * คำนวณราคารวมของสินค้าทั้งหมดในตะกร้า
     * โดยใช้กลยุทธ์ราคาจาก {@link PricingService}
     *
     * @return ราคารวมทั้งหมดของสินค้าทุกชิ้น
     */
    public double getTotalPrice(){
        double total = 0;
        for(CartItem i : items){
            total += pricing.calculateItemPrice(i);
        }
        return total;
    }
}
