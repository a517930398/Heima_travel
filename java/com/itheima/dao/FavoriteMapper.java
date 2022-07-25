package com.itheima.dao;

import com.itheima.domain.Favorite;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FavoriteMapper {
    @Select("SELECT * FROM tab_favorite WHERE rid = #{rid} AND uid = #{uid}")
    Favorite queryByRidAndUid(@Param("rid") String rid, @Param("uid") int uid);
    @Insert("INSERT INTO tab_favorite VALUES(#{rid},NOW(),#{uid})")
    int addFavorite(@Param("rid") String rid, @Param("uid") int uid);
    //分页数据查询：多表关联查询-使用xml实现
    List<Favorite> pageQuery(@Param("uid") int uid, @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
    //分页总记录数查询
    @Select("SELECT COUNT(*) FROM tab_favorite WHERE uid = #{uid}")
    int queryTotalRecord(@Param("uid") int uid);
}
