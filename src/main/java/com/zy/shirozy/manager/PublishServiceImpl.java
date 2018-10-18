package com.zy.shirozy.manager;

import com.zy.shirozy.domain.Publish;
import com.zy.shirozy.mapper.PublishMapper;
import com.zy.shirozy.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublishServiceImpl implements PublishService {
    @Autowired
    public PublishMapper publishMapper;

    @Override
    public List<Publish> findByUid(int uid) {
        List<Publish> list = null;
        if (uid != 0) {
            list = publishMapper.selectByUid(uid);
        }
        return list;
    }
}
