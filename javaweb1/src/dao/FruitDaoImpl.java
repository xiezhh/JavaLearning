/*
 *@Description:
 *@author:xiezhh
 *@create:2022-09-18 23:00
 *@Version 1.0
 */
package dao;

import bean.Fruit;

import java.sql.Connection;
import java.util.List;

public class FruitDaoImpl extends BaseDao implements FruitDao{
    @Override
    public void insert( Fruit fruit){
        String sql = "insert into fruit(name,price,fCount,remark) values(?,?,?,?)";
        update(sql,fruit.getName(),fruit.getPrice(),fruit.getfCount(),fruit.getRemark());
    }

    @Override
    public void update( Fruit fruit) {
        String sql = "update fruit set name = ?,price=?,fCount=?,remark=? where id=?";
        update(sql,fruit.getName(),fruit.getPrice(),fruit.getfCount(),fruit.getRemark(),fruit.getId());
    }

    @Override
    public Fruit getFruitById(long i) {
        String sql = "select id,name,price,fCount,remark from fruit where id=?";
        return getInstance(Fruit.class,sql,i);
    }

    @Override
    public List<Fruit> getAllFruit() {
        String sql = "select id,name,price,fCount,remark from fruit";
        List<Fruit> list = getForList(Fruit.class,sql);
        //List<Fruit> list = getForList(connection,Fruit.class,sql);
        return list;
    }

    @Override
    public List<Fruit> getAllFruit(int pageNo) {
        String sql = "select id ,name,price,fCount,remark from fruit limit ?,?";
        List<Fruit> list = getForList(Fruit.class,sql,(pageNo-1)*5,5);
        return list;
    }

    @Override
    public long getCount() {
        String sql = "select count(1) from fruit";
        return getValue(sql);
    }

    @Override
    public void deleteById(long id) {
        String sql = "delete from fruit where id = ?";
        update(sql,id);
    }
}
