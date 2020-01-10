package org.study.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.study.web.aop.BizException;
import org.study.web.model.Result;
import org.study.web.model.UserQuery;

import javax.servlet.http.HttpServletRequest;

/**
 * @desc: TODO
 * @author: lipo
 * @date: 2019-09-20 18:38
 * @version: v1.0
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    /**
     * http://localhost:8080/user/list
     * @param query
     * @param request
     * @return
     */
    @PostMapping("list")
    public Object listUser(@RequestBody UserQuery query, HttpServletRequest request) {
        log.info(request.getParameter("token"));
        return "ok";
    }

    /**
     * http://localhost:8080/user/aop
     */
    @GetMapping("aop")
    public Object aop(UserQuery query) {
        if (query.getToken() == null) {
            throw new BizException(1, "token缺失");
        }
        return Result.ok();
    }

}
