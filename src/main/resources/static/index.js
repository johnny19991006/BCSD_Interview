function login() {
    window.location.href = '/signin';
}

function signup() {
    window.location.href = '/signup';
}

    function logout() {
    // 폼 데이터 가져오기
    var formData = {
        userId: sessionStorage.getItem("userId"),
        token: sessionStorage.getItem("token")
};

    // API 호출
    fetch('/sign/out', {
    method: 'POST',
    headers: {
    'Content-Type': 'application/json'
},
    body: JSON.stringify(formData)
})
    .then(response => {
    if (!response.ok) {
    throw new Error('로그아웃 실패');
}
    return response.json();
})
    .then(data => {
    // API 호출이 성공한 경우
    console.log('로그인 성공:', data);
    // 여기서 추가적인 동작을 수행할 수 있습니다.

        sessionStorage.clear()

    // 홈페이지로 이동
    window.location.href = '/';
})
    .catch(error => {
    // API 호출이 실패한 경우
    console.error('로그아웃 실패:', error.message);
    // 여기서 에러 처리를 수행할 수 있습니다.
});
}

    // 세션 스토리지에서 사용자 정보 가져오기
    var userId = sessionStorage.getItem('userId');
    var token = sessionStorage.getItem('token');

    // userInfo 엘리먼트에 사용자 정보 출력
    var userInfoElement = document.getElementById('userInfo');
    if (userId && token) {
    userInfoElement.innerHTML = '사용자 아이디: ' + userId + '<br>토큰: ' + token;
} else {
    userInfoElement.innerHTML = '로그인되지 않았습니다.';
}