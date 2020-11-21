# sudoku
A simple sudoku web service, where you can solve sudoku puzzles, save and load your progress.
The service is written using layered architecture. From lower to highter, the layers are :table, :game, :service, :web, :web-ui. All the dependencies point from upper layers to the lower ones.
Above all that we have SudokuMainApplication and gradle file with its properties. In properties we can specify what modules to run. for example we can run only web api(:web),
or web with little javascript app I've made (:web-ui). Or maybe add a module with a desktop app, and just run that.

<h3>Modules</h3>
<ul>

<li><b>Table: </b>This can be considered a core module. It depends on nothing except :interaction(a common module). This module is responsible for sudoku table and all its elements
like cells, rows, columns, boxes.</li>
<li><b>Game: </b>Comes right after table. This models a game of sudoku</li>

</ul>
