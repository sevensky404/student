package com.example.service;



import cn.hutool.core.util.ObjectUtil;
import com.example.common.ResultCode;
import com.example.dao.StudentInfoDao;
import com.example.dao.XueyuanInfoDao;
import com.example.entity.Account;
import com.example.entity.StudentInfo;

import com.example.entity.XueyuanInfo;
import com.example.exception.CustomException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class StudentInfoService {
   @Resource
    private StudentInfoDao studentInfoDao;
   @Resource
   private XueyuanInfoDao xueyuanInfoDao;

    public Account login(String name, String password) {
        StudentInfo studentInfo=studentInfoDao.findByNameAndPassword(name,password);
        if(ObjectUtil.isEmpty(studentInfo)){
            throw new CustomException("-1","用户名、密码或者角色选择错误");
        }
        return studentInfo;
    }



    public StudentInfo findById(Long id) {
        return studentInfoDao.selectByPrimaryKey(id);
    }

    public void update(StudentInfo studentInfo) {
        studentInfoDao.updateByPrimaryKeySelective(studentInfo);
    }

    public List<StudentInfo> findAll() {
/*
        //方法一：使用java
        List<StudentInfo> list =studentInfoDao.selectAll();
        for(StudentInfo studentInfo : list){
            if(ObjectUtil.isNotEmpty(studentInfo.getXueyuanId())){
            XueyuanInfo xueyuanInfo = xueyuanInfoDao.selectByPrimaryKey(studentInfo.getXueyuanId());
            studentInfo.setXueyuanName(xueyuanInfo.getName());
            }
        }
        return list;
        */
        //方法二:使用sql
        List<StudentInfo> list2=studentInfoDao.findAllJoinXueyuan();
        return list2;
    }

    public void add(StudentInfo studentInfo) {
        StudentInfo info = studentInfoDao.findByName((studentInfo.getName()));
        if(ObjectUtil.isNotEmpty(info)){
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        if(ObjectUtil.isEmpty(studentInfo.getPassword())){
            studentInfo.setPassword("123456");
        }
        studentInfoDao.insertSelective(studentInfo);
    }

    public void deleteById(Long id) {
        studentInfoDao.deleteByPrimaryKey(id);
    }
}