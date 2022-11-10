
function out(callBack,get_post,url,sync){
    if(!callBack || typeof callBack !== "function"){
        console.log("Errore! Non esiste niente");
        alert("Errore! Non esiste niente");
    }
    let xhttp = new XMLHttpRequest();
    xhttp.open(get_post.toUpperCase(get_post), url, sync);
    let id = document.getElementById("id").value;
    let name = document.getElementById("name").value;
    let surname = document.getElementById("surname").value;
    let parameters = "id="+id+"&name="+name+"&surname="+surname;
    xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhttp.onload = function() {callBack(this.status,this.response);}
    xhttp.send(parameters);
}

var callBack1 = function (status, response){
    switch(status) {
        case 200:
            let crudists = JSON.parse(response);
            let table = document.getElementById("crudisti");
            crudists.forEach(crudist => {
                let row = table.insertRow(table.rows.length);
                let cell = null;
                cell = row.insertCell(0);
                cell.innerHTML = crudist['id'];
                cell = row.insertCell(1);
                cell.innerHTML = crudist['name'];
                cell = row.insertCell(2);
                cell.innerHTML = crudist['surname'];
            });
            break;
        default:
            console.log("Errore: " + status + " - " /*+ this.statusText*/);
            break;
    }
}

var callBackCreate = function (status,response){
    switch(status) {
        case 200:
            alert('Element added!');
            break;
        default:
            console.log("Errore: " + status + " - " /*+ this.statusText*/);
            break;
    }

}
var callBackUpdate = function (status,response){
    switch(status) {
        case 200:
            alert('Element updated!');
            break;
        default:
            console.log("Errore: " + status + " - " /*+ this.statusText*/);
            break;
    }
}
var callBackDelete = function (status,response){
    switch(status) {
        case 200:
            break;
        default:
            console.log("Errore: " + status + " - " /*+ this.statusText*/);
            break;
    }
}
function outNoPar(callBack,get_post,url,sync){
    if(!callBack || typeof callBack !== "function"){
        console.log("Errore! Non esiste niente");
        alert("Errore! Non esiste niente");
    }
    let xhttp = new XMLHttpRequest();
    xhttp.open(get_post.toUpperCase(get_post), url, sync);
    xhttp.onload = function() {callBack(this.status,this.response);}
    xhttp.send();
}

function outOnePar(callBack,get_post,url,sync){
    if(!callBack || typeof callBack !== "function"){
        console.log("Errore! Non esiste niente");
        alert("Errore! Non esiste niente");
    }
    let xhttp = new XMLHttpRequest();
    xhttp.open(get_post.toUpperCase(get_post), url, sync);
    let id = document.getElementById("id").value;
    let parameters = "id="+id;
    xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhttp.onload = function() {callBack(this.status,this.response);}
    xhttp.send(parameters);
}


var pageSize    = 5;
var currentPage = 0;
var page = 0;

var callBack2 = function (status,response){
    switch (status) {
        case 200:
            let crudists = JSON.parse(response);
            let table = document.getElementById("crudisti");
            if(page <= 1){
                document.getElementById("prevB").disabled = true;
            }
            else{document.getElementById("prevB").disabled = false;}
            if(crudists.length%pageSize === 0 && page === Math.trunc(crudists.length/pageSize)){
                document.getElementById("nextB").disabled = true;
            }
            else if(crudists.length%pageSize !== 0 && page === Math.trunc(crudists.length/pageSize)+1){
                document.getElementById("nextB").disabled = true;
            }else {
                document.getElementById("nextB").disabled = false;
            }

            currentPage=page;

            for (let i = (currentPage - 1) * pageSize; i < currentPage * pageSize; i++) {
                let row = table.insertRow(table.rows.length);
                let cell = null;
                cell = row.insertCell(0);
                cell.innerHTML = crudists[i]['id'];
                cell = row.insertCell(1);
                cell.innerHTML = crudists[i]['name'];
                cell = row.insertCell(2);
                cell.innerHTML = crudists[i]['surname'];
            }
            break;
        default:
            console.log("Errore: " + this.status + " - " + this.statusText);
            break;
    }

}