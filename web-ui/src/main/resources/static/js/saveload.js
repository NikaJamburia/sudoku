const SAVE_GAMES_LOCAL_STORAGE_KEY = "savedGames"

function saveToLocalStorage(savedGameState) {
    let alreadySavedGames = getSavedGames();

    let newSavedGame = {
        id: (alreadySavedGames.length > 0) ? Math.max(...alreadySavedGames.map(game => game.id)) + 1 : 1,
        date: now(),
        serialization: savedGameState.serializationFormat,
        savedGameState: savedGameState.content
    };

    alreadySavedGames.push(newSavedGame);
    localStorage.setItem(SAVE_GAMES_LOCAL_STORAGE_KEY, JSON.stringify(alreadySavedGames));
    onSuccessfulSave();
}

function getSavedGames() {
    let savedGames = JSON.parse(localStorage.getItem(SAVE_GAMES_LOCAL_STORAGE_KEY));
    return savedGames != null ? savedGames : [];
}

function now() {
    let today = new Date();
    let date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
    let time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
    return date + ' ' + time;
}

function savedGamesTable() {
    let htmlRows = "";
    let htmlTable;

    let savedGames = getSavedGames();

    savedGames.forEach(game => {
        htmlRows += `<tr>
                  <td class="pl-3">${game.id}</td>
                  <td class="pl-3">${game.date}</td>
                  <td class="pl-3">${game.serialization}</td>
                  <td class="pl-3">${JSON.parse(game.savedGameState).playedTime}</td>
                  <td class="pl-3">${JSON.parse(game.savedGameState).numberOfTurns}</td>
                  <td class="pl-3"> <button class="btn btn-danger deleteSaveGameBtn" saveId="${game.id}"><i saveId="${game.id}" class="fas fa-trash-alt"></i></button></td>
                </tr>`;
    });

    htmlTable = `<table class="table mt-3 table-borderless">
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>Date</th>
                          <th>Serialization</th>
                          <th>Time Played</th>
                          <th>Moves</th>
                          <th></th>
                        </tr>
                      </thead>
                      <tbody>
                        ${htmlRows}
                      </tbody>
                </table>`;

    htmlTable += `<button class="btn btn-success mt-3" onclick="createNewSaveGame()"> Create new savegame </button>`;

    return htmlTable;
}

function displaySaveGames() {
    document.getElementById("localSave").innerHTML = savedGamesTable();

    document.querySelectorAll('.deleteSaveGameBtn').forEach(btn => {
        btn.addEventListener('click', evt => deleteSavedGame(evt))
    });
}

function createNewSaveGame() {
    if (getSavedGames().length < 5) {
        save();
    } else {
        displayModal("Error", "Can't keep more then 5 save games in local storage");
    }
}

function deleteSavedGame(event) {
    let saveId = event.target.getAttribute("saveId");
    localStorage.setItem(SAVE_GAMES_LOCAL_STORAGE_KEY, JSON.stringify(getSavedGames().filter(save => save.id != saveId)));
    displaySaveGames();
}

function onSuccessfulSave() {
    displaySaveGames();
}

function saveGameModal() {
    displaySaveGames();
    $("#saveGameModal").modal();
}