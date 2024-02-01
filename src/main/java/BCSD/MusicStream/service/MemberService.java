package BCSD.MusicStream.service;

import BCSD.MusicStream.dto.member.ResponseMemberDTO;
import BCSD.MusicStream.dto.token.JwtTokenDTO;
import BCSD.MusicStream.dto.member.ModifyMemberDTO;
import BCSD.MusicStream.dto.member.SignInMemberDTO;
import BCSD.MusicStream.dto.member.SignUpMemberDTO;

public interface MemberService {
    public JwtTokenDTO signIn(SignInMemberDTO signInMemberDTO);
    public ResponseMemberDTO signUp(SignUpMemberDTO signUpMemberDTO);
    public Boolean existsByMemberEmail(String memberEmail);
    public void deleteMemberByMemberId(Integer memberId);
    public ResponseMemberDTO modifyMember(ModifyMemberDTO modifyMemberDTO, Integer memberId);

}
