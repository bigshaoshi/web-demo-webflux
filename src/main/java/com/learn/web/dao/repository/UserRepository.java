package com.learn.web.dao.repository;

import com.learn.web.dao.entity.UserDO;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<UserDO,Long> {

}
