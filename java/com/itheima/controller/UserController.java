package com.itheima.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.domain.ResultInfo;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.utils.Md5Util;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/userServlet")
    public void acceptRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if ("register".equals(action)) {
            register(request, response);
            //System.out.println(action);
        } else if ("login".equals(action)) {
            //处理登录业务
            login(request, response);
            //System.out.println(action);
        } else if ("getLoginUser".equals(action)) {
            //处理登录业务
            getLoginUser(request, response);
        } else if ("loginOut".equals(action)) {
            //处理用户退出请求
            loginOut(request,response);
        }

    }
    /*** 处理用户注册业务* @param request * @param response * @throws ServletException * @throws IOException */
    private void register(HttpServletRequest request,HttpServletResponse response) {
        ResultInfo resultInfo=null;

        try {
            //1、接收请求数据
            Map<String, String[]> parameterMap = request.getParameterMap();
            //使用BeanUtils把数据封装到user对象中
            User user = new User();
            BeanUtils.populate(user, parameterMap);
            //BeanUtils.copyProperties(parameterMap, user);
            user.setPassword(Md5Util.getMD5(user.getPassword()));
            //2、处理请求：调用service层把user中的数据插入到数据库中
            boolean registerFlag=userService.register(user);
            ObjectMapper mapper=new ObjectMapper();
            if (registerFlag) {
                //注册成功，响应成功状态
               // System.out.println("123");
                 resultInfo = new ResultInfo(1000, true, "", null);

            } else { //注册失败
                 resultInfo = new ResultInfo(1001, false, "注册失败", null);
            }
            response.getWriter().print(new ObjectMapper().writeValueAsString(resultInfo));
        } catch (Exception e) {
            e.printStackTrace();
            //异常响应
        }
        //3、响应数据
    }
    /*** 处理用户登录业务  */
    private void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        //Md5Util.getMD5(password);
        //登录返回值： 用户信息
        User loginUser = userService.login(username,password);
        //3、响应数据
        ResultInfo result = null;
        if (loginUser != null) {
            //登录成功： 1、把用户数据存储在session中； 2、响应成功状态
             request.getSession().setAttribute("loginUser", loginUser);
             result = new ResultInfo(2000,true,"",null);

        }
        else { //登录失败：响应失败状态
             result = new ResultInfo(1003, false, "用户名或密码错误", null);
        }
        try{
            response.getWriter().print(new ObjectMapper().writeValueAsString(result));
        }catch(Exception e){ }
    }
    /*** 获取登录用户数据 * @param req * @param resp */
    private void getLoginUser(HttpServletRequest request, HttpServletResponse response){
        //从session中获取用户数据
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        ResultInfo resultInfo =null;
        if (loginUser == null) {
            resultInfo = new ResultInfo(10001, false, "用户未登录", null);
        }else{
            resultInfo = new ResultInfo(1000, true, "", loginUser.getUsername());
        }
        try{//响应数据
             response.getWriter().print(new ObjectMapper().writeValueAsString(resultInfo));
        }catch(Exception e){ }
    }
    //处理用户退出请求
    private void loginOut(HttpServletRequest request, HttpServletResponse response){
        //1.销毁当前session
        request.getSession().invalidate();
        //2.跳转登录页面
        try {
            response.sendRedirect(request.getContextPath()+"/login.html");
        }catch(Exception e){ }

    }

}
