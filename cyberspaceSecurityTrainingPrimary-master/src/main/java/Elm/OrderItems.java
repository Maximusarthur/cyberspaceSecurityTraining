package Elm;

import lombok.Data;

@Data
public class OrderItems {
    private int ItemId;
    private String ItemName;
    private float ItemPrice;
    private int ItemCount;//商品数量
    private int OrderId;
}
