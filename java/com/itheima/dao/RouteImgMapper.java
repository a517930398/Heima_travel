package com.itheima.dao;

import com.itheima.domain.RouteImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface RouteImgMapper {
    @Select("SELECT * FROM tab_route_img WHERE rid = #{rid}")
    List<RouteImg> findByRid(int rid);
}
