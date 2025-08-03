package Lib.Discount;
import Lib.*;

public class ConcreteStrategies{

    public class DefaultPricingStrategy implements DiscountStrategy{
        @Override
        public double calculatePrice(CartItem item) {
            return item.getProduct().getPrice() * item.getQuantity();
        }
    }
    public class BogoDiscountStrategy implements DiscountStrategy{
        @Override
        public double calculatePrice(CartItem item) {
            return item.getProduct().getPrice() * item.getQuantity() / 2;
        }
    }
    public class BulkDiscountStrategy implements DiscountStrategy{
        public BulkDiscountStrategy(int i, double d) {
            
        }

        @Override
        public double calculatePrice(CartItem item) {
            return item.getProduct().getPrice() * item.getQuantity();
        }
    }
}
