let imgElement;
document.onmouseover = function (event){   
    let focus = event.target;

    let imgtipHTML = focus.dataset.imgtip;
    if (!imgtipHTML) return;

    imgElement = document.createElement('img');
    imgElement.style.zIndex = 0;
    imgElement.style.position = 'absolute';

    let positionHTML = focus.dataset.position;

    if(imgtipHTML =='SQQ'){
        imgElement.src = "../../../img/system/Чиби_Цинцю.png";
        imgElement.style.width = '20%';
        imgElement.style.height = '40%';
    }
    else if (imgtipHTML == 'LBH'){
        imgElement.src = "../../../img/system/Чиби_Бинхэ .png";
        imgElement.style.width = '17%';
        imgElement.style.height = '40%';
    }
    else if(imgtipHTML == 'YQY'){
        imgElement.src = "../../../img/system/Юэ_Цинъюань.png";
        //добавить проверку на размер экрана
        imgElement.style.width = '12%';
        imgElement.style.height = '48%';
    }
    document.body.append(imgElement);
    let coords = focus.getBoundingClientRect();

    if(positionHTML == 'right'){
        let right = coords.right - (focus.offsetWidth + imgElement.offsetWidth);
        if  (right > 0) { right = 0; }
    
        let top = pageYOffset + document.documentElement.clientHeight - imgElement.offsetHeight;
        //if(top > 0){ top = coords.top - coords.bottom;}
        imgElement.style.right = right + 'px';
        imgElement.style.top = top + 'px';
    }
    if(positionHTML == 'left'){
        let left = coords.left - (focus.offsetWidth + imgElement.offsetWidth);
        if  (left < 0) { left = 0; }

        let top = pageYOffset + document.documentElement.clientHeight - imgElement.offsetHeight;
        imgElement.style.left = left + 'px';
        imgElement.style.top = top + 'px';
    }
    
}

document.onmouseout = function (e){
    if(imgElement){
        imgElement.remove();
        imgElement = null;
    }
}