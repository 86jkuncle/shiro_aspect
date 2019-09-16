package com.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

/**
 * @author Administrator
 * @date 2019/9/10 14:06
 */
@Getter
@Setter
@Accessors(chain = true)
@Alias("Log")
public class Log {
    private Integer id;
    private String username;
    private String operation;
    private Integer time;
    private String method;
    private String params;
    private String ip;
    private LocalDateTime createtime;
}
