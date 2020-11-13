let webService = "http://localhost:8080/sudoku/web/api/";
let xhr = new XMLHttpRequest();

function startNewGame(onSuccess, onFail) {
    let url = webService + "start-new-game";
    get(url, onSuccess, onFail)
}

function restartGame(gameState, onSuccess, onFail) {
    let url = webService + "restart-game";

    let sudokuInteractionRequest = {
        gameState: gameState,
        timerValue: timer.innerHTML,
    };
    post(url, JSON.stringify(sudokuInteractionRequest), onSuccess, onFail)
}

function fillCell(value, x, y, gameState, onSuccess, onFail) {
    let url = webService + "fill-cell";

    let fillCellRequest = {
        value: value,
        coordinateX: x,
        coordinateY: y,
        gameState: gameState,
        timerValue: timer.innerHTML,
    };
    post(url, JSON.stringify(fillCellRequest), onSuccess, onFail)
}

function emptyCell(x, y, gameState, onSuccess, onFail) {
    let url = webService + "empty-cell";

    let fillCellRequest = {
        coordinateX: x,
        coordinateY: y,
        gameState: gameState,
        timerValue: timer.innerHTML,
    };
    post(url, JSON.stringify(fillCellRequest), onSuccess, onFail)
}

function saveGame(gameState) {
    let url = webService + "save";

    let saveGameRequest = {
        format: "JSON",
        gameState: gameState,
        timerValue: timer.innerHTML
    };
    post(url, JSON.stringify(saveGameRequest))
}

function loadGame(savedGameState) {
    let url = webService + "load";

    let loadGameRequest = {
        savedSudokuGameState: savedGameState
    };
    post(url, JSON.stringify(loadGameRequest))
}

function post(url, jsonBody, onSuccess, onFail) {
    xhr.open('POST', url, true);
    xhr.setRequestHeader('Content-type', 'application/json');

    xhr.onload = function() {
        if (JSON.parse(this.responseText).isSuccessful) {
            onSuccess(this.responseText);
        } else {
            onFail(this.responseText)
        }
    };

    xhr.send(jsonBody);

}

function get(url, onSuccess, onFail) {
    xhr.open('GET', url, true);
    xhr.setRequestHeader("Access-Control-Allow-Origin", "*");
    xhr.setRequestHeader('Content-type', 'application/json');

    xhr.onload = function() {
        if (JSON.parse(this.responseText).isSuccessful) {
            onSuccess(this.responseText);
        } else {
            onFail(this.responseText)
        }

    };

    xhr.send();
}