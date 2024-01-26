# BCSD_Interview
회고) 성적 관리 시스템

1. 우선순위
- 1. 전체적인 CRUD 구현 -> 기본적으로 유저, 성적, 학기에 대한 정보에 대한 CRUD 구현
- 2. 성적 관련 매커니즘 추가하여 SubjectGradeInformation 구현
- 3. 회원가입 추가
- 4. 학사 정보 관련하여 성적에 관한 사항 처리하기
- 5. 전공, HRD, 교양 등 분리하여 학점 정리 할 수 있는 매커니즘 구현

2. 현재 진행상황
- 서버 구동 시 발생하던 오류 해결 완료
- 학생과 과목에 대한 CRUD 추가 완료

3. 추가할 사항
- 학기에 대한 DB를 활용하기 위해서 학기의 성적과 학점을 알 수 있도록 하는 게 좋을 것 같음(DB 수정 필요)
- 현재로서는 계산 로직 추가보다 수기로 먼저 작성하여 정보를 올리는 것 먼저 진행해 볼 예정
  -> 추후 StudentGrade에서 계산로직을 추가해 가져오는 것이 좋아보임

4. 1.27~1.28 진행예정 내용
- 기본적인 CRUD 틀 제작 후 학점 관련 내용 추가 에정
