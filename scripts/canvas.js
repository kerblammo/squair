/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var gridWidth = 10;
var gridHeight = 20;

window.onload = function () {
    
    loadGrid();
    var singleGrid = document.querySelectorAll(".gridSingle");
    for (var i = 0; i < singleGrid.length; i++) {
        singleGrid[i].addEventListener("click", handleRowClick);
        console.log(i);
    }
}
  //saves the color user have used and displays it
  var colorCounter = 0;

function loadGrid(){
    var html = "";
    var loadInto = document.querySelector("#gridBox");
    
    for (var i = 0; i < gridHeight; i++) {
        
        html += "<div class=\"row\">";
        
        for (var j = 0; j < gridWidth; j++) {
            
            html += "<div class=\"gridSingle\">";
            html += (((i * 10) + (j + 1)));
            html += "</div>";
            
        }
        
        html += "</div>";
        
    }
    
    html += "<style>.row{height:" + (100 / gridHeight) + "%;}";
    html += ".gridSingle{width:" + (100 / gridWidth) + "%;}</style>";
    
    loadInto.innerHTML = html;
}

function clearSelections() {
    var rows = document.querySelectorAll(".gridSingle");
    for (var i = 0; i < rows.length; i++) {
        rows[i].classList.remove("highlighted");
    }

    var rows = document.querySelectorAll(".row");
    for (var i = 0; i < rows.length; i++) {
        rows[i].classList.remove("highlighted");
    }
}

function handleRowClick(e) {
    clearSelections();
    e.target.classList.add("highlighted");
}

function saveUsedColor(){
  
   if(colorCounter == 12){
       colorCounter = 0;
   }
   
  var squares = document.querySelectorAll(".savedColor");
  
  //get the color that user has chosen from the color picker
  var colorCode = document.querySelector("#colorInput").value;
  
  
  //loop through the squares and find the one with nothing in it
  // shove the color in that box
   for(var i = colorCounter; i < 12; i++){
      var temp = squares[i];  
          temp.style.backgroundColor = "#" + colorCode ;
          break;
  }//end for
  
  colorCounter++;;
  
}//end ftn

