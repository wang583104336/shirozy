package com.zy.shirozy.mapper;

import com.zy.shirozy.domain.Focusgoods;
import com.zy.shirozy.domain.GoodDetaill;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FocusgoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Focusgoods record);

    int insertSelective(Focusgoods record);

    Focusgoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Focusgoods record);

    int updateByPrimaryKey(Focusgoods record);

    @Select("select gd.* from t_gooddetail gd inner join t_focusgoods fg on gd.id=fg.goodid where fg.uid=#{uid}")
    List<GoodDetaill> selectByUid(int uid);
}