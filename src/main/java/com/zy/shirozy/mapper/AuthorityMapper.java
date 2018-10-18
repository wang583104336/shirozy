package com.zy.shirozy.mapper;


import com.zy.shirozy.domain.Authority;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AuthorityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Authority record);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);

    @Select("SELECT DISTINCT a1.* from t_authority a1 INNER JOIN t_roleauthority roa on a1.id = roa.aid INNER JOIN t_role r on roa.rid = r.id")
    @ResultType(Authority.class)
    List<Authority> selectByParentId();
}