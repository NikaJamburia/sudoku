let webService = "http://localhost:8080/sudoku/web/api/"
let xhr = new XMLHttpRequest();

function startNewGame(onLoad) {
    let url = webService + "start-new-game";
    get(url, onLoad)
}

function restartGame(gameState) {
    let url = webService + "restart-game";

    let sudokuInteractionRequest = {
        gameState: gameState,
        timerValue: timer.innerHTML,
    };
    post(url, JSON.stringify(sudokuInteractionRequest))
}

function fillCell(value, x, y, gameState) {
    let url = webService + "fill-cell";

    let fillCellRequest = {
        value: value,
        coordinateX: x,
        coordinateY: y,
        gameState: gameState,
        timerValue: timer.innerHTML,
    };
    post(url, JSON.stringify(fillCellRequest))
}

function emptyCell(x, y, gameState) {
    let url = webService + "empty-cell";

    let fillCellRequest = {
        coordinateX: x,
        coordinateY: y,
        gameState: gameState,
        timerValue: timer.innerHTML,
    };
    post(url, JSON.stringify(fillCellRequest))
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

function post(url, jsonBody) {
    xhr.open('POST', url, true);
    xhr.setRequestHeader('Content-type', 'application/json');

    xhr.onload = function() {
        return this.responseText
    };

    xhr.send(jsonBody);

}

function get(url, onLoad) {
    xhr.open('GET', url, true);
    xhr.setRequestHeader("Access-Control-Allow-Origin", "*");
    xhr.setRequestHeader('Content-type', 'application/json');

    xhr.onload = onLoad(this.responseText) ;

    xhr.send();
}