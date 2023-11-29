package Elm;

import lombok.Data;

@Data
public class Orders {
    private int OrderId;
    private String OrderDate;
    private float OrderSum;
    private String Cname;
    private String Ctel;//顾客电话号码
    private String Caddress;//顾客地址
    private int Bid;//店家id
    private String Baddress;//店家地址
    private int Rid;
}
