package dao;

import bean.Fruit;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface FruitDao {
    void insert(Connection connection, Fruit fruit) throws SQLException;

    void update(Connection connection,Fruit fruit);

    Fruit getFruitById(Connection connection,long i);

    List<Fruit> getAllFruit(Connection connection);

    long getCount(Connection connection);

    void deleteById(Connection connection,long id);
}
