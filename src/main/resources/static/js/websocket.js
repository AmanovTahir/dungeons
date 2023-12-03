const logMessages = document.getElementById("logMessages");

const socket = new SockJS('/dungeon');
const stompClient = Stomp.over(socket);

function addMessageToLog(messageText) {
    const messageElement = document.createElement("div");
    messageElement.textContent = messageText;

    logMessages.insertBefore(messageElement, logMessages.firstChild);
}

const savedMessages = JSON.parse(localStorage.getItem("logMessages")) || [];

savedMessages.reverse().forEach(messageText => {
    addMessageToLog(messageText);
});

stompClient.connect({}, frame => {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/receive', message => {
        const messageText = message.body;
        addMessageToLog(messageText);
        savedMessages.unshift(messageText);
        localStorage.setItem("logMessages", JSON.stringify(savedMessages));
    });
});
