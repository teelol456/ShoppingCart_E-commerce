package Lib;
import java.util.*;
import Lib.Discount.*;

public class PricingService extends ConcreteStrategies {
    private record StrategyRule(String sku, DiscountStrategy strategy){}
    private final ArrayList<StrategyRule> strategies = new ArrayList<>();
    private final DiscountStrategy defaulStrategy = new DefaultPricingStrategy();
    /**
     * เพิ่มหรืออัปเดตกลยุทธ์สำหรับสินค้าเฉพาะ SKU
     * หากมีอยู่แล้วจะทำการแทนที่กลยุทธ์เก่า
     *
     * @param sku รหัสสินค้า (Product ID)
     * @param strategy กลยุทธ์ส่วนลดที่จะใช้กับสินค้า
     */
    public void addStrategy(String sku, DiscountStrategy strategy){
        StrategyRule ruleToRemove = null;
        for(StrategyRule rule : strategies){
            if(rule.sku().equals(sku)){
                ruleToRemove = rule;
                break;
            }
        }
        if (ruleToRemove != null){
            strategies.remove(ruleToRemove);
        }
        strategies.add(new StrategyRule(sku, strategy));
    }
    /**
     * คำนวณราคาสินค้าในตะกร้าตามกลยุทธ์ที่กำหนดไว้
     * หากไม่พบกลยุทธ์เฉพาะ จะใช้กลยุทธ์เริ่มต้น
     *
     * @param item สินค้าในตะกร้า
     * @return ราคาที่คำนวณแล้วตามกลยุทธ์ที่กำหนด
     */
    public double calculateItemPrice(CartItem item){
        String sku = item.getProduct().getProductId();
        for(StrategyRule rule : strategies){
            if(rule.sku().equals(sku)){
                return rule.strategy().calculatePrice(item);
            }
        }
        return defaulStrategy.calculatePrice(item);
    }
}
