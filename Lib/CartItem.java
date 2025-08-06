package Lib;

public class CartItem {
    private final Product product;
    private int quantity;
    /**
     * ตรวจสอบค่าภายในของวัตถุ (Representation Invariant)
     * - product ต้องไม่เป็น null
     * - quantity ต้องไม่ติดลบ
    */
    private void checkRep(){
        if (product == null) throw new RuntimeException("RI violate: product error");
        if (quantity < 0) throw new RuntimeException("RI violate: quantity error");
    }
    /**
     * สร้างออบเจ็กต์ {@code CartItem} ด้วยสินค้าและจำนวนที่กำหนด
     *
     * @param product สินค้า
     * @param quantity จำนวนที่เลือกซื้อ (ต้องมากกว่าหรือเท่ากับ 0)
     * @throws RuntimeException ถ้า product เป็น null หรือ quantity < 0
    */
    public CartItem(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
        checkRep();
    }
    /**
     * ดึงสินค้าในรายการ
     *
     * @return สินค้า {@link Product}
     */
    public Product getProduct() { return product;}
    /**
     * ดึงจำนวนสินค้าที่อยู่ในตะกร้า
     *
     * @return จำนวนสินค้า
     */
    public int getQuantity() { return quantity;}
    /**
     * เพิ่มจำนวนสินค้าจากค่าที่ระบุ
     *
     * @param amount จำนวนที่ต้องการเพิ่ม (ต้องเป็นค่าบวกหรือศูนย์)
     * @throws RuntimeException ถ้า amount < 0
     */
    public void increasesQuantity(int amount){
        if(amount >= 0) this.quantity = quantity + amount;
        else throw new RuntimeException("RI violate: amount error");
        checkRep();
    }
}
