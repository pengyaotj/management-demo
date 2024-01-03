package com.example.management_demo.mapper;

import com.example.management_demo.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    public Long count();

    public List<Emp> page(Integer start, Integer pageSize);

    public List<Emp> list(String name, Short gender, LocalDate start, LocalDate end);

    void delete(List<Integer> ids);

    void insert(Emp emp);

    Emp getById(Integer id);

    void update(Emp emp);

    Emp getByUserAndPassword(Emp emp);

    void deleteByDeptId(Integer deptId);
}
