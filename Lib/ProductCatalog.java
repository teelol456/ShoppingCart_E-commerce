package Lib;
import java.util.*;

public class ProductCatalog {
    private ArrayList<Product> products = new ArrayList<>();
    /**
     * ตรวจสอบความถูกต้องของข้อมูลภายใน (Representation Invariant)
     * - products ต้องไม่เป็น null
     * - สินค้าทุกชิ้นต้องไม่ซ้ำกัน (ใช้เมธอด equals())
     */
    private void checkRep(){
        if (products == null) throw new RuntimeException("RI violate: products error");
        for(int i=0;i<products.size();i++){
            for(int j = i+1;j < products.size();j++){
                if(products.get(i).equals(products.get(j))) throw new RuntimeException("RI violate: products error");
            }
        }

    }
    public ProductCatalog(){
        checkRep();
    }
    /**
     * เพิ่มสินค้าใหม่เข้ารายการสินค้า
     * ไม่ได้เช็กซ้ำโดยตรงในเมธอดนี้ แต่จะตรวจสอบโดย checkRep() หากเรียกใช้
     *
     * @param product สินค้าที่จะเพิ่ม
     */
    public void addProduct(Product product){
        products.add(product);
    }
    /**
     * ค้นหาสินค้าในรายการโดยใช้รหัสสินค้า (productId)
     *
     * @param productId รหัสสินค้าที่ต้องการค้นหา
     * @return สินค้าที่ตรงกับรหัส ถ้าไม่พบจะคืนค่า {@code null}
     */
    public Product findById(String productId){
        for(Product p : products){
            if(p.getProductId().equals(productId)) return p;
        }
        return null;
    }
}
