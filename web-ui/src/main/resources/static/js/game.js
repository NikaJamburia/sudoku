let gameStarted = false;
let table =  document.getElementById('table')

window.addEventListener('load', (event) => {
    new Promise(function (resolve, reject) {
        table.innerHTML = "<button id='startBtn' class='btn btn-lg btn-success'>Start</button>"
        resolve()
    }).then(() =>
        document.getElementById("startBtn").addEventListener('click', startGame))

});

function startGame() {
    startTimer();
    gameStarted = true;
    generateTable(tableSize)
}
