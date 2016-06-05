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

function s_requestMaze() {
    $.getJSON("GetSinglePlayerMaze", function( data ) {
        maze = data.Content.Maze;
        Start = data.Content.Start;
        End = data.Content.End;
        pRow = Start.Row;
        pCol = Start.Col;
        won = false;
        drawMaze("mazeCanvas", maze, rows, cols);
        drawPlayer("mazeCanvas", pRow, pCol, rows, cols, won);
        solPath = fillSolPath(maze, Start, End, rows, cols);
        onTheSolPath = true;
        currHint = 2;
        document.getElementById("restartBtn").disabled = false;
        document.getElementById("hintBtn").disabled = false;
    });
}

function s_restart() {
    pRow = Start.Row;
    pCol = Start.Col;
    won = false;
    drawMaze("mazeCanvas", maze, rows, cols);
    drawPlayer("mazeCanvas", pRow, pCol, rows, cols, won);
    onTheSolPath = true;
    currHint = 2;
}

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
    
    drawMaze("mazeCanvas", maze, rows, cols);
    drawPlayer("mazeCanvas", pRow, pCol, rows, cols, won);
}

function s_movePlayer(rowDir, colDir) {
    if (won === true)
        return;
    pRow += rowDir;
    pCol += colDir;
    
    if (pRow < 0 || pRow >= rows || pCol < 0 || pCol >= cols || maze[pRow * cols + pCol] === '1') {
        pRow -=rowDir;
        pCol -=colDir;
        return;
    }
    
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
    
    if (pRow === End.Row && pCol === End.Col)
        won = true;
}

function s_drawHint() {
    drawHint("mazeCanvas", solPath[currHint], rows, cols);
}