package Lib;
import java.util.*;

public class ProductCatalog {
    private ArrayList<Product> products = new ArrayList<>();
    
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

    public void addProduct(Product product){
        products.add(product);
    }
    public Product findById(String productId){
        for(Product p : products){
            if(p.getProductId().equals(productId)) return p;
        }
        return null;
    }
}
