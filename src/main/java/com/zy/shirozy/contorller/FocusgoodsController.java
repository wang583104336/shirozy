package com.zy.shirozy.contorller;

import com.zy.shirozy.domain.Focusgoods;
import com.zy.shirozy.domain.GoodDetaill;
import com.zy.shirozy.domain.User;
import com.zy.shirozy.service.FocusgoodsService;
import com.zy.shirozy.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class FocusgoodsController {
    @Autowired
    public FocusgoodsService focusgoodsService;

    @RequestMapping("/focusgoodslist.do")
    @ResponseBody
    public R selectByUid(HttpSession session) {
        User user = (User)session.getAttribute("user");
        List<GoodDetaill> list = focusgoodsService.selectByUid(user.getId());
        R r = new R();
        r.setCode(1);
        r.setData(list);

        return r;
    }
}
