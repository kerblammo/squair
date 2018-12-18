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
                Color: <input class="jscolor" value="ab2567">
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
                <div id="fillHeight" class="row">
                    <div id="savedColor" class="column25">
                        
                    </div>
                    <div id="savedColor" class="column25">
                        
                    </div>
                    <div id="savedColor" class="column25">
                        
                    </div>
                    <div id="savedColor" class="column25">
                        
                    </div>
                </div>
                <div id="" class="row">
                    <div id="savedColor" class="column25">
                    
                    </div>
                    <div id="savedColor" class="column25">
                    
                    </div>
                    <div id="savedColor" class="column25">
                    
                    </div>
                    <div id="savedColor" class="column25">
                    
                    </div>
                </div>
                <div id="" class="row">
                    <div id="savedColor" class="column25">
                    
                    </div>
                    <div id="savedColor" class="column25">
                    
                    </div>
                    <div id="savedColor" class="column25">
                    
                    </div>
                    <div id="savedColor" class="column25">
                    
                    </div>                    
                </div>
            </div>
            
            <div id="toolBoxBot" class="row">
                    
            </div>
        </div>
    </body>
</html>
