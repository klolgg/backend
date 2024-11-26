package site.klol.app.member.service;


import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import site.klol.app.common.utils.SHA256;
import site.klol.app.member.dto.LoginReqDto;
import site.klol.app.member.dto.LogoutReqDto;
import site.klol.app.member.dto.SignUpReqDTO;
import site.klol.app.member.entity.Member;
import site.klol.app.member.repository.MemberRepository;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void createUser(SignUpReqDTO signUpReqDTO) {
        validateIdAndNickNameIsUnique(signUpReqDTO);

        Member saveMember = Member.of(signUpReqDTO);

        memberRepository.save(saveMember);
    }

    @Override
    @Transactional
    public void login(LoginReqDto loginReqDto) {
        Member member = memberRepository.findById(loginReqDto.getId())
                .orElseThrow(() -> new NoSuchElementException("아이디 혹은 비밀번호가 일치하지 않습니다."));

        String plainInputPassword = loginReqDto.getPassword();
        String hashedMemberPassword = member.getPassword();

        if(!verifyPassword(plainInputPassword, hashedMemberPassword)){
            throw new NoSuchElementException("아이디 혹은 비밀번호가 일치하지 않습니다.");
        }
    }

    @Override
    public void logout(LogoutReqDto logoutReqDto) {

    }


    private void validateIdAndNickNameIsUnique(SignUpReqDTO signUpReqDTO) {
        if( memberRepository.findByIdOrNickname(signUpReqDTO.getId(), signUpReqDTO.getNickname()).isPresent()){
            throw new EntityExistsException("아이디 혹은 닉네임은 고유해야 합니다.");
        }
    }

    private boolean verifyPassword(String plainInputPassword, String hashedMemberPassword) {
        return SHA256.verify(plainInputPassword, hashedMemberPassword);
    }
}
