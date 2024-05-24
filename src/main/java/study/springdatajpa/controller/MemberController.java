package study.springdatajpa.controller;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import study.springdatajpa.entity.Member;
import study.springdatajpa.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Transactional(readOnly = true)
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login(@ModelAttribute Member member) {
        log.info("login");
        return "member/login";
    }

    @PostMapping("/login")
    public String loginProc(@ModelAttribute Member member) {
        return "member/memberList";
    }

    @GetMapping("/{id}")
    public String memberDetail(@PathVariable("id") Member member, Model model) {
        // 스프링은 클래스 컨버터를 통해 아래 과정을 지원한다.
//        model.addAttribute("member", memberService.findById(member.getId()));
        return member.getMemberName();
    }

    @PostConstruct
    @Transactional
    public void init() {
        Member memberA = new Member("member a", 00);
        memberService.signUpMember(memberA);

    }


}
