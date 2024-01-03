package com.example.management_demo.service;

import com.example.management_demo.pojo.Dept;

import java.util.List;

public interface DeptService {

    /**
     * 查询全部部门数据
     * @return List<Dept>
     */
    List<Dept> list();

    void delete(Integer id);

    void add(Dept dept);

    Dept selectById(Integer id);

    void update(Dept dept);
}
