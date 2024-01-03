package com.example.management_demo.controller;

import com.example.management_demo.pojo.Dept;
import com.example.management_demo.pojo.Result;
import com.example.management_demo.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    // private static Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;

    /**
     * 查询全部部门数据
     * @return
     */
    @GetMapping
    public Result list() {
        log.info("查询全部部门数据");

        // 调用service查询全部部门数据
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    /**
     * 根据ID查询部门
     * @return
     */
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id) {
        log.info("根据id查询部门：{}",id);

        Dept dept = deptService.selectById(id);
        return Result.success(dept);
    }

    /**
     * 根据ID删除部门
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteDept(@PathVariable Integer id) {
        log.info("根据id删除部门：{}",id);

        deptService.delete(id);
        return Result.success();
    }

    /**
     * 新增部门
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门：{}",dept);

        deptService.add(dept);
        return Result.success();
    }

    /**
     * 修改部门
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("新增部门：{}",dept);

        deptService.update(dept);
        return Result.success();
    }

}
