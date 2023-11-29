package Work;

import Dao.RiderOrderDao;
import Dao.RiderUserDao;
import Elm.OrderItems;
import Elm.Orders;
import Elm.Rider;

import java.util.Scanner;

public class RiderWork {
    public void Rid(){
        System.out.println("                    ------骑手用户界面------                    ");
        System.out.println(" ____________________________________________________________ ");
        System.out.println("|                       1.注册成为骑手                         |");
        System.out.println("|                       2.更新骑手信息                         |");
        System.out.println("|                       3.查看骑手信息                         |");
        System.out.println("|                       4.未接订单列表                         |");
        System.out.println("|                       5.已接订单列表                         |");
        System.out.println("|                       6.注 销 骑 手                         |");
        System.out.println("|                       10.  退 出                           |");
        System.out.println("|____________________________________________________________|");
        Scanner input = new Scanner(System.in);
        RiderUserDao rUserDao = new RiderUserDao();
        while(true){
            System.out.println("在此处输入(输入10推出):");
            int tag = input.nextInt();

            if(tag == 1){
                Rider rider = new Rider();
                System.out.println("输入骑手名字:");
                input.nextLine();//吸收回车
                rider.setRname(input.nextLine());

                System.out.println("输入账号密码:");
                rider.setRpwd(input.nextLine());

                System.out.println("输入骑手联系方式:");
                rider.setRtel(input.nextLine());

                rUserDao.addUser(rider);
                System.out.println("                    ------注册成功------                    ");
            }
            else if (tag == 2) {
                Rider rider = new Rider();
                input.nextLine();//吸收回车
                System.out.println("输入骑手ID:");
                rider.setRid(input.nextInt());

                System.out.println("输入骑手名字:");
                input.nextLine();//吸收回车
                rider.setRname(input.nextLine());

                System.out.println("输入账号密码:");
                rider.setRpwd(input.nextLine());

                System.out.println("输入骑手联系方式:");
                rider.setRtel(input.nextLine());

                rUserDao.updateUser(rider);
                System.out.println("                    ------更新成功------                    ");
            }
            else if(tag == 3){
                Rider rider = new Rider();
                System.out.println("输入骑手名字:");
                input.nextLine();//吸收回车
                rider.setRname(input.nextLine());

                System.out.println("输入账号密码:");
                rider.setRpwd(input.nextLine());

                rider = rUserDao.loginUser(rider.getRname(), rider.getRpwd());
                if (rider != null) {
                    System.out.printf("骑手ID:%-10d 骑手名字:%S  骑手联系方式:%s%n",
                            rider.getRid(), rider.getRname(), rider.getRtel());
                }
                else {
                    System.out.println("用户名或密码错误，登录失败！\n");
                }
            }
            else if(tag == 4){
                System.out.println("                  ------未接订单列表------                  ");
                Rider rider = new Rider();
                System.out.println("输入骑手ID:");
                rider.setRid(input.nextInt());

                RiderOrderDao Rorder = new RiderOrderDao();
                Rorder.checkOrderNullRid();
                if(!Rorder.checkOrderNullRid().isEmpty()){
                    for(Orders orders : Rorder.checkOrderNullRid()){
                        System.out.printf("订单ID:%-10d  订单时间:%s  " +
                                        "顾客名字:%s  顾客联系方式:%s  顾客地址:%s  " +
                                        "商家ID:%-10d  商家地址:%s  %n",
                                orders.getOrderId(), orders.getOrderDate(),
                                orders.getCname(), orders.getCtel(), orders.getCaddress(),
                                orders.getBid(), orders.getBaddress());
                    }
                    System.out.println("是否选择订单配送(y|n):");
                    char t = input.next().charAt(0);;
                    if(t == 'y'){
                        Orders orders = new Orders();
                        orders.setRid(rider.getRid());
                        while(true){
                            System.out.println("输入订单ID:");
                            orders.setOrderId(input.nextInt());
                            if (orders.getOrderId() == 0) {
                                System.out.println("请尽快完成订单!");
                                break;
                            }
                            else {
                                Rorder.getOrderForRider(orders.getOrderId(), rider.getRid());
                                System.out.println("选择成功！");
                            }
                        }
                    }
                }
                else{
                    System.out.println("暂时没有新的订单!");
                }
            }
            else if(tag == 5){
                System.out.println("                  ------已接订单列表------                  ");
                Rider rider = new Rider();
                System.out.println("输入骑手ID:");
                rider.setRid(input.nextInt());
                RiderOrderDao orderDao = new RiderOrderDao();
                orderDao.checkOrder(rider);
                if(orderDao.checkOrder(rider).isEmpty()){
                    System.out.println("暂时没有订单!");
                }
                else{
                    for(Orders orders : orderDao.checkOrder(rider)) {
                        System.out.printf("订单ID:%-10d  订单时间:%s  " +
                                        "顾客名字:%s  顾客联系方式:%s  顾客地址:%s  " +
                                        "商家ID:%-10d  商家地址:%s  %n",
                                orders.getOrderId(), orders.getOrderDate(),
                                orders.getCname(), orders.getCtel(), orders.getCaddress(),
                                orders.getBid(), orders.getBaddress());
                    }
                }
            }
            else if(tag == 6){
                Rider rider = new Rider();
                input.nextLine();//吸收回车
                System.out.println("输入骑手ID:");
                rider.setRid(input.nextInt());
                rUserDao.deleteUser(rider.getRid());
                System.out.println("                    ------注销成功------                    ");
            }
            else{
                System.out.println("                ------已退出骑手用户界面------                 ");
                break;
            }
        }
    }
}
