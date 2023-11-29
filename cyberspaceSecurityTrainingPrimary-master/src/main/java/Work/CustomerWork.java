package Work;

import Dao.CustomerOrderDao;
import Dao.CustomerUserDao;
import Elm.Customer;
import Elm.OrderItems;
import Elm.Orders;

import java.util.Scanner;

public class CustomerWork {
    public void cus(){
        System.out.println("                    ------个人用户界面------                    ");
        System.out.println(" ____________________________________________________________ ");
        System.out.println("|                       1.注册成为用户                         |");
        System.out.println("|                       2.更新用户信息                         |");
        System.out.println("|                       3.查看个人信息                         |");
        System.out.println("|                       4.注 销 用 户                          |");
        System.out.println("|                       5.查看并购买商品                        |");
        System.out.println("|                       6. 查看购物车                          |");
        System.out.println("|                       10.  退 出                            |");
        System.out.println("|____________________________________________________________|");
        Scanner input = new Scanner(System.in);
        CustomerUserDao cusUserDao = new CustomerUserDao();
        while(true){
            System.out.println("在此处输入(输入10退出):");
            int tag = input.nextInt();

            if(tag == 1){
                Customer customer = new Customer();
                System.out.println("输入用户名字:");
                input.nextLine();//吸收回车
                customer.setCname(input.nextLine());

                System.out.println("输入用户密码:");
                customer.setCpwd(input.nextLine());

                System.out.println("输入用户电话:");
                customer.setCtel(input.nextLine());

                System.out.println("输入用户地址:");
                customer.setCaddress(input.nextLine());

                cusUserDao.addUser(customer);
                System.out.println("                    ------注册成功------                    ");
            }
            else if (tag == 2) {
                Customer customer = new Customer();
                input.nextLine();//吸收回车
                System.out.println("输入用户ID:");
                customer.setCid(input.nextInt());

                input.nextLine();//吸收回车
                System.out.println("输入用户名字:");
                customer.setCname(input.nextLine());

                System.out.println("输入用户密码:");
                customer.setCpwd(input.nextLine());

                System.out.println("输入用户电话:");
                customer.setCtel(input.nextLine());

                System.out.println("输入用户地址:");
                customer.setCaddress(input.nextLine());

                cusUserDao.updateUser(customer);
                System.out.println("                    ------更新成功------                    ");
            }
            else if(tag == 3){
                Customer customer = new Customer();
                System.out.println("输入用户名字:");

                input.nextLine();//吸收回车
                customer.setCname(input.nextLine());

                System.out.println("输入用户密码:");
                customer.setCpwd(input.nextLine());

                customer = cusUserDao.loginUser(customer.getCname(), customer.getCpwd());
                if (customer != null) {
                    System.out.printf("用户ID:%-10d 用户名字:%S  用户联系方式:%s  用户地址:%s%n",
                            customer.getCid(), customer.getCname(), customer.getCtel(), customer.getCaddress());
                }
                else {
                    System.out.println("用户名或密码错误，登录失败！\n");
                }
            }
            else if(tag == 4){
                Customer customer = new Customer();
                input.nextLine();//吸收回车
                System.out.println("输入用户ID:");
                customer.setCid(input.nextInt());
                cusUserDao.deleteUser(customer.getCid());
                System.out.println("                    ------注销成功------                    ");
            }
            else if(tag == 5){
                System.out.println("                  ------查看并购买商品------                    ");
                Shoping shop = new Shoping();
                Customer customer = new Customer();
                System.out.println("输入用户ID:");
                customer.setCid(input.nextInt());
                shop.shoping(customer.getCid());
            }
            else if(tag == 6){
                System.out.println("                  ------查看并管理订单------                     ");
                Customer customer = new Customer();
                CustomerOrderDao CODao = new CustomerOrderDao();

                System.out.println("输入用户ID:");
                customer.setCid(input.nextInt());

                customer = CODao.checkCustomer(customer.getCid());

                CODao.checkOrder(customer);
                for(Orders orders : CODao.checkOrder(customer)){
                    System.out.printf("订单ID:%-10d 订单时间:%S  商家ID:%-10d  商家地址:%s%n",
                            orders.getOrderId(), orders.getOrderDate(), orders.getBid(), orders.getBaddress());
                }
                System.out.println("是否查看订单详细(Y|N):");
                char t = input.next().charAt(0);
                if(t == 'y'){
                    Orders orders = new Orders();
                    System.out.println("输入订单ID:");
                    orders.setOrderId(input.nextInt());
                    CODao.checkOrderItem(orders.getOrderId());
                    for(OrderItems items: CODao.checkOrderItem(orders.getOrderId())){
                        System.out.printf("单品名称:%s  单品价格:%f  单品数量:%-5d%n",
                                items.getItemName(), items.getItemPrice(), items.getItemCount());
                    }
                }
            }
            else{
                System.out.println("                ------已退出个人用户界面------                 ");
                break;
            }
        }
    }
}
