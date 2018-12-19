<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">        
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <script src="scripts/canvas.js"></script>
        <script src="scripts/jscolor.js"></script>
        <title>Squair - The community drawing board</title>
    </head>
    <body>
        <div id="gridBox">
            
        </div>
        <div id="chatBox">
            <h1>Chat</h1`>
        </div>
        <div id="toolBox">
            <div id="toolBoxTop" class="row">
                Red Bar
                <div id="" class="row">
                <input type="range" id="slideRed" name="slideRed" min="0" max="255">
                </div>
            </div>
            <div id="toolBoxTop" class="row">
                Green Bar
                <div id="" class="row">
                <input type="range" id="slideGreen" name="slideRed" min="0" max="255">
                </div>
            </div>
            <div id="toolBoxTop" class="row">
                Blue Bar
                <div id="" class="row">
                <input type="range" id="slideBlue" name="slideRed" min="0" max="255">
                </div>
            </div>
            
       <div id="colorTools" class="row">
              
          <div id="colorCurrent" class="column50" style="height: 50px; background-color: beige">
                Color: <input id="colorInput" class="jscolor" value="ab2567" onchange="saveUsedColor();">
            </div>
<!--                <div id="colorCurrent" class="column25" style="height: 50px; background-color: rgb(255, 111, 0);">
                    Current Color
                </div>-->
<!--                <div id="colorMatchTool" class="column25">
                    Match Tool
                </div>-->
                <div id="colorBalance" class="column50">
                    Budget: 100
                </div>
            </div>
            
            <div id="paletteBox" class="row">
                <div id="row1" >
                    <div id="usedColor1" class="savedColor" >
                       
                    </div>
                    <div id="usedColor2" class="savedColor" >
                       
                    </div>
                    <div id="usedColor3" class="savedColor" >
                    
                    </div>
                    <div id="usedColor4" class="savedColor" >
                       
                    </div>
                </div>
                <div id="row2" >
                    <div id="usedColor5" class="savedColor" >
                    
                    </div>
                    <div id="usedColor6" class="savedColor" >
                
                    </div>
                    <div id="usedColor7" class="savedColor" >
                 
                    </div>
                    <div id="usedColor8" class="savedColor" >
                    
                    </div>
                </div>
                <div id="row3">
                    <div id="usedColor9" class="savedColor" >
                    
                    </div>
                    <div id="usedColor10" class="savedColor" >
                    
                    </div>
                    <div id="usedColor11" class="savedColor" >
                   
                    </div>
                    <div id="usedColor12" class="savedColor" >
                   
                    </div>                    
                </div>
            </div>
            
            <div id="toolBoxBot" class="row">
                <p>Zoom lenzez</p>
            </div>
        </div>
    </body>
</html>
