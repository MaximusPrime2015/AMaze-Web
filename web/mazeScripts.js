/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//$("head").append('<script type="text/javascript" src="' + script + '"></script>');

var bW = 0;
var bH = 0;
var c = null;
var img = null;
var maze = null;
var rows = 0;
var cols = 0;
var pRow = 0;
var pCol = 0;
var Start = null, End = null;
var won = false;

var solPath = [];

function printLoading() {
    c = document.getElementById("mazeCanvas");
    var ctx = c.getContext("2d");
    ctx.font = "60px Tahoma";
    ctx.fillStyle = "#444444";
    ctx.fillText("Loading...",40,130);
    ctx.fillStyle = "#000000";
    ctx.fillText("Loading...",37,128);
}

function requestSinglePlayerMaze() {
    $.getJSON("GetSinglePlayerMaze", function( data ) {
        createSinglePlayerMazeClicked(data, 19, 19, true);
    });
}

function restartCurrentMaze() {
    createSinglePlayerMazeClicked(maze,rows,cols,false);
}

function createSinglePlayerMazeClicked(mazeStr, ro, co, isNew) {
    c = document.getElementById("mazeCanvas");
    if (isNew) {
        maze = mazeStr.Content.Maze;
    }
    rows = ro;
    cols = co;
    if (isNew) {
        Start = mazeStr.Content.Start;
        End = mazeStr.Content.End;
    }
    fillSolPath(maze, Start, End);
    pRow = Start.Row;
    pCol = Start.Col;
    won = false;
    img = document.getElementById("userPic");
    bW = c.width / (cols + 2);
    bH = c.height / (rows + 2);
    drawMazeOnCanvas();
    drawPlayer();
    drawPlayer();
    document.getElementById("restartBtn").disabled = false;
    document.getElementById("hintBtn").disabled = false;
}

var currHint = -1;

function fillSolPath(mazeSol, mStart, mEnd) {
    solPath = [];
    solPath[0] = mStart;
    solPath[1] = mStart;
    var i = 1;
    while (solPath[i].Row !== mEnd.Row || solPath[i].Col !== mEnd.Col) {
        if ((mazeSol[(solPath[i].Row + 1) * cols + (solPath[i].Col)] == '2' || mazeSol[(solPath[i].Row + 1) * cols + (solPath[i].Col)] == '#')
                && (solPath[i].Row + 1 != solPath[i-1].Row || solPath[i].Col != solPath[i-1].Col))
            solPath[i+1] = {Row:solPath[i].Row + 1, Col:solPath[i].Col};
        else if ((mazeSol[(solPath[i].Row - 1) * cols + (solPath[i].Col)] == '2' || mazeSol[(solPath[i].Row - 1) * cols + (solPath[i].Col)] == '#')
                && (solPath[i].Row - 1 != solPath[i-1].Row || solPath[i].Col != solPath[i-1].Col))
            solPath[i+1] = {Row:solPath[i].Row - 1, Col:solPath[i].Col};
        else if ((mazeSol[(solPath[i].Row) * cols + (solPath[i].Col + 1)] == '2' || mazeSol[(solPath[i].Row) * cols + (solPath[i].Col + 1)] == '#')
                && (solPath[i].Row != solPath[i-1].Row || solPath[i].Col + 1 != solPath[i-1].Col))
            solPath[i+1] = {Row:solPath[i].Row, Col:solPath[i].Col + 1};
        else
            solPath[i+1] = {Row:solPath[i].Row, Col:solPath[i].Col - 1};
        i++;
        if (i == 300) {
            alert("300");
            break;
        }
    }
    onTheSolPath = true;
    currHint = 2;
}

function drawMazeOnCanvas() {
    c = document.getElementById("mazeCanvas");
    var ctx = c.getContext("2d");
    ctx.clearRect(0,0,c.width ,c.height);
    // draw borders
    for(j=0;j<cols + 2;j++) {
        ctx.fillStyle = "#220020";
        ctx.fillRect(j*bW,0,bW ,bH);
        ctx.fillStyle = "#550020";
        ctx.fillRect(j*bW,bH,bW ,bH / 3);
    }
    
    for(j=0;j<rows + 2;j++) {
        ctx.fillStyle = "#220020";
        ctx.fillRect(0,j*bH,bW ,bH);
        ctx.fillRect(c.width - bW,j*bH,bW ,bH);
    }
    // draw walls
    for(i=0;i<rows;i++) {
        for(j=0;j<cols;j++) {
            if(maze[i*cols + j] === '1') {
                ctx.globalCompositeOperation = 'source-over';
                ctx.fillStyle = "#220020";
                ctx.fillRect(j*bW + bW,i*bH + bH,bW ,bH);
                ctx.fillStyle = "#550020";
                ctx.fillRect(j*bW + bW,i*bH + bH + bH,bW ,bH / 3);
            }
            else if (maze[i*cols + j] === '*') {
                ctx.globalCompositeOperation = 'destination-over';
                ctx.fillStyle = "#AA0020";
                ctx.fillRect(j*bW + bW,i*bH + bH / 3 + bH,bW ,bH);
            }
            else if (maze[i*cols + j] === '#') {
                ctx.globalCompositeOperation = 'destination-over';
                ctx.fillStyle = "#2060AA";
                ctx.fillRect(j*bW + bW,i*bH + bH / 3 + bH,bW ,bH);
            }
        }
    }
    
    // draw lower borders
    for(j=0;j<cols + 2;j++) {
        ctx.globalCompositeOperation = 'source-over';
        ctx.fillStyle = "#220020";
        ctx.fillRect(j*bW,c.height - bH,bW ,bH);
    }
}

function drawPlayer() {
    c = document.getElementById("mazeCanvas");
    img = document.getElementById("userPic");
    var ctx = c.getContext("2d");
    ctx.globalCompositeOperation = 'source-over';
    ctx.drawImage(img, bW + bW * pCol, bH + bH * pRow, bW, bH);
    
    if (won == true) {
        ctx.fillStyle = "#FFAAAA";
        ctx.fillRect(50, 50, 300, 200);
        ctx.fillStyle = "#906090";
        ctx.fillRect(45, 45, 300, 200);
        ctx.fillStyle = "#FFAAAA";
        ctx.fillText("You Won!", 70, 150);
    }
}

var onTheSolPath = true;

function movePlayer(rowDir, colDir) {
    if (won == true)
        return;
    pRow += rowDir;
    pCol += colDir;
    
    if (pRow < 0 || pRow >= rows || pCol < 0 || pCol >= cols || maze[pRow * cols + pCol] == '1') {
        pRow -=rowDir;
        pCol -=colDir;
        return;
    }
    
    if (maze[pRow * cols + pCol] == '2' && onTheSolPath) {
        if (solPath[currHint].Row == pRow && solPath[currHint].Col == pCol)
            currHint++;
        else
            currHint--;
    }
    else if (maze[pRow * cols + pCol] != '2' && onTheSolPath) {
        currHint--;
        onTheSolPath = false;
    }
    else if (maze[pRow * cols + pCol] == '2' && !onTheSolPath) {
        currHint++;
        onTheSolPath = true;
    }
    if (currHint < 2) currHint = 2;
    
    if (pRow == End.Row && pCol == End.Col) {
        won = true;
    }
    
    drawMazeOnCanvas();
    drawPlayer();
}

function keyPressed(e) {
    var key = e.keyCode ? e.keyCode : e.which;
    if (key === 39)// right
        movePlayer(0,1);
    else if (key === 37)// left
        movePlayer(0,-1);
    else if (key === 38)// up
        movePlayer(-1,0);
    else if (key === 40)// down
        movePlayer(1,0);
}

function drawHint() {
    c = document.getElementById("mazeCanvas");
    var ctx = c.getContext("2d");
    ctx.globalCompositeOperation = 'destination-over';
    ctx.fillStyle = "#FF2020";
    var i = solPath[currHint].Row;
    var j = solPath[currHint].Col;
    ctx.fillRect(j*bW + bW,i*bH + bH / 3 + bH,bW ,bH);
    ctx.globalCompositeOperation = 'source-over';
}