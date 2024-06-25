let stompClient = null;

function showMessage(message) {

    $("#messages").append("<tr> <th scope='row'>" + message.name + "</th><td>" + message.content + "</td></tr>");
}

function connect() {

    const socket = new SockJS('/ws'); // create a new SockJS object with the /ws endpoint
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {

        console.log('Connected: ' + frame);

        stompClient.subscribe('/topic/messages', function (message) { // subscribe to the /topic/messages endpoint to receive messages

            console.log("message received: " , JSON.parse(message.body));
            showMessage(JSON.parse(message.body));
        });
    });
}

function sendMessage() {

    let username = $("#username");
    let message = $("#message");

    if ( username.val() && message.val() ) {

        let request = JSON.stringify({'name' : username.val(), 'content': message.val()});
        console.log("sending message: ", request);

        stompClient.send("/app/message", {}, request); // send to the server the message by the /app/message endpoint

        username.attr("disabled", "disabled");
        message.val("");
    }
}

$(document).ready(function () {

    console.log("Index page is loaded");
    connect();

    $("#send").click(function () {

        sendMessage();
    });
});

// Enable Bootstrap validation
(() => {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    const forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {

            event.preventDefault()
            event.preventDefault()
            event.stopPropagation()

            form.classList.add('was-validated')
        }, false)
    })
})()

