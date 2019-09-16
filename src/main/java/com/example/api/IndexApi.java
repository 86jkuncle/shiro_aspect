package com.example.api;

import com.example.entity.ResponseBo;
import com.example.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 * @date 2019/9/9 10:07
 */
@Controller
public class IndexApi {

    @RequestMapping("/index")
    public String  index(Model model){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user",user);
        return "index";
    }

    @RequestMapping("/")
    public String redirectIndex(){
        return "redirect:/index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseBo login(String username,String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try{
            subject.login(token);
        }catch(UnknownAccountException e){
            return ResponseBo.error("用户不存在");
        }catch (IncorrectCredentialsException e){
            return ResponseBo.error("用户名或密码错误");
        }catch (LockedAccountException e){
            return ResponseBo.error("用户已被锁定,请联系管理员");
        }catch (NullPointerException e){
            return ResponseBo.error("用户名不能为空");
        }catch (AuthenticationException e){

        }catch(Exception e){
            return ResponseBo.error("未知错误");
        }
        return null;
    }

}
