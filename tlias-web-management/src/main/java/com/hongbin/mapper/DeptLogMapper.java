package com.hongbin.mapper;

import com.hongbin.pojo.DeptLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptLogMapper {

    public void insertDeptLog(DeptLog deptLog);

}
