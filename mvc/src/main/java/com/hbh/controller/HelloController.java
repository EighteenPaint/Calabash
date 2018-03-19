package com.hbh.controller;

import com.hbh.TestPO;
import com.hbh.annotations.Controller;
import com.hbh.annotations.RequestMapping;
import com.hbh.bean.ModelAndView;

/**
 * Created by chenbin@megaeyes.com on 2018/2/27 0027.
 */
@Controller
public class HelloController {
    @RequestMapping("/app/lo")
    public ModelAndView mvc(String[] name, Integer value, TestPO po,ModelAndView view){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mvc");
        modelAndView.addAttribute("name",po);
        return modelAndView;
    }
}
