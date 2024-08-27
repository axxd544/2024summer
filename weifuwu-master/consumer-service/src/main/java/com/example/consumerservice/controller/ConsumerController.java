package com.example.consumerservice.controller;

import com.example.consumerservice.common.R;
import com.example.consumerservice.model.domain.Consumer;
import com.example.consumerservice.model.request.ConsumerRequest;
import com.example.consumerservice.model.request.ResetPasswordRequest;
import com.example.consumerservice.service.ConsumerService;
import com.example.consumerservice.service.impl.ConsumerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Api(tags="用户接口")
@RestController
@RequestMapping
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    ConsumerServiceImpl consumerServiceimpl;


    @Autowired
    StringRedisTemplate stringRedisTemplate;
    /**
     * TODO 前台页面调用 注册
     * 用户注册
     */
    @ApiOperation(value = "增加新用户")
    @PostMapping("/user/add")
    public R addUser(@RequestBody ConsumerRequest registryRequest) {
        return consumerService.addUser(registryRequest);
    }

    /**
     * TODO 前台页面调用  登录
     * 登录判断
     */
    @PostMapping("/user/login/status")
    public R loginStatus(@RequestBody ConsumerRequest loginRequest, HttpSession session) {
        return consumerService.loginStatus(loginRequest, session);
    }


    /**
     * 密码恢复（忘记密码）
     */

    @PostMapping("/user/resetPassword")
    public R resetPassword(@RequestBody ResetPasswordRequest passwordRequest){
        Consumer user = consumerService.findByEmail(passwordRequest.getEmail());
        String code = stringRedisTemplate.opsForValue().get("code");
        if (user==null){
            return R.fatal("用户不存在");
        }else if (!code.equals(passwordRequest.getCode())){
            return R.fatal("验证码不存在或失效");
        }
        ConsumerRequest consumerRequest=new ConsumerRequest();
        BeanUtils.copyProperties(user, consumerRequest);
        System.out.println(user);
        System.out.println(consumerRequest);
        consumerRequest.setPassword(passwordRequest.getPassword());
        consumerServiceimpl.updatePassword01(consumerRequest);

        return R.success("密码修改成功");
    }


    /**
     * TODO 管理界面调用
     * 返回所有用户
     */
    @GetMapping("/user")
    public R allUser() {
        return consumerService.allUser();
    }


    /**
     * TODO 用户界面调用
     * 返回指定 ID 的用户
     */
    @GetMapping("/user/detail")
    public R userOfId(@RequestParam int id) {
        return consumerService.userOfId(id);
    }

    //返回like用户名的用户
    @GetMapping("/user/likeUsername/detail")
    public R userLikeUsername(@RequestParam String username) {
        return consumerService.likeUsername('%' + username + '%');
    }

    //查找和给定name完全相同的用户
    @GetMapping("/user/by-singername")
    public R getConsumerByUsername(@RequestParam String username){
        return consumerService.getConsumerByUsername(username);
    }

    /**
     * TODO 管理界面的调用
     * 删除用户
     */
    @GetMapping("/user/delete")
    public R deleteUser(@RequestParam int id) {
        return consumerService.deleteUser(id);
    }

    /**
     * TODO 前后台界面的调用
     * 更新用户信息
     */
    @PostMapping("/user/update")
    public R updateUserMsg(@RequestBody ConsumerRequest updateRequest) {
        return consumerService.updateUserMsg(updateRequest);
    }

    /**
     * TODO 前后台更新用户的密码
     * 更新用户密码
     */
    @PostMapping("/user/updatePassword")
    public R updatePassword(@RequestBody ConsumerRequest updatePasswordRequest) {
        return consumerService.updatePassword(updatePasswordRequest);
    }

    /**
     * 更新用户头像
     */
    @PostMapping("/user/avatar/update")
    public R updateUserPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        return consumerService.updateUserAvator(avatorFile, id);
    }

}
