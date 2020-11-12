function parseTableAsHtml(tableState) {
    let content = "";

    console.log(tableState);
    console.log(groupByRows(tableState.cells));

    let rows = groupByRows(tableState.cells);

    for (let row of rows.values()){
        console.log(row);
        content += "<tr>";
        row.forEach(cell => {
            const id = cell.coordinateX + "|" + cell.coordinateY;
            const styles = cellStyles(cell);
            const value = cell.cellIsOpen ? "" : cell.value
            content += `<td>
                            <input 
                                type='text' 
                                x='${ cell.coordinateX }' 
                                y='${ cell.coordinateY} ' 
                                value='${ value }'
                                id='${id}' 
                                ${ cell.cellIsOpen ? '' : 'disabled' }
                                class='${ styles }'
                            >
                        </td>`
        });
        content += "</tr>";
    }

    console.log(content);
    return content
}

function cellStyles(cell) {
    let style = "";
    if(cell.coordinateX == 3 || cell.coordinateX == 6){
        style += "boldBorderCol ";
    }
    if(cell.coordinateY == 3 || cell.coordinateY == 6){
        style += "boldBorderRow ";
    }
    if(!cell.cellIsOpen){
        style += "closedCell ";
    }
    return style;
}

function groupByRows(cells) {
    return  cells.reduce((rowsMap, cell) => {
        const y = cell.coordinateY;
        if (typeof rowsMap.get(y) != 'undefined') {
            rowsMap.get(y).push(cell);
        } else {
            rowsMap.set(y, [cell]);
        }
        return rowsMap;
    }, new Map());
}
