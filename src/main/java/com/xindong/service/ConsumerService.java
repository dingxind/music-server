package com.xindong.service;

import com.xindong.entities.Consumer;

import java.util.List;

public interface ConsumerService {

    boolean addUser(Consumer consumer);

    boolean updateUserMsg(Consumer consumer);

    boolean updateUserAvator(Consumer consumer);

    boolean existUser(String username);

    boolean veritypasswd(String username, String password);

    boolean deleteUser(Integer id);

    List<Consumer> allUser();

    List<Consumer> conmmentUser(Integer id);

    List<Consumer> consumerLists(String username);

}
