/*
 *@Description:
 *@author:xiezhh
 *@create:2022-09-18 23:00
 *@Version 1.0
 */
package dao;

import Bean.Fruit;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FruitDaoImpl extends BaseDao implements FruitDao{
    @Override
    public void insert(Connection connection, Fruit fruit){
        String sql = "insert into fruit(name,price,fCount,remark) values(?,?,?,?)";
        update(connection,sql,fruit.getName(),fruit.getPrice(),fruit.getfCount(),fruit.getRemark());
    }

    @Override
    public void update(Connection connection, Fruit fruit) {
        String sql = "update fruit set name = ?,price=?,fCount=?,remark=? where id=?";
        update(connection,sql,fruit.getName(),fruit.getPrice(),fruit.getfCount(),fruit.getRemark(),fruit.getId());
    }

    @Override
    public Fruit getFruitById(Connection connection, int i) {
        String sql = "select id,name,price,fCount,remark from fruit where id=?";
        return getInstance(connection,Fruit.class,sql,i);
    }

    @Override
    public List<Fruit> getAllFruit(Connection connection) {
        String sql = "select id,name,price,fCount,remark from fruit";
        List<Fruit> list = getForList(connection,Fruit.class,sql);
        return list;
    }

    @Override
    public long getCount(Connection connection) {
        String sql = "select count(1) from fruit";
        return getValue(connection,sql);
    }
}
