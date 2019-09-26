package org.study.web.model;

import lombok.Data;

import java.util.Date;

/**
 * @desc: TODO
 * @author: lipo
 * @date: 2019-09-20 18:40
 * @version: v1.0
 */
@Data
public class UserQuery {
    private Date startDate;
    private Date endDate;
    private String token;
    private String sign;
}
