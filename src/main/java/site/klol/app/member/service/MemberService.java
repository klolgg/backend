package site.klol.app.member.service;

import site.klol.app.member.dto.LoginReqDto;
import site.klol.app.member.dto.LogoutReqDto;
import site.klol.app.member.dto.SignUpReqDTO;

public interface MemberService {
    void createUser(SignUpReqDTO signUpReqDTO);


    void login(LoginReqDto loginReqDto);

    void logout(LogoutReqDto logoutReqDto);
}
