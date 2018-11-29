const getById = id => document.getElementById(id);

// global constants
const localStorage = window.localStorage;
const feedbackTextarea = getById("feedback-text");
const feedbackContainer = getById("feedback-container");


const isOnline = () => {
    return window.navigator.onLine;
}

const feedbackTemplate = (text, date, time, nickname) => `
    <div class="request">
        <p>${text}</p>
        <div class="req-footer">
            <div class="date">${date}, ${time}</div>
            <div class="nickname">${nickname}</div>
        </div>
    </div>
`

const reportNetworkStatus = () => {
    if (isOnline()) {//if navigator.online == true
        console.log("Network status: ONLINE");
    } else {
        console.log("Network status: offline");
    }
}

function sendFeedback() {
	var username = "Anonym";

    if (feedbackTextarea.value.replace(/\s/g, '').length == 0) {
        feedbackTextarea.classList.add("error-textarea");
        feedbackTextarea.value = '';
        feedbackTextarea.placeholder = "Please, add description";
        return;
    } else {
        feedbackTextarea.classList.remove("error-textarea");
    }

    var date = new Date();
    var feedback = [
        feedbackTextarea.value, date.toLocaleDateString(), date.toLocaleTimeString(), username
    ];

    storeMessage(feedback);
}


function storeMessage(elem) {
    if (isOnline()) {
        storeMessageRemotely(elem);
    } else {
        storeMessageLocaly(elem);
    }
}

function storeMessageLocaly(elem) {
    addToLocalStorage(elem, "feedback")
    clearUI();
    logEvent('Message saved locally: ' + elem[0]);
}

//TODO: Implement after learning Node.js
function storeMessageRemotely(elem) {
    clearUI();
    console.log('Message sent to server:');
}


function displayFromLocalStorage() {
    var feedbacks = [];
    getAllItems((resultArray) => {
        feedbacks = resultArray;
    }, "feedback")

    for (var i = 0; i < feedbacks.length; i++) {
        displayFeedback(feedbacks[i]);
    }
    deleteAllItems("feedback");
}

function displayFeedback(feedback) {
    $('#feedback-container').prepend(
        feedbackTemplate(feedback[0], feedback[1], feedback[2], feedback[3])
    );
}

function clearUI() {
    feedbackTextarea.value = "";
}


 if (window.applicationCache) {
    window.addEventListener('online', function (e) {
        reportNetworkStatus();
        displayFromLocalStorage();
    }, true);

    window.addEventListener('offline', function (e) {
        reportNetworkStatus();
    }, true);

    displayFromLocalStorage();
}
