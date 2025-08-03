package Lib.Discount;
import Lib.*;

public interface DiscountStrategy {
    double calculatePrice(CartItem item);
}
