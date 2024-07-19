import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Administrator implements Functions {
    private String adminCode;
    private String adminId;
    Scanner scanner = new Scanner(System.in);
    private List<Customer> customerList;
    private List<Goods> goodsList;


    public Administrator() {
        this.adminId = "admin";
        this.adminCode = "ynuinfo#777";
        this.customerList = Customer.getCustomerList();
        this.goodsList = Goods.getGoodsList();
    }

    // 修改管理员密码
    public void changeAdminPassword() {
        System.out.println("输入新密码:");
        adminCode = scanner.nextLine();
        System.out.println("密码成功更改为：" + adminCode);
    }

    public void resetCustomerPassword(String customerId) {
        for (Customer customer : customerList) {
            if (customer.getCustomerId().equals(customerId)) {
                customer.resetPassword();
                System.out.println("客户ID: " + customerId + " 的密码已被重置。");
                return;
            }
        }
        System.out.println("未找到客户ID: " + customerId);
    }

    public void listAllCustomers() {
        Customer.listAllCustomers(customerList);
    }

    public void deleteCustomer(String customerId) {
        Customer.deleteCustomer(customerList, customerId, scanner);
    }

    public void queryCustomer() {
        Customer.queryCustomer(customerList);
    }

    // 列出所有商品的信息
    public void listAllGoods() {
        for (Goods goods : goodsList) {
            System.out.println(goods.toString());
        }
    }

    // 添加商品的信息
    public void addGoods() {
        System.out.println("请输入商品信息：");

        System.out.print("商品ID: ");
        String productId = scanner.nextLine();

        System.out.print("商品名称: ");
        String productName = scanner.nextLine();

        System.out.print("制造商: ");
        String manufacturer = scanner.nextLine();

        System.out.print("生产日期: ");
        String productionDate = scanner.nextLine();

        System.out.print("型号: ");
        String model = scanner.nextLine();

        System.out.print("进价: ");
        int purchasePrice = scanner.nextInt();

        System.out.print("零售价: ");
        int retailPrice = scanner.nextInt();

        System.out.print("数量: ");
        int quantity = scanner.nextInt();

        // 清空输入缓冲区
        scanner.nextLine();

        // 创建新的Goods对象
        Goods newGoods = new Goods(productId, productName, manufacturer, productionDate,
                model, purchasePrice, retailPrice, quantity);

        // 调用addGoods方法添加商品
        goodsList.add(newGoods);
        System.out.println("添加成功");
    }

    // 修改商品的信息
    public void modifyGoods() {
        System.out.print("请输入要修改的商品ID: ");
        String productIdToModify = scanner.nextLine();

        for (Goods goods : goodsList) {
            if (goods.getProductId().equals(productIdToModify)) {
                System.out.println("找到商品: " + goods);
                System.out.println("请输入新的信息（如果不修改某项，请直接按回车）：");

                System.out.print("新商品名称 (当前: " + goods.getProductName() + "): ");
                String newName = scanner.nextLine();
                if (!newName.isEmpty()) goods.setProductName(newName);

                System.out.print("新制造商 (当前: " + goods.getManufacturer() + "): ");
                String newManufacturer = scanner.nextLine();
                if (!newManufacturer.isEmpty()) goods.setManufacturer(newManufacturer);

                System.out.print("新生产日期 (当前: " + goods.getProductionDate() + "): ");
                String newDate = scanner.nextLine();
                if (!newDate.isEmpty()) goods.setProductionDate(newDate);

                System.out.print("新型号 (当前: " + goods.getModel() + "): ");
                String newModel = scanner.nextLine();
                if (!newModel.isEmpty()) goods.setModel(newModel);

                System.out.print("新进价 (当前: " + goods.getPurchasePrice() + "): ");
                String newPurchasePrice = scanner.nextLine();
                if (!newPurchasePrice.isEmpty()) goods.setPurchasePrice(Integer.parseInt(newPurchasePrice));

                System.out.print("新零售价 (当前: " + goods.getRetailPrice() + "): ");
                String newRetailPrice = scanner.nextLine();
                if (!newRetailPrice.isEmpty()) goods.setRetailPrice(Integer.parseInt(newRetailPrice));

                System.out.print("新数量 (当前: " + goods.getQuantity() + "): ");
                String newQuantity = scanner.nextLine();
                if (!newQuantity.isEmpty()) goods.setQuantity(Integer.parseInt(newQuantity));

                System.out.println("商品信息已更新: " + goods);
                return;
            }
        }

        System.out.println("未找到ID为 " + productIdToModify + " 的商品。");
    }

    // 删除商品的信息
    public void deleteGoods() {
        System.out.print("请输入要删除的商品ID: ");
        String productIdToDelete = scanner.nextLine();

        for (Iterator<Goods> iterator = goodsList.iterator(); iterator.hasNext(); ) {
            Goods goods = iterator.next();
            if (goods.getProductId().equals(productIdToDelete)) {
                System.out.println("找到商品: " + goods);
                System.out.println("警告：删除后无法恢复。");
                System.out.print("您确定要删除这个商品吗？(y/n): ");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("y")) {
                    iterator.remove();
                    System.out.println("商品已成功删除。");
                } else {
                    System.out.println("删除操作已取消。");
                }
                return;
            }
        }

        System.out.println("未找到ID为 " + productIdToDelete + " 的商品。");
    }

    // 查询商品的信息
    public void searchGoods() {
        System.out.println("请输入商品名称（或留空以跳过）：");
        String productName = scanner.nextLine().trim();
        System.out.println("请输入生产厂家（或留空以跳过）：");
        String manufacturer = scanner.nextLine().trim();
        System.out.println("请输入最低零售价格（或留空以跳过）：");
        String retailPriceInput = scanner.nextLine().trim();

        int minRetailPrice = retailPriceInput.isEmpty() ? -1 : Integer.parseInt(retailPriceInput);

        List<Goods> result = new ArrayList<>();
        for (Goods goods : goodsList) {
            boolean matches = productName.isEmpty() || goods.getProductName().equalsIgnoreCase(productName);
            if (!manufacturer.isEmpty() && !goods.getManufacturer().equalsIgnoreCase(manufacturer)) {
                matches = false;
            }
            if (minRetailPrice > -1 && goods.getRetailPrice() < minRetailPrice) {
                matches = false;
            }
            if (matches) {
                result.add(goods);
            }
        }
    }


    @Override
    public void login() {
        System.out.println("---当前登入管理员系统---\n默认ID为：" + adminId + "\n默认密码为：" + adminCode);
    }

    @Override
    public void logout() {
        System.out.println("管理员系统已退出");
    }
}
