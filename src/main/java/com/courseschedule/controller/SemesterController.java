package com.courseschedule.controller;


import com.courseschedule.common.lang.Result;
import com.courseschedule.common.vo.SemesterVo;
import com.courseschedule.service.SemesterService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author
 * @since 2024-05-19
 */
@RestController
@RequestMapping("/semester")
public class SemesterController {
    @Autowired
    private SemesterService semesterService;

    // @RequiresAuthentication
    @GetMapping("/getList")
    public Result getList() {
        return semesterService.getList();
    }

    @RequiresAuthentication
    @PostMapping
    public Result add(@RequestBody @Validated SemesterVo vo) {
        return semesterService.add(vo);
    }

    @RequiresAuthentication
    @PutMapping
    public Result update(@RequestBody @Validated SemesterVo vo) {
        return semesterService.update(vo);
    }

    @RequiresAuthentication
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        return semesterService.delete(id);
    }
}
