package site.klol.app.member.service;


import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import site.klol.app.member.dto.SignUpReqDTO;
import site.klol.app.member.repository.MemberRepository;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceUnitTest {
    @InjectMocks
    MemberServiceImpl memberService;
    @Mock
    MemberRepository memberRepository;

    @Test
    @DisplayName("중복 아이디, 닉네임은 EntityExistsException 발생")
    void createUser() {
        // given
        SignUpReqDTO signUpReqDTO = new SignUpReqDTO();
        signUpReqDTO.setId("dummyId");
        signUpReqDTO.setPassword("dummyPassword");
        signUpReqDTO.setNickname("dummyNickname");

        when(memberRepository.existsByNickname(anyString())).thenReturn(true);
        // when & then
        Assertions.assertThrows(EntityExistsException.class, () -> memberService.createUser(signUpReqDTO));
        verify(memberRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    @DisplayName("중복 아이디, 닉네임은 EntityExistsException 발생")
    void createUser2() {
        // given
        SignUpReqDTO signUpReqDTO = new SignUpReqDTO();
        signUpReqDTO.setId("dummyId");
        signUpReqDTO.setPassword("dummyPassword");
        signUpReqDTO.setNickname("dummyNickname");

        when(memberRepository.existsByNickname(anyString())).thenReturn(false);
        when(memberRepository.existsById(anyString())).thenReturn(false);
        // when & then
        Assertions.assertDoesNotThrow(() -> memberService.createUser(signUpReqDTO));
        verify(memberRepository, Mockito.times(1)).save(Mockito.any());
    }

}