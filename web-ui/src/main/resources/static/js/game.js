let table =  document.getElementById('table');
let lastResponseGameState;

window.addEventListener('load', (event) => {
    new Promise(function (resolve, reject) {
        emptyTable();
        resolve()
    }).then(() => {
        document.getElementById("start-Btn").addEventListener('click', startGame);
        document.getElementById("save-btn").addEventListener('click', savedGamesModal);
        document.getElementById("load-btn").addEventListener('click', loadGameModal);
        document.getElementById("restart_game_btn").addEventListener('click', restart);
        document.getElementById("generate_new_btn").addEventListener('click', startGame);
    })
});

function startGame() {
    startNewGame(response => {
        handle(response);
    }, response => {
        let error = JSON.parse(response);
        displayModal("Error Occurred", error.message)
    });
}

function restart() {
    restartGame(lastResponseGameState, response => {
        handle(response);
    }, response => {
        let error = JSON.parse(response);
        displayModal("Error Occurred", error.message)
    });
}

function fill(value, x, y) {
    fillCell(value, x, y, lastResponseGameState, response => {
        handle(response);
    }, response => {
        let error = JSON.parse(response);
        displayModal("Error Occurred", error.message)
    });
}

function empty(x, y) {
    emptyCell(x, y, lastResponseGameState, response => {
        handle(response);
    }, response => {
        let error = JSON.parse(response);
        displayModal("Error Occurred", error.message)
    });
}

function save() {
    saveGame(lastResponseGameState, response => {
        saveToLocalStorage(JSON.parse(response).savedGameState);
    }, response => {
        let error = JSON.parse(response);
        displayModal("Error Occurred", error.message)
    })
}

function load(savedGame) {
    loadGame(savedGame, response => {
        $("#saveGameModal").modal('hide');
        handle(response)
    }, response => {
        let error = JSON.parse(response);
        displayModal("Error Occurred", error.message)
    })
}

function handle(response) {
    let gameState = JSON.parse(response).content;

    if (!gameState.gameIsWon) {
        startTimer(gameState.playedTime);
        lastResponseGameState = gameState;
        fillTable(parseTableAsHtml(gameState.tableState), () => {
            registerEventListenersForCells();
        })
    } else {
        displayModal("You Won!",`Congratulations! You won in ${ gameState.playedTime } and ${ gameState.numberOfTurns } moves`);
        emptyTable();
        clearTimer(`<p class="text-success">Game Won!</p>`)
    }

}

function displayModal(title, message) {
    document.getElementById("modal-title").innerHTML = title;
    document.getElementById("modal-message").innerHTML = message;
    $("#errorModal").modal();
}

