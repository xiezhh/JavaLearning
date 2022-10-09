/*
 *@Description:
 *@author:xiezhh
 *@create:2022-10-07 22:27
 *@Version 1.0
 */
package servlets;

import bean.Fruit;
import dao.FruitDaoImpl;
import util.JDBCUtil;
import util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet{
    private FruitDaoImpl fruitDaoImpl = new FruitDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Connection connection = null;

        //connection = JDBCUtil.getConnection();
        String idStr = req.getParameter("id");
        if(StringUtil.isNotEmpty(idStr)){
            long id = Long.parseLong(idStr);
            Fruit fruit = fruitDaoImpl.getFruitById(id);
            // HttpSession httpSession = req.getSession();
            // httpSession.setAttribute("fruit",fruit);
            req.setAttribute("fruit",fruit);
            super.processTemplate("edit",req,resp);
        }

        // System.out.println(id);
    }


}
