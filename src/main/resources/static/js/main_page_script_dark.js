var link = document.getElementById("theme-link");
var currThemeForAll = "dark_theme"; //только для локал варианта

function ChangeTheme()
{
    let lightTheme = "all_styles/main_page_light.css";
    let darkTheme = "all_styles/main_page_dark.css";
    var currTheme = link.getAttribute("href");
    var theme = "";

    if(currTheme == lightTheme) {
   	    currTheme = darkTheme;
        theme = "dark";
        currThemeForAll = "dark_theme";   
        console.log(currThemeForAll);
    }
    else {    
   	    currTheme = lightTheme;
        theme = "light";
        currThemeForAll = "light_theme";
        console.log(currThemeForAll);   
    }

    link.setAttribute("href", currTheme);

    Save(theme);
}

function Save(theme)
{
    var Request = new XMLHttpRequest();
    Request.open("GET", "./themes.php?theme=" + theme, true);
    Request.send();
}



//готов код 
let j = 0;
function openNav() {
    console.log(currThemeForAll);
    if(currThemeForAll == "dark_theme"){
        if(document.documentElement.clientWidth > 700){
            if(j == 0){
                document.getElementById("mySidenav").style.width = "25%";
                document.body.style.backgroundColor = "rgba(0,0,0,0.8)";
                document.getElementById("searchIcon").style.backgroundColor = "#ceba85";
                document.getElementById("homeIcon").style.backgroundColor = "rgba(86, 86, 86, 0.01)";
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
                document.body.style.backgroundColor = "rgba(0,0,0,0.8)";
                document.getElementById("searchIcon").style.backgroundColor = "#ceba85";
                document.getElementById("homeIcon").style.backgroundColor = "rgba(86, 86, 86, 0.01)";
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
    else if(currThemeForAll == "light_theme"){
        if(document.documentElement.clientWidth > 700){
            if(j == 0){
                document.getElementById("mySidenav").style.width = "25%";
                document.body.style.backgroundColor = "rgba(157,157,157,0.7)";
                document.getElementById("searchIcon").style.backgroundColor = "#857154";
                document.getElementById("homeIcon").style.backgroundColor = "rgba(157, 157, 157, 0.1)";
                j++;
            }
            else if(j == 1){
                document.getElementById("mySidenav").style.width = "0";
                document.body.style.backgroundColor = "";
                document.getElementById("searchIcon").style.backgroundColor = "";
                document.getElementById("homeIcon").style.backgroundColor = "";
                document.getElementById("menuIcon").style.backgroundColor = "";
                j--;
            }
        }
        else if(document.documentElement.clientWidth <= 700){
            if(j == 0){
                document.getElementById("mySidenav").style.width = "45%";
                document.body.style.backgroundColor = "rgba(157,157,157,0.7)";
                document.getElementById("searchIcon").style.backgroundColor = "#857154";
                document.getElementById("homeIcon").style.backgroundColor = "rgba(157, 157, 157, 0.1)";
                j++;
            }
            else if(j == 1){
                document.getElementById("mySidenav").style.width = "0";
                document.body.style.backgroundColor = "";
                document.getElementById("searchIcon").style.backgroundColor = "";
                document.getElementById("homeIcon").style.backgroundColor = "";
                document.getElementById("menuIcon").style.backgroundColor = "";
                j--;
            }
        }
    }
}
 


//выпадающее меню
let i = 0;
function dropMenu(){
    if(currThemeForAll == "dark_theme"){
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
    else if(currThemeForAll == "light_theme"){
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
}

function searchOnPage() {

    var input, filter, ul, li, a, i;
    input = document.getElementById("textToFind");
    filter = input.value.toUpperCase();
    ul = document.getElementById("menuSearch");
    li = ul.getElementsByTagName("li");
  
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



let tooltipElem;
document.onmouseover = function(event) {
    let focus = event.target;

    let tooltipHtml = focus.dataset.tooltip;
    if (!tooltipHtml) return;

    tooltipElem = document.createElement('div');
    tooltipElem.className = 'tooltip';
    tooltipElem.innerHTML = tooltipHtml;
    document.body.append(tooltipElem);

    let coords = focus.getBoundingClientRect();

    let right = coords.right - (focus.offsetWidth - tooltipElem.offsetWidth) / 2;
    if (right < 0) { right = 0; } // не заезжать за левый край окна

    let top = coords.top + tooltipElem.offsetHeight - 30;
    if (top < 0) { // если подсказка не помещается сверху, то отображать её снизу
        top = coords.top + focus.offsetHeight;
    }
    tooltipElem.style.left = right + 'px';
    tooltipElem.style.top = top + 'px';
};

document.onmouseout = function(e) {
    if (tooltipElem) {
        tooltipElem.remove();
        tooltipElem = null;
    }
};



// модальные окна
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
    anchorsChoice1.href = choiceYes;
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