// your-script.js

$(document).ready(function () {
    function submitLoginForm() {
        // 폼 데이터 가져오기
        var formData = {
            userId: $('#username').val(),
            userPassword: $('#password').val()
        };

        // API 호출
        $.ajax({
            url: '/sign/in',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function (data) {
                // API 호출이 성공한 경우
                console.log('로그인 성공:', data);

                // 세션 스토리지에 사용자 아이디와 토큰 저장
                sessionStorage.setItem('userId', data.userId);
                sessionStorage.setItem('token', data.token);

                // 홈페이지로 이동
                window.location.href = '/';
            },
            error: function (error) {
                // 예외 응답 처리
                console.error('예외 발생:', error.responseText);
                try {
                    const errorMessage = JSON.parse(error.responseText).error;
                    const errorCode = JSON.parse(error.responseText).errorCode;
                    console.error('서버에서 받은 예외 메시지:', errorMessage);
                    console.error('에러 코드:', errorCode);
                } catch (parseError) {
                    console.error('예외 메시지 파싱 오류:', parseError.message);
                }
            }
        });
    }

    // 로그인 버튼 클릭 이벤트에 submitLoginForm 함수 연결
    $('#loginbutton').click(function () {
        submitLoginForm();
    });
});
