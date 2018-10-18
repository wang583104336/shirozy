package com.zy.shirozy.manager;

import com.zy.shirozy.domain.Focusgoods;
import com.zy.shirozy.domain.GoodDetaill;
import com.zy.shirozy.mapper.FocusgoodsMapper;
import com.zy.shirozy.service.FocusgoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FocusgoodsServiceImpl implements FocusgoodsService {
    @Autowired
    public FocusgoodsMapper focusgoodsMapper;

    @Override
    public List<GoodDetaill> selectByUid(int uid) {
        List<GoodDetaill> list = null;
        if (uid != 0) {
            list = focusgoodsMapper.selectByUid(uid);
        }
        return list;
    }
}
