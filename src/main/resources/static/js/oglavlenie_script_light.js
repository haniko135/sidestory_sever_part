function openNav() {
    if(document.documentElement.clientWidth > 700 && document.documentElement.clientWidth < 1024){
        document.getElementById("mySidenav").style.width = "100%";
        document.body.style.backgroundColor = "rgba(157,157,157,0.7)";
        document.getElementById("homeIcon").style.backgroundColor = "rgba(157, 157, 157, 0.9)";
        document.getElementById("mySidenav").style.zIndex="1030";
        document.getElementById("mySidenav").style.background="rgba(157,157,157)";
    }
    else if(document.documentElement.clientWidth <= 700){
        document.getElementById("mySidenav").style.width = "100%";
        document.getElementById("homeIcon").style.backgroundColor = "rgba(157, 157, 157, 0.9)";
        document.getElementById("mySidenav").style.zIndex="1030";
        document.getElementById("mySidenav").style.background="rgba(157,157,157)";
    }
    else if(document.documentElement.clientWidth >= 1024){
        document.getElementById("mySidenav").style.width = "25%";
        document.body.style.backgroundColor = "rgba(157,157,157,0.7)";
        document.getElementById("homeIcon").style.backgroundColor = "rgba(157, 157, 157, 0.9)";
        document.getElementById("mySidenav").style.zIndex="1030";
        document.getElementById("mySidenav").style.background="rgba(157,157,157)";
    }
}

function closeNav(){
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("searchIcon").style.backgroundColor = "";
    document.getElementById("homeIcon").style.backgroundColor = "";
    document.getElementById("menuIcon").style.backgroundColor = "";
    document.body.style.backgroundColor = "";
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

function showModalInfo(chapterName){
    let base = document.createElement("div");
    base.className = "modal fade";
    base.id="exampleModal";
    base.tabIndex=-1;
    base.ariaLabeledBy="exampleModalLabel";
    base.ariaHidden="true";
    document.body.append(base);

    let baseDialog = document.createElement("div");
    baseDialog.className="modal-dialog";
    base.append(baseDialog);

    let baseContent = document.createElement("div");
    baseContent.className="modal-content";
    baseDialog.append(baseContent);

    let baseHeader = document.createElement("div");
    baseHeader.className="modal-header";
    baseContent.append(baseHeader);

    let baseTitle = document.createElement("h4");
    baseTitle.className="modal-title";
    baseTitle.id="exampleModalLabel";
    baseTitle.innerHTML="Выберете игрового персонажа";
    baseHeader.append(baseTitle);

    let closeButton = document.createElement("button");
    closeButton.type="button";
    closeButton.className="close";
    closeButton.dataDismiss="modal";
    closeButton.ariaLabel="Close";
    baseHeader.append(closeButton);

    let closeButtonIcon = document.createElement("span");
    closeButtonIcon.ariaHidden="true";
    closeButtonIcon.innerHTML="&times";
    closeButton.append(closeButtonIcon);

    let baseBody = document.createElement("div");
    baseBody.className="modal-body";
    baseContent.append(baseBody);

    let spanBodyTitle=document.createElement("span");
    spanBodyTitle.innerHTML=chapterName;
    baseBody.append(spanBodyTitle);

    let baseFooter = document.createElement("div");
    baseFooter.className="modal-footer";
    baseBody.append(baseFooter);

    let footerButton = document.createElement("button");
    footerButton.type="button";
    footerButton.className="btn btn-secondary";
    footerButton.dataDismiss="modal";
    footerButton.innerHTML="Закрыть";
    baseFooter.append(footerButton);
}