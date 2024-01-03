package com.example.management_demo.mapper;

import com.example.management_demo.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 查询全部部门数据
     * @return List<Dept>
     */

    List<Dept> list();

    void deleteById(Integer id);

    void insert(Dept dept);

    Dept selectById(Integer id);

    void update(Dept dept);
}
