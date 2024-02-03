package BCSD.MusicStream.service;

import BCSD.MusicStream.dto.member.*;
import BCSD.MusicStream.dto.token.JwtTokenDTO;

public interface MemberService {
    public JwtTokenDTO signIn(SignInMemberDTO signInMemberDTO);
    public ResponseMemberDTO signUp(SignUpMemberDTO signUpMemberDTO);
    public Boolean existsByMemberEmail(String memberEmail);
    public void deleteMemberByMemberId(Integer memberId);
    public ResponseMemberDTO modifyMember(ModifyMemberInfoDTO modifyMemberDTO, Integer memberId);
    public ResponseMemberDTO getMemberInfo(Integer memberId);
    public Boolean modifyPassword(ModifyMemberPasswordDTO modifyMemberPasswordDTO, Integer memberId);

}
