package com.itheima.controller;

import com.itheima.domain.User;
import com.itheima.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;
    @RequestMapping("/favoriteServlet")
    public void receiveRequest(HttpServletRequest req, HttpServletResponse resp){
        String action = req.getParameter("action");
        if("isFavorite".equals(action)) {
            isFavorite(req,resp);
        }
        else if("addFavorite".equals(action)) {
            addFavorite(req,resp);
        }
        else if("pageQueryMyFavorite".equals(action)) {
            pageQueryMyFavorite(req,resp);
        }

    }

    /**
     * 分页查询我的收藏数据
     * @param request
     * @param response
     * @throws IOException
     */
    private void pageQueryMyFavorite(HttpServletRequest request, HttpServletResponse response){
        try{
            //1、接收请求数据
            String strPageNum = request.getParameter("pageNum");
            String strPageSize = request.getParameter("pageSize");
            User loginUser = (User) request.getSession().getAttribute("loginUser");
            //2、处理数据
            String myFavoriteJson = favoriteService.pageQuery(loginUser,strPageNum,strPageSize);
            //3、响应数据
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(myFavoriteJson);
        }catch (Exception e){
        }
    }

    /**
     * 判断当前线路是否可被当前用户收藏
     * @param request
     * @param response
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response){

        try {
            //1、接收请求数据
            String rid = request.getParameter("rid");
            //2、处理数据
            User loginUser = (User) request.getSession().getAttribute("loginUser");
            String isFavorite = favoriteService.isFavorite(rid, loginUser);
            //3、响应数据
            response.getWriter().print(isFavorite);
        }catch(Exception e){
        }
    }
    /**
     * 添加收藏
     * @param request
     * @param response
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request,HttpServletResponse response){
        //1、接收请求数据
        String rid = request.getParameter("rid");
        //2、处理数据：调用service层处理登录业务
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        String addFavoriteJson =  favoriteService.addFavorite(rid, loginUser);
        //3、响应数据
        try{
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(addFavoriteJson);
        }catch (Exception e){
        }
    }

}
