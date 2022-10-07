package servlets;

import bean.Fruit;
import dao.FruitDaoImpl;

import util.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection connection = JDBCUtil.getConnection();
            FruitDaoImpl fruitDao = new FruitDaoImpl();
            List<Fruit> fruitList = fruitDao.getAllFruit(connection);
            System.out.printf(fruitList.toString());
            HttpSession session = req.getSession();
            session.setAttribute("fruitList",fruitList);
            super.processTemplate("index",req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
