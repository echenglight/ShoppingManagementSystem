import java.util.ArrayList;

public class Goods {
    private String productId;
    private String productName;
    private String manufacturer;
    private String productionDate;
    private String model;
    private int purchasePrice;
    private int retailPrice;
    private int quantity;

    public Goods(String productId, String productName, String manufacturer, String productionDate,
                 String model, int purchasePrice, int retailPrice, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.productionDate = productionDate;
        this.model = model;
        this.purchasePrice = purchasePrice;
        this.retailPrice = retailPrice;
        this.quantity = quantity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProductId() {
        return productId;
    }


    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", productionDate='" + productionDate + '\'' +
                ", model='" + model + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", retailPrice=" + retailPrice +
                ", quantity=" + quantity +
                '}';
    }

    // 静态块，用于初始化商品列表
    private static final ArrayList<Goods> goodsList = new ArrayList<>();

    static {
        goodsList.add(new Goods("001", "可乐", "可口可乐公司", "2023-06-01", "瓶装", 2, 3, 100));
        goodsList.add(new Goods("002", "薯片", "乐事", "2023-05-15", "袋装", 5, 8, 50));
        goodsList.add(new Goods("003", "巧克力", "费列罗", "2023-04-20", "盒装", 10, 15, 30));
        goodsList.add(new Goods("004", "牛奶", "蒙牛", "2023-07-10", "瓶装", 3, 5, 200));
        goodsList.add(new Goods("005", "面包", "好丽友", "2023-06-25", "袋装", 4, 6, 80));
    }

    public static ArrayList<Goods> getGoodsList() {
        return goodsList;
    }
}
