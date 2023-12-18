package com.example.dao;



import com.example.entity.ClassInfo;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@Repository
public interface ClassInfoDao extends Mapper<ClassInfo> {

    @Select("SELECT a.* ,b.name as teacherName from class_info as a LEFT JOIN teacher_info AS b on a.teacherId=b.id where a.name like concat('%',#{search},'%')")
    List<ClassInfo> findBySearch(@Param("search") String search);

    @Select("SELECT a.* ,b.name as teacherName from class_info as a LEFT JOIN teacher_info AS b on a.teacherId=b.id")
    List<ClassInfo> findAll();

    @Select("select  * from class_info where name = #{name} and teacherId = #{teacherId} limit 1")
    ClassInfo findByNameAndTeacher(@Param("name") String name,@Param("teacherId") Long teacherId);
}