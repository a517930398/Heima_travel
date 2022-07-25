package com.itheima.controller;

import com.itheima.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("/categoryServlet")
    public void receiveRequest(HttpServletRequest req, HttpServletResponse resp){
        String action = req.getParameter("action");
        if("queryAll".equals(action)) {
            //处理用户注册请求
            queryAll(req,resp);
        }
    }
    /**
     * 查询所有商品类别数据
     * @param request
     * @param response
     * @throws IOException
     */
    private void queryAll(HttpServletRequest request, HttpServletResponse response){
        //1、接收请求数据
        //2、处理请求
        String categoryListJson = categoryService.queryAll();
        try{
            //3、响应数据
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(categoryListJson);
        }catch (Exception e){
        }
    }
}
