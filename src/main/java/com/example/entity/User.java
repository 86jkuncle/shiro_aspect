package com.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

/**
 * @author Administrator
 * @date 2019/9/9 9:48
 */
@Getter
@Setter
@Accessors(chain = true)
@Alias("User")
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer status;

    public boolean isLocked(){
        return status == 0;
    }
}
