package com.hongbin.service.impl;

import com.hongbin.mapper.DeptMapper;
import com.hongbin.mapper.EmpMapper;
import com.hongbin.pojo.Dept;
import com.hongbin.pojo.DeptLog;
import com.hongbin.service.DeptLogService;
import com.hongbin.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;

    public List<Dept> getAllDepts() {
        return deptMapper.getAllDepts();
    }

    @Override
    public Dept getDeptById(int id) {
        return deptMapper.getDeptById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteDeptById(int id) {
        int status = 0;
        try {
            deptMapper.deleteDeptById(id);
//            int i = 1/0;
            deleteEmpsByDeptId(id);
        } catch (Exception e) {
            status = -1;
        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散 " + id + " 号部门的操作");
            if (status == 0) {
                deptLog.setOperationStatus(1);
            }else {
                deptLog.setOperationStatus(-1);
            }
            deptLogService.insertDeptLog(deptLog);
        }
    }

    public void insertDept(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insertDept(dept);
    }

    public void updateDept(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateDept(dept);
    }

    @Override
    public void deleteEmpsByDeptId(int deptId) {
        empMapper.deleteEmpsByDeptId(deptId);
    }


}
