let j = 0;
function openNav() {
    if(document.documentElement.clientWidth > 700){
        if(j == 0){
            document.getElementById("mySidenav").style.width = "25%";
            document.body.style.backgroundColor = "rgba(157,157,157,0.7)";
            document.getElementById("searchIcon").style.backgroundColor = "#857154";
            j++;
        }
        else if(j == 1){
            document.getElementById("mySidenav").style.width = "0";
            document.body.style.backgroundColor = "";
            document.getElementById("searchIcon").style.backgroundColor = "";
            document.getElementById("homeIcon").style.backgroundColor = "";
            j--;
        }
    }
    else if(document.documentElement.clientWidth <= 700){
        if(j == 0){
            document.getElementById("mySidenav").style.width = "45%";
            document.body.style.backgroundColor = "rgba(157,157,157,0.7)";
            document.getElementById("searchIcon").style.backgroundColor = "#857154";
            j++;
        }
        else if(j == 1){
            document.getElementById("mySidenav").style.width = "0";
            document.body.style.backgroundColor = "";
            document.getElementById("searchIcon").style.backgroundColor = "";
            document.getElementById("homeIcon").style.backgroundColor = "";
            j--;
        }
    }
}

let i = 0;
function dropMenu(){
    if(document.documentElement.clientWidth > 700){
        if(i == 0){
            document.getElementById("pageMenu").style.width="9.5%";
            document.body.style.backgroundColor = "rgba(157,157,157,0.7)";
            document.getElementById("menuIcon").style.backgroundColor = "rgba(86, 86, 86, 0.01)";
            document.getElementById("menuBar").style.backgroundColor = "rgba(86, 86, 86, 0.01)";
            i++;
        }
        else if(i == 1){
            document.getElementById("pageMenu").style.width = "0";
            document.body.style.backgroundColor = "";
            document.getElementById("menuIcon").style.backgroundColor = "";
            document.getElementById("menuBar").style.backgroundColor = "";
            i--;
        }
    }
    else if(document.documentElement.clientWidth <= 700){
        if(i == 0){
            document.getElementById("pageMenu").style.width="19.5%";
            document.body.style.backgroundColor = "rgba(157,157,157,0.7)";
            document.getElementById("menuIcon").style.backgroundColor = "rgba(86, 86, 86, 0.01)";
            document.getElementById("menuBar").style.backgroundColor = "rgba(86, 86, 86, 0.01)";
            i++;
        }
        else if(i == 1){
            document.getElementById("pageMenu").style.width = "0";
            document.body.style.backgroundColor = "";
            document.getElementById("menuIcon").style.backgroundColor = "";
            document.getElementById("menuBar").style.backgroundColor = "";
            i--;
        }
    }
}
  

function searchOnPage() {
    // Объявить переменные
    var input, filter, ul, li, a, i;
    input = document.getElementById("textToFind");
    filter = input.value.toUpperCase();
    ul = document.getElementById("menuSearch");
    li = ul.getElementsByTagName("li");
  
    // Перебирайте все элементы списка и скрывайте те, которые не соответствуют поисковому запросу
    for (i = 0; i < li.length; i++) {
      a = li[i].getElementsByTagName("a")[0];
        if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } 
        else {
            li[i].style.display = "none";
        }
    }
}