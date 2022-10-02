package BO;

import BO.Custom.Implement.CustomerImplementBO;
import BO.Custom.Implement.ItemImplementBO;
import BO.Custom.Implement.OrderImplementBO;

public class FactoryBO {
    private static FactoryBO factoryBO;

    private FactoryBO(){
    }

    public static FactoryBO getFactoryBO(){
        if (factoryBO == null){
            factoryBO = new FactoryBO();
        }
        return factoryBO;
    }

    public enum BOType {
        CUSTOMER, ITEM, ORDER
    }

    public SuperBO getBO(FactoryBO.BOType type){
        switch (type){
            case CUSTOMER:
                return new CustomerImplementBO();
            case ITEM:
                return new ItemImplementBO();
            case ORDER:
                return new OrderImplementBO();
            default:
                return null;
        }
    }
}
