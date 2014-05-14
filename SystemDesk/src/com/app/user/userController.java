package com.app.user;


import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.glassfish.gmbal.ParameterNames;

//@Scope("prototype") 原型模式：每次请求生成一个controller对象，默认为单例模式，只有一个对象，单例模式下不能有类变量
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
	
	
	//**文件下载
	
	@RequestMapping(value = "/download.do",method = RequestMethod.GET)
    public ResponseEntity<byte[]> downFile(HttpServletRequest req)  {
        String downFileName = "";
//        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String path = req.getSession().getServletContext().getRealPath("/download");
        System.out.println(path);
        try {
            downFileName = URLEncoder.encode("1.txt", "UTF-8");//转码解决IE下文件名乱码问题
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Http响应头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", downFileName);

        try {
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(path + "/" + downFileName)),
                                              headers,
                                              HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            //日志
            //TODO
        }
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "error.txt");
        return new ResponseEntity<byte[]>("文件不存在.".getBytes(), headers, HttpStatus.OK);
    }
	
}
