package com.itheima.dao;

import com.itheima.domain.Seller;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface SellerMapper {
    @Select("SELECT * FROM tab_seller WHERE sid = #{sid}")
    Seller findBySid(int sid);
}
