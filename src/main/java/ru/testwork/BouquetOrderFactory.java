
package ru.testwork;

import java.util.Date;

public class BouquetOrderFactory {

    private static BouquetOrderFactory boFactory = null;

    static {
        boFactory = new BouquetOrderFactory();
    }
    
    private BouquetOrderFactory(){
    }
    
    public BouquetOrder getNewBouquetOrder(String customer, Date untilDate, Integer quantity, 
            Integer price, Integer moneySpend, Integer total, Status status, String wishes, String imgPath){
        return new BouquetOrder(customer, untilDate, quantity, price, moneySpend, total, status, wishes, imgPath);
    }
    
    public static BouquetOrderFactory getBouquetOrderFactory(){
        return boFactory;
    }
}
