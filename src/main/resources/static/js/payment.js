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
        storeId: target.getAttribute("storeId"),
        channelKey: target.getAttribute("channelKey"),
        paymentId: `payment-` + target.getAttribute("paymentId"),
        orderName: target.getAttribute("orderName"),
        totalAmount: parseInt(target.getAttribute("totalAmount"),10),
        currency: 'CURRENCY_KRW',
        payMethod: 'CARD',
        customer: {
            fullName: target.getAttribute("fullName"),
            phoneNumber: target.getAttribute("phoneNumber"),
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
        xhr.onerror = function () {
            const errorMessage = "서버와의 통신 중 오류가 발생했습니다. 연결을 확인하거나 잠시 후 다시 시도해 주세요.";
            console.error(errorMessage);
            alert(errorMessage);
            reject(new Error("Server communication failed"));
        };
        xhr.send(JSON.stringify(response));
    });
}

function PaymentSuccess(paymentId) {
    window.location.href = `/payment/success/${paymentId}`;
}
