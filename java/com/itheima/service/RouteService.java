package com.itheima.service;

import com.itheima.domain.Route;

import java.util.Map;

public interface RouteService {
    /*** 分页查询 * @param strPageNum * @param strPageSize * @return */
//    public PageBean<Route> pageQuery(String strPageNum, String strPageSize, String cid, String rname);
  public Map<String, Object> pageQuery(String strPageNum, String strPageSize,String cid);
    /*** 根据rid查询线路详情 * @param rid * @return */
    public Route queryDetailByRid(String rid);
//    public String pageQueryFavoriteRank(String strPageNum, String strPageSize);
    public String pageQueryFavoriteRank(String strPageNum, String strPageSize, Map<String, Object> conditionMap);




}
