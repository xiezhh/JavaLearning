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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //Connection connection = JDBCUtil.getConnection();
        int pageNO = 1;
        FruitDaoImpl fruitDao = new FruitDaoImpl();
        HttpSession session = req.getSession();
        //获取operation的值
        String operation = req.getParameter("operation");
        String keyword = "";
        //如果operation值是search，说明是点查询过来的
        if(StringUtil.isNotEmpty(operation) && "search".equals(operation)){
            pageNO = 1;
            keyword = req.getParameter("keyword");
            if(StringUtil.isEmpty(keyword)){
                keyword = "";
            }
            session.setAttribute("keyword",keyword);
        }else{
            String pageNoStr = req.getParameter("pageNo");
            if(StringUtil.isNotEmpty(pageNoStr)){
                pageNO = Integer.parseInt(pageNoStr);
            }
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj != null){
                keyword = (String)keywordObj;
            }else{
                keyword = "";
            }
        }
        //如果不是，说明是初始化页面或输入url过来的

        //获取数据总条目数
        long count = fruitDao.getCount(keyword);
        long pageCount = (count+4)/5;
        // if(pageNO<1){
        //     pageNO =1;
        // }
        // if(pageNO >pageCount){
        //     pageNO = (int)pageCount;
        // }
        session.setAttribute("pageCount",pageCount);
        session.setAttribute("pageNo",pageNO);
        List<Fruit> fruitList = fruitDao.getAllFruit(keyword,pageNO);
        System.out.printf(fruitList.toString());
//        HttpSession session = req.getSession();
        session.setAttribute("fruitList",fruitList);

        super.processTemplate("index",req,resp);

    }
}
