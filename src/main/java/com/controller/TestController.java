package com.controller;

import com.annotation.SysLog;
import com.result.Result;
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

}
