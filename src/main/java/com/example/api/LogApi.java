package com.example.api;

import com.example.annotation.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2019/9/10 14:30
 */
@RestController
@RequestMapping("/log")
public class LogApi {

    @Log("执行方法一")
    @GetMapping("/one")
    public void methodOne(String name){}

    @Log("执行方法二")
    @GetMapping("/two")
    public void methodTwo() throws InterruptedException{}

    @Log("执行方法三")
    @GetMapping("/three")
    public void methodThree(String name,Integer age){}
}
