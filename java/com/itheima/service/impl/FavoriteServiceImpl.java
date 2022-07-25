package com.itheima.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.dao.FavoriteMapper;
import com.itheima.dao.RouteMapper;
import com.itheima.domain.Favorite;
import com.itheima.domain.ResultInfo;
import com.itheima.domain.Route;
import com.itheima.domain.User;
import com.itheima.service.FavoriteService;
import com.itheima.utils.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteMapper favoriteMapper;
    @Autowired
    private RouteMapper routeMapper;
    /**
     * 判断当前线路是否可被当前用户收藏
     *
     * @param rid
     * @param loginUser
     * @return
     */
    @Override
    public String isFavorite(String rid, User loginUser) {
        //封装结果
        ResultInfo resultInfo = null;

        if (loginUser == null) {
            //未登录：可收藏状态
            resultInfo = new ResultInfo(1005, true, "用户未登录", null);
        } else {
            //已登录：获取用户id
            int uid = loginUser.getUid();
            //根据uid和rid查询收藏数据
            Favorite favorite = favoriteMapper.queryByRidAndUid(rid, uid);
            resultInfo = new ResultInfo(1006, favorite == null, "用户已登录", null);
        }
        try {
            return new ObjectMapper().writeValueAsString(resultInfo);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 添加收藏
     * @param rid
     * @param loginUser
     * @return
     */
    @Override
    public String addFavorite(String rid, User loginUser){
        //1、判断用户是否登录
        ResultInfo resultInfo = null;
        if (loginUser == null) {
            //未登录：返回未登录状态
            resultInfo = new ResultInfo(1005, false, "未登录", null);
        }
        else{
            //2.1 添加收藏数据
            int addNum = favoriteMapper.addFavorite(rid, loginUser.getUid());
            //2.2 更新收藏次数
            int updateNum = routeMapper.updateCountByRid(rid);
            //2.3 查询最新的收藏次数
            Route route = routeMapper.queryByRid(rid);
            resultInfo = new ResultInfo(2000, true, "", route.getCount());
        }
        try{
            return new ObjectMapper().writeValueAsString(resultInfo);
        }catch (Exception e){
            return null;
        }
    }
    /**
     * 分页查询我的收藏数据
     * @param loginUser
     * @param strPageNum
     * @param strPageSize
     * @return
     */
    @Override
    public String pageQuery(User loginUser, String strPageNum, String strPageSize) {
        //1、判断是否登录
        if (loginUser == null) {
            //未登录：可收藏-true
            ResultInfo resultInfo = new ResultInfo(1005, true, "用户未登录", null);
            try {
                return new ObjectMapper().writeValueAsString(resultInfo);
            } catch (Exception e) {
                return null;
            }

        } else {
            //已登录：分页查询我的收藏数据
            int uid = loginUser.getUid();
            //【1】分页数据查询
            int pageNum = 1;
            int pageSize = 12;
            int startIndex=0;
            if (StringUtils.isNotBlank(strPageNum)) {
                pageNum = Integer.parseInt(strPageNum);
            }
            if (StringUtils.isNotBlank(strPageSize)) {
                pageSize = Integer.parseInt(strPageSize);
            }
            startIndex = (pageNum - 1) * pageSize;
            List<Favorite> favoriteList = favoriteMapper.pageQuery(uid, startIndex, pageSize);
            //【2】总记录数查询
            int totalRecord = favoriteMapper.queryTotalRecord(uid);
            System.out.println(totalRecord);
            int totalPage = (int) Math.ceil((totalRecord * 1.0) / pageSize);
            PageBean<Favorite> pageBean = PageBean.getPageBean(pageNum, pageSize, totalRecord, favoriteList);
            try {
                return new ObjectMapper().writeValueAsString(pageBean);
            } catch (Exception e) {
                return null;
            }
        }
    }
}