package DAO.Custom.Implement;

import DAO.CrudUtil;
import DAO.Custom.QueryDAO;
import DTO.CustomDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryImplementDAO implements QueryDAO {
    @Override
    public ArrayList<CustomDTO> getOrderDetail(String orderId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("select o.orderId,o.customerId,o.date,od.orderId,od.itemCode,od.qty,od.price from Orders o join OrderDetails is od on o.orderId=od.orderId where o.orderId=?", orderId);
        ArrayList<CustomDTO> allCustomDTO = new ArrayList<>();
        while (resultSet.next()){
            allCustomDTO.add(new CustomDTO(resultSet.getString("orderId"), resultSet.getString("customerId"), resultSet.getString("date"), resultSet.getString("orderId"), resultSet.getString("itemCode"), resultSet.getInt("qty"), resultSet.getDouble("price")));
        }
        return allCustomDTO;
    }
}
