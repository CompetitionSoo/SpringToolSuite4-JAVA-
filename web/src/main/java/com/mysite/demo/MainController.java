package com.mysite.demo;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mysite.demo.ask.QuestionForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final QuestionRepository questionRepository;

    // 홈 페이지
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "CupBap - 간편하고 맛있는 한 끼!");
        return "mainpage";  // 렌더링 템플릿: mainpage.html
    }

    // 메뉴 선택 페이지
    @GetMapping("/list")
    public String listMenu(Model model) {
        model.addAttribute("title", "메뉴 선택");
        return "list";  // 렌더링 템플릿: list.html
    }

    // 문의 목록 페이지
    @GetMapping("/question")
    public String contact(Model model) {
        List<Question> questionList = questionRepository.findAll();  // 모든 질문 조회
        model.addAttribute("questionList", questionList);
        return "question";  // 렌더링 템플릿: question.html
    }

    // 질문 상세 보기
    @GetMapping("/question/detail/{id}")
    public String viewQuestion(@PathVariable("id") Integer id, Model model) { // 이미 Integer로 변경
        Question question = questionRepository.findById(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    // 질문 등록 페이지
    @GetMapping("/question/create")
    public String createQuestionForm(Model model) {
        model.addAttribute("questionForm", new QuestionForm());
        return "question_form";  // 렌더링 템플릿: question_form.html
    }

    @PostMapping("/question/create")
    public String createQuestion(
            @Valid @ModelAttribute("questionForm") QuestionForm questionForm,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "question_form";  // 유효성 실패 시 다시 폼 렌더링
        }

        Question question = new Question();
        question.setSubject(questionForm.getSubject());
        question.setContent(questionForm.getContent());
        questionRepository.save(question);  // DB 저장

        return "redirect:/question";  // 등록 후 문의 목록으로 리다이렉트
    }
    // 로그인 페이지
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "로그인");
        return "login";  // 렌더링 템플릿: login.html
    }

    // 회원가입 페이지
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("title", "회원가입");
        return "signup";  // 렌더링 템플릿: signup.html
    }

    // 주문 내역 페이지
    @GetMapping("/order-history")
    public String orderHistory(Model model) {
        model.addAttribute("title", "주문 내역");
        return "order_history";  // 렌더링 템플릿: order_history.html
    }
}
