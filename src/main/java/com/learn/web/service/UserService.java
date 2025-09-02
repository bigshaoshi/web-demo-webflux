package com.learn.web.service;

import com.learn.web.dao.entity.UserDO;
import com.learn.web.model.qry.UserListQry;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface UserService {
    Mono<UserDO> getById(Long id);

    Flux<UserDO> getList(UserListQry userListQry);

    Mono<UserDO> save(UserDO userDO);

    Mono<Void> deleteById(Long id);

    Mono<UserDO> updateUser(UserDO userDO);
}
