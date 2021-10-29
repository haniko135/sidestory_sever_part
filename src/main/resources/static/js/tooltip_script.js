let tooltipElem;
document.onmouseover = function(event) {
    let focus = event.target;

    let tooltipHtml = focus.dataset.tooltip;
    if (!tooltipHtml) return;

    tooltipElem = document.createElement('div');
    tooltipElem.style.zIndex = 10;
    tooltipElem.className = 'tooltip';
    tooltipElem.innerHTML = tooltipHtml;
    document.body.append(tooltipElem);

    let coords = focus.getBoundingClientRect();

    let left = coords.left + (focus.offsetWidth - tooltipElem.offsetWidth) / 2;
    if (left < 0) { left = 0; } // не заезжать за левый край окна

    let top = coords.top - tooltipElem.offsetHeight;
    if (top < 0) { // если подсказка не помещается сверху, то отображать её снизу
        top = coords.top - focus.offsetHeight + 60;
    }
    tooltipElem.style.left = left + 'px';
    tooltipElem.style.top = top + 'px';
};

document.onmouseout = function(e) {
    if (tooltipElem) {
        tooltipElem.remove();
        tooltipElem = null;
    }
};