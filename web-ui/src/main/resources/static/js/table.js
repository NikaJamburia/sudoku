let cellClassName = "sudokuCell";

function parseTableAsHtml(tableState) {
    let content = "";
    let rows = groupByRows(tableState.cells);
    let conflictedCells = tableState.conflicts.flatMap(conflict => conflict.conflictedCells);

    for (let row of rows.values()) {
        content += "<tr>";
        row.sort((cell1, cell2) => {
            return cell1.coordinateX - cell2.coordinateX
        })
            .forEach(cell => {
                const id = cell.coordinateX + "|" + cell.coordinateY;
                const styles = cellStyles(cell, tableState);
                const value = cell.value === 0 ? "" : cell.value;
                content += `<td>
                            <input 
                                type='text' 
                                x='${cell.coordinateX}' 
                                y='${cell.coordinateY} ' 
                                value="${value}"
                                maxlength="1"
                                id='${id}' 
                                ${cell.cellIsOpen ? '' : 'disabled'}
                                class='${cellClassName} ${styles} ${ isInConflict(cell, conflictedCells) ? 'conflicted' : '' }'
                            >
                        </td>`
            });
        content += "</tr>";
    }
    return content
}

function cellStyles(cell, tableState) {
    let style = "";
    if (cell.coordinateX == 3 || cell.coordinateX == 6) {
        style += "boldBorderCol ";
    }
    if (cell.coordinateY == 3 || cell.coordinateY == 6) {
        style += "boldBorderRow ";
    }
    if (!cell.cellIsOpen) {
        style += "closedCell ";
    }
    return style;
}

function groupByRows(cells) {
    return cells.reduce((rowsMap, cell) => {
        const y = cell.coordinateY;
        if (typeof rowsMap.get(y) != 'undefined') {
            rowsMap.get(y).push(cell);
        } else {
            rowsMap.set(y, [cell]);
        }
        return rowsMap;
    }, new Map());
}

function fillTable(html, onTableFilled) {
    table.innerHTML = html;
    onTableFilled();
}

function emptyTable() {
    fillTable("<button id='start-Btn' class='btn btn-lg btn-success'>Start New Game</button>", () => {})
}

function registerEventListenersForCells() {
    document.querySelectorAll('.' + cellClassName).forEach(cell => {
        cell.addEventListener('input', evt => {
            let value = evt.target.value;
            let x = evt.target.getAttribute('x');
            let y = evt.target.getAttribute('y');

            if (value === null || value === "") {
                empty(x, y);
            } else {
                fill(value, x, y);
            }
        })
    });
}

function isInConflict(cell, conflicts) {
    return  conflicts.filter(conflictedCell =>
         conflictedCell.coordinateX === cell.coordinateX
        && conflictedCell.coordinateY === cell.coordinateY
    ).length;
}
