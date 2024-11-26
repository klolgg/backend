package site.klol.app.auth.service;

import site.klol.app.member.dto.LoginReqDto;

public interface AuthService {
    public String login(LoginReqDto dto);
}
