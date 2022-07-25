package com.itheima.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.dao.CategoryMapper;
import com.itheima.domain.Category;
import com.itheima.service.CategoryService;
import com.itheima.utils.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public String queryAll() {
        //1、优先从redis中查询：有数据直接返回，没数据从mysql中查询
        Jedis jedis = JedisUtil.getJedis();
        String categoryListStr = jedis.get("HEIMA91_CATEGORY_LIST");
        //2、redis中没有数据，从mysql中查询
        if (categoryListStr == null) {
            List<Category> categories =categoryMapper.queryAll();
            try {
                categoryListStr = new ObjectMapper().writeValueAsString(categories);
                jedis.set("HEIMA91_CATEGORY_LIST", categoryListStr);
            } catch (Exception e) {
            }
        }
        JedisUtil.close(jedis);
        return categoryListStr;
    }
}
