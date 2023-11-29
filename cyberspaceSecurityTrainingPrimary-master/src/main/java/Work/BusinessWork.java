package Work;

import Dao.BusinessOrderDao;
import Dao.BusinessUserDao;
import Elm.BusiUser;
import Elm.OrderItems;
import Elm.Orders;

import java.util.Scanner;

public class BusinessWork {
    public void Busi(){
        System.out.println("                    ------商家用户界面------                    ");
        System.out.println(" ____________________________________________________________ ");
        System.out.println("|                       1.注册成为商家                         |");
        System.out.println("|                       2.更新商家信息                         |");
        System.out.println("|                       3.查 看 商 家                         |");
        System.out.println("|                       4.注 销 商 家                         |");
        System.out.println("|                       5.商家商品管理                         |");
        System.out.println("|                       6.商家订单管理                         |");
        System.out.println("|                       10.  退 出                            |");
        System.out.println("|____________________________________________________________|");
        Scanner input = new Scanner(System.in);
        BusinessUserDao bUserDao = new BusinessUserDao();
        while(true){
            System.out.println("在此处输入在此处输入(输入10退出):");
            int tag = input.nextInt();

            if(tag == 1){
                BusiUser Buser = new BusiUser();
                System.out.println("输入商家名字:");
                input.nextLine();//吸收回车
                Buser.setBname(input.nextLine());

                System.out.println("输入商家密码:");
                Buser.setBpwd(input.nextLine());

                System.out.println("输入商家电话:");
                Buser.setBtel(input.nextLine());

                System.out.println("输入商家地址:");
                Buser.setBaddress(input.nextLine());

                bUserDao.addUser(Buser);
                System.out.println("                    ------注册成功------                    ");
            }
            else if (tag == 2) {
                BusiUser Buser = new BusiUser();
                input.nextLine();//吸收回车
                System.out.println("输入商家ID:");
                Buser.setBid(input.nextInt());

                System.out.println("输入商家名字:");
                Buser.setBname(input.nextLine());

                System.out.println("输入商家密码:");
                Buser.setBpwd(input.nextLine());

                System.out.println("输入商家电话:");
                Buser.setBtel(input.nextLine());

                System.out.println("输入商家地址:");
                Buser.setBaddress(input.nextLine());

                bUserDao.updateUser(Buser);
                System.out.println("                    ------更新成功------                    ");
            }
            else if(tag == 3){
                BusiUser Buser = new BusiUser();
                System.out.println("输入商家名字:");

                input.nextLine();//吸收回车
                Buser.setBname(input.nextLine());

                System.out.println("输入商家密码:");
                Buser.setBpwd(input.nextLine());

                Buser = bUserDao.loginUser(Buser.getBname(), Buser.getBpwd());
                if (Buser != null) {
                    System.out.printf("商家ID:%-10d 商家名字:%S  商家联系方式:%s  商家地址:%s%n",
                            Buser.getBid(), Buser.getBname(), Buser.getBtel(), Buser.getBaddress());
                }
                else {
                    System.out.println("用户名或密码错误，登录失败！\n");
                }
            }
            else if(tag == 4){
                BusiUser Buser = new BusiUser();
                input.nextLine();//吸收回车
                System.out.println("输入商家ID:");
                Buser.setBid(input.nextInt());
                bUserDao.deleteUser(Buser.getBid());
                System.out.println("                    ------注销成功------                    ");
            }
            else if(tag == 5){
                GoodsWork gWork = new GoodsWork();
                BusiUser Buser = new BusiUser();
                System.out.println("输入商家ID:");
                Buser.setBid(input.nextInt());
                gWork.Goods(Buser);
            }
            else if(tag == 6){
                System.out.println("                  ------查看商家订单------                     ");
                BusinessOrderDao BODao = new BusinessOrderDao();
                BusiUser Buser = new BusiUser();
                System.out.println("输入商家ID:");
                Buser.setBid(input.nextInt());
                BODao.checkOrder(Buser.getBid());
                for(Orders orders: BODao.checkOrder(Buser.getBid())){
                    System.out.printf("订单ID:%-10d 订单时间:%S  顾客联系方式:%s  顾客地址:%s%n",
                            orders.getOrderId(), orders.getOrderDate(), orders.getCtel(), orders.getCaddress());
                }
                System.out.println("是否查看订单详细(y|n):");
                char t = input.next().charAt(0);;
                if(t == 'y'){
                    Orders orders = new Orders();
                    System.out.println("输入订单ID:");
                    orders.setOrderId(input.nextInt());
                    BODao.checkOrderItem(orders.getOrderId());
                    for(OrderItems items: BODao.checkOrderItem(orders.getOrderId())){
                        System.out.printf("单品名称:%s  单品价格:%f  单品数量:%-5d%n",
                                items.getItemName(), items.getItemPrice(), items.getItemCount());
                    }
                }
            }
            else{
                System.out.println("                ------已退出商家用户界面------                 ");
                break;
            }
        }
    }
}
