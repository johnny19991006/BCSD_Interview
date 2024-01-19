# BCSD_BackEnd_Project

- [BCSD_BackEnd_Project](#bcsd-backend-project)
    * [1. 기능 우선순위](#1--------)
        - [1-1 User권한](#1-1-user--)
        - [1-1 User권한 api 사용법](#1-1-user---api----)
            + [1.회원가입](#1----)
            + [2. 로그인](#2----)
            + [3. 회원정보 수정](#3--------)
            + [4. 회원 탈퇴](#4------)
            + [5. 회원 정보 열람](#5---------)
            + [6-1 개인정보 공개 설정](#6-1-----------)
        - [1-2 Admin 권한](#1-2-admin---)

## 1. 기능 우선순위

#### 1-1 User권한

```text
1. 회원가입
2. 로그인
3. 회원정보 수정
4. 회원 탈퇴
5. 회원 정보 열람
6. 포트폴리오 작성
    6-1 개인정보 공개 설정
    6-2 github, blog, instagram 작성
    6-3 개발 분야별 사용 가능한 기술스택 작성
    6-4 아카이빙 작성
    6-5 커리어 작성
    6-6 프로젝트 작성
7. 다른 유저 검색
8. 즐겨찾기 추가
```
#### 1-1 User권한 api 사용법
###### 1.회원가입
```text
localhost:8080/sign-up, POST

{
    "userName": "",
    "userBirth": "",
    "userTel": "",
    "userEmail": "",
    "userId": "",
    "userPassword": "",
    "userNickname": "",
    "userEducation": ""
}
```

###### 2. 로그인
```text
localhost:8080/sign-in, POST

{
    "userId": "",
    "userPassword": ""
}
```
###### 3. 회원정보 수정
```text
localhost:8080/user, PUT

{
    "userName": "",
    "userBirth": "",
    "userTel": "",
    "userEmail": "",
    "userId": "",
    "userPassword": "",
    "userNickname": "",
    "userEducation": "",
    "token": ""
}
```

###### 4. 회원 탈퇴
```text
localhost:8080/user, DELETE

{
    "userId": "",
    "token": ""
}
```

###### 5. 회원 정보 열람
```text
localhost:8080/user, POST

{
    "userId": "",
    "token": ""
}
```

###### 6-1 개인정보 공개 설정
```text
localhost:8080/about, POST

{
    "userId": ""
}
```
```text
localhost:8080/about, PUT

{
    "userId": "",
    "userNameVisible": true or false,
    "userTelVisible": true or false,
    "userEmailVisible": true or false,
    "userEducationVisible": true or false
}
```

###### 6-2 github, blog, instagram 작성
```text
localhost:8080/sns, POST
{
    "userId": ""
}
```

```text
localhost:8080/sns, PUT

{
    "userId": "",
    "userGithub": "",
    "userBlog": "",
    "userInstagram": ""
}
```

###### 6-e image 불러오기

```text
localhost:8080/download-img, POST

{
    "imageName": ["이름", "쓰기", "여러개", ...]
}

```

###### 6-3 개발 분야별 사용 가능한 기술스택 작성

```text
localhost:8080/portfolio, POST

{
    "userId": "",
    "category": "{frontend, backend, deployment, communication, certification, versioncontrol, mobileapp} 중 하나 작성"
    "skillsId": n
}

```

```text
localhost:8080/portfolio, DELETE

{
    "userId": "",
    "category": "{frontend, backend, deployment, communication, certification, versioncontrol, mobileapp} 중 하나 작성"
    "skillsId": n
}

```

#### 1-2 Admin 권한

```text
1. 개발 분야별 사용 가능한 기술스택 이미지 추가 및 삭제
2. 일반 사용자 삭제
```
#### 1-2 Admin권한 api 사용법

###### 1.이미지 업로드
```text
localhost:8080/upload-img, POST, form-data

var formData = new FormData();
formData.append('image', file, 'name', '파일이름(확장자 제외)', 'category', '{frontend, backend, deployment, versioncontrol, communication, certification} 중 택 1');
```