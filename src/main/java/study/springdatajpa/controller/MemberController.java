package study.springdatajpa.controller;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import study.springdatajpa.dto.ApiResultResponse;
import study.springdatajpa.entity.Member;
import study.springdatajpa.entity.Team;
import study.springdatajpa.repository.MemberRepository;
import study.springdatajpa.repository.TeamRepository;
import study.springdatajpa.repository.query.MemberQueryDto;
import study.springdatajpa.service.MemberService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Transactional(readOnly = true)
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

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

    @GetMapping("/memberList")
    @Transactional
    public Page<Member> memberList(Pageable pageable){
        Page<Member> all = memberRepository.findAll(pageable);
        return all;
    }

    @GetMapping("/memberDtoList")
    @Transactional
    public Page<MemberQueryDto> memberDtoList(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Page<Member> all = memberRepository.findAll(pageable);
        log.info("list size : {}", all.getTotalElements());
        Page<MemberQueryDto> map = all.map(member -> new MemberQueryDto(member.getId(), member.getMemberName(), member.getAge()));
        return map;
    }

    @GetMapping("/memberListApiResponse")
    @Transactional
    public ApiResultResponse memberListApiResponse(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Page<Member> all = memberRepository.findAll(pageable);
        log.info("list size : {}", all.getTotalElements());
        Page<MemberQueryDto> map = all.map(member -> new MemberQueryDto(member.getId(), member.getMemberName(), member.getAge()));
        return new ApiResultResponse((long) map.getSize(), map);
    }

    @PostConstruct
    @Transactional
    public void init() {
//        Member memberA = new Member("member a", 00);
//        memberService.signUpMember(memberA);

        List<Member> collect = IntStream.rangeClosed(1, 100).mapToObj(i -> {
            Member member = new Member("MEMBER" + i, i);
            memberRepository.save(member);
            Team team = new Team("team" + i);
            teamRepository.save(team);
            member.addTeam(team);
            return member;
        }).collect(Collectors.toList());

    }


}
