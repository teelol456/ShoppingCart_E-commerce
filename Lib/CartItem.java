package Lib;

public class CartItem {
    private final Product product;
    private int quantity;



    private void checkRep(){
        if (product == null) throw new RuntimeException("RI violate: product error");
        if (quantity < 0) throw new RuntimeException("RI violate: quantity error");
    }

    public CartItem(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
        checkRep();
    }

    public Product getProduct() { return product;}
    public int getQuantity() { return quantity;}

    public void increasesQuantity(int amount){
        if(amount > 0) this.quantity = quantity + amount;
        else throw new RuntimeException("RI violate: amount error");
        checkRep();
    }
}
