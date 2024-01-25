# BCSD_BackEnd_Project
- [1. 기능 우선순위](#1-기능-우선순위)
    + [1-1 User권한](#1-1-user권한)
    + [1-2 Admin 권한](#1-2-admin-권한)
- [2. 전체 권한 api](#2-전체-권한-api)
  * [/sign/in, POST](#signin-post)
  * [/sign/out, POST](#signout-post)
  * [/sign/up, POST](#signup-post)
- [3. User, Admin 권한 api](#3-user-admin-권한-api)
  * [/user/{id}, GET](#userid-get)
  * [/user/{id}, PUT](#userid-put)
  * [/user/{id}, DELETE](#userid-delete)
  * [/user/blog/{id}, GET](#userblogid-get)
  * [/user/blog/{id}, POST](#userblogid-post)
  * [/user/blog/{id}, DELETE](#userblogid-delete)
  * [/user/blog/{id}, PUT](#userblogid-put)
  * [/user/github/{id}, GET](#usergithubid-get)
  * [/user/github/{id}, POST](#usergithubid-post)
  * [/user/github/{id}, DELETE](#usergithubid-delete)
  * [/user/github/{id}, PUT](#usergithubid-put)
  * [/user/instagram/{id}, GET](#userinstagramid-get)
  * [/user/instagram/{id}, POST](#userinstagramid-post)
  * [/user/instagram/{id}, DELETE](#userinstagramid-delete)
  * [/user/instagram/{id}, PUT](#userinstagramid-put)
  * [/portfolio/about/{id}, GET](#portfolioaboutid-get)
  * [/portfolio/about/{id}, PUT](#portfolioaboutid-put)
  * [/portfolio/skillslist/{category}, GET](#portfolioskillslistcategory-get)
  * [/portfolio/skills/{id}, GET](#portfolioskillsid-get)
  * [/portfolio/skills/{id}, POST](#portfolioskillsid-post)
  * [/portfolio/skills/{id}, DELETE](#portfolioskillsid-delete)
  * [/portfolio/archiving/{id}, GET](#portfolioarchivingid-get)
  * [/portfolio/archiving/{id}, POST](#portfolioarchivingid-post)
  * [/portfolio/archiving/{id}, DELETE](#portfolioarchivingid-delete)
  * [/portfolio/archiving/{id}, PUT](#portfolioarchivingid-put)
  * [/portfolio/project/{id}, GET](#portfolioprojectid-get)
  * [/portfolio/project/{id}, POST](#portfolioprojectid-post)
  * [/portfolio/project/{id}, DELETE](#portfolioprojectid-delete)
  * [/portfolio/project/{id}, PUT](#portfolioprojectid-put)
  * [/portfolio/career/{id}, GET](#portfoliocareerid-get)
  * [/portfolio/career/{id}, POST](#portfoliocareerid-post)
  * [/portfolio/career/{id}, DELETE](#portfoliocareerid-delete)
  * [/portfolio/career/{id}, PUT](#portfoliocareerid-put)
  * [/portfolio/image/{name}, POST](#portfolioimagename-post)
- [4. Admin 권한 api](#4-admin-권한-api)
  * [/admin/user/{id}, GET](#adminuserid-get)
  * [/admin/user/{id}, DELETE](#adminuserid-delete)
  * [/admin/image, POST](#adminimage-post)
  * [/admin/image/{name}, DELETE](#adminimagename-delete)

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

#### 1-2 Admin 권한

```text
1. 개발 분야별 사용 가능한 기술스택 이미지 추가 및 삭제
2. 일반 사용자 삭제
```

## 2. 전체 권한 api
```text
/sign/in - 로그인 post
/sign/up - 회원가입 post
/sign/out - 로그아웃 post
```

###### /sign/in, POST
```text
localhost:8080/sign/in, POST

# request
raw-json
{
    "userId": "",
    "userPassword": ""
}

# response
{
    "userId": "",
    "userPassword": "",
    "roles": [
        {
            "authName": "",
            "authId": 
        }
    ],
    "token": ""
}
```

###### /sign/out, POST
```text
localhost:8080/sign/out, POST

# request
raw-json
{
    "userId": "",
    "token": ""
}

# response
true or false
```

###### /sign/up, POST
```text
localhost:8080/sign/up, POST

# request
raw-json
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

# response
true or false
```

## 3. User, Admin 권한 api
###### /user/{id}, GET
```text
localhost:8080/user/{id}, GET

# request

# response
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

###### /user/{id}, PUT
```text
localhost:8080/user/{id}, PUT

# request
raw-json
{
    "userName": "",
    "userBirth": "",
    "userTel": "",
    "userEmail": "",
    "userPassword": "",
    "userNickname": "",
    "userEducation": ""
}

# response
true or false
```

###### /user/{id}, DELETE
```text
localhost:8080/user/{id}, DELETE

# request

# response
true or false
```

###### /user/blog/{id}, GET
```text
localhost:8080/user/blog/{id}, GET

# request

# response
{
    "snsName": ""
}
```

###### /user/blog/{id}, POST
```text
localhost:8080/user/blog/{id}, POST

# request
raw-json
{
    "snsName": ""
}

# response
true or false
```

###### /user/blog/{id}, DELETE
```text
localhost:8080/user/blog/{id}, DELETE

# request

# response
true or false
```

###### /user/blog/{id}, PUT
```text
localhost:8080/user/blog/{id}, PUT

# request
raw-json
{
    "snsName": ""
}

# response
true or false
```

###### /user/github/{id}, GET
```text
localhost:8080/user/github/{id}, GET

# request

# response
{
    "snsName": ""
}
```

###### /user/github/{id}, POST
```text
localhost:8080/user/github/{id}, POST

# request
raw-json
{
    "snsName": ""
}

# response
true or false
```

###### /user/github/{id}, DELETE
```text
localhost:8080/user/github/{id}, DELETE

# request

# response
true or false
```

###### /user/github/{id}, PUT
```text
localhost:8080/user/github/{id}, PUT

# request
raw-json
{
    "snsName": ""
}

# response
true or false
```

###### /user/instagram/{id}, GET
```text
localhost:8080/user/instagram/{id}, GET

# request

# response
{
    "snsName": ""
}
```

###### /user/instagram/{id}, POST
```text
localhost:8080/user/instagram/{id}, POST

# request
raw-json
{
    "snsName": ""
}

# response
true or false
```

###### /user/instagram/{id}, DELETE
```text
localhost:8080/user/instagram/{id}, DELETE

# request

# response
true or false
```

###### /user/instagram/{id}, PUT
```text
localhost:8080/user/instagram/{id}, PUT

# request
raw-json
{
    "snsName": ""
}

# response
true or false
```

###### /portfolio/about/{id}, GET
```text
localhost:8080/portfolio/about/{id}, GET

# request

# response
{
    "userNameVisible": true or false,
    "userTelvisible": true or false,
    "userEmailVisible": true or false,
    "userEducationVisible": true or false
}
```

###### /portfolio/about/{id}, PUT
```text
localhost:8080/portfolio/about/{id}, PUT

# request
raw-json
{
    "userNameVisible": true or false,
    "userTelvisible": true or false,
    "userEmailVisible": true or false,
    "userEducationVisible": true or false
}

# response
true or false
```

###### /portfolio/skillslist/{category}, GET
```text
localhost:8080/portfolio/skillslist/{category}, GET

# request

# response
[
    {
        "skillId": num,
        "skillName": ""
    }
]
```

###### /portfolio/skills/{id}, GET
```text
localhost:8080/portfolio/skills/{id}, GET

# request

# response
{
    "skillsFrontendIdList": [],
    "skillsBackendIdList": [],
    "skillsMobileappIdList": [],
    "skillsDeploymentIdList": [],
    "skillsVersioncontrolIdList": [],
    "skillsCertificationIdList": [],
    "skillsCommunicationIdList": []
}
```

###### /portfolio/skills/{id}, POST
```text
localhost:8080/portfolio/skills/{id}, POST

# request
raw-json
{
    "category": "",
    "skillsId": num
}

# response
true or false
```

###### /portfolio/skills/{id}, DELETE
```text
localhost:8080/portfolio/skills/{id}, DELETE

# request
raw-json
{
    "category": "",
    "skillsId": num
}

# response
true or false
```

###### /portfolio/archiving/{id}, GET
```text
localhost:8080/portfolio/archiving/{id}, GET

# request

# response
[
    {
        "portfolioArchivingId": num,
        "archivingName": "",
        "archivingExplanation": ""
    }
    .
    .
    .
]
```

###### /portfolio/archiving/{id}, POST
```text
localhost:8080/portfolio/archiving/{id}, POST

# request
raw-json
{
    "portfolioArchivingId": num,
    "archivingName": "",
    "archivingExplanation": ""
}
# response
true or false
```

###### /portfolio/archiving/{id}, DELETE
```text
localhost:8080/portfolio/archiving/{id}, DELETE

# request
raw-json
{
    "portfolioArchivingId": num,
    "archivingName": "",
    "archivingExplanation": ""
}
# response
true or false
```

###### /portfolio/archiving/{id}, PUT
```text
localhost:8080/portfolio/archiving/{id}, PUT

# request
raw-json
{
    "portfolioArchivingId": num,
    "archivingName": "",
    "archivingExplanation": ""
}
# response
true or false
```

###### /portfolio/project/{id}, GET
```text
localhost:8080/portfolio/project/{id}, GET

# request

# response
[
    {
        "portfolioProjectId": num,
        "projectName": "",
        "projectExplanation": ""
    }
    .
    .
    .
]
```

###### /portfolio/project/{id}, POST
```text
localhost:8080/portfolio/project/{id}, POST

# request
raw-json
{
    "portfolioProjectId": num,
    "projectName": "",
    "projectExplanation": ""
}
# response
true or false
```

###### /portfolio/project/{id}, DELETE
```text
localhost:8080/portfolio/project/{id}, DELETE

# request
raw-json
{
    "portfolioProjectId": num,
    "projectName": "",
    "projectExplanation": ""
}
# response
true or false
```

###### /portfolio/project/{id}, PUT
```text
localhost:8080/portfolio/project/{id}, PUT

# request
raw-json
{
    "portfolioProjectId": num,
    "projectName": "",
    "projectExplanation": ""
}
# response
true or false
```

###### /portfolio/career/{id}, GET
```text
localhost:8080/portfolio/career/{id}, GET

# request

# response
[
    {
        "portfolioCareerId": num,
        "careerExplanation": ""
    }
    .
    .
    .
]
```

###### /portfolio/career/{id}, POST
```text
localhost:8080/portfolio/career/{id}, POST

# request
raw-json
{
    "portfolioCareerId": num,
    "careerExplanation": ""
}
# response
true or false
```

###### /portfolio/career/{id}, DELETE
```text
localhost:8080/portfolio/career/{id}, DELETE

# request
raw-json
{
    "portfolioCareerId": num,
    "careerExplanation": ""
}
# response
true or false
```

###### /portfolio/career/{id}, PUT
```text
localhost:8080/portfolio/career/{id}, PUT

# request
raw-json
{
    "portfolioCareerId": num,
    "careerExplanation": ""
}
# response
true or false
```

###### /portfolio/image/{name}, POST
```text
localhost:8080/portfolio/image/{name}, POST

# request

# response
{
    "base64Images": ""
}
```

## 4. Admin 권한 api
###### /admin/user/{id}, GET
```text
localhost:8080/admin/user/{id}, GET

# request

# response
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

###### /admin/user/{id}, DELETE
```text
localhost:8080/admin/user/{id}, DELETE

# request

# response
true or false
```

###### /admin/image, POST
```text
localhost:8080/admin/image, POST

# request
form-data
{
    "image": 파일,
    "name": "",
    "category": ""
}
# response
true or false
```

###### /admin/image/{name}, DELETE
```text
localhost:8080/admin/image/{name}, DELETE

# request

# response
true or false
```