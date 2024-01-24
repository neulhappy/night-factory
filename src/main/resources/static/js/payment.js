// 사용할 View의 결제 버튼에 paymentBtn 클래스를 달아 사용 하면 됨. 해당 버튼에 PaymentRequest의 데이터를 데이터 셋으로 담아 줄 것.

const paymentBtn = document.getElementById('PaymentBtn');

const portOneLib = document.createElement('script');
portOneLib.src = 'https://cdn.portone.io/v2/browser-sdk.js';
portOneLib.crossOrigin = "anonymous";
const PortOne = window.PortOne;

portOneLib.onload = function () {
    impReady();
}

document.head.appendChild(portOneLib);

function impReady() {
    paymentBtn.disabled = true;
    paymentBtn.addEventListener("click", submitPayment);
}

async function submitPayment(event) {
    const target = event.target;
    await requestPayment(target);
}

async function requestPayment(target) {
    const paymentData = setPaymentData(target);
    const response = await PortOne.requestPayment(paymentData);
    if (response && response.code) {
        alert(response.message);
    } else {
        await completePayment(response);
    }
}

function setPaymentData(target) {
    return {
        storeId: target.getAttribute("data-storeId"),
        channelKey: target.getAttribute("data-channelKey"),
        paymentId: `payment-` + target.getAttribute("data-paymentId"),
        orderName: target.getAttribute("data-orderName"),
        totalAmount: parseInt(target.getAttribute("data-totalAmount"),10),
        currency: 'CURRENCY_KRW',
        payMethod: 'CARD',
        customer: {
            fullName: target.getAttribute("data-fullName"),
            phoneNumber: target.getAttribute("data-phoneNumber"),
        }
    };
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
