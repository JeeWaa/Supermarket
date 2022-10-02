package DAO.Custom.Implement;

import DAO.CrudUtil;
import DAO.Custom.OrderDAO;
import DTO.OrderDTO;
import Entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderImplementDAO implements OrderDAO {
    @Override
    public boolean save(Order order) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO `Order` VALUES(?,?,?,?,?)", order.getOrderId(), order.getCustomerId(), order.getTime(), order.getDate(), order.getTotal());
    }

    @Override
    public boolean update(Order order) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order get(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateOrderId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT orderId FROM `Order` ORDER BY orderId DESC LIMIT 1");
        if (resultSet.next()){
            int orderId = Integer.parseInt(resultSet.getString(1).split("-")[1]);
            orderId=orderId+1;
            if (orderId<9){
                return "O-00"+orderId;
            }else if(orderId<99){
                return "O-0"+orderId;
            }else{
                return "O-"+orderId;
            }
        }else {
            return "O-001";
        }
    }


//    @Override
//    public boolean save(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
//        return CrudUtil.executeUpdate("INSERT INTO `OrderDetail` VALUES(?,?,?,?)", orderDetail.getOrderDetailItemCode(), orderDetail.getOrderDetailOrderId(), orderDetail.getOrderDetailPrice(), orderDetail.getOrderDetailQty());
//    }
//
//    @Override
//    public boolean update(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
//        return false;
//    }
//
//    @Override
//    public boolean delete(String s) throws SQLException, ClassNotFoundException {
//        return false;
//    }
//
//    @Override
//    public OrderDetail get(String s) throws SQLException, ClassNotFoundException {
//        return null;
//    }
//
//    @Override
//    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
//        return null;
//    }
}
