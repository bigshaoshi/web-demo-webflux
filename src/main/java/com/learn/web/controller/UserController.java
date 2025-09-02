package com.learn.web.controller;

import com.learn.web.dao.entity.UserDO;
import com.learn.web.model.qry.UserListQry;
import com.learn.web.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v2/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "新增用户")
    @PostMapping
    public Mono<UserDO> save(@RequestBody UserDO userDO) {
        return userService.save(userDO);
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return userService.deleteById(id);
    }

    @Operation(summary = "修改用户")
    @PutMapping("/{id}")
    public Mono<UserDO> update(@Parameter(description = "用户id") @PathVariable Long id, @RequestBody UserDO userDO) {
        userDO.setId(id);
        return userService.updateUser(userDO);
    }


    @Operation(summary = "根据id查询用户")
    @GetMapping("/{id}")
    public Mono<UserDO> getById(@Parameter(description = "用户id") @PathVariable Long id) {
        return userService.getById(id);
    }

    @Operation(summary = "根据条件查询用户")
    @GetMapping("/list")
    public Flux<UserDO> getList(@ParameterObject UserListQry userListQry) {
        return userService.getList(userListQry);
    }

}
