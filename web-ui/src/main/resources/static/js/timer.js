let seconds = 0;
let minutes = 0;
let hours = 0;

timer = document.getElementById('timer');
timer.addEventListener("render", startTimer);

function addSecond(){
    if (seconds < 60){
        seconds++;
    }
    if (seconds === 60){
        minutes++;
        seconds = 0;
    }
    if (minutes === 60){
        hours++;
        minutes = 0;
    }
    timer.innerHTML = timerValue(hours) +" : "+ timerValue(minutes) +" : "+ timerValue(seconds);
}
function startTimer(){
    setInterval(addSecond, 1000);
}

function timerValue(value) {
    let valueString = value.toString()
    return (valueString.length === 1 ? "0" + valueString :  valueString)
}