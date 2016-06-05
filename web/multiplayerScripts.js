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

function m_requestMaze() {
    $.getJSON("GetMultiplayerMaze", function( data ) {
        myMaze = data.Content.You.Maze;
        hisMaze = data.Content.Other.Maze;
        myStart = data.Content.You.Start;
        hisStart = data.Content.Other.Start;
        End = data.Content.You.End;
        myRow = myStart.Row;
        myCol = myStart.Col;
        hisRow = hisStart.Row;
        hisCol = hisStart.Col;
        drawMaze("myCanvas", myMaze, rows, cols);
        drawPlayer("myCanvas", myRow, myCol, rows, cols, false);
        drawMaze("othersCanvas", hisMaze, rows, cols);
        drawPlayer("othersCanvas", hisRow, hisCol, rows, cols, false);
        // long polling moves
        m_longPollMoves();
    });
}

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
    if (!isGameOver) {
        isGameOver = res.Won;
        iWon = true;
    }
    
    $.ajax({
        data : {move:myMove},
        url: "GetMultiplayerMoves",
        type : "POST"
    });
    
    drawMaze("myCanvas", myMaze, rows, cols);
    drawPlayer("myCanvas", myRow, myCol, rows, cols, isGameOver && iWon);
}

var isGameOver = false;
var iWon = false;

function m_movePlayer(rowDir, colDir, maze, pRow, pCol) {
    if (isGameOver) return;
    var won = false;
    pRow += rowDir;
    pCol += colDir;
    
    if (pRow < 0 || pRow >= rows || pCol < 0 || pCol >= cols || maze[pRow * cols + pCol] === '1') {
        pRow -=rowDir;
        pCol -=colDir;
        return;
    }
    
    if (pRow === End.Row && pCol === End.Col)
        won = true;
    
    return {Row:pRow, Col:pCol, Won:won};
}

function m_longPollMoves() {
    if (isGameOver) return;
    $.getJSON("GetMultiplayerMoves", function( data ) {
        var move = data.Content.Move;
        var resO;
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
        if (!isGameOver) {
            isGameOver = resO.Won;
        }
        
        drawMaze("othersCanvas", hisMaze, rows, cols);
        if (hisRow === End.Row && hisCol === End.Col)
            drawPlayer("othersCanvas", hisRow, hisCol, rows, cols, true);
        else
            drawPlayer("othersCanvas", hisRow, hisCol, rows, cols, false);
        m_longPollMoves();
    });
}