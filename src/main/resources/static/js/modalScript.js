let stompClient = null;

$(document).ready(function (){
    console.log('Готово!');
    buttonContent = document.getElementById("buttonContent");
});
function connect(event) {
    let socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected);
}
connect();

function onConnected(){
    stompClient.subscribe('/topic/currChar', openModalGet);
}

function openModal(event, novelId, source, novelURL){
    console.log(novelId);
    console.log(source);
    console.log("Success!");
    let curChar = {
        novelId: novelId,
        source: source,
        novelUrl: novelURL,
        curCh: ''
    };
    stompClient.send("/app/select", {}, JSON.stringify(curChar));
}

function openModalGet(payload){
    let message = [];
    message = JSON.parse(payload.body);
    let sub = document.getElementById('one');
    let sub2 = document.getElementById('two');
    while (sub.firstChild) {
        sub.removeChild(sub.firstChild);
    }
    while (sub2.firstChild) {
        sub2.removeChild(sub2.firstChild);
    }

    $("<p>За кого будете играть?</p>").appendTo("div.modal-body");

    for(let i in message){

        let $model = '<a class = "btn btn-primary" href="game_pages/' + message[i].novelUrl + '/' + message[i].curCh + '/' + message[i].source + '">' +
            '<span>'+message[i].curCh+'</span>' +
            '</a>';
        $('div.modal-footer').append($model);

    }

}

let btns = document.getElementsByClassName("btn-primary");

for (let i =0; i < btns.length; i++){
    btns[i].addEventListener("click", function (){
        openModal(true, btns[i].getAttribute("data-novelId"),
            btns[i].getAttribute("data-href"),
            btns[i].getAttribute("data-novelURL"));
    });
}
