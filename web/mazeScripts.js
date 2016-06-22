/**
 * exe 3
 * @author Michael Vassernis 319582888 vaserm3
 * @author Max Anisimov 322068487 anisimm
 */

// prints the given message on the given canvas
function printMessageOnCanvas(message, canvasId, isSmaller) {
    // get the canvas and it's graphics object
    c = document.getElementById(canvasId);
    var ctx = c.getContext("2d");
    // draw the message background
    ctx.font = "60px Tahoma";
    if (isSmaller)
        ctx.font = "35px Tahoma";
    ctx.fillStyle = "#444444";
    ctx.fillText(message,40,130);
    // draw the message's front
    ctx.fillStyle = "#000000";
    if (!isSmaller)
        ctx.fillText(message,37,128);
    else
        ctx.fillText(message,39,129);
}

// draws the given maze onto the given canvas
function drawMaze(canvasId, maze, rows, cols) {
    // get the canvas and it's graphics object
    var c = document.getElementById(canvasId);
    // calculate dimentions
    var bW = c.width / (cols + 2);
    var bH = c.height / (rows + 2);
    var ctx = c.getContext("2d");
    ctx.clearRect(0,0,c.width ,c.height);
    // draw upper border
    for(j=0;j<cols + 2;j++) {
        ctx.fillStyle = "#220020";
        ctx.fillRect(j*bW,0,bW ,bH);
        ctx.fillStyle = "#550020";
        ctx.fillRect(j*bW,bH,bW ,bH / 3);
    }
    // draw side borders
    for(j=0;j<rows + 2;j++) {
        ctx.fillStyle = "#220020";
        ctx.fillRect(0,j*bH,bW ,bH);
        ctx.fillRect(c.width - bW,j*bH,bW ,bH);
    }
    // draw walls
    for(i=0;i<rows;i++) {
        for(j=0;j<cols;j++) {
            if(maze[i*cols + j] === '1') {
                // a wall
                ctx.globalCompositeOperation = 'source-over';
                ctx.fillStyle = "#220020";
                ctx.fillRect(j*bW + bW,i*bH + bH,bW ,bH);
                ctx.fillStyle = "#550020";
                ctx.fillRect(j*bW + bW,i*bH + bH + bH,bW ,bH / 3);
            }
            else if (maze[i*cols + j] === '*') {
                // starting position
                ctx.globalCompositeOperation = 'destination-over';
                ctx.fillStyle = "#AA0020";
                ctx.fillRect(j*bW + bW,i*bH + bH / 3 + bH,bW ,bH);
            }
            else if (maze[i*cols + j] === '#') {
                // end goal
                ctx.globalCompositeOperation = 'destination-over';
                ctx.fillStyle = "#2060AA";
                ctx.fillRect(j*bW + bW,i*bH + bH / 3 + bH,bW ,bH);
            }
        }
    }
    // draw bottom borders
    for(j=0;j<cols + 2;j++) {
        ctx.globalCompositeOperation = 'source-over';
        ctx.fillStyle = "#220020";
        ctx.fillRect(j*bW,c.height - bH,bW ,bH);
    }
}

// returns a solution path of the maze
function fillSolPath(mazeSol, mStart, mEnd, rows, cols) {
    var solPath = [];
    solPath[0] = mStart;
    solPath[1] = mStart;
    var i = 1;
    // while end not reached continue to travel on '2's
    while (solPath[i].Row !== mEnd.Row || solPath[i].Col !== mEnd.Col) {
        if ((mazeSol[(solPath[i].Row + 1) * cols + (solPath[i].Col)] === '2' || mazeSol[(solPath[i].Row + 1) * cols + (solPath[i].Col)] === '#')
                && (solPath[i].Row + 1 !== solPath[i-1].Row || solPath[i].Col !== solPath[i-1].Col))
            solPath[i+1] = {Row:solPath[i].Row + 1, Col:solPath[i].Col};
        else if ((mazeSol[(solPath[i].Row - 1) * cols + (solPath[i].Col)] === '2' || mazeSol[(solPath[i].Row - 1) * cols + (solPath[i].Col)] === '#')
                && (solPath[i].Row - 1 !== solPath[i-1].Row || solPath[i].Col !== solPath[i-1].Col))
            solPath[i+1] = {Row:solPath[i].Row - 1, Col:solPath[i].Col};
        else if ((mazeSol[(solPath[i].Row) * cols + (solPath[i].Col + 1)] === '2' || mazeSol[(solPath[i].Row) * cols + (solPath[i].Col + 1)] === '#')
                && (solPath[i].Row !== solPath[i-1].Row || solPath[i].Col + 1 !== solPath[i-1].Col))
            solPath[i+1] = {Row:solPath[i].Row, Col:solPath[i].Col + 1};
        else
            solPath[i+1] = {Row:solPath[i].Row, Col:solPath[i].Col - 1};
        i++;
        if (i === 300) {
            break;
        }
    }
    return solPath;
}

// draws the player on the canvas
function drawPlayer(canvas, pRow, pCol, rows, cols, won) {
    // get the canvas and it's graphics object
    c = document.getElementById(canvas);
    // get drawing dimentions
    var bW = c.width / (cols + 2);
    var bH = c.height / (rows + 2);
    img = document.getElementById("userPic");
    var ctx = c.getContext("2d");
    ctx.globalCompositeOperation = 'source-over';
    // draw the player
    ctx.drawImage(img, bW + bW * pCol, bH + bH * pRow, bW, bH);
    
    // draws a big Won message if the player won
    if (won === true) {
        ctx.fillStyle = "#FFAAAA";
        ctx.fillRect(50, 50, 300, 200);
        ctx.fillStyle = "#906090";
        ctx.fillRect(45, 45, 300, 200);
        ctx.fillStyle = "#FFAAAA";
        ctx.fillText("You Won!", 70, 150);
    }
}

// draws the given hint on the given maze
function drawHint(canvasId, HintPos, rows, cols) {
    // get the canvas and it's graphics object
    c = document.getElementById(canvasId);
    // get drawing dimentions
    var bW = c.width / (cols + 2);
    var bH = c.height / (rows + 2);
    var ctx = c.getContext("2d");
    ctx.globalCompositeOperation = 'destination-over';
    ctx.fillStyle = "#FF2020";
    var i = HintPos.Row;
    var j = HintPos.Col;
    // draw the hint
    ctx.fillRect(j*bW + bW,i*bH + bH / 3 + bH,bW ,bH);
    ctx.globalCompositeOperation = 'source-over';
}