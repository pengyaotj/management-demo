package com.example.management_demo.mapper;

import com.example.management_demo.pojo.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptLogMapper {

    void insert(DeptLog log);

}