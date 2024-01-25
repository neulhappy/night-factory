// 사용할 View의 결제 버튼에 paymentBtn id 달아 사용 하면 됨. 해당 버튼에 PaymentRequest의 데이터를 데이터 셋으로 담아 줄 것.
// 서로 다른 데이터를 사용하는 복수의 결제 버튼을 구현할 경우 ID가 아니라 클래스로 관리하고 가맹점 식별코드를 받아오는 방식만 변경.

const paymentBtn = document.getElementById('PaymentBtn');

const portOneLib = document.createElement('script');
portOneLib.src = 'https://cdn.iamport.kr/v1/iamport.js';
portOneLib.crossOrigin = "anonymous";
portOneLib.onload = function () {
    IMP.init(paymentBtn.getAttribute("data-userCode"));
    impReady();
}
document.head.appendChild(portOneLib);


function impReady() {
    paymentBtn.disabled = false;
    paymentBtn.addEventListener("click", submitPayment);
}

async function submitPayment(event) {
    const target = event.target;
    await requestPayment(target);
}

async function requestPayment(target) {
    const paymentData = setPaymentData(target);
    const response = await PortOne.request_pay(paymentData);
    if (response && response.success === false) {
        alert(response.error_msg);
    } else if (response && response.success === true) {
        await completePayment(response);
    } else {
        alert("오류가 발생했습니다. 다시 시도해주시기 바랍니다.");
    }
}

function setPaymentData(target) {
    return {
        pg: 'kakaopay',
        pay_method: 'card',
        merchant_uid: `test_` + crypto.randomUUID(),
        name: "방 탈출 카페 밤의 공장",
        amount: parseInt(target.getAttribute("data-amount"), 10),
        buyer_tel: target.getAttribute("data-buyer_tel")
    }
}

async function completePayment(response) {
    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest();
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.open("PUT", "/payment/api/complete", true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    PaymentSuccess(response.paymentId);
                    resolve();
                } else {
                    const errorMessage = "결제 처리 과정에서 심각한 오류가 발생했습니다. 다시 시도하지 말고 연락처로 문의하여 주세요.";
                    console.error(errorMessage);
                    alert(errorMessage);
                    reject(new Error("Payment completion failed"));
                }
            }
        };
        xhr.send(JSON.stringify(response));
    });
}

function PaymentSuccess(paymentId) {
    window.location.href = `/payment/success/${paymentId}`;
}
