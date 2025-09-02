package com.hongbin;

import com.hongbin.controller.EmpController;
import com.hongbin.mapper.DeptMapper;
import com.hongbin.mapper.EmpMapper;
import com.hongbin.pojo.Dept;
import com.hongbin.pojo.Emp;
import com.hongbin.pojo.PageBean;
import com.hongbin.pojo.Result;
import com.hongbin.service.impl.EmpServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
class TliasWebManagementApplicationTests {

    @Autowired
    public DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    public EmpServiceImpl empService;
    @Autowired
    private EmpController empController;

    @Test
    public void testGetAllDepts() {
        List<Dept> DeptsList = deptMapper.getAllDepts();
        for (Dept dept : DeptsList) {
            System.out.println(dept);
        }
    }

    @Test
    public void testGetDeptById() {
        Dept dept = deptMapper.getDeptById(22);
        System.out.println(dept);
    }


    @Test
    public void testDeleteDept() {
        deptMapper.deleteDeptById(10);
        List<Dept> DeptsList = deptMapper.getAllDepts();
        for (Dept dept : DeptsList) {
            System.out.println(dept);
        }
    }

    @Test
    public void testInsertDept() {
        Dept dept = new Dept("测试4部", LocalDateTime.now(), LocalDateTime.now());
        deptMapper.insertDept(dept);
        List<Dept> DeptsList = deptMapper.getAllDepts();
        for (Dept dept1 : DeptsList) {
            System.out.println(dept1);
        }
    }

    @Test
    public void testUpdateDept() {
        Dept dept = deptMapper.getDeptById(25);
        dept.setName("嘿嘿嘿2");
        deptMapper.updateDept(dept);
    }


    /// ////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////

    @Test
    public void testGetEmpsListFromMapperLayer() {
        LocalDate beginScope = LocalDate.of(2005, 1, 1);
        LocalDate endScope = LocalDate.of(2015, 1, 1);

        List<Emp> empsList = empMapper.getEmpsList(null, (short) 2, beginScope, endScope, 0, 3);
        for (Emp emp : empsList) {
            System.out.println(emp);
        }
    }


    @Test
    public void testGetEmpsListFromServiceLayer() {
//        Short gender = Short.valueOf(1);
        LocalDate beginScope = LocalDate.of(2005, 1, 1);
        LocalDate endScope = LocalDate.of(2015, 1, 1);
        List<Emp> empsList = empService.getEmpsList("小", (short) 2, beginScope, endScope, 1, 3);
        for (Emp emp : empsList) {
            System.out.println(emp);
        }
    }

    @Test
    public void testEmpsCount() {
//        LocalDate beginScope = LocalDate.of(2005, 1, 1);
//        LocalDate endScope = LocalDate.of(2015, 1, 1);
        Long empsCount = empService.getEmpsCount(null, (short) 2, null, null);
        System.out.println(empsCount);
    }

    @Test
    public void testSearchEmpReturnPageBean() {
        LocalDate beginScope = LocalDate.of(2005, 1, 1);
        LocalDate endScope = LocalDate.of(2015, 1, 1);

        PageBean pageBean = empService.searchEmpReturnPageBean(null, (short) 2, beginScope, endScope, 1, 3);
        System.out.println(pageBean);
    }

    @Test
    public void testSearchEmpReturnPageBeanInController() {
        LocalDate beginScope = LocalDate.of(2005, 1, 1);
        LocalDate endScope = LocalDate.of(2015, 1, 1);
        Result result = empController.searchEmpReturnPageBean(null, (short) 2, beginScope, endScope, 1, 4);
        System.out.println(result);
    }


    @Test
    public void testDeleteEmpsByIds() {
        List<Integer> ids = Arrays.asList(20,21,22,23,24,25);
        empService.deleteEmpsByIds(ids);
    }

    @Test
    public void testDeleteEmpsByIdsInControllerLayer() {
        List<Integer> ids = Arrays.asList(20,21,22,23,24,25);
        empController.deleteEmpsByIds(ids);
    }

    @Test
    public void testInsertEmp() {
        Emp emp = new Emp();
        emp.setUsername("hahaha2");
        emp.setName("倚天屠龙记");
        emp.setGender((short)1);
        emp.setImage("5.jpg");
        emp.setJob((short)2);
        emp.setEntrydate(LocalDate.of(2015, 1, 1));
        emp.setDeptId(2);

        int insertEmpCount = empMapper.insertEmp(emp);
        System.out.println(insertEmpCount + "插入emp成功");
    }

    @Test
    public void testGetEmpByIdInMapperLayer() {
        System.out.println(empMapper.getEmpById(36));
    }

    @Test
    public void testGetEmpById() {
        System.out.println(empController.getEmpById(36));
    }

    @Test
    public void testUpdateEmp() {
        Emp emp = new Emp();
        emp.setId(36);
        emp.setUsername("hahaha36");
        emp.setName("hahaha36");
        emp.setGender((short)1);
        emp.setImage("5.jpg");
        emp.setDeptId(2);
        emp.setEntrydate(LocalDate.of(2015, 1, 1));
        emp.setJob((short)2);
        int temp = empMapper.updateEmp(emp);
        System.out.println(temp);
    }

    @Test
    public void testUpdateEmpInControllerLayer() {
        Emp emp = new Emp();
        emp.setId(36);
        emp.setUsername("hahaha36");
        emp.setName("hahaha36");
        emp.setGender((short)1);
        emp.setImage("5.jpg");
        emp.setDeptId(2);
        emp.setEntrydate(LocalDate.of(2015, 1, 1));
        emp.setJob((short)2);
        int temp = empService.updateEmp(emp);
        System.out.println(temp);
    }

    @Test
    public void testUuid() {
        for (int i = 0; i < 1000; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
        }

    }

    @Test
    public void testJWT(){
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("id",37);
        claims.put("username", "hahaha2");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "nihongbin")
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .compact();
        System.out.println(jwt);
    }


    @Test
    public void testParseJWT(){
        Claims claims = Jwts.parser()
                .setSigningKey("nihongbin")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MzcsImV4cCI6MTc1Mzc4MDkzNiwidXNlcm5hbWUiOiJoYWhhaGEyIn0.SW2cpg7BDTLEy1S8-aCeD_fCQukXkkXDmMe1Wj_xYkk")
                .getBody();
//                .getHeader();
        System.out.println(claims);
    }







}
