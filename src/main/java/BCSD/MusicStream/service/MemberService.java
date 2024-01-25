package BCSD.MusicStream.service;

import BCSD.MusicStream.dto.token.JwtTokenDTO;
import BCSD.MusicStream.dto.member.ModifyMemberDTO;
import BCSD.MusicStream.dto.member.SignInMemberDTO;
import BCSD.MusicStream.dto.member.SignUpMemberDTO;

public interface MemberService {
    public JwtTokenDTO signIn(SignInMemberDTO signInMemberDTO);
    public void signUp(SignUpMemberDTO signUpMemberDTO);
    public Boolean existsByMemberEmail(String memberEmail);
    public void deleteMemberByUserId(Integer memberId);
    public void modifyMember(ModifyMemberDTO modifyMemberDTO, Integer memberId);

}
