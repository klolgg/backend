package site.klol.app.user.service;

import site.klol.app.user.dto.LoginReqDto;
import site.klol.app.user.dto.SignUpReqDTO;

public interface MemberService {
    void createUser(SignUpReqDTO signUpReqDTO);


    void login(LoginReqDto loginReqDto);
}
