package com.example.management_demo.service.impl;

import com.example.management_demo.mapper.EmpMapper;
import com.example.management_demo.pojo.Emp;
import com.example.management_demo.pojo.PageBean;
import com.example.management_demo.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate start, LocalDate end) {
//        // 1.获取总记录数
//        Long count = empMapper.count();
//        // 2.获取分页查询结果列表
//        Integer start = (page - 1) * pageSize;
//        List<Emp> empList = empMapper.page(start,pageSize);
//        // 3.封装对象
//        PageBean pageBean = new PageBean(count,empList);
//        return pageBean;


        // 1.设置分页参数
        PageHelper.startPage(page, pageSize);
        // 2.执行查询
        List<Emp> empList = empMapper.list(name, gender, start, end);
        Page<Emp> p = (Page<Emp>)empList;
        // 3.封装对象
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);

    }

    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.insert(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUserAndPassword(emp);
    }
}
