
var pageSize    = 10;
var currentPage = 0;
var page = 0;

function clearTable(table_Id){
    let table = document.getElementById(table_Id);
    while(table.rows.length>1){
    table.deleteRow(table.rows.length-1);
    }
}
    function goToPage(newpage) {
    clearTable("students_table");
    page = newpage;
    var get_post = "GET";
    var url = "http://localhost:8080/login_war_exploded/get-all-students"
    var sync = false;
    out(callBack2,get_post,url,sync)
}

    function getAllStudents() {
        clearTable("students_table");
        //let xhttp = new XMLHttpRequest();
        var get_post = "GET";
        var url = "http://localhost:8080/login_war_exploded/get-all-students"
        var sync = false;
        out(callBack1,get_post,url,sync)
    }

var callBack1 = function (status, response){
    switch(status) {
        case 200:
            let students = JSON.parse(response);
            let table = document.getElementById("students_table");
            students.forEach(student => {
                let row = table.insertRow(table.rows.length);
                let cell = null;
                cell = row.insertCell(0);
                cell.innerHTML = student['firstName'];
                cell = row.insertCell(1);
                cell.innerHTML = student['lastName'];
            });
            break;
        default:
            console.log("Errore: " + status + " - " /*+ this.statusText*/);
            break;
    }
}

var callBack2 = function (status,response){
    switch (status) {
        case 200:
            let students = JSON.parse(response);
            let table = document.getElementById("students_table");
            if(page <= 1){
                document.getElementById("prevB").disabled = true;
            }
            else{document.getElementById("prevB").disabled = false;}
            if(students.length%pageSize === 0 && page === Math.trunc(students.length/pageSize)){
                document.getElementById("nextB").disabled = true;
            }
            else if(students.length%pageSize !== 0 && page === Math.trunc(students.length/pageSize)+1){
                document.getElementById("nextB").disabled = true;
            }else {
                document.getElementById("nextB").disabled = false;
            }

            currentPage=page;

            for (let i = (currentPage - 1) * pageSize; i < currentPage * pageSize; i++) {
                let row = table.insertRow(table.rows.length);
                let cell = null;
                cell = row.insertCell(0);
                cell.innerHTML = students[i]['firstName'];
                cell = row.insertCell(1);
                cell.innerHTML = students[i]['lastName'];
            }
            break;
        default:
            console.log("Errore: " + this.status + " - " + this.statusText);
            break;
    }

}

function out(callBack,get_post,url,sync){
    if(!callBack || typeof callBack !== "function"){
        console.log("Errore! Non esiste niente");
        alert("Errore! Non esiste niente");
    }
    let xhttp = new XMLHttpRequest();
    xhttp.onload = function() {callBack(this.status,this.response);}
    xhttp.open(get_post.toUpperCase(get_post), url, sync);
    xhttp.send();

}
