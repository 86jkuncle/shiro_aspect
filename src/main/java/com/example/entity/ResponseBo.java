package com.example.entity;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author Administrator
 * @date 2019/9/9 10:46
 */
public class ResponseBo extends HashMap<String,Object> implements Serializable {
    private ResponseBo(){
        put("code",0);
        put("msg","操作成功");
    }

    public static ResponseBo ok(){
        return new ResponseBo();
    }

    public static ResponseBo ok(String msg){
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("msg",msg);
        return responseBo;
    }

    public static ResponseBo error(){
        return error(1,"操作失败");
    }

    public static ResponseBo error(String msg){
        return error(500,msg);
    }

    private static ResponseBo error(int code,String msg){
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("code",code);
        responseBo.put("msg",msg);
        return responseBo;
    }

    @Override
    public ResponseBo put(String key,Object value){
        super.put(key,value);
        return this;
    }


}
