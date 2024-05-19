package study.springdatajpa.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import study.springdatajpa.entity.Member;
import study.springdatajpa.entity.Team;
import study.springdatajpa.service.MemberService;
import study.springdatajpa.service.TeamService;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TeamController {

    private final TeamService teamService;
    private final MemberService memberService;


//    @GetMapping("/add")
//    public String createTeam() {
//        return "team/add";
//    }

    @PostMapping("/createTeam")
    public String createTeam(@RequestBody Team team, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "team/add";
        }

        Team team1 = teamService.createTeam(team);
        return "redirect:/team/list";
    }

    @PostMapping("/add")
    public String addMemberToTeam(@PathVariable Long teamId, @PathVariable Long memberId) {

        teamService.addMemberToTeam(teamId, memberId);
        return "redirect:/team/list";
    }

    @PostMapping("/{memberId}")
    public String findByIdWithTeams(@PathVariable Long memberId, Model model) {
//        Member member1 = memberService.findByIdWithTeams(memberId).get();

        Member member = new Member("MEMBER1", 20);
        Team team = new Team("team 1");

//        Member member1 =
                memberService.addMemberToTeamV2(team, member);

//        return member1.toString();
            return "dd";



    }


}
