var error = false;

function GenerateGivenNumbers(n){
    for(i=0;i<18;i++){
        x = Math.floor(Math.random() * (n - 0) + 0);
        y = Math.floor(Math.random() * (n - 0) + 0);
        id = x.toString() + y.toString();
        num = Math.floor(Math.random() * (10 - 1) + 1);

        document.getElementById(id).classList.add('givenNumber');
        document.getElementById(id).setAttribute('disabled', true);
        document.getElementById(id).value = num;
    }
}

function GenerateTable(n){
    table = "";
    for(i=0;i<n;i++){
        if(i < 3){
            cell = 1;
        }
        if( i>=3 && i < 6){
            cell = 4;
        }
        if(i >= 6){
            cell = 7;
        }

        for(j=0;j<n;j++){
            id = i.toString() + j.toString();
            column = "<td><input type='text' x='"+(i+1)+"' y='"+(j+1)+"' id='"+id+"' belongsTo='"+cell+"' class= '";
            if(j==2 || j==5){
                column += "boldBorderCol ";
                cell++;

            }
            if(i==2 || i==5){
                column += "boldBorderRow";
            }
            
            column += "' onchange='check(event)'></td>";
            table += column;
        }
        table += "</tr>";
    }
    document.getElementById('table').innerHTML = table;

    GenerateGivenNumbers(n);
}

function check(e){
    thisCell = e.target;
    thisCellsValue = e.target.value;
    x = e.target.attributes[1].value;
    y = e.target.attributes[2].value;

    parentCell = e.target.attributes[4].value
    Xcells = [];
    Ycells = [];
    siblingCells = [];
    allCells = document.getElementsByTagName('input');

    for(i=0;i<81;i++){
        if(allCells[i].attributes[1].value == x && allCells[i] !== thisCell){
            Xcells.push(allCells[i]);
        }
    }
    for(i=0;i<81;i++){
        if(allCells[i].attributes[2].value == y && allCells[i] !== thisCell){
            Ycells.push(allCells[i]);
        }
    }
    for(i=0;i<81;i++){
        if(allCells[i].attributes[4].value == parentCell && allCells[i] !== thisCell){
            siblingCells.push(allCells[i]);
        }
    }

    for(i=0;i<Xcells.length;i++){
        if(Xcells[i].value == thisCellsValue){
            console.log(1);
            Xcells[i].classList.add('error');
            thisCell.classList.add('error');
            break;
        }
        else if(Xcells[i].value != thisCellsValue && thisCell.classList.contains('error')){
            Xcells[i].classList.remove('error');
            thisCell.classList.remove('error');
        }
    }

    for(i=0;i<Ycells.length;i++){
        if(Ycells[i].value == thisCellsValue){
            console.log(1);
            Ycells[i].classList.add('error');
            thisCell.classList.add('error');
            break;
        }
        else if(Ycells[i].value != thisCellsValue && thisCell.classList.contains('error')){
            Ycells[i].classList.remove('error');
            thisCell.classList.remove('error');
        }
    }

    repeatsinX = false;
    repeatsinY = false;
    repeatsinParent = false;

    for(i=0;i<8;i++){
        if(Xcells[i].classList.contains('error')){
            for(j=0;j<Xcells.length;j++){
                if (Xcells[i].value == Xcells[j].value && Xcells[j] !== Xcells[i]){
                    console.log('ragaca');
                    repeatsinX = true;
                    break;
                }
            }
        }
        if(Ycells[i].classList.contains('error')){
            for(j=0;j<Ycells.length;j++){
                if (Ycells[i].value == Ycells[j].value && Ycells[j] !== Ycells[i]){
                    console.log('ragaca');
                    repeatsinY = true;
                    break;
                }
            }
        }
        if(!repeatsinX){
            Xcells[i].classList.remove('error');
        }
    }

}