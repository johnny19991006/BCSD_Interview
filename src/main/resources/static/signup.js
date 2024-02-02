function submitSignupForm() {
    var formData = {
        userName: $('#userName').val(),
        userBirth: $('#userBirth').val(),
        userTel: $('#userTel').val(),
        userEmail: $('#userEmail').val(),
        userId: $('#userId').val(),
        userPassword: $('#userPassword').val(),
        userNickname: $('#userNickname').val(),
        userEducation: $('#userEducation').val()
    };

    // API 호출 (예: /sign/up)
    $.ajax({
        url: '/sign/up', // 실제 서버 엔드포인트에 맞게 수정
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(formData),
        success: function (data) {
            console.log('회원가입 성공:', data);
            // 여기서 추가적인 동작을 수행할 수 있습니다.
            // 홈페이지로 이동
            window.location.href = '/signin';
        },
        error: function (error) {
            console.error('회원가입 실패:', error.responseText);
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
