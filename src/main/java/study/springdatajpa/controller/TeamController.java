package study.springdatajpa.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import study.springdatajpa.entity.Team;
import study.springdatajpa.service.TeamService;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

//    @GetMapping("/add")
//    public String createTeam() {
//        return "team/add";
//    }

    @PostMapping("/createTeam")
    public String createTeam(@RequestBody Team team, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "team/add";
        }

        Team team1 = teamService.createTeam(team);
        return "redirect:/team/list";
    }

    @PostMapping("/{teamId}/add/{memberId}")
    public String addMemberToTeam(@PathVariable Long teamId, @PathVariable Long memberId) {
        teamService.addMemberToTeam(teamId, memberId);
        return "redirect:/team/list";
    }

}
