package dao;

import bean.Fruit;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface FruitDao {
    void insert( Fruit fruit) throws SQLException;

    void update(Fruit fruit);

    Fruit getFruitById(long i);

    List<Fruit> getAllFruit();

    List<Fruit> getAllFruit(int pageNo);

    long getCount();

    void deleteById(long id);
}
