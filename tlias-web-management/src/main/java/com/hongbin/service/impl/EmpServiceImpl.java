package com.hongbin.service.impl;

import com.hongbin.exception.BusinessException;
import com.hongbin.mapper.EmpMapper;
import com.hongbin.pojo.Emp;
import com.hongbin.pojo.PageBean;
import com.hongbin.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    //根据搜索条件，获取emp list
//    @Override
    public List<Emp> getEmpsList(String name, Short gender, LocalDate beginScope, LocalDate endScope, Integer pageNo, Integer rows) {
        if(pageNo == null || pageNo < 1){
            pageNo = 1;
        }
        if(rows == null || rows < 1){
            rows = 10;
        }
        name = name.trim();
        if(name.isEmpty()){
            name = null;
        }
        Integer startRow = (pageNo - 1) * rows;
        List<Emp> empsList = empMapper.getEmpsList(name, gender, beginScope, endScope, startRow, rows);
        return empsList;
    }

    //根据搜索条件，获得总记录数
//    @Override
    public Long getEmpsCount(String name, Short gender, LocalDate beginScope, LocalDate endScope) {
        name = name.trim();
        if(name.isEmpty()){
            name = null;
        }
        return empMapper.empsCount(name, gender, beginScope, endScope);
    }

    /*
        根据搜索条件，通过 getEmpsCount(xxx), getEmpsList(xxx)获取数据数量及emp数据
     */
    public PageBean searchEmpReturnPageBean(String name, Short gender, LocalDate beginScope, LocalDate endScope, Integer pageNo, Integer rows){
        Long empsCount = getEmpsCount(name,gender,beginScope,endScope);
        List<Emp> empList = getEmpsList(name,gender,beginScope,endScope,pageNo,rows);

        PageBean pageBean = new PageBean();
        pageBean.setTotal(empsCount);
        pageBean.setRows(empList);

        return pageBean;
    }

    @Override
    @Transactional
    public int deleteEmpsByIds(List<Integer> ids) {

        return empMapper.deleteEmpsByIds(ids);
    }

    @Override
    public int insertEmp(Emp emp) {
        if(emp.getUsername() == null){
            throw new BusinessException(40201,"Username is null");
        }
        return empMapper.insertEmp(emp);
    }

    @Override
    public Emp getEmpById(Integer id) {
//        Emp tempEmp = empMapper.getEmpById(id);
//        if(tempEmp == null){
//            return null;
//        }
        return empMapper.getEmpById(id);
    }

    @Override
    public int updateEmp(Emp emp) {
        return empMapper.updateEmp(emp);
    }

    @Override
    public Emp loginCheck(Emp emp) {
        return empMapper.loginCheck(emp);
    }





}
