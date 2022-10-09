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
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Connection connection = JDBCUtil.getConnection();
        String pageNoStr = req.getParameter("pageNo");
        FruitDaoImpl fruitDao = new FruitDaoImpl();
        HttpSession session = req.getSession();
        int pageNO = 1;
        if(StringUtil.isNotEmpty(pageNoStr)){
            pageNO = Integer.parseInt(pageNoStr);
        }
        //获取数据总条目数
        long count = fruitDao.getCount();
        long pageCount = (count+4)/5;
        if(pageNO<1){
            pageNO =1;
        }
        if(pageNO >pageCount){
            pageNO = (int)pageCount;
        }
        session.setAttribute("pageCount",pageCount);
        session.setAttribute("pageNo",pageNO);
        List<Fruit> fruitList = fruitDao.getAllFruit(pageNO);
        System.out.printf(fruitList.toString());
//        HttpSession session = req.getSession();
        session.setAttribute("fruitList",fruitList);

        super.processTemplate("index",req,resp);

    }
}
