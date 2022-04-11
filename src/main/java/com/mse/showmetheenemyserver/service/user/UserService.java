package com.mse.showmetheenemyserver.service.user;

import com.mse.showmetheenemyserver.domain.User;

import java.util.List;

public interface UserService {
    User getUser(String username);
    List<User> getUsers();
}
