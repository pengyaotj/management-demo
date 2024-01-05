package com.example.management_demo.service.impl;

import com.example.management_demo.aop.MyLog;
import com.example.management_demo.mapper.DeptMapper;
import com.example.management_demo.mapper.EmpMapper;
import com.example.management_demo.pojo.Dept;
import com.example.management_demo.pojo.DeptLog;
import com.example.management_demo.service.DeptLogService;
import com.example.management_demo.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;

    @MyLog
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @MyLog
    @Transactional
    public void delete(Integer id) {
        try {
            deptMapper.deleteById(id); //根据ID删除部门数据

            int i = 1/0;

            empMapper.deleteByDeptId(id); //根据ID删除该部门下员工数据

        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，此次解散的是"+id+"号部门");
            deptLogService.insert(deptLog);
        }
    }

    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(dept);
    }

    public Dept selectById(Integer id) {
        return deptMapper.selectById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.update(dept);
    }


}
