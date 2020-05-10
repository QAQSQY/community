package top.yuyayao.community.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yuyayao.community.community.mapper.UserMapper;
import top.yuyayao.community.community.model.User;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public void createOrupdate(User user) {
        User dbUser = userMapper.findByaccountId(user.getAccountId());
        if (dbUser == null) {
            user.setGmtCreate(System.currentTimeMillis());
            user.setToken(user.getToken());
            user.setGmtModified(user.getGmtModified());
        } else {
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            userMapper.updateUser(dbUser);
        }
    }
}
