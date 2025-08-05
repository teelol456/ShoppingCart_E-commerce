package Lib;
import java.util.*;

public class ShoppingCart {

    private ArrayList<CartItem> items = new ArrayList<>();
    private ProductCatalog catalog;
    private PricingService pricing;

    private void checkRep(){
        if (items == null) throw new RuntimeException("RI violate: items error");
        for(int i=0;i<items.size();i++){
            for(int j = i+1;j < items.size();j++){
                if(items.get(i).equals(items.get(j))) throw new RuntimeException("RI violate: items error");
            }
        }
    }

    public ShoppingCart(PricingService pricing,ProductCatalog catalog){
        this.pricing = pricing;
        this.catalog = catalog;
    }

    public void addItem(String productId, int quantity){
        catalog.findById(productId);
        
    }
}
