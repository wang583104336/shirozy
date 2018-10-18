package com.zy.shirozy.mapper;

import com.zy.shirozy.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    @Insert("insert into t_user(no, password, name, phone, flag) value(#{no}, #{name}, #{phone}, #{flag})")
    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select("select * from t_user where no=#{name} or name=#{name} and flag=1")
    @ResultType(User.class)
    User selectByName(String name);

    @Update("update t_user set no=#{no}, name=#{name}, phone=#{phone}, sex=#{sex}, intro=#{intro} where id=#{id}")
    int updateByNo(User user);
}