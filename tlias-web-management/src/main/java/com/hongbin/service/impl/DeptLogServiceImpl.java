package com.hongbin.service.impl;

import com.hongbin.mapper.DeptLogMapper;
import com.hongbin.pojo.DeptLog;
import com.hongbin.service.DeptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptLogServiceImpl implements DeptLogService {

    @Autowired
    DeptLogMapper deptLogMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insertDeptLog(DeptLog deptLog) {
        deptLogMapper.insertDeptLog(deptLog);
    }
}
