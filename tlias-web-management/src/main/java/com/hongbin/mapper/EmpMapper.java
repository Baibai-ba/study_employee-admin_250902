package com.hongbin.mapper;

import com.hongbin.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    public List<Emp> getEmpsList(String name, Short gender, LocalDate beginScope, LocalDate endScope, Integer startRow, Integer rows);

    public Long empsCount(String name, Short gender, LocalDate beginScope, LocalDate endScope);

    public int deleteEmpsByIds(List<Integer> ids);

    public int insertEmp(Emp emp);

    public Emp getEmpById(Integer id);

    public int updateEmp(Emp emp);

    public Emp loginCheck(Emp emp);

    @Delete("delete from emp where dept_id = #{deptId}")
    public void deleteEmpsByDeptId(Integer deptId);
}
