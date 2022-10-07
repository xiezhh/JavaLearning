package servlets;/*
 *@Description:
 *@author:xiezhh
 *@create:2022-09-07 23:29
 *@Version 1.0
 */

import bean.Fruit;
import dao.FruitDaoImpl;
import util.JDBCUtil;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class AddServlet extends HttpServlet {

    private FruitDaoImpl fruitDao = new FruitDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");
        double price = Double.parseDouble(priceStr);
        String fCountStr = req.getParameter("fCount");
        double fCount = Double.parseDouble(fCountStr);
        String remark = req.getParameter("remark");


        Fruit fruit = new Fruit(1,name,price,fCount,remark );
        System.out.printf(fruit.toString());
        try {
            Connection connection = JDBCUtil.getConnection();
            System.out.println(connection);
            fruitDao.insert(connection,fruit);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
