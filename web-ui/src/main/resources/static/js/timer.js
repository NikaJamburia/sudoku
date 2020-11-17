let seconds = 0;
let minutes = 0;
let hours = 0;
let timerOn = false;
let interval;

timer = document.getElementById('timer');

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
    timer.innerHTML = timerValue(hours) +":"+ timerValue(minutes) +":"+ timerValue(seconds);
}
function startTimer(timeString){
    hours = timeString.substr(0, 2);
    minutes = timeString.substr(3, 2);
    seconds = timeString.substr(6, 2);

    if (!timerOn) {
        interval = setInterval(addSecond, 1000);
        timerOn = true;
    }
}

function pauseTimer() {
    timerOn = false;
    clearInterval(interval)
}

function resumeTimer() {
    if (!timerOn) {
        interval = setInterval(addSecond, 1000)
    }
}

function clearTimer(newValue) {
    seconds = 0;
    minutes = 0;
    hours = 0;
    timerOn = false;
    clearInterval(interval);
    timer.innerHTML = newValue;
}

function timerValue(value) {
    let valueString = value.toString();
    return (valueString.length === 1 ? "0" + valueString :  valueString)
}