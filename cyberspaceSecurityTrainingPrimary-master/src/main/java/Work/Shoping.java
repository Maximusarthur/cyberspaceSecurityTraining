package Work;

import Dao.CustomerOrderDao;
import Elm.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Shoping {
    public void shoping(int Cid) {
        System.out.println(" ____________________________________________________________ ");
        System.out.println("|                       1.查 看 商 家                         |");
        System.out.println("|                       2.购 买 商 品                         |");
        System.out.println("|                       3.查 看 订 单                         |");
        System.out.println("|                       4.更 改 订 单                         |");
        System.out.println("|                       5.删 除 订 单                         |");
        System.out.println("|                       10. 退 出                            |");
        System.out.println("|____________________________________________________________|");

        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("在此处输入(输入10退出):");
            int tag = input.nextInt();
            if (tag == 1) {
                System.out.println("                  ------查看所有商家------                     ");
                CustomerOrderDao CODao = new CustomerOrderDao();
                CODao.checkBusiness();
                for (BusiUser Buser : CODao.checkBusiness()) {
                    System.out.printf("商家ID:%-10d  商家名称:%S  商家联系方式:%s  商家地址:%s%n",
                            Buser.getBid(), Buser.getBname(), Buser.getBtel(), Buser.getBaddress());
                }
                System.out.println("是否查看详细商品(y|n):");
                char t = input.next().charAt(0);
                if (t == 'y') {
                    BusiUser Buser = new BusiUser();
                    System.out.println("输入商家ID:");
                    Buser.setBid(input.nextInt());
                    CODao.checkBusinessItem(Buser.getBid());
                    if (!CODao.checkBusinessItem(Buser.getBid()).isEmpty()) {
                        for (Goods goods : CODao.checkBusinessItem(Buser.getBid())) {
                            System.out.printf("商品ID:%-10d  商品名称:%S  商品价格:%6.2f  商品详情:%s%n",
                                    goods.getGId(), goods.getGName(), goods.getGPrice(), goods.getGDetail());
                        }
                    } else {
                        System.out.println("暂时没有商品！");
                    }
                }
            } else if (tag == 2) {
                System.out.println("                  ------购 买 商 品------                     ");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                CustomerOrderDao CODao = new CustomerOrderDao();
                Orders orders = new Orders();

                Date date = new Date();
                String dateString = sdf.format(date);

                orders.setOrderDate(dateString);//订单日期

                Customer customer = new Customer();
                customer = CODao.checkCustomer(Cid);
                orders.setCtel(customer.getCtel());//订单客户电话
                orders.setCname(customer.getCname());//订单客户名
                orders.setCaddress(customer.getCaddress());//订单客户地址

                BusiUser Buser = new BusiUser();
                System.out.println("输入商家ID:");
                orders.setBid(input.nextInt());//订单商家ID
                Buser = CODao.checkBusinessAddress(orders.getBid());
                orders.setBaddress(Buser.getBaddress());//订单商家地址

                CODao.addOrder(orders);//创建订单

                while (true) {
                    Goods goods = new Goods();
                    System.out.println("输入商品ID(输入0结束购物):");
                    goods.setGId(input.nextInt());
                    if (goods.getGId() == 0) {
                        System.out.println("购物愉快!");
                        break;
                    }
                    else {
                        OrderItems items = new OrderItems();
                        goods = CODao.getGoods(goods.getGId());//获取商品

                        items.setItemName(goods.getGName());//单品名

                        items.setItemPrice(goods.getGPrice());//价格

                        items.setItemCount(1);//单品数量

                        orders = CODao.checkOrderForItem(dateString);//根据订单时间获取对应的订单

                        items.setOrderId(orders.getOrderId()); //单品对应的订单ID

                        CODao.addItem(items);//数据库创建订单单品
                    }
                }
                for (OrderItems items : CODao.getItemPrice(orders.getOrderId())) {
                    orders.setOrderSum(orders.getOrderSum() + items.getItemPrice() * items.getItemCount());
                }
                CODao.insertOrderSum(orders.getOrderId(), orders.getOrderSum());

            } else if (tag == 3) {
                System.out.println("                  ------查 看 订 单------                     ");
                CustomerOrderDao CODao = new CustomerOrderDao();

                Customer customer = new Customer();
                customer = CODao.checkCustomer(Cid);

                if (CODao.checkOrder(customer).isEmpty()) {
                    System.out.println("暂时没有订单,赶快购物吧!");
                } else {
                    for (Orders orders : CODao.checkOrder(customer)) {
                        System.out.printf("订单ID:%-10d 订单时间:%S  订单金额:%f  商家ID:%-10d  商家地址:%s%n",
                                orders.getOrderId(), orders.getOrderDate(), orders.getOrderSum(), orders.getBid(), orders.getBaddress());
                    }
                    System.out.println("是否查看订单详细(Y|N):");
                    char t = input.next().charAt(0);
                    if (t == 'y') {
                        Orders orders = new Orders();
                        System.out.println("输入订单ID:");
                        orders.setOrderId(input.nextInt());

                        CODao.checkOrderItem(orders.getOrderId());
                        for (OrderItems items : CODao.checkOrderItem(orders.getOrderId())) {
                            System.out.printf("单品名称:%s  单品价格:%f  单品数量:%-5d%n",
                                    items.getItemName(), items.getItemPrice(), items.getItemCount());
                        }
                    }
                }
            }
            else if(tag == 4){
                System.out.println("                  ------更 改 订 单------                     ");
                CustomerOrderDao CODao = new CustomerOrderDao();
                Orders orders = new Orders();
                System.out.println("输入订单ID:");
                orders.setOrderId(input.nextInt());
                input.nextLine();
                System.out.println("输入顾客姓名:");
                orders.setCname(input.nextLine());
                System.out.println("输入顾客联系方式:");
                orders.setCtel(input.nextLine());
                System.out.println("输入顾客地址:");
                orders.setCaddress(input.nextLine());
                CODao.updateOrder(orders);
            }
            else if(tag == 5){
                System.out.println("                  ------删 除 订 单------                     ");
                CustomerOrderDao CODao = new CustomerOrderDao();
                Orders orders = new Orders();
                System.out.println("输入订单编号:");
                orders.setOrderId(input.nextInt());
                CODao.deleteOrder(orders.getOrderId());
            }
            else{
                System.out.println("                ------已退出购物界面------                 ");
                break;
            }
        }
    }
}
