function connect() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected: " + frame);
        stompClient.subscribe('/topic/messages', function (response) {
            const data = JSON.parse(response.body);
            draw_bg("left", data.message);
        });
    });
}

function draw_bg(side, text) {
    console.log("drawing...");
    let draw;
    draw = $($('.message_template').clone().html());
    draw.addClass(side).find('.text').html(text);
    $('.messages').append(draw);
    return setTimeout(function () {
        return draw.addClass('appeared');
    }, 0);
}

function disconnect() {
    stompClient.disconnect();
}


function sendMessage() {
    let message = document.getElementById('message_input_value').value;

    stompClient.send("/app/message", {}, JSON.stringify(
        {'message': message}));
}


