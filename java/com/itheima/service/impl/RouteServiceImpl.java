package com.itheima.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.dao.CategoryMapper;
import com.itheima.dao.RouteImgMapper;
import com.itheima.dao.RouteMapper;
import com.itheima.dao.SellerMapper;
import com.itheima.domain.Route;
import com.itheima.service.RouteService;
import com.itheima.utils.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RouteMapper routeMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private SellerMapper sellerMapper;
    @Autowired
    private RouteImgMapper routeImgMapper;
//    @Override
//    public PageBean<Route> pageQuery(String strPageNum, String strPageSize, String cid,String rname) {
//        //1、分页数据查询
//        int pageNum = 1;
//        int pageSize=8;
//        if (StringUtils.isNotBlank(strPageNum)) {
//            pageNum = Integer.parseInt(strPageNum);
//        }
//        if (StringUtils.isNotBlank(strPageSize)) {
//            pageSize = Integer.parseInt(strPageSize);
//        }
//        int startIndex = (pageNum - 1) * pageSize;
//        List<Route> routeList = routeMapper.pageQuery(startIndex, pageSize,cid);
//        //2、分页条计算
//        int totalRecord = routeMapper.queryTotaoRecord(cid);
//
//        PageBean<Route> pageBean = PageBean.getPageBean(pageNum, pageSize, totalRecord, routeList);
//
////        int totalPage = (int) Math.ceil((totalRecord * 1.0) / pageSize);
////
////
////        //封装数据
////        Map<String, Object> result = new HashMap<>();
////        result.put("data", routeList);
////        result.put("totalPage", totalPage);
////        result.put("totalRecord", totalRecord);
//        return pageBean;
//    }
//    @Override
//public String pageQueryFavoriteRank(String strPageNum, String strPageSize){
//    //1、分页数据查询
//    int pageNum = 1;
//    int pageSize = 8;
//    int startIndex = 0;
//
//    if (StringUtils.isNotBlank(strPageNum)) {
//        pageNum = Integer.parseInt(strPageNum);
//    }
//    if (StringUtils.isNotBlank(strPageSize)) {
//        pageSize = Integer.parseInt(strPageSize);
//    }
//
//    //计算startIndex
//    startIndex = (pageNum - 1) * pageSize;
//
//    //调用dao层查询分页数据
//    List<Route> routeList =  routeMapper.pageQueryOrderByCountDesc(startIndex, pageSize);
//
//    //2、分页按钮处理 计算总页数
//    int totalRecord = routeMapper.queryTotalCount();
//
//    //封装数据 Map==>PageBean(封装分页参数)
//    PageBean<Route> pageBean = PageBean.getPageBean(pageNum, pageSize, totalRecord, routeList);
//
//    try{
//        String s = new ObjectMapper().writeValueAsString(pageBean);
//        return s;
//    }catch(Exception e){
//        return null;
//    }

//}
    @Override
public String pageQueryFavoriteRank(String strPageNum, String strPageSize, Map<String, Object> conditionMap){
    //1、分页数据查询
    int pageNum = 1;
    int pageSize = 8;
    int startIndex = 0;

    if (StringUtils.isNotBlank(strPageNum)) {
        pageNum = Integer.parseInt(strPageNum);
    }
    if (StringUtils.isNotBlank(strPageSize)) {
        pageSize = Integer.parseInt(strPageSize);
    }
    //计算startIndex
    startIndex = (pageNum - 1) * pageSize;
    //调用dao层查询分页数据
    List<Route> routeList =  routeMapper.pageQueryOrderByCountDesc(startIndex, pageSize,conditionMap);
    //2、分页按钮处理 计算总页数
    int totalRecord = routeMapper.queryTotalCount(conditionMap);
    //封装数据 Map==>PageBean(封装分页参数)
    PageBean<Route> pageBean = PageBean.getPageBean(pageNum, pageSize, totalRecord, routeList);
    try{
        String s = new ObjectMapper().writeValueAsString(pageBean);
        return s;
    }catch (Exception e){
        return null;
    }
}

    @Override
    public Map<String, Object> pageQuery(String strPageNum, String strPageSize,String cid) {
        //1、分页数据查询
        int pageNum = 1;
        int pageSize = 8;

        if (StringUtils.isNotBlank(strPageNum)) {
            pageNum = Integer.parseInt(strPageNum);
        }
        if (StringUtils.isNotBlank(strPageSize)) {
            pageSize = Integer.parseInt(strPageSize);
        }
        int startIndex = (pageNum - 1) * pageSize;
        List<Route> routeList = routeMapper.pageQuery(startIndex, pageSize,cid);
        //2、分页条计算
        int totalRecord = routeMapper.queryTotaoRecord(cid);
        int totalPage = (int) Math.ceil((totalRecord * 1.0) / pageSize);
        //封装数据
        Map<String, Object> result = new HashMap<>();
        result.put("data", routeList);
        result.put("totalPage", totalPage);
        result.put("totalRecord", totalRecord);
        return result;
    }
    /*** 根据rid查询线路详情 * @param rid * @return */
    @Override
    public Route queryDetailByRid(String rid) {
        Route route=routeMapper.queryDetailByRid(rid);
        route.setRouteImgList( routeImgMapper.findByRid(route.getRid()));
        route.setSeller( sellerMapper.findBySid(route.getSid()));
        route.setCategory( categoryMapper.findByCid(route.getCid()));
        return route;
    }



}
