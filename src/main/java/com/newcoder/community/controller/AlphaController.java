package com.newcoder.community.controller;

import com.newcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {


    @Autowired
    private AlphaService alphaService;
    @RequestMapping("/hello")
    @ResponseBody
    public String SayHello(){
        return "hello";
    }


    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String>enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name= enumeration.nextElement();
            String value=request.getHeader(name);
            System.out.println(name+":"+value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try (
                PrintWriter writer= response.getWriter();
                ){
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //GET请求  希望向服务器获取东西
    //  student?current=1&limit=20  第几页。最多显示多少数据
    @RequestMapping(path="/students",method= RequestMethod.GET)//限制路径与请求方式
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current",required = false,defaultValue = "1") int current,
            @RequestParam(name = "limit",required = false,defaultValue = "1") int limit
    ){
        System.out.println(current);
        System.out.println(limit);
        return "some  student";
    }

    @RequestMapping(path="/student/{id}",method=RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }

    //POST请求处理
    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav=new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age","30");
        mav.setViewName("/demo/view");
        return mav;
    }

    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","beijingdaxuye");
        model.addAttribute("age",80);
        return "/demo/view";
    }

    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getMap(){
        Map<String,Object>emp=new HashMap<>();
        emp.put("name","张三");
        emp.put("age","23");
        emp.put("salary",8000.00);

        return emp;
    }

    @RequestMapping(path = "/emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getMaps(){

        List<Map<String,Object>> list=new ArrayList<>();
        Map<String,Object>emp=new HashMap<>();
        emp.put("name","张三");
        emp.put("age","23");
        emp.put("salary",8000.00);
        list.add(emp);

        emp=new HashMap<>();
        emp.put("name","张4");
        emp.put("age","24");
        emp.put("salary",8600.00);
        list.add(emp);

        emp=new HashMap<>();
        emp.put("name","张5");
        emp.put("age","25");
        emp.put("salary",8005.00);
        list.add(emp);

        return list;
    }

}
