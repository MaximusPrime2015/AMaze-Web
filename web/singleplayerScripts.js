/**
 * exe 3
 * @author Michael Vassernis 319582888 vaserm3
 * @author Max Anisimov 322068487 anisimm 
 */

// print a loading message on the canvas of the player
function s_printLoading() {
    printMessageOnCanvas("Loading...", "mazeCanvas");
}

var maze;
var Start;
var End;
var rows = 19;
var cols = 19;

var pRow, pCol;
var won;

var solPath = [];
var onTheSolPath;
var currHint = -1;

// requests a single player maze game using long polling
function s_requestMaze() {
    $.getJSON("GetSinglePlayerMaze", function( data ) {
        // extract the maze data from the json object
        maze = data.Content.Maze;
        Start = data.Content.Start;
        End = data.Content.End;
        pRow = Start.Row;
        pCol = Start.Col;
        won = false;
        // draws the maze and the player
        drawMaze("mazeCanvas", maze, rows, cols);
        drawPlayer("mazeCanvas", pRow, pCol, rows, cols, won);
        // gets the solution path for the maze
        solPath = fillSolPath(maze, Start, End, rows, cols);
        onTheSolPath = true;
        currHint = 2;
        // enables the restart and hint buttons
        document.getElementById("restartBtn").disabled = false;
        document.getElementById("hintBtn").disabled = false;
    });
}

// restarts the current maze game
function s_restart() {
    // return the player to the starting position
    pRow = Start.Row;
    pCol = Start.Col;
    won = false;
    // redraw
    drawMaze("mazeCanvas", maze, rows, cols);
    drawPlayer("mazeCanvas", pRow, pCol, rows, cols, won);
    onTheSolPath = true;
    currHint = 2;
}

// responds to keyboard presses
function s_keyPressed(e) {
    var key = e.keyCode ? e.keyCode : e.which;
    if (key === 39)// right
        s_movePlayer(0,1);
    else if (key === 37)// left
        s_movePlayer(0,-1);
    else if (key === 38)// up
        s_movePlayer(-1,0);
    else if (key === 40)// down
        s_movePlayer(1,0);
    
    // redraw
    drawMaze("mazeCanvas", maze, rows, cols);
    drawPlayer("mazeCanvas", pRow, pCol, rows, cols, won);
}

// moves the player to the given direction
function s_movePlayer(rowDir, colDir) {
    if (won === true)
        return;
    pRow += rowDir;
    pCol += colDir;
    
    // check if move was legall
    if (pRow < 0 || pRow >= rows || pCol < 0 || pCol >= cols || maze[pRow * cols + pCol] === '1') {
        pRow -=rowDir;
        pCol -=colDir;
        return;
    }
    
    // calculate new hint position
    if (maze[pRow * cols + pCol] === '2' && onTheSolPath) {
        if (solPath[currHint].Row === pRow && solPath[currHint].Col === pCol)
            currHint++;
        else
            currHint--;
    }
    else if (maze[pRow * cols + pCol] !== '2' && onTheSolPath) {
        currHint--;
        onTheSolPath = false;
    }
    else if (maze[pRow * cols + pCol] === '2' && !onTheSolPath) {
        currHint++;
        onTheSolPath = true;
    }
    if (currHint < 2) currHint = 2;
    // check if player finished the maze
    if (pRow === End.Row && pCol === End.Col)
        won = true;
}

// draws the hint onto the maze
function s_drawHint() {
    drawHint("mazeCanvas", solPath[currHint], rows, cols);
}