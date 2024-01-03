package com.example.management_demo.controller;

import com.example.management_demo.pojo.Emp;
import com.example.management_demo.pojo.PageBean;
import com.example.management_demo.pojo.Result;
import com.example.management_demo.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.net.Inet4Address;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询，参数：{},{},{},{},{},{}",page,pageSize,name,gender,start,end);

        PageBean pageBean = empService.page(page,pageSize,name,gender,start,end);
        return Result.success(pageBean);
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("执行批量删除操作，ids:{}",ids);

        empService.delete(ids);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Emp emp) {
        log.info("新增员工，emp: {}",emp);
        empService.add(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询员工信息，id: {}",id);

        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result updateById(@RequestBody Emp emp) {
        log.info("根据ID查询员工信息，id: {}",emp.getId());

        empService.update(emp);
        return Result.success();
    }
}