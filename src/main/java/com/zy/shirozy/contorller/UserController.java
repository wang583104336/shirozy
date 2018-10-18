package com.zy.shirozy.contorller;

import com.zy.shirozy.common.ResultUtil;
import com.zy.shirozy.domain.Focususer;
import com.zy.shirozy.domain.User;
import com.zy.shirozy.service.MsgService;
import com.zy.shirozy.service.UserService;
import com.zy.shirozy.vo.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MsgService msgService;



    @PostMapping("userregister.do")
    public R save(User user){
        return userService.save(user);
    }


    @PostMapping("/userlogin.do")
    public R login(String name, String password, HttpServletRequest request){
        User user = userService.login(name, password);
        if(user!=null){
            Subject subject=SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(name,password);
            subject.getSession().setAttribute("user",user);
            subject.login(token);
            return ResultUtil.setOK("登录成功");
        }else {
            return ResultUtil.setERROR("用户名或密码错误");
        }

    }

    //修改个人资料
    @PostMapping(value ="userupdate.do")
    //@ResponseBody
    public String updateUserById(User user){
        if(userService.updateUserById(user)){
            return "redirect:/memenber.html";
        }else {
            return "redirect:/EditData.html";
        }
    }

    //粉丝数量、关注人数和消息数量
    @RequestMapping("count.do")
    @ResponseBody
    public R selectCountBeByUid(HttpSession session) {
        User user = (User)session.getAttribute("user");
        //关注人数
        int count1 = userService.selectCountBeByUid(user.getId());
        //粉丝数量
        int count2 = userService.selectCountToByUid(user.getId());
        //消息数量
        int count3 = msgService.countOfMsg(user.getId());

        Map<String, Object> map = new HashMap<>();
        map.put("uname", user.getName());
        map.put("photo", user.getPhoto());
        map.put("countbe", count1);
        map.put("countto", count2);
        map.put("countmsg", count3);
        R r = new R();
        r.setCode(1);
        r.setData(map);

        return r;
    }

    @RequestMapping("/fanslist.do")
    @ResponseBody
    public R selectFans(HttpSession session) {
        R r = new R();
        Map<String, Object> map = new HashMap<>();
        User user = (User)session.getAttribute("user");

        List<User> list1 = userService.selectFansByUid1(user.getId());
        List<User> list0 = userService.selectFansByUid0(user.getId());
        map.put("list1", list1);
        map.put("list0", list0);

        r.setCode(1);
        r.setData(map);
        return r;
    }

    @PostMapping("/changeflag.do")
    @ResponseBody
    public R changeFlag(HttpSession session, int id) {
        R r = new R();
        User user = (User)session.getAttribute("user");
        Focususer focususer = userService.selectByToAndBe(id, user.getId());
        if (focususer.getFlag() == 1) {
            userService.deleteByToAndBe(user.getId(), id);
            userService.updateByToAndBe1(id, user.getId());
        } else {
            userService.insertByToAndBe(user.getId(), id);
            userService.updateByToAndBe0(id, user.getId());
        }
        r.setCode(1);

        return r;
    }
}
