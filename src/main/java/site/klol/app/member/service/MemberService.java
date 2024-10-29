package site.klol.app.member.service;

import site.klol.app.member.dto.SignUpReqDTO;

public interface MemberService {
    void createUser(SignUpReqDTO signUpReqDTO);
}
