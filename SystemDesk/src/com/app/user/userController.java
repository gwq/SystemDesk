package com.app.user;


import java.util.HashMap;
import java.util.Map;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller  
@RequestMapping("/user")  
public class userController {
	
	@RequestMapping(value = "/configSet.do", method = RequestMethod.POST)  
    public ModelAndView userInfconfigSet(@RequestParam("name") String name){  
		
        ModelAndView mav = new ModelAndView(); 
        mav.addObject("methodtype", "POST Method");
        mav.setViewName("/user/userconfig");  
        return mav;  
    }  
     
	@RequestMapping(value = "/config.do", method = RequestMethod.GET)  
    public ModelAndView userInfo(){  
        ModelAndView mav = new ModelAndView();  
        mav.setViewName("/user/userconfig");
        mav.addObject("methodtype", "GET Method");
        return mav;  
    }  
	
	@RequestMapping(value = "/usertest.do", method = RequestMethod.GET)  
    public ModelAndView userInfotest(){  
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/user/usertest");
        return mav;  
    }  
	
	//重定向用法
	@RequestMapping(value = "/useredirect.do", method = RequestMethod.GET)  
    public String useredirect(){  
       return "redirect:/user/config.do";
    }  
	
	
	
	/*
	 * SpringMVC ajax操作
	 * jar包冲突：jackson-mapper-asl-1.9.*.jar与spring自带的jar包
	 * com.springsource.org.codehaus.jackson.mapper-1.4.2.jar冲突，
	 * 从而在项目初始中抛出异常java.lang.NoSuchMethodErro。
	 * 解决方法是将com.springsource.org.codehaus.jackson.mapper-1.4.2.jar移除。

      *  spring MVC在使用jackson时一定要注意：
      *  jackson-core-asl-1.9.*.jar和jackson-mapper-asl-1.9.*.jar的版本号一定要相同，
      *  如：同时使用1.9.5。
	 */
	@RequestMapping(value = "/ajaxpost.do", method = RequestMethod.POST) 
	@ResponseBody 
    public Map<String, Object> ajaxpost(@RequestParam("name") String name){ 
		System.out.println(name);
		Map<String, Object> modelMap = new HashMap<String, Object>();  
	    modelMap.put("total", "1");  
	    //modelMap.put("data", list);  
	    modelMap.put("success", name + " ajax post success!!");
       return modelMap;
    }  
	
	
	
	
}
