package com.itheima.service;

import com.itheima.domain.User;

public interface FavoriteService {
    public String isFavorite(String rid, User loginUser);
    public String addFavorite(String rid, User loginUser);
    String pageQuery(User loginUser, String strPageNum, String strPageSize);
}
