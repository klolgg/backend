package site.klol.app.member.service;


import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import site.klol.app.member.dto.SignUpReqDTO;
import site.klol.app.member.entity.Member;
import site.klol.app.member.repository.MemberRepository;

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

    private void validateIdAndNickNameIsUnique(SignUpReqDTO signUpReqDTO) {
        if( memberRepository.findByIdOrNickname(signUpReqDTO.getId(), signUpReqDTO.getNickname()).isPresent()){
            throw new EntityExistsException("아이디 혹은 닉네임은 고유해야 합니다.");
        }
    }
}
