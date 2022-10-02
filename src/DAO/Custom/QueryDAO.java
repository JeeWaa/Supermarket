package DAO.Custom;

import DAO.SuperDAO;
import DTO.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    public ArrayList<CustomDTO> getOrderDetail(String orderId) throws SQLException, ClassNotFoundException;
}
