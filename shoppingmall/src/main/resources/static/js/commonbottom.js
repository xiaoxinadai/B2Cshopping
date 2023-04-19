$(function (){
    const myDiv = document.getElementById('myDiv');
    const hasScrollbar = window.innerWidth > document.documentElement.clientWidth;

    if (hasScrollbar) {
        const scrollbarHeight = window.innerHeight - document.documentElement.clientHeight;
        myDiv.style.bottom = `${scrollbarHeight}px`;
    } else {
        myDiv.style.bottom = '0';
    }
})