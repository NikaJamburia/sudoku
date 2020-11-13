let table =  document.getElementById('table');
let lastResponseGameState;

window.addEventListener('load', (event) => {
    new Promise(function (resolve, reject) {
        fillTable("<button id='startBtn' class='btn btn-lg btn-success'>Start</button>", () => {});
        resolve()
    }).then(() =>
        document.getElementById("startBtn").addEventListener('click', startGame))
});

document.getElementById("restart_game_btn").addEventListener('click', restart);
document.getElementById("generate_new_btn").addEventListener('click', startGame);

function startGame() {
    startNewGame(response => {
        handle(response);
    }, response => {
        let error = JSON.parse(response);
        displayError(error.message)
    });
}

function restart() {
    restartGame(lastResponseGameState, response => {
        handle(response);
    }, response => {
        let error = JSON.parse(response);
        displayError(error.message)
    });
}

function fill(value, x, y) {
    fillCell(value, x, y, lastResponseGameState, response => {
        handle(response);
    }, response => {
        let error = JSON.parse(response);
        displayError(error.message)
    });
}

function empty(x, y) {
    emptyCell(x, y, lastResponseGameState, response => {
        handle(response);
    }, response => {
        let error = JSON.parse(response);
        displayError(error.message)
    });
}

function handle(response) {
    let gameState = JSON.parse(response).content;
    startTimer(gameState.playedTime);
    lastResponseGameState = gameState;
    fillTable(parseTableAsHtml(gameState.tableState), () => {
        registerEventListenersForCells();
    })
}

function displayError(errorText) {
    document.getElementById("error_text_display").innerHTML = errorText;
    $("#errorModal").modal();
}

