package com.itheima.dao;

import com.itheima.domain.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("SELECT * FROM tab_category WHERE cid =#{cid}")
    Category findByCid(int cid);
    @Select("SELECT * FROM tab_category")
    List<Category> queryAll();
}
