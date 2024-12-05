package site.klol.app.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.klol.app.member.entity.Member;
import site.klol.app.member.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<Member>> test() {
        return ResponseEntity.ok(memberService.findAlld());
    }
}
