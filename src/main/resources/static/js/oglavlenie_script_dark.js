function openNav() {
    if(document.documentElement.clientWidth > 700 && document.documentElement.clientWidth < 1024){
        document.getElementById("mySidenav").style.width = "100%";
        document.body.style.backgroundColor = "rgba(0,0,0,0.8)";
        document.getElementById("searchIcon").style.backgroundColor = "#ceba85";
        document.getElementById("homeIcon").style.backgroundColor = "rgba(86, 86, 86, 0.01)";
        document.getElementById("mySidenav").style.zIndex="1030";
        document.getElementById("mySidenav").style.backgroundColor = "rgba(86, 86, 86)";
    }
    else if(document.documentElement.clientWidth <= 700){
        document.getElementById("mySidenav").style.width = "100%";
        document.body.style.backgroundColor = "rgba(0,0,0,0.8)";
        document.getElementById("searchIcon").style.backgroundColor = "#ceba85";
        document.getElementById("homeIcon").style.backgroundColor = "rgba(86, 86, 86, 0.01)";
        document.getElementById("mySidenav").style.zIndex="1030";
        document.getElementById("mySidenav").style.backgroundColor = "rgba(86, 86, 86)";
    }
    else if(document.documentElement.clientWidth >= 1024){
        document.getElementById("mySidenav").style.width = "25%";
        document.body.style.backgroundColor = "rgba(0,0,0,0.8)";
        document.getElementById("searchIcon").style.backgroundColor = "#ceba85";
        document.getElementById("homeIcon").style.backgroundColor = "rgba(86, 86, 86, 0.01)";
        document.getElementById("mySidenav").style.zIndex="1030";
        document.getElementById("mySidenav").style.backgroundColor = "rgba(86, 86, 86)";
    }
}

function closeNav(){
    document.getElementById("mySidenav").style.width = "0";
    document.body.style.backgroundColor = "";
    document.getElementById("searchIcon").style.backgroundColor = "";
    document.getElementById("homeIcon").style.backgroundColor = "";
}

function openNavBar() {
    document.getElementById("pageHeader").style.height = "100%";
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