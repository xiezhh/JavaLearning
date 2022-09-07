/*
 *@Description:
 *@author:xiezhh
 *@create:2022-09-07 23:29
 *@Version 1.0
 */

import Bean.Fruit;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AddServlet extends HttpServlet {
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
    }
}
