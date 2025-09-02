package com.hongbin.controller;

import com.hongbin.anno.Log;
import com.hongbin.exception.BusinessException;
import com.hongbin.pojo.Emp;
import com.hongbin.pojo.PageBean;
import com.hongbin.pojo.Result;
import com.hongbin.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping("/emps")
    public Result searchEmpReturnPageBean(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "gender", required = false) Short gender,
            @RequestParam(name = "begin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate beginScope,
            @RequestParam(name = "end", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endScope,
            @RequestParam(name = "page", required = true, defaultValue = "1") Integer pageNo,
            @RequestParam(name = "pageSize", required = true, defaultValue = "10") Integer rows) {
        log.info("参数输出：{},{},{},{},{},{}", name, gender, beginScope, endScope, pageNo, rows);

        PageBean pageBean = empService.searchEmpReturnPageBean(name, gender, beginScope, endScope, pageNo, rows);

        return Result.success(pageBean);
    }

    @Log
    @DeleteMapping("/emps/{ids}")
    public Result deleteEmpsByIds(@PathVariable List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return Result.error("无需要删除的用户");
        }

//        log.info("批量删除操作，ids: {}", ids);
        int deleteCount = empService.deleteEmpsByIds(ids);
//        System.out.println("\n\n" + deleteCount + "\n\n");
        return Result.success(deleteCount + "条用户数据删除成功");
    }

    @Log
    @PostMapping("/emps")
    public Result insertEmp(@RequestBody Emp emp) {
        int insertEmpCount;
        try{
            insertEmpCount = empService.insertEmp(emp);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error(e.getMessage());
//            throw new RuntimeException(e);
//            log.info("" + e.getCode());
        }
        return Result.success(insertEmpCount + "插入成功");
    }

    @GetMapping("/emps/{id}")
    public Result getEmpById(@PathVariable Integer id){
        Emp tempEmp = empService.getEmpById(id);
        if (tempEmp == null) {
            return Result.error("未找到对应emp");
        }
        return Result.success(tempEmp);
    }

    @Log
    @PutMapping("/emps")
    public Result updateEmpById(@RequestBody Emp emp) {
        int temp;
        try{
            temp = empService.updateEmp(emp);
        }catch (Exception e) {
            return Result.error("username 重复了");
        }
        if (temp == 0) {
            return Result.error("未能更新");
        }
        return Result.success();
    }



//    public ResponseEntity<String> delete(String id) {
//
//    }
}
