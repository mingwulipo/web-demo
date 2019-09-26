package org.study.web.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.web.model.UserQuery;

import javax.servlet.http.HttpServletRequest;

/**
 * @desc: TODO
 * @author: lipo
 * @date: 2019-09-20 18:38
 * @version: v1.0
 */
@RestController
@RequestMapping("user")
public class UserController {

    //http://localhost:8080/user/list
    /**
    {
    "startDate": "2019-09-01T12:12:12",
    "endDate": "2019-09-21T12:12:12"
}
     */
    @RequestMapping("list")
    public Object listUser(@RequestBody UserQuery query, HttpServletRequest request) {
        System.out.println(request.getParameter("token"));
        return "ok";
    }

}
