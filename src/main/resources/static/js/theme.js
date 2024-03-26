document.addEventListener('DOMContentLoaded', function() {
    getThemeAll();

    const realityButton = document.querySelector('.reality');
    const testEyeElement = document.querySelector('.test-eye');

    realityButton.addEventListener('click', function() {
        testEyeElement.style.display = 'block';
    });
});
function getThemeAll(){

    fetch(`http://localhost:8080/api/themes`)
        .then(response => {
            if (!response.ok) {
                throw new Error('네트워크 상태가 좋지 않습니다.');
            }
            return response.json();
        })
        .then(themes => {
            const themeList = document.querySelector('#theme-list');
            themeList.innerHTML = ''; // 기존 목록 초기화

            themes.forEach(theme => {
                const themeItem = document.createElement('li');
                themeItem.style.marginBottom = '150px'; // 각 항목의 하단 여백 조절

                themeItem.innerHTML = `
            <ul>
                <li class="theme-item">
                    <div class="theme-content">
                        <div class="theme-image">
                            <img src="../img/theme${theme.roomId - 100}.png" alt="테마 포스터">
                        </div>
                        <div class="theme-text">
                            <div style="font-size: 2rem; font-weight: 600; color: white">${theme.name}</div>
                            <div style="color: white; padding-top: 15px">${theme.description}</div>
                            <div style="color: #f1c1c1; padding-top: 30px">${theme.difficultyName}</div>
                            <div style="color: #f1c1c1; padding-top: 30px">${theme.estimatedTime}</div>
                            <div style="color: #f1c1c1; padding-top: 30px">${theme.genre}</div>
                            <div style="margin-top: 20px; padding-top: 30px"><button type="button" class="reservation btn btn-danger">RESERVATION</button></div>
                        </div>
                    </div>
                </li>
            </ul>
        `;
                themeList.appendChild(themeItem); // 테마 목록에 항목 추가

                const reservationButton = themeItem.querySelector('.reservation');
                reservationButton.addEventListener('click', () => {
                    handleReservation(theme.name);
                });

            });
        })
        .catch(error => {
            console.error('데이터를 가져오는 중 오류가 발생했습니다:', error);
        });
}

function getThemeByGenre(genre){

    fetch(`http://localhost:8080/api/themes`)
        .then(response => {
            if (!response.ok) {
                throw new Error('네트워크 상태가 좋지 않습니다.');
            }
            return response.json();
        })
        .then(themes => {
            const themeList = document.querySelector('#theme-list');
            themeList.innerHTML = ''; // 기존 목록 초기화

            themes.forEach(theme => {
                // 테마의 장르가 'gore'인 경우에만 처리
                if (theme.genre === genre) {
                    const themeItem = document.createElement('li');
                    themeItem.style.marginBottom = '150px'; // 각 항목의 하단 여백 조절

                    themeItem.innerHTML = `
            <ul>
                <li class="theme-item">
                    <div class="theme-content">
                        <div class="theme-image">
                            <img src="../img/theme${theme.roomId - 100}.png" alt="테마 포스터">
                        </div>
                        <div class="theme-text">
                            <div style="font-size: 2rem; font-weight: 600; color: white">${theme.name}</div>
                            <div style="color: white; padding-top: 15px">${theme.description}</div>
                            <div style="color: #f1c1c1; padding-top: 30px">${theme.difficultyName}</div>
                            <div style="color: #f1c1c1; padding-top: 30px">${theme.estimatedTime}</div>
                            <div style="color: #f1c1c1; padding-top: 30px">${theme.genre}</div>
                            <div style="margin-top: 20px; padding-top: 30px"><button type="button" class="reservation btn btn-danger">RESERVATION</button></div>
                        </div>
                    </div>
                </li>
            </ul>
        `;
                    themeList.appendChild(themeItem); // 테마 목록에 항목 추가

                    const reservationButton = themeItem.querySelector('.reservation');
                    reservationButton.addEventListener('click', () => {
                        handleReservation(theme.name);
                    });
                }
            });
        })
        .catch(error => {
            console.error('데이터를 가져오는 중 오류가 발생했습니다:', error);
        });
}

function getThemeByDifficultyName(difficultyName) {
    fetch(`http://localhost:8080/api/themes`)
        .then(response => {
            if (!response.ok) {
                throw new Error('네트워크 상태가 좋지 않습니다.');
            }
            return response.json();
        })
        .then(themes => {
            const themeList = document.querySelector('#theme-list');
            themeList.innerHTML = '';

            themes.forEach(theme => {
                if (theme.difficultyName === difficultyName) {
                    const themeItem = document.createElement('li');
                    themeItem.style.marginBottom = '100px';

                    themeItem.innerHTML = `
                        <ul>
                            <li class="theme-item">
                                <div class="theme-content">
                                    <div class="theme-image">
                                        <img src="../img/theme${theme.roomId - 100}.png" alt="테마 포스터">
                                    </div>
                                    <div class="theme-text">
                                        <div style="font-size: 2rem; font-weight: 600; color: white">${theme.name}</div>
                                        <div style="color: white; padding-top: 15px">${theme.description}</div>
                                        <div style="color: #f1c1c1; padding-top: 30px">${theme.difficultyName}</div>
                                        <div style="color: #f1c1c1; padding-top: 30px">${theme.estimatedTime}</div>
                                        <div style="color: #f1c1c1; padding-top: 30px">${theme.genre}</div>
                                        <div style="margin-top: 20px; padding-top: 30px"><button type="button" class="reservation btn btn-danger">RESERVATION</button></div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    `;
                    themeList.appendChild(themeItem);

                    const reservationButton = themeItem.querySelector('.reservation');
                    reservationButton.addEventListener('click', () => {
                        handleReservation(theme.name);
                    });
                }
            });
        })
        .catch(error => {
            console.error('데이터를 가져오는 중 오류가 발생했습니다:', error);
        });
}

// 각 버튼의 클릭 이벤트 처리 함수
function handleReservation(themeName) {
    switch (themeName) {
        case '관리실의 비밀 - 나폴리탄':
            sessionStorage.setItem('scrollPosition', '300');
            window.location.href = '/reservation/do';
            break;
        case '공장 작업실 - 살인사건':
            sessionStorage.setItem('scrollPosition', '900');
            window.location.href = '/reservation/do';
            break;
        case '기계실: 피의 유령':

            sessionStorage.setItem('scrollPosition', '1400');
            window.location.href = '/reservation/do';
            break;
        case '기숙사 탈출':
            sessionStorage.setItem('scrollPosition', '2000');
            window.location.href = '/reservation/do';
            break;
        default:
            console.log('해당 테마에 대한 동작이 정의되어 있지 않습니다.');
    }
}

