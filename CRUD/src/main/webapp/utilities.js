
function clearTable(table_Id){
    let table = document.getElementById(table_Id);
    while(table.rows.length>1){
        table.deleteRow(table.rows.length-1);
    }
}
function goEntry(){
    let parent = document.getElementById('divInput');
    parent.replaceChildren();
    parent.innerHTML += "<p>Please, insert new entry:</p><br>";
    parent.innerHTML += "<input type='text' id='id' placeholder='Id'></input>";
    parent.innerHTML += "<input type='text' id='name' placeholder='Name'></input>";
    parent.innerHTML += "<input type='text' id='surname' placeholder='Surname'> </input>";
    parent.innerHTML += "<button onclick='out(callBackCreate,\"POST\",\"http://localhost:8080/CRUD_war_exploded/createServlet\",false)'>GO</button>";

}

function goUpdate(){
    let parent = document.getElementById('divInput');
    parent.replaceChildren();
    parent.innerHTML += "<p>Please, insert entry:</p><br>";
    parent.innerHTML += "<input type='text' id='id' placeholder='Id'></input>";
    parent.innerHTML += "<input type='text' id='name' placeholder='Name'></input>";
    parent.innerHTML += "<input type='text' id='surname' placeholder='Surname'> </input>";
    parent.innerHTML += "<button onclick='out(callBackUpdate,\"POST\",\"http://localhost:8080/CRUD_war_exploded/updateServlet\",false)'>GO</button>";
    goProRead();
}


function goDelete(){
    let parent = document.getElementById('divInput');
    parent.replaceChildren();
    parent.innerHTML += "<p>Please, insert entry to delete:</p><br>";
    parent.innerHTML += "<input type='text' id='id' placeholder='Id'></input>";
    parent.innerHTML += "<button onclick='doCheck()'>GO</button>"
}

function doCheck(){
    let result = window.confirm("Are you sure?");
    let message = result;
    if(result){
        message = 'Entry deleted';
        outOnePar(callBackDelete,"POST","http://localhost:8080/CRUD_war_exploded/deleteServlet",false)
    }else{
        message = 'Operation aborted';
    }
    goProRead();
    alert(message);


}

function goRead(){
    clearTable("crudisti");
    let parent = document.getElementById('divInput');
    parent.replaceChildren();
    goToPage(1);
    let parent1 = document.getElementById('divButtons');
    parent1.removeAttribute("hidden");
    let parent2 = document.getElementById('table');
    parent2.removeAttribute("hidden");
}

function goToPage(newpage) {
    clearTable("crudisti");
    page = newpage;
    var get_post = "GET";
    var url = "http://localhost:8080/CRUD_war_exploded/readServlet"
    var async = false;
    outNoPar(callBack2,get_post,url,async);
}

function goProRead(){
    clearTable("crudisti");
    goToPage(1);
    let parent1 = document.getElementById('divButtons');
    parent1.removeAttribute("hidden");
    let parent2 = document.getElementById('table');
    parent2.removeAttribute("hidden");
}

