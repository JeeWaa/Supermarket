package BO.Custom.Implement;

import BO.Custom.OrderBO;
import DAO.CrudDAO;
import DAO.Custom.CustomerDAO;
import DAO.Custom.Implement.CustomerImplementDAO;
import DAO.Custom.Implement.ItemImplementDAO;
import DAO.Custom.Implement.OrderDetailImplementDAO;
import DAO.Custom.Implement.OrderImplementDAO;
import DAO.Custom.ItemDAO;
import DAO.Custom.OrderDAO;
import DAO.Custom.OrderDetailDAO;
import DAO.FactoryDAO;
import DTO.OrderDetailDTO;
import Database.DBConnection;
import DTO.CustomerDTO;
import DTO.ItemDTO;
import DTO.OrderDTO;
import Entity.Customer;
import Entity.Item;
import Entity.Order;
import Entity.OrderDetail;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderImplementBO implements OrderBO {

    private final CustomerDAO customerDAO = (CustomerDAO) FactoryDAO.getFactoryDAO().getDAO(FactoryDAO.DAOType.CUSTOMER);
    private final ItemDAO itemDAO = (ItemDAO) FactoryDAO.getFactoryDAO().getDAO(FactoryDAO.DAOType.ITEM);
    private final OrderDAO orderDAO = (OrderDAO) FactoryDAO.getFactoryDAO().getDAO(FactoryDAO.DAOType.ORDER);
    private final OrderDetailDAO orderDetailDAO = (OrderDetailDAO) FactoryDAO.getFactoryDAO().getDAO(FactoryDAO.DAOType.ORDERDETAIL);

    @Override
    public boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        System.out.println("a  "+ orderDTO.getOrderId() + orderDTO.getOrderCustomerId() + orderDTO.getOrderTime() + orderDTO.getOrderDate() + orderDTO.getOrderTotal());

        Connection connection = null;

        connection = DBConnection.getDbConnection().getConnection();

        connection.setAutoCommit(false);

        Order od = new Order(orderDTO.getOrderId(), orderDTO.getOrderCustomerId(), orderDTO.getOrderTime(), orderDTO.getOrderDate(), orderDTO.getOrderTotal());
        boolean save = orderDAO.save(od);
        if (!save){
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        for (OrderDetailDTO orderDetailDTO : orderDTO.getOrderItem()){
            OrderDetail orderDetails = new OrderDetail(orderDetailDTO.getOrderDetailItemCode(), orderDTO.getOrderId(), orderDetailDTO.getOrderDetailPrice(), orderDetailDTO.getOrderDetailQty());
            boolean saveOrderDetail = orderDetailDAO.save(orderDetails);
            if (!saveOrderDetail){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;

                //
            }
        }
        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public CustomerDTO getCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.get(id);
        if (customer != null){
            return new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(), customer.getNumber());
        }else {
            return null;
        }
    }

    @Override
    public ItemDTO getItem(String code) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.get(code);
        if (item != null){
            return new ItemDTO(item.getItemCode(), item.getItem(), item.getCompany(), item.getDescription(), item.getPrice(), item.getQty());
        }else {
            return null;
        }
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        ArrayList<Customer> customers = customerDAO.getAll();
        for (Customer customer : customers){
            customerDTOS.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(), customer.getNumber()));
        }
        return customerDTOS;
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        ArrayList<Item> items = itemDAO.getAll();
        for (Item item : items){
            itemDTOS.add(new ItemDTO(item.getItemCode(), item.getItem(), item.getCompany(), item.getDescription(), item.getPrice(), item.getQty()));
        }
        return itemDTOS;
    }

    @Override
    public String generateOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateOrderId();
    }
}
