# sudoku
A simple sudoku web service, where you can solve sudoku puzzles, save and load your progress.
The service is written using layered architecture. From inner to outer, the layers are :table, :game, :service, :web, :web-ui. And also separate layer for integration tests All the dependencies point from upper layers to the lower ones.
Above all that we have SudokuMainApplication and gradle file with its properties. In properties we can specify what modules to run. for example we can run only web api(:web),
or web with little javascript app I've made (:web-ui). Or maybe add a module with a desktop app, and just run that.

<hr>
<h3>Modules</h3>
<ul>
        <li>
            <b>Interaction: </b> A module containing common data structures, that all the layers can use to interact with each other. Also contains interface that lets object expose it's internal state in a way that is standartized for the whole project
        </li>
        <li>
            <b>Table: </b>This can be considered a core module. It depends on nothing except :interaction(a common module). This module is responsible for sudoku table and all its elements
            like cells, rows, columns, boxes.
        </li>
        <li>
            <b>Game: </b>Comes right after table. This models a game of sudoku
        </li>
        <li>
            <b>Service: </b>Contains interfaces and implementations for all the services that we provide. Right now has 2 services, one is for playing the game, and another is for saving and loading it.
            Also has properties that can configure gaming experience. <b>Spring starts here.</b> We start depending on Spring with this layer.
        </li>
        <li>
            <b>Web: </b>A presentation layer. Contains a web api for our services. Contains http controllers and classes that convert our internal interaction data structures to http.
        </li>
        <li>
            <b>Web-Ui: </b>A Also a presentation layer. Just contains static html and javascript files, that connect to our web api and present the game data in playable way
        </li>
</ul>
