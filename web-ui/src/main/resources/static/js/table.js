let tableSize = 9;

function generateTable(n) {
    let content = "";
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
            content += column;
        }
        content += "</tr>";
    }
    document.getElementById('table').innerHTML = content;
    generateClosedCellValues(n);
}

function generateClosedCellValues(n){
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
