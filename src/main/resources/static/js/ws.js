window.onload = function onStart() {
    onKeyUpEnter();

    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    connect();
}

function connect() {
    stompClient.connect({}, function (frame) {
        console.log("connected: " + frame);
        stompClient.subscribe('/topic/messages', function (response) {
            const data = JSON.parse(response.body);
            draw_bg("left", data);
        });
    });
}


function draw_bg(side, data) {
    console.log("drawing...");
    let draw;
    draw = $($('.message_template').clone().html());
    draw.addClass(side).find('.text').html(data.message);
    draw.find('.date').html(data.createdAt);
    draw.find('.name').html('John')
    $('.messages').append(draw);
    return setTimeout(function () {
        return draw.addClass('appeared');
    }, 0);
}

function disconnect() {
    stompClient.disconnect();
}

// disables send button for 3 seconds
function hideSendButton() {
    document.getElementById('send').hidden = true;
    setTimeout(function () {
        document.getElementById('send').hidden = false;
    }, 3000);
}

function sendMessage() {
    let message = document.getElementById('message_input_value').value.trim();

    if (message !== '') {
        stompClient.send("/app/message", {}, JSON.stringify({message}));
        document.getElementById('message_input_value').value = ''

        hideSendButton();
        timer()
    }
}


function timer() {
    let timeleft = 2;
    const downloadTimer = setInterval(function () {
        if (timeleft <= 0) {
            clearInterval(downloadTimer);
            document.getElementById("countdown").innerHTML = "";
        } else {
            document.getElementById("countdown").innerHTML = timeleft;
        }
        timeleft -= 1;
    }, 1000);
}

function onKeyUpEnter() {
    const messageInput = document.getElementById('message_input_value');
    const sendButton = document.getElementById('send');

    messageInput.addEventListener("keyup", function (event) {
        if ((event.key === 'Enter') && !sendButton.hidden) {
            event.preventDefault();
            sendMessage();
        }
    })
}

