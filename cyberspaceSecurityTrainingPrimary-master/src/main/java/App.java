import Work.BusinessWork;
import Work.CustomerWork;
import Work.RiderWork;
import lombok.Data;
import java.util.Scanner;

/*运行过程截图会保存在readme.md文件中*/

@Data
public class App {
    public static void main(String[] args) {
        System.out.println("                   ------饿了吗管理系统------                   ");
        System.out.println(" ____________________________________________________________ ");
        System.out.println("|                       1.个人用户界面                         |");
        System.out.println("|                       2.商家用户界面                         |");
        System.out.println("|                       3.骑手用户界面                         |");
        System.out.println("|                       10. 退 出                             |");
        System.out.println("|____________________________________________________________|");

        Scanner input = new Scanner(System.in);
        BusinessWork busiWork = new BusinessWork();
        CustomerWork cusWork = new CustomerWork();
        RiderWork ridWork = new RiderWork();

        while(true){
            System.out.println("在此处输入(输入10退出):");
            int tag = input.nextInt();
            if(tag == 1){
                cusWork.cus();
            }
            else if (tag == 2){
                busiWork.Busi();
            }
            else if(tag == 3){
                ridWork.Rid();
            }
            else{
                System.out.println("              ------已退出饿了吗管理系统------             ");
                break;
            }
        }
    }
}