package com.learn.web.service.impl;

import com.learn.web.dao.entity.UserDO;
import com.learn.web.dao.repository.UserRepository;
import com.learn.web.model.qry.UserListQry;
import com.learn.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final R2dbcEntityTemplate template;


    @Override
    public Mono<UserDO> getById(Long id) {
        return userRepository.findById(id)
                .filter(user ->
                        user.getDeleted() != null && user.getDeleted() == 0);
    }

    @Override
    public Flux<UserDO> getList(UserListQry userListQry) {
        Criteria criteria = Criteria.where("deleted").is(0); // 只查未删除的

        if (userListQry.getKeywords() != null && !userListQry.getKeywords().isEmpty()) {
            criteria = criteria.and("username").like("%" + userListQry.getKeywords() + "%")
                    .or("email").like("%" + userListQry.getKeywords() + "%")
                    .or("name").like("%" + userListQry.getKeywords() + "%");
        }

        if (userListQry.getUserIdList() != null && !userListQry.getUserIdList().isEmpty()) {
            criteria = criteria.and("id").in(userListQry.getUserIdList());
        }

        Query query = Query.query(criteria);

        return template.select(query, UserDO.class);
    }

    @Override
    public Mono<UserDO> save(UserDO userDO) {
        return userRepository.findById(userDO.getId())
                .flatMap(user -> {
                    if (userDO.getName() != null) user.setName(userDO.getName());
                    if (userDO.getUsername() != null) user.setUsername(userDO.getUsername());
                    if (userDO.getEmail() != null) user.setEmail(userDO.getEmail());
                    if (userDO.getPassword() != null) user.setPassword(userDO.getPassword());
                    return userRepository.save(user);
                });
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return userRepository.findById(id)
                .flatMap(user -> {
                    user.setDeleted(1);
                    return userRepository.save(user).then();
                });
    }

    @Override
    public Mono<UserDO> updateUser(UserDO userDO) {
        return userRepository.save(userDO);
    }

}
