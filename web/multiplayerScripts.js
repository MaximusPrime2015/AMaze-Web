/**
 * exe 3
 * @author Michael Vassernis 319582888 vaserm3
 * @author Max Anisimov 322068487 anisimm
 */

// prints loading messages on the canvases of the two players
function m_printLoading() {
    printMessageOnCanvas("waiting for opponent", "myCanvas", true);
    printMessageOnCanvas("opponent's maze", "othersCanvas", true);
}

var myMaze;
var hisMaze;
var myStart;
var hisStart;
var End;

var rows = 19;
var cols = 19;

var myRow, myCol;
var hisRow, hisCol;

// requests a multiplayer maze
function m_requestMaze() {
    // get the multiplayer game with long poll request
    $.getJSON("GetMultiplayerMaze", function( data ) {
        // extract needed data from the json object
        myMaze = data.Content.You.Maze;
        hisMaze = data.Content.Other.Maze;
        myStart = data.Content.You.Start;
        hisStart = data.Content.Other.Start;
        End = data.Content.You.End;
        myRow = myStart.Row;
        myCol = myStart.Col;
        hisRow = hisStart.Row;
        hisCol = hisStart.Col;
        // draw the player's and his oponent's mazes
        drawMaze("myCanvas", myMaze, rows, cols);
        drawPlayer("myCanvas", myRow, myCol, rows, cols, false);
        drawMaze("othersCanvas", hisMaze, rows, cols);
        drawPlayer("othersCanvas", hisRow, hisCol, rows, cols, false);
        // long polling moves
        m_longPollMoves();
    });
}

// responds to players keyboard clicks
function m_keyPressed(e) {
    var key = e.keyCode ? e.keyCode : e.which;
    var myMove;
    var res;
    if (key === 39) { // right
        res = m_movePlayer(0,1, myMaze, myRow, myCol);
        myMove = "right";
    }
    else if (key === 37) { // left
        res = m_movePlayer(0,-1, myMaze, myRow, myCol);
        myMove = "left";
    }
    else if (key === 38) { // up
        res = m_movePlayer(-1,0, myMaze, myRow, myCol);
        myMove = "up";
    }    
    else if (key === 40){// down
        res = m_movePlayer(1,0, myMaze, myRow, myCol);
        myMove = "down";
    }
    
    myRow = res.Row;
    myCol = res.Col;
    // check if player won
    if (!isGameOver) {
        isGameOver = res.Won;
        iWon = true;
    }
    
    // send the player's movement using ajax
    $.ajax({
        data : {move:myMove},
        url: "GetMultiplayerMoves",
        type : "POST"
    });
    
    // redraw
    drawMaze("myCanvas", myMaze, rows, cols);
    drawPlayer("myCanvas", myRow, myCol, rows, cols, isGameOver && iWon);
}

var isGameOver = false;
var iWon = false;

// moves the player acording to the given direction
function m_movePlayer(rowDir, colDir, maze, pRow, pCol) {
    if (isGameOver) return;
    var won = false;
    // move the player
    pRow += rowDir;
    pCol += colDir;
    // check if movement was legall
    if (pRow < 0 || pRow >= rows || pCol < 0 || pCol >= cols || maze[pRow * cols + pCol] === '1') {
        pRow -=rowDir;
        pCol -=colDir;
        return;
    }
    // check if reached end
    if (pRow === End.Row && pCol === End.Col)
        won = true;
    
    return {Row:pRow, Col:pCol, Won:won};
}

// listens to the other player moves using long polling 
function m_longPollMoves() {
    if (isGameOver) return;
    $.getJSON("GetMultiplayerMoves", function( data ) {
        var move = data.Content.Move;
        var resO;
        // attempt to make the oponents move
        if (move === "right")
            resO = m_movePlayer(0,1, hisMaze, hisRow, hisCol);
        else if (move === "left")
            resO = m_movePlayer(0,-1, hisMaze, hisRow, hisCol);
        else if (move === "up")
            resO = m_movePlayer(-1,0, hisMaze, hisRow, hisCol);
        else if (move === "down")
            resO = m_movePlayer(1,0, hisMaze, hisRow, hisCol);
        
        hisRow = resO.Row;
        hisCol = resO.Col;
        // check if oponent won
        if (!isGameOver) {
            isGameOver = resO.Won;
        }
        // redraw
        drawMaze("othersCanvas", hisMaze, rows, cols);
        if (hisRow === End.Row && hisCol === End.Col)
            drawPlayer("othersCanvas", hisRow, hisCol, rows, cols, true);
        else
            drawPlayer("othersCanvas", hisRow, hisCol, rows, cols, false);
        // re - ask again for another move
        m_longPollMoves();
    });
}