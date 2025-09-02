package com.learn.web.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("sys_user")
public class UserDO {

    @Id
    private Long id;

    private String name;

    private String username;

    private String password;

    private String email;

    private Integer deleted;

}
