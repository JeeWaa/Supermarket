package DAO;

import DAO.Custom.Implement.*;

public class FactoryDAO {
    private static FactoryDAO factoryDAO;

    private FactoryDAO(){
    }

    public static FactoryDAO getFactoryDAO(){
        if (factoryDAO == null){
            factoryDAO = new FactoryDAO();
        }
        return factoryDAO;
    }

    public enum DAOType {
        CUSTOMER, ITEM, ORDER, ORDERDETAIL, QUERY
    }

    public SuperDAO getDAO(DAOType type){
        switch (type){
            case CUSTOMER:
                return new CustomerImplementDAO();
            case ITEM:
                return new ItemImplementDAO();
            case ORDER:
                return new OrderImplementDAO();
            case ORDERDETAIL:
                return new OrderDetailImplementDAO();
            case QUERY:
                return new QueryImplementDAO();
            default:
                return null;
        }
    }
}
