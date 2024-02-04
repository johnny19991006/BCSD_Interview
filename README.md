# BCSD_Interview
회고) 성적 관리 시스템

1. 우선순위
- 1. 전체적인 CRUD 구현 -> 기본적으로 유저, 성적, 학기에 대한 정보에 대한 CRUD 구현
- 2. 성적 관련 매커니즘 추가하여 SubjectGradeInformation 구현
- 3. 회원가입 추가
- 4. 학사 정보 관련하여 성적에 관한 사항 처리하기
- 5. 전공, HRD, 교양 등 분리하여 학점 정리 할 수 있는 매커니즘 구현

2. 현재 진행 상황
- 학생, 성적, 학기, 과목 성적 CRUD 추가 완료
- 과목 성적에서 Score/{SubjectName} 500번 오류 발생

3. 추가 할 사항
- StudentGradeInformation에 필요한 정보 생각해보기
- Credit과 Score 관련 계산 로직 Service 내에 추가할 것

4. 추후 진행 내용
- SubjectScore에서 발생한 오류 해결(1/28 해결 완료)
- StudentGradeInformation CRUD 구현하기

5. 생각 중인 것
- 현재 Service를 interface를 이용하여 구현했었는데, 프로젝트 규모가 크지 않고, 크게
  복잡한 상황이 아닌 것 같다 판단하여 Semester부턴 Class만 구현
- Domain 내에서 Setter 사용을 지양하고 있는데, 현재 Service update 부분에서 Setter
  를 사용하고 있어서 이를 어떻게 해결 할 지 생각해보기
- REST API 규칙을 지켰는지 확인하기
- 현재 사용하고 있는 SubjectGrade의 SubjectName을 PK로 사용하지 않고, 
  과목 코드를 사용하여 구분 할 수 있도록 수정해보기

1월 28일(일) 
- 과목 성적 Score/{과목이름}/{학생아이디}로 RUD 가능하도록 수정 완료

1월 31일(수)
- "학기"와 "학년"을 이용하여 전체적인 학점 계산이 가능하도록 했음
- "전공"과 "교양"을 분리하여 계산하는 로직을 만들어야 할 것을 생각, 미리 하면 전체적 계산이 편함
- 전체적인 성적이 계산 가능하도록 구현 완료

2월 3일(토)
- 외래키를 사용하는 곳에서 POST, PUT에 대한 오류가 발생
- 1차적으로는 로그인 기능을 먼저 구현
- 필요한 경우 성적 관련 로직 외에 StudentGradeInformation에 대한 내용 간단한 CRUD로 구현
- 현재 DB에서 직접적으로 넣는 경우에는 오류 발생하지 않음

2월 4일(일)
- 마무리 단계 진행
- 현재 오류가 있는 부분은 그대로 진행, DB 부분 수정 필요한 부분 처리하기
- StudentGradeInformation CRUD 생성 완료
-> 계산 로직 추가 실패로, 일차적으로는 대입으로 진행..
- 현재 있는 아이디로 Access Token 발급하여 기능 사용가능