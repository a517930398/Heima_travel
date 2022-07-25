package com.itheima.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.domain.Route;
import com.itheima.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
@Controller
public class RouteController {
    @Autowired
    private RouteService routeService;
    @RequestMapping("/routeServlet")
    public void receiveRequest(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        String action = req.getParameter("action");
        if ("pageQuery".equals(action)) {
            //处理用户注册请求
            pageQuery(req, resp);
        } else if ("queryDetailByRid".equals(action)) {
            queryDetailByRid(req, resp);
        } else if ("pageQueryFavoriteRank".equals(action)) {
            pageQueryFavoriteRank(req, resp);
        }
    }
    /**
     * 分页查询收藏排行榜
     * @param request
     * @param response
//     * @throws IOException
     */
    private void pageQueryFavoriteRank(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        //1、接收请求数据
        String strPageNum = request.getParameter("pageNum");
        String strPageSize = request.getParameter("pageSize");
        String rname= request.getParameter("rname");
        //获取查询条件
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("rname", rname);
        conditionMap.put("minPrice", minPrice);
        conditionMap.put("maxPrice", maxPrice);
        //String pageJson =  routeService.pageQueryFavoriteRank(strPageNum, strPageSize);
        String pageJson =  routeService.pageQueryFavoriteRank(strPageNum, strPageSize,conditionMap);
        //3、响应数据
        try{
            //3、响应数据
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(pageJson);
        }catch(Exception e){
        }
    }
    /**
     * 分页查询收藏排行榜
     * @param request
     * @param response
     * @throws IOException
     */
//    private void pageQueryFavoriteRank(HttpServletRequest request,HttpServletResponse response){
        //1、接收请求数据
//        String strPageNum = request.getParameter("pageNum");
//        String strPageSize = request.getParameter("pageSize");

        //获取查询条件
        //String rname = request.getParameter("rname");
        //String minPrice = request.getParameter("minPrice");
        //String maxPrice = request.getParameter("maxPrice");

        /*Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("rname", rname);
        conditionMap.put("minPrice", minPrice);
        conditionMap.put("maxPrice", maxPrice);*/

//        String pageJson =  routeService.pageQueryFavoriteRank(strPageNum, strPageSize);
        //String pageJson =  routeService.pageQueryFavoriteRank(strPageNum, strPageSize,conditionMap);


        //3、响应数据
//        try{
//            //3、响应数据
//            response.setContentType("text/html;charset=UTF-8");
//            response.getWriter().print(pageJson);
//        }catch(Exception e){
//
//        }
//    }
    /*** 根据rid查询线路详情信息 * @param request * @param response * @throws IOException */
    public void queryDetailByRid(HttpServletRequest request,HttpServletResponse response){
        //1、接收请求数据
         String rid = request.getParameter("rid");
        // 2、处理数据：调用service层 根据rid查询线路详情
         Route routeDetail = routeService.queryDetailByRid(rid);
         System.out.println(routeDetail.getCid());
        System.out.println(routeDetail.getSid());
         try{
        // 3、响应数据
         response.setContentType("text/html;charset=UTF-8");
         response.getWriter().print(new ObjectMapper().writeValueAsString(routeDetail));
         }catch(Exception e){ }
    }
    /**
     * 分页查询收藏排行榜
     * @param request
     * @param response
     * @throws IOException
     */
    /**** @param request * @param response * @throws IOException */
    public void pageQuery(HttpServletRequest request,HttpServletResponse response){
        //1、接收请求数据
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        String cid = request.getParameter("cid");
        //String cid = request.getParameter("cid");
        // 2、处理数据 /
        // /Map<String,Object> mapData = routeService.pageQuery(pageNum, pageSize);
        Map<String,Object> mapData = routeService.pageQuery(pageNum, pageSize,cid);
        try{//3、响应数据
             response.setContentType("text/html;charset=UTF-8");
             response.getWriter().print(new ObjectMapper().writeValueAsString(mapData)); }catch(Exception e){ }
    }

}
