package Lib;

public final class Product{
    private final String productId;
    private final String productName;
    private final double price;

    // Rep Inveriant (RI)
    //  productId and productName ไม่ใช่ Null และ เว้นว่าง
    //  price >= 0
    //  
    // Abstraction Function (AF)
    //  AF(productId, productName, price)
    
    /*
     * ตรวจสอบว่า RI จริงไหม
     */

    private void checkRep(){
        if (productId == null || productId.isBlank()) throw new RuntimeException("RI violate: productId error");
        if (productName == null || productId.isBlank()) throw new RuntimeException("RI violate: productName error");
        if (price < 0) throw new RuntimeException("RI violate: price error");
    }

    /*
     *
     * 
     */
    public Product(String productId, String productName ,double price){
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        checkRep();
    }
    public String getProductId() { return productId;}
    public String getProductName() { return productName;}
    public double getPice() { return price;}
    /*
     * 
     * 
     * 
     */
    @Override
    public boolean equals(Object obj) {
    // ถ้าอ็อบเจกต์นี้เป็นอ็อบเจกต์เดียวกับที่ส่งเข้ามา ก็ถือว่าเท่ากันเลย
        if (this == obj) return true;
    // ถ้าอ็อบเจกต์ที่ส่งมาเป็น null หรือคลาสไม่ตรงกัน ก็ไม่เท่ากัน
        if (obj == null || getClass() != obj.getClass()) return false;
    // แปลงอ็อบเจกต์เป็นคลาส Product
        Product product = (Product) obj;
    // เช็คว่า productId เท่ากันหรือไม่ (ต้องตรวจ null ด้วย)
        return productId != null ? productId.equals(product.productId) : product.productId == null;
    }
}
