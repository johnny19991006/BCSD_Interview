# 🎧 음악스트리밍 API
- 음악 스트리밍 사이트의 멤버에게 음악을 제공하는 API이다.
## ⚙️ 기능
### 🙍‍♂️ 일반회원
- 로그인, 회원가입을 할 수 있다. 
- 자신만의 플레이리스트를 만들어서 음악을 플레이리스트에 등록할 수 있다.
- 음악을 조회할 수 있다.
    - 가수 이름 또는 노래 제목을 통해 음악을 검색할 수 있다.
    - 현재 회원의 IP주소를 기반으로 해당 지역의 날시에 맞는 음악을 추천해준다.
    - 사용자의 음악 취향을 분석하여 음악을 추천해준다.
    - 사용자의 플레이리스트를 조회하여 음악을 조회할 수 있다.
    - 사용자는 음악을 좋아요, 싫어요 할 수 있다.
        * 좋아요 한 음악은 음악 추천의 높은 우선순위를 가질 수 있다. (좋아요 한 음악과 같은 카테고리의 음악은 추천 우선순위에서 높은 우선순위를 가진다.)
        * 싫어요 한 음악은 사용자에게 추천하지 않는다. (싫어요 한 음악과 같은 카테고리의 음악은 추천 우선순위에서 낮은 우선순위를 가진다.)

### 🤵 ‍♂️관리회원
- 관리회원은 일반회원이 이용할 수 있는 모든 기능을 이용할 수 있다.
- 음악을 추가 수정 할 수 있다.
- 노래 주인 또는 소속사 관계자는 노래를 업로드 할 수 있다.
- 노래를 업로드 한 사람은 해당 노래를 수정, 삭제 할 수 있다.

## 🧾 API명세서
### [Swagger](http://localhost:8080/swagger-ui/index.html#/)

## 🗂️ DB관계도
<img width="1050" alt="스크린샷 2024-02-03 오전 11 43 54" src="https://github.com/20HyeonsuLee/BCSD_Interview/assets/127578418/a14afc92-c271-4baf-9472-75a78896a9e2">

## 🔧 기술 스택
<img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white"><img src="https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white"><img src="https://img.shields.io/badge/lombok-%23BC4521.svg?style=for-the-badge&logo=lombok&logoColor=white"><img src="https://img.shields.io/badge/JWT-%23000000.svg?style=for-the-badge&logo=JSON%20web%20tokens"><img src="https://img.shields.io/badge/gradle-%2302303A.svg?style=for-the-badge&logo=gradle&logoColor=white"><img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white"><img src="https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white"><img src="https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white"><img src="https://img.shields.io/badge/Spring_JDBC-6DB33F?style=for-the-badge&logo=spring&logoColor=white"><img src="https://img.shields.io/badge/GeoIP2-953DAD?style=for-the-badge&logo=maxmind&logoColor=white"><img src="https://img.shields.io/badge/Springdoc_OpenAPI-6DB33F?style=for-the-badge&logo=swagger&logoColor=white"><img width="100" height="30" src="https://i0.wp.com/blog.niftyimages.com/wp-content/uploads/2019/08/openweather-logo.png?resize=341%2C228&ssl=1">