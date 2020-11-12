let gameStarted = false;
let table =  document.getElementById('table');

window.addEventListener('load', (event) => {
    new Promise(function (resolve, reject) {
        table.innerHTML = "<button id='startBtn' class='btn btn-lg btn-success'>Start</button>";
        resolve()
    }).then(() =>
        document.getElementById("startBtn").addEventListener('click', startGame))

});

function startGame() {
    startNewGame(response => {
        startTimer();
        gameStarted = true;
        table.innerHTML = parseTableAsHtml(JSON.parse(response).content.tableState);
    }, response => {
        let error = JSON.parse(response);
        console.log(error.message)
        document.getElementById("error_text_display").innerHTML = error.message;
        $("#errorModal").modal();
    });
}
