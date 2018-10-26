package com.learnMaven.action;

import com.learnMaven.entity.User;
import com.learnMaven.service.CheckUserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 马昕 on 2018/10/26.
 */
public class CheckAction extends HttpServlet{

    private CheckUserService cku=new CheckUserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname=req.getParameter("uname");
        String passwd=req.getParameter("upwd");
        RequestDispatcher rd=null;

        String forward=null;

        if(uname==null||passwd==null){
            req.setAttribute("msg","用户名或密码为空");
            rd=req.getRequestDispatcher("error.jsp");
            rd.forward(req,resp);
        }else {
            User user=new User();
            user.setName(uname);
            user.setPassword(passwd);
            boolean bool=cku.check(user);
            if(bool){
                forward="success.jsp";
            }else {
                req.setAttribute("msg","用户名或密码错误");
                forward="error.jsp";
            }
            rd=req.getRequestDispatcher(forward);
            rd.forward(req,resp);

        }
    }
}
