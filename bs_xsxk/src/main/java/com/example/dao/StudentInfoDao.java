package com.example.dao;


import com.example.entity.StudentInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface StudentInfoDao extends Mapper<StudentInfo> {

    @Select("select  * from  student_info where name= #{name} and password = #{password}")
    StudentInfo findByNameAndPassword(@Param("name") String name, @Param("password") String password);

    @Select("select * from  student_info where name = #{name}")
    StudentInfo findByName(@Param("name") String name);

    @Select("select a.*,b.name as xueyuanName from student_info as a left join xueyuan_info as b on a.xueyuanId=b.id")
    List<StudentInfo> findAllJoinXueyuan();
}