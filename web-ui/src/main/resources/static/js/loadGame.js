function loadGameModal() {
    pauseTimer();
    displaySaveGames(gamesToLoadTable());
    $("#saveGameModal").modal();
}

function gamesToLoadTable() {
    let htmlRows = "";
    let htmlTable;

    let savedGames = getSavedGames();

    savedGames.forEach(game => {
        htmlRows += `<tr>
                  <td class="pl-3"><button class="btn btn-success loadGameTableBtn" saveId="${game.id}">Load</button></td>
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
                          <th></th>
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

    return htmlTable;
}

function makeLoadGameRequest(evt) {
    console.log(evt);

    let saveId = evt.target.getAttribute("saveId");
    console.log(saveId);
    let savedGame = getSavedGameById(saveId);
    console.log(savedGame);

    savedGameState = {
        serializationFormat: savedGame.serialization,
        content: savedGame.savedGameState
    };

    load(savedGameState)
}