package com.itheima.dao;

import com.itheima.domain.Route;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface RouteMapper {
    @Select("SELECT * FROM tab_route WHERE cid=#{cid} LIMIT #{startIndex},#{pageSize}")
    List<Route> pageQuery(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize,@Param("cid") String cid);
    @Select("SELECT COUNT(*) FROM tab_route WHERE cid=#{cid}")
    int queryTotaoRecord(@Param("cid") String cid);
    //使用注解实现多表关联查询
    @Select("SELECT * FROM tab_route WHERE rid  = #{rid}")
     Route queryDetailByRid(@Param("rid") String rid);
    @Update("UPDATE tab_route SET count=count+1 WHERE rid = #{rid}")
    int updateCountByRid(@Param("rid") String rid);
    @Select("SELECT * FROM tab_route WHERE rid = #{rid}")
    Route queryByRid(@Param("rid") String rid);
//    @Select("SELECT * FROM tab_route  ORDER BY count DESC LIMIT #{startIndex},#{pageSize}")
//    List<Route> pageQueryOrderByCountDesc(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
//    @Select("SELECT COUNT(*) FROM tab_route ")
//    int queryTotalCount();
    List<Route> pageQueryOrderByCountDesc(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize, @Param("conditionMap") Map<String, Object> conditionMap);
    int queryTotalCount(@Param("conditionMap") Map<String, Object> conditionMap);
}
