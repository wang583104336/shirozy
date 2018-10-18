package com.zy.shirozy.service;

import com.zy.shirozy.mapper.MsgMapper;
import org.springframework.beans.factory.annotation.Autowired;

public interface MsgService {

    int countOfMsg(int uid);
}
