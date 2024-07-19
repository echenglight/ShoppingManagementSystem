import java.util.ArrayList;
import java.util.List;

class Order {
    private List<Goods> items;
    private double totalAmount;
    private String paymentMethod;

    public Order(List<Goods> items, double totalAmount, String paymentMethod) {
        this.items = new ArrayList<>(items);
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("支付方式: ").append(paymentMethod).append("\n");
        sb.append("总金额: ").append(totalAmount).append("\n");
        sb.append("商品列表:\n");
        for (Goods item : items) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }
}



