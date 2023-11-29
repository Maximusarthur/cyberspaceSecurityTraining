package Work;

import Dao.GoodsDao;
import Elm.BusiUser;
import Elm.Goods;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GoodsWork {
    public void Goods(BusiUser Buser){
        System.out.println("                  ------商家商品管理------                    ");
        System.out.println(" ____________________________________________________________ ");
        System.out.println("|                       1.新 增 商 品                         |");
        System.out.println("|                       2.更 新 商 品                         |");
        System.out.println("|                       3.查 看 商 品                         |");
        System.out.println("|                       4.删 除 商 品                         |");
        System.out.println("|                       10. 退 出                            |");
        System.out.println("|____________________________________________________________|");
        Scanner input = new Scanner(System.in);
        GoodsDao gDao = new GoodsDao();
        while(true){
            System.out.println("在此处输入(输入10退出商家商品管理界面):");
            int tag = input.nextInt();

            if(tag == 1){
                Goods goods = new Goods();
                System.out.println("输入商品名称:");
                input.nextLine();//吸收回车
                goods.setGName(input.nextLine());

                System.out.println("输入商品价格:");
                goods.setGPrice(input.nextFloat());

                input.nextLine();//吸收回车
                System.out.println("输入商品详情:");
                goods.setGDetail(input.nextLine());

                gDao.addGoods(goods, Buser.getBid());
                System.out.println("                    ------新增成功------                    ");
            }
            else if (tag == 2) {
                Goods goods = new Goods();
                input.nextLine();//吸收回车
                System.out.println("输入商品ID:");
                goods.setBid(input.nextInt());

                System.out.println("输入商品名称:");

                goods.setGName(input.nextLine());

                System.out.println("输入商品价格:");
                goods.setGPrice(input.nextFloat());

                input.nextLine();//吸收回车
                System.out.println("输入商品详情:");
                goods.setGDetail(input.nextLine());

                gDao.updateGoods(goods);
                System.out.println("                    ------更新成功------                    ");
            }
            else if(tag == 3){
                List<Goods> list = new ArrayList<>();
                list = gDao.checkGoods(Buser.getBid());
                for(Goods goods : list){
                     if (goods != null) {
                        System.out.printf("商品ID:%-10d 商品名称:%S  商品价格:%-6.2f  商品详情:%s%n",
                                goods.getGId(), goods.getGName(), goods.getGPrice(), goods.getGDetail());
                    }
                     else {
                         System.out.println("商品不存在,请检查商品ID是否正确！\n");
                     }
                }
            }
            else if(tag == 4){
                Goods goods = new Goods();
                input.nextLine();//吸收回车
                System.out.println("输入商品ID:");
                goods.setGId(input.nextInt());
                gDao.deleteGoods(goods.getGId());
                System.out.println("                    ------删除成功------                    ");
            }
            else{
                System.out.println("                ------已退出商品管理界面------                 ");
                break;
            }
        }
    }
}
