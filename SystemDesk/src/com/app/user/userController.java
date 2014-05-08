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
	
	//�ض����÷�
	@RequestMapping(value = "/useredirect.do", method = RequestMethod.GET)  
    public String useredirect(){  
       return "redirect:/user/config.do";
    }  
	
	
	
	/*
	 * SpringMVC ajax����
	 * jar����ͻ��jackson-mapper-asl-1.9.*.jar��spring�Դ���jar��
	 * com.springsource.org.codehaus.jackson.mapper-1.4.2.jar��ͻ��
	 * �Ӷ�����Ŀ��ʼ���׳��쳣java.lang.NoSuchMethodErro��
	 * ��������ǽ�com.springsource.org.codehaus.jackson.mapper-1.4.2.jar�Ƴ���

      *  spring MVC��ʹ��jacksonʱһ��Ҫע�⣺
      *  jackson-core-asl-1.9.*.jar��jackson-mapper-asl-1.9.*.jar�İ汾��һ��Ҫ��ͬ��
      *  �磺ͬʱʹ��1.9.5��
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
