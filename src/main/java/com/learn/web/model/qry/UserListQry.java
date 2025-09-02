package com.learn.web.model.qry;

import lombok.Data;

import java.util.List;

@Data
public class UserListQry {

    private List<Long> userIdList;

    private String keywords;
}
