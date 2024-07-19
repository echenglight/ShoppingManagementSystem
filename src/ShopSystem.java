import java.util.Scanner;

public class ShopSystem {
    Scanner scanner = new Scanner(System.in);
    Administrator administrator = new Administrator();
    Customer customer = new Customer();

    public void initialization() {
        while (true) {
            System.out.println("请选择登录账户:1.管理员 2.客户");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 清空缓冲区
            if (choice == 1) {
                administrator.login();
                AdminMenu();
                break;
            } else if (choice == 2) {
                while (true) {
                    System.out.println("请选择1.注册或是2.登录");
                    int choose = scanner.nextInt();
                    scanner.nextLine(); // 清空缓冲区
                    if (choose == 1) {
                        customer.register();
                        customer.login();
                        break;
                    } else if (choose == 2) {
                        customer.login();
                        break;
                    } else {
                        System.out.println("输入不合法，重新输入");
                    }
                }
                CustomerMenu();
                break;
            } else {
                System.out.println("无效输入，请重新选择");
            }
        }
    }

    public void AdminMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n===== 管理员系统 =====");
            System.out.println("1.密码管理");
            System.out.println("2.客户管理");
            System.out.println("3.商品管理");
            System.out.println("4.退出登录");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 清空缓冲区
            switch (choice) {
                case 1:
                    while (true) {
                        System.out.println("1.修改管理员密码\n2.修改客户密码");
                        int judge = scanner.nextInt();
                        scanner.nextLine(); // 清空缓冲区
                        if (judge == 1) {
                            administrator.changeAdminPassword();
                            break;
                        } else if (judge == 2) {
                            System.out.print("请输入客户ID: ");
                            String customerId = scanner.nextLine();
                            administrator.resetCustomerPassword(customerId);
                            break;
                        } else {
                            System.out.println("无效输入,请重新输入");
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println("1.列出所有客户信息");
                        System.out.println("2.删除客户信息");
                        System.out.println("3.查询客户信息");
                        int judge = scanner.nextInt();
                        scanner.nextLine(); // 清空缓冲区
                        if (judge == 1) {
                            administrator.listAllCustomers();
                            break;
                        } else if (judge == 2) {
                            System.out.print("请输入客户ID: ");
                            String customerId = scanner.nextLine();
                            administrator.deleteCustomer(customerId);
                            break;
                        } else if (judge == 3) {
                            administrator.queryCustomer();
                            break;
                        } else {
                            System.out.println("无效输入");
                        }
                    }
                    break;
                case 3:
                    while (true) {
                        System.out.println("1.列出所有商品的信息");
                        System.out.println("2.添加商品的信息");
                        System.out.println("3.修改商品的信息");
                        System.out.println("4.删除商品的信息");
                        System.out.println("5.查询商品的信息");
                        int judge = scanner.nextInt();
                        scanner.nextLine(); // 清空缓冲区
                        if (judge == 1) {
                            administrator.listAllGoods();
                            break;
                        } else if (judge == 2) {
                            administrator.addGoods();
                            break;
                        } else if (judge == 3) {
                            administrator.modifyGoods();
                            break;
                        } else if (judge == 4) {
                            administrator.deleteGoods();
                            break;
                        } else if (judge == 5) {
                            administrator.searchGoods();
                            break;
                        } else {
                            System.out.println("无效输入");
                        }
                    }
                    break;
                case 4:
                    administrator.logout();
                    running = false;
                    break;
                default:
                    System.out.println("错误输入，请重新开始");
            }
        }
    }

    public void CustomerMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n===== 客户系统 =====");
            System.out.println("请选择功能");
            System.out.println("1.密码管理");
            System.out.println("2.购物");
            System.out.println("3.退出登录");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 清空缓冲区
            switch (choice) {
                case 1:
                    while (true) {
                        System.out.println("1.修改自身密码");
                        System.out.println("2.忘记密码");
                        int judge = scanner.nextInt();
                        scanner.nextLine(); // 清空缓冲区
                        if (judge == 1) {
                            customer.changeMyCode();
                            break;
                        } else if (judge == 2) {
                            customer.forgetCode();
                            break;
                        } else {
                            System.out.println("无效输入");
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println("1.将商品加入购物车");
                        System.out.println("2.将商品从购物车中移除");
                        System.out.println("3.修改购物车中的商品");
                        System.out.println("4.结账");
                        System.out.println("5.查看购物历史");
                        int judge = scanner.nextInt();
                        scanner.nextLine(); // 清空缓冲区
                        if (judge == 1) {
                            System.out.println("请输入商品名称:");
                            String productName = scanner.nextLine();
                            System.out.println("请输入数量:");
                            int quantity = scanner.nextInt();
                            scanner.nextLine(); // 清空缓冲区
                            Goods item = findGoodsName(productName);
                            if (item != null) {
                                customer.addItem(item, quantity);
                            } else {
                                System.out.println("商品未找到");
                            }
                            break;
                        } else if (judge == 2) {
                            System.out.println("请输入商品名称:");
                            String productName = scanner.nextLine();
                            customer.removeItem(productName);
                            break;
                        } else if (judge == 3) {
                            System.out.println("请输入商品名称:");
                            String productName = scanner.nextLine();
                            System.out.println("请输入新数量:");
                            int newQuantity = scanner.nextInt();
                            scanner.nextLine(); // 清空缓冲区
                            customer.modifyItem(productName, newQuantity);
                            break;
                        } else if (judge == 4) {
                            customer.checkout();
                            break;
                        } else if (judge == 5) {
                            customer.viewHistory();
                            break;
                        } else {
                            System.out.println("无效输入");
                        }
                    }
                    break;
                case 3:
                    customer.logout();
                    running = false;
                    break;
                default:
                    System.out.println("错误输入，请重新开始");
            }
        }
    }

    private Goods findGoodsName(String productName) {
        for (Goods goods : Goods.getGoodsList()) {
            if (goods.getProductName().equals(productName)) {
                return goods;
            }
        }
        return null;
    }
}

