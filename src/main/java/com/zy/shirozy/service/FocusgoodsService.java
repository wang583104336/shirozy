package com.zy.shirozy.service;

import com.zy.shirozy.domain.Focusgoods;
import com.zy.shirozy.domain.GoodDetaill;

import java.util.List;

public interface FocusgoodsService {
    List<GoodDetaill> selectByUid(int uid);
}
