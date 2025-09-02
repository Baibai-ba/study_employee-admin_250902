package com.hongbin.controller;

import com.hongbin.anno.Log;
import com.hongbin.pojo.Dept;
import com.hongbin.pojo.Result;
import com.hongbin.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class DeptController {

//    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DeptService deptService;
//        @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result getAllDepts(){
        log.info("查询全部的部门信息");
//        return new Result(1,"ok",deptService.getAllDepts());
        return Result.success(deptService.getAllDepts());
    }

    @GetMapping("/depts/{id}")
    public Result getDeptById(@PathVariable int id){
        log.info("根据id查询部门");
        Dept dept = deptService.getDeptById(id);
        if(dept == null){
            return Result.error("所搜索的部门不存在");
        }
        return Result.success(dept);
    }

    @Log
    @DeleteMapping("/depts/{id}")
    public Result deleteDeptById(@PathVariable int id){
        log.info("删除部门 "+ id);
        deptService.deleteDeptById(id);
        return Result.success("删除");
    }

    @Log
    @PostMapping("/depts")
    public Result insertDept(@RequestBody Dept dept){
        log.info("add new dept: "+ dept);
        deptService.insertDept(dept);
        return Result.success("添加成功");
    }

//    @PostMapping("/depts")
//    public Result insertDept(@RequestBody Dept dept){
//        log.info("add new dept: "+ dept);
////      deptService.insertDept(dept);
//        try {
//            deptService.insertDept(dept);
//        }catch (Exception e){
//            System.out.println("部门名称重复");
////            e.printStackTrace();
//            return Result.error("部门名称重复");
//        }
//        return Result.success("添加成功");
//    }






    @Log
    @PutMapping("/depts")
    public Result updateDeptName(@RequestBody Dept dept){
        log.info("updateDeptName");

        try {
            deptService.updateDept(dept);
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("部门名称重复");
        }
        return Result.success("修改成功");
    }

    @RequestMapping("/xxxx")
    public Result checkxxx(){
        System.out.println("\nhahaha\n");
        return null;
    }

}
