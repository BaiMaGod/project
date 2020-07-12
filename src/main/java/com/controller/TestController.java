package com.controller;

import com.common.annotation.ExeForm;
import com.common.annotation.SysLog;
import com.common.form.PageForm;
import com.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Api(value = "测试接口",tags = "测试接口")
@RestController
@RequestMapping("/test")
public class TestController {


    @SysLog("测试接口")
    @ApiOperation(value = "测试接口",notes = "测试接口,hello")
    @GetMapping("/hello")
    public Result test(String name){

        return Result.success("hello ["+name+"]") ;
    }


    @SysLog("测试接口")
    @ApiOperation(value = "测试接口2",notes = "测试接口,hello")
    @GetMapping("/test2")
    @ExeForm
    public Result test2(PageForm form){

        return Result.success("hello ["+form.getPage()+"]") ;
    }

}
