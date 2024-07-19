import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Customer implements Functions {
    private String customerId;
    private String customerName;
    private String customerCode;
    private String customerLevel;
    private String registrationTime;
    private double totalConsumption;
    private String phoneNumber;
    private String email;
    Scanner scanner = new Scanner(System.in);
    private List<Customer> customerList;
    private int loginAttempts = 0;
    private List<Goods> cartItems;
    private List<Order> orderHistory;


    public Customer() {
        this.customerList = new ArrayList<>();
        this.cartItems = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
    }

    private static final ArrayList<Customer> customersList = new ArrayList<>();

    static {
        customersList.add(new Customer("user123", "P@ssword123", "John Doe", "Gold", "2023-01-15", 5000.0, "123-456-7890", "john.doe@example.com"));
        customersList.add(new Customer("jane456", "S3cretC0de!", "Jane Smith", "Silver", "2023-03-22", 2000.0, "987-654-3210", "jane.smith@example.com"));
    }

    public static ArrayList<Customer> getCustomerList() {
        return customersList;
    }

    // 构造函数
    public Customer(String customerId, String password, String customerName, String customerLevel, String registrationTime, double totalConsumption, String phoneNumber, String email) {
        this.customerId = customerId;
        this.customerCode = password;
        this.customerName = customerName;
        this.customerLevel = customerLevel;
        this.registrationTime = registrationTime;
        this.totalConsumption = totalConsumption;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.cartItems = new ArrayList<>();
        this.orderHistory = new ArrayList<>();

    }

    @Override
    public void login() {
        System.out.println("请输入你的账户ID名：");
        String inputId = scanner.nextLine();
        System.out.println("请输入你的密码：");
        String inputPassword = scanner.nextLine();

        for (Customer customer : customersList) {
            if (customer.getCustomerId().equals(inputId)) {
                if (customer.loginAttempts >= 5) {
                    System.out.println("账户已锁定。");
                    return;
                }

                if (customer.customerCode.equals(inputPassword)) {
                    System.out.println("登录成功！");
                    customer.loginAttempts = 0;

                    // 重置登录尝试次数
                    return;
                } else {
                    customer.loginAttempts++;
                    System.out.println("密码错误！你还有 " + (5 - customer.loginAttempts) + " 次机会。");
                    if (customer.loginAttempts >= 5) {
                        System.out.println("账户已锁定，请联系管理员。");
                    }
                    return;
                }
            }
        }
        System.out.println("账户ID不存在。");
    }

    public void register() {
        System.out.println("欢迎使用购物系统，请按指示完成注册");
        String customerId;
        String password;

        while (true) {
            System.out.println("输入你的账户ID名（长度不少于5个字符）:");
            customerId = scanner.nextLine();
            if (isValidUsername(customerId)) {
                break;
            } else {
                System.out.println("用户名不符合要求或已被使用，请重新输入。");
            }
        }

        while (true) {
            System.out.println("输入你的密码（长度大于8个字符，必须包含大小写字母、数字和标点符号）:");
            password = scanner.nextLine();
            if (isValidPassword(password)) {
                break;
            } else {
                System.out.println("密码不符合要求，请重新输入。");
            }
        }

        Customer newCustomer = new Customer(customerId, password, "", "", "", 0.0, "", "");
        customerList.add(newCustomer);
        System.out.println("注册成功！请登录");
    }

    private boolean isValidUsername(String username) {
        return username != null && username.length() >= 5;
    }

    private boolean isValidPassword(String password) {
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*\\p{Punct}).{9,}$";
        return Pattern.matches(passwordPattern, password);
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId + ", Name: " + customerName + ", Code: " + customerCode +
                ", Level: " + customerLevel + ", Registration Time: " + registrationTime +
                ", Total Consumption: " + totalConsumption + ", Phone Number: " + phoneNumber +
                ", Email: " + email;
    }

    public void resetPassword() {
        while (true) {
            System.out.println("输入你的新密码（长度大于8个字符，必须包含大小写字母、数字和标点符号）:");
            String newPassword = scanner.nextLine();
            if (isValidPassword(newPassword)) {
                this.customerCode = newPassword;
                System.out.println("密码重置成功！");
                break;
            } else {
                System.out.println("密码不符合要求，请重新输入。");
            }
        }
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public static void listAllCustomers(List<Customer> customerList) {
        for (Customer customer : customerList) {
            System.out.println(customer);
        }
    }

    public static void deleteCustomer(List<Customer> customerList, String customerId, Scanner scanner) {
        Iterator<Customer> iterator = customerList.iterator();
        while (iterator.hasNext()) {
            Customer customer = iterator.next();
            if (customer.getCustomerId().equals(customerId)) {
                System.out.println("Are you sure you want to delete the customer with ID: " + customerId + "? (yes/no)");
                String confirmation = scanner.nextLine();
                if (confirmation.equalsIgnoreCase("yes")) {
                    iterator.remove();
                    System.out.println("Customer with ID: " + customerId + " has been deleted.");
                } else {
                    System.out.println("Deletion canceled.");
                }
                return;
            }
        }
        System.out.println("Customer with ID: " + customerId + " not found.");
    }

    public static void queryCustomer(List<Customer> customerList) {
        System.out.println("请输入用户ID或用户名");
        Scanner scanner = new Scanner(System.in);
        String identifier = scanner.nextLine();
        for (Customer customer : customerList) {
            if (customer.getCustomerId().equals(identifier) || customer.getCustomerName().equalsIgnoreCase(identifier)) {
                System.out.println(customer);
                return;
            }
        }
        System.out.println("Customer not found.");
    }

    public void changeMyCode() {
        System.out.println("输入你的新密码（长度大于8个字符，必须包含大小写字母、数字和标点符号）:");
        String newPassword = scanner.nextLine();
        if (isValidPassword(newPassword)) {
            this.customerCode = newPassword;
            System.out.println("密码更改成功！");
        } else {
            System.out.println("密码不符合要求，请重新输入。");
        }
    }

    public void forgetCode() {
        System.out.println("请输入你的用户名:");
        String username = scanner.nextLine();
        System.out.println("请输入你的注册邮箱:");
        String email = scanner.nextLine();
        for (Customer customer : customerList) {
            if (customer.getCustomerName().equals(username) && customer.getEmail().equals(email)) {
                String newCode = generateRandomPassword();
                customer.setCustomerCode(newCode);
                System.out.println("新的密码已发送到你的邮箱：" + email);
                System.out.println("新的密码为：" + newCode);
                return;
            }
        }
        System.out.println("用户名或邮箱错误，无法重置密码。");
    }

    private String generateRandomPassword() {
        return "NewP@ssw0rd";
    }

    public String getEmail() {
        return email;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    @Override
    public void logout() {
        System.out.println("顾客系统已退出");
    }

    public void addItem(Goods item, int quantity) {
        for (Goods cartItem : cartItems) {
            if (cartItem.getProductName().equals(item.getProductName())) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                System.out.println(item.getProductName() + " 数量增加到 " + cartItem.getQuantity());
                return;
            }
        }
        item.setQuantity(quantity);
        cartItems.add(item);
        System.out.println(item.getProductName() + " 已加入购物车，数量为 " + quantity);
    }

    // 将商品从购物车中移除
    public void removeItem(String productName) {
        for (Goods item : cartItems) {
            if (item.getProductName().equals(productName)) {
                cartItems.remove(item);
                System.out.println(item.getProductName() + " 已从购物车中移除");
                return;
            }
        }
        System.out.println("商品未找到");
    }

    // 修改购物车中的商品
    public void modifyItem(String productName, int newQuantity) {
        for (Goods item : cartItems) {
            if (item.getProductName().equals(productName)) {
                if (newQuantity <= 0) {
                    cartItems.remove(item);
                    System.out.println(item.getProductName() + " 已从购物车中清除");
                } else {
                    item.setQuantity(newQuantity);
                    System.out.println(item.getProductName() + " 数量已修改为 " + newQuantity);
                }
                return;
            }
        }
        System.out.println("商品未找到");
    }

    // 结账
    public void checkout() {
        if (cartItems.isEmpty()) {
            System.out.println("购物车为空，无法结账。");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("请选择支付方式（输入数字）：1. 支付宝  2. 微信  3. 银行卡");
        int choice = scanner.nextInt();
        String paymentMethod;

        switch (choice) {
            case 1:
                paymentMethod = "支付宝";
                break;
            case 2:
                paymentMethod = "微信";
                break;
            case 3:
                paymentMethod = "银行卡";
                break;
            default:
                System.out.println("无效的支付方式。");
                return;
        }

        double totalAmount = 0;
        for (Goods item : cartItems) {
            totalAmount += item.getRetailPrice() * item.getQuantity();
        }

        System.out.println("使用 " + paymentMethod + " 支付成功，总金额: " + totalAmount);

        Order order = new Order(cartItems, totalAmount, paymentMethod);
        orderHistory.add(order);

        // 清空购物车
        cartItems.clear();
    }

    // 查看购物历史
    public void viewHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("没有订单历史。");
            return;
        }

        System.out.println("订单历史：");
        for (Order order : orderHistory) {
            System.out.println(order);
        }
    }
}