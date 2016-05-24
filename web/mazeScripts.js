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

function onCreateSinglePlayerMazeClicked(r, c) {
    var x = document.getElementById("mazeName");
    if (x.value === "") {
        alert("enter a name for the maze.");
        return;
    }
    var text = '{"Type":2,"Content":{"Name":"iM","Maze":"222220000100*00100021112111111121111102100210100222001010211121011121011101021002222222101010002111010101011101011200101010101222222221111111111121110122220010000002001012012111110111211111201200100010120012221121111111012111210002220010022200121011012111112111112110101200101200100200011121110121111121101002222222001002000111010101011111211000001010101#222200","Start":{"Row":0,"Col":12},"End":{"Row":18,"Col":12}}}';
    obj = JSON.parse(text);
    maze = obj.Content.Maze;
    rows = r;
    cols = c;
    Start = obj.Content.Start;
    End = obj.Content.End;
    pRow = Start.Row;
    pCol = Start.Col;
   
    c = document.getElementById("mazeCanvas");
    img = document.getElementById("userPic");
    bW = c.width / (cols + 2);
    bH = c.height / (rows + 2);
    drawMazeOnCanvas();
    drawPlayer();
    // send x to servlet and get JSON answer
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
    var ctx = c.getContext("2d");
    ctx.globalCompositeOperation = 'source-over';
    ctx.drawImage(img, bW + bW * pCol, bH + bH * pRow, bW, bH);
}

function movePlayer(rowDir, colDir) {
    pRow += rowDir;
    pCol += colDir;
    
    if (pRow < 0 || pRow >= rows || pCol < 0 || pCol >= cols || maze[pRow * cols + pCol] == '1') {
        pRow -=rowDir;
        pCol -=colDir;
    } 
    
    drawMazeOnCanvas();
    drawPlayer();
}