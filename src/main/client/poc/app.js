let projectId = getUrlParameter("room");
clientId = newId();

$(document).ready(() => {

    $(window).bind("beforeunload", function () {
        return stompClient.send("/syncoder/project/onClosed", {}, JSON.stringify({ clientId: clientId, projectId: projectId }));
    });

    const socket = new SockJS('http://localhost:8082/syncoder');
    let stompClient = Stomp.over(socket);
    // stompClient.debug = (message) => message.startsWith('Whoops!') ? console.log(message) : null;

    stompClient.connect({}, (frame) => {
        stompClient.subscribe("/topic/after-registration", (response) => {
            const users = JSON.parse(response.body);
            showAllUsers(users[0]);
        });

        stompClient.subscribe("/topic/project/onchange/" + projectId, (reponse) => {
            const project = JSON.parse(reponse.body);
            $('#content').val(project.content);
        });

        stompClient.subscribe("/topic/project/onClientCountChange/" + projectId, (response) => {
            const project = JSON.parse(response.body);
            $('#content').val(project.content);
            showPeers(project.clients);
        });

        stompClient.send("/syncoder/project/onOpened", {}, JSON.stringify({ clientId: clientId, projectId: projectId, username: `${clientId}:${new Date().getTime()}` }));
    });

    $('#allProjects').click(() => {


        $.get({
            url: "http://localhost:8081/project/all",
            success: projects => {
                console.log(projects)
            }
        })
    });

    $('#content').keyup(() => {
        stompClient.send("/syncoder/project/change/" + projectId, {}, JSON.stringify({
            id: projectId,
            content: $('#content').val()
        }));
    });

    $('#submit').click(() => {
        const data = JSON.stringify({
            "username": $('#username').val(),
            "email": $('#email').val(),
            "password": $('#password').val(),
            "date": Math.floor(new Date().getTime() / 1000)
        });

        $.ajax({
            type: "POST",
            url: "http://localhost:8081/authentication/user",
            beforeSend: (request) => {
                request.setRequestHeader("Content-Type", "application/json")
            },
            data: data,
            success: (response) => {

            },
            error: (err) => {
                console.log(err);
            }
        });
    });

    const loadAllUsers = () => {
        $.get({
            url: "http://localhost:8081/authentication/user/all",
            success: (response) => {
                showAllUsers(response);
            },
            error: (err) => {
                console.log(err);
            }
        })
    };

    const showAllUsers = (users) => {
        $('#all-users').html('');

        users.forEach(user => {
            $('#all-users').append(`<p>${user.username}</p>`);
        });
    };

    const showPeers = (clients) => {
        $('#all-peers').html('');

        clients.forEach(client => {
            if (client.id !== clientId) {
                $('#all-peers').append(`<p>${client.username}</p>`)
            }
        })
    };


    loadAllUsers();
    // initProject();
});

function getUrlParameter(name) {
    const url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    const regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

function newId() {
    const chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789'

    let result = "";

    for (let i = 0; i < 8; i++) {
        result += chars[Math.floor(Math.random() * chars.length)];
    }

    return result;
}
