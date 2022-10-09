/*
 *@Description:
 *@author:xiezhh
 *@create:2022-10-08 0:01
 *@Version 1.0
 */
package servlets;

import bean.Fruit;
import dao.FruitDaoImpl;
import util.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet{
    private FruitDaoImpl fruitDaoImpl = new FruitDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String idStr = req.getParameter("id");
        long id = Long.parseLong(idStr);
        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");
        double price = Double.parseDouble(priceStr);
        String fCountStr = req.getParameter("fCount");
        double fCount = Double.parseDouble(fCountStr);
        String remark = req.getParameter("remark");
        Fruit fruit = new Fruit(id,name,price,fCount,remark) ;

    //  connection = JDBCUtil.getConnection();
        fruitDaoImpl.update(fruit);
        //req.getRequestDispatcher("index",req,resp);
        resp.sendRedirect("index");

    }
}
