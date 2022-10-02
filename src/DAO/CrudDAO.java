package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T, I> extends SuperDAO {
    public boolean save(T t) throws SQLException, ClassNotFoundException;
    public boolean update(T t) throws SQLException, ClassNotFoundException;
    public boolean delete(I i) throws SQLException, ClassNotFoundException;
    public T get(I i) throws SQLException, ClassNotFoundException;
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
}
