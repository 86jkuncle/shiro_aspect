package com.example.services.impl;

import com.example.entity.Log;
import com.example.mapper.LogMapper;
import com.example.services.ILog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @date 2019/9/10 14:10
 */
@Service("logServices")
public class LogImpl implements ILog {

    @Resource
    private LogMapper logMapper;
    @Override
    public void saveSysLog(Log log) {
        logMapper.saveLog(log);
    }
}
