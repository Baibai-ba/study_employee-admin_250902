package com.hongbin.mapper;

import com.hongbin.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from dept")
    public List<Dept> getAllDepts();

    @Select("select * from dept where id = #{id}")
    public Dept getDeptById(int id);

    @Delete("delete from dept where id = #{id}")
    public void deleteDeptById(int id);

//    @Insert("insert into dept (name, create_time, update_time) values (#{name},now(),now())")


    @Insert("insert into dept (name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    public void insertDept(Dept dept);

    @Update("Update dept set name = #{name} where id = #{id}")
    public void updateDept(Dept dept);

}
