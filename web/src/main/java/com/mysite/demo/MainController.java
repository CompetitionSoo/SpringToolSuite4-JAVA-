package com.mysite.demo;
// 메인페이지 = 루트페이지 (랜딩페이지)
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "mainpage";
    }
    
    //메뉴선택 에대한 질문을 눌렀을때 넘어가는페이지    
    @GetMapping("/list")
    public String root() {
        return "redirect:/question/list";
    }
}
    
