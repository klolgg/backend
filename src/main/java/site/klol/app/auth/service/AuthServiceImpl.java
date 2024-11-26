package site.klol.app.auth.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import site.klol.app.auth.jwt.JwtUtil;
import site.klol.app.auth.dto.CustomUserInfoDto;
import site.klol.app.member.dto.LoginReqDto;
import site.klol.app.member.entity.Member;
import site.klol.app.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService{

    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public String login(LoginReqDto dto) {
        String id = dto.getId();
        String password = dto.getPassword();
        Member member = memberRepository.findById(id).orElseThrow();
        if(member == null) {
            throw new UsernameNotFoundException("이메일이 존재하지 않습니다.");
        }

        // 암호화된 password를 디코딩한 값과 입력한 패스워드 값이 다르면 null 반환
        if(!encoder.matches(password, member.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        CustomUserInfoDto info = modelMapper.map(member, CustomUserInfoDto.class);

        String accessToken = jwtUtil.createAccessToken(info);
        return accessToken;
    }
}
