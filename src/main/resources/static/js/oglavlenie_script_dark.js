let j = 0;
function openNav() {
    if(document.documentElement.clientWidth > 700){
        if(j == 0){
            document.getElementById("mySidenav").style.width = "25%";
            document.body.style.backgroundColor = "rgba(0,0,0,0.7)";
            document.getElementById("searchIcon").style.backgroundColor = "#ceba85";
            document.getElementById("homeIcon").style.backgroundColor = "rgba(86, 86, 86, 0.1)";
            j++;
        }
        else if(j == 1){
            document.getElementById("mySidenav").style.width = "0";
            document.body.style.backgroundColor = "#0e0e0e";
            document.getElementById("searchIcon").style.backgroundColor = "#0e0e0e";
            document.getElementById("homeIcon").style.backgroundColor = "#0e0e0e";
            j--;
        }
    }
    else if(document.documentElement.clientWidth <= 700){
        if(j == 0){
            document.getElementById("mySidenav").style.width = "45%";
            document.body.style.backgroundColor = "rgba(0,0,0,0.7)";
            document.getElementById("searchIcon").style.backgroundColor = "#ceba85";
            document.getElementById("homeIcon").style.backgroundColor = "rgba(86, 86, 86, 0.1)";
            j++;
        }
        else if(j == 1){
            document.getElementById("mySidenav").style.width = "0";
            document.body.style.backgroundColor = "#0e0e0e";
            document.getElementById("searchIcon").style.backgroundColor = "#0e0e0e";
            document.getElementById("homeIcon").style.backgroundColor = "#0e0e0e";
            j--;
        }
    }
}

let i = 0;
function dropMenu(){
    if(document.documentElement.clientWidth > 700){
        if(i == 0){
            document.getElementById("pageMenu").style.width="9.5%";
            document.body.style.backgroundColor = "rgba(0,0,0,0.7)";
            document.getElementById("menuIcon").style.backgroundColor = "rgba(86, 86, 86, 0.01)";
            document.getElementById("menuBar").style.backgroundColor = "rgba(86, 86, 86, 0.01)";
            i++;
        }
        else if(i == 1){
            document.getElementById("pageMenu").style.width = "0";
            document.body.style.backgroundColor = "#0e0e0e";
            document.getElementById("menuIcon").style.backgroundColor = "#ceba85";
            document.getElementById("menuBar").style.backgroundColor = "#ceba85";
            i--;
        }
    }
    else if(document.documentElement.clientWidth <= 700){
        if(i == 0){
            document.getElementById("pageMenu").style.width="19.5%";
            document.body.style.backgroundColor = "rgba(0,0,0,0.7)";
            document.getElementById("menuIcon").style.backgroundColor = "rgba(86, 86, 86, 0.01)";
            document.getElementById("menuBar").style.backgroundColor = "rgba(86, 86, 86, 0.01)";
            i++;
        }
        else if(i == 1){
            document.getElementById("pageMenu").style.width = "0";
            document.body.style.backgroundColor = "#0e0e0e";
            document.getElementById("menuIcon").style.backgroundColor = "#ceba85";
            document.getElementById("menuBar").style.backgroundColor = "#ceba85";
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
let chAnchor = document.querySelectorAll(".ch_href");
let anAnchor = document.querySelectorAll(".an_href");
for(let i = 0; i <chAnchor.length; i++){
    anAnchor[i].addEventListener("click", createInfoBlocks('Вам есть 18+?', 'img/age.jpg', chAnchor[i],'#home_anchor'))
}

function createInfoBlocks(name, img, choiceYes, choiceNo){
    //оверлай слой
    let overlay = document.createElement("div");
    overlay.className = "overlay";
    document.body.append(overlay);

    //сам блок
    let blockScript = document.createElement("div");
    blockScript.className = "block-with-info";
    blockScript.innerHTML = name;
    overlay.append(blockScript);

    //выйти
    let anchorExit = document.createElement("a");
    anchorExit.href = "#home_anchor";
    anchorExit.innerHTML = "&times;";
    anchorExit.className = "exitButton";
    blockScript.append(anchorExit);

    //изображение
    let imgScript = document.createElement("img");
    imgScript.className = "img-in-block";
    imgScript.src= img;
    blockScript.append(imgScript);

    //блок с информацией
    let infoBlockScript = document.createElement("div");
    infoBlockScript.className = "choice-in-block";
    blockScript.append(infoBlockScript);


    //кнопка "да"
    let anchorsChoice1 = document.createElement("a");
    anchorsChoice1.href = choiceYes.getAttribute("href");
    anchorsChoice1.className = "close";
    anchorsChoice1.innerHTML = "Да";
    infoBlockScript.append(anchorsChoice1);

    //кнопка "нет"
    let anchorsChoice2 = document.createElement("a");
    anchorsChoice2.href = choiceNo;
    anchorsChoice2.className = "close";
    anchorsChoice2.innerHTML = "Нет";
    infoBlockScript.append(anchorsChoice2);


    anchorExit.onclick = function(){
        anchorsChoice2.remove();
        anchorsChoice1.remove();
        infoBlockScript.remove();
        imgScript.remove();
        blockScript.remove();
        overlay.remove();
        anchorExit.remove();
    }
    anchorsChoice2.onclick = function(){
        anchorsChoice2.remove();
        anchorsChoice1.remove();
        infoBlockScript.remove();
        imgScript.remove();
        blockScript.remove();
        overlay.remove();
        anchorExit.remove();
    }
}