/*
 *@Description:
 *@author:xiezhh
 *@create:2023-02-14 23:04
 *@Version 1.0
 */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Demo06 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Demo06...");
        req.getRequestDispatcher("Demo07").forward(req,resp);
    }
}
