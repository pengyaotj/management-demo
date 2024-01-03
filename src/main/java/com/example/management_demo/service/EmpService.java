package com.example.management_demo.service;

import com.example.management_demo.mapper.EmpMapper;
import com.example.management_demo.pojo.Emp;
import com.example.management_demo.pojo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate start, LocalDate end);

    void delete(List<Integer> ids);

    void add(Emp emp);

    Emp getById(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);
}
