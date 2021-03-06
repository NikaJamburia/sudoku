let webService = "http://localhost:8080/sudoku/web/api/";
let xhr = new XMLHttpRequest();

function startNewGame(difficulty, onSuccess, onFail) {
    let url = webService + "start-new-game";

    let startNewGameRequest = {
        difficulty: difficulty
    };

    withLoadingAnimation(
        post(url, JSON.stringify(startNewGameRequest), onSuccess, onFail)
    );
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

function saveGame(gameState, onSuccess, onFail) {
    let url = webService + "save";

    let saveGameRequest = {
        format: "JSON",
        gameState: gameState,
        timerValue: timer.innerHTML
    };
    post(url, JSON.stringify(saveGameRequest), onSuccess, onFail)
}

function loadGame(savedGame, onSuccess, onFail) {
    let url = webService + "load";

    let loadGameRequest = {
        savedSudokuGameState: savedGame
    };
    post(url, JSON.stringify(loadGameRequest), onSuccess, onFail)
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

function withLoadingAnimation(afterLoad) {
    let loadingHtml = ` <div class="text-center">
                            <p><b>Generating Table...</b></p> 
                            <br>
                            <img width="300" height="300" src="../src/gif/loading.gif" />
                        </div>`;
    table.innerHTML = loadingHtml;
    afterLoad()
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