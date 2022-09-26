package servlets;

import Bean.Fruit;
import dao.FruitDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.JDBCUtil;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection connection = JDBCUtil.getConnection();
            FruitDaoImpl fruitDao = new FruitDaoImpl();
            List<Fruit> allFruit = fruitDao.getAllFruit(connection);
            System.out.printf(allFruit.toString());
            HttpSession session = req.getSession();
            session.setAttribute("fruitList",allFruit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
