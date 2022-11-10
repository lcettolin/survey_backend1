<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Liste studenti</title>
</head>
<body>
<h1 style="text-align:center"><%= "Liste studenti" %>
</h1>
<br/>
<p>Viene fornita la lista di studenti da tre diverse sorgenti: da DataBase, da File
    o da lista presalvata in memoria rispettivamente.</p>
<br/>
<h2>Selezionare sorgente:</h2>
<br/>
<a href="servlet-DB">Servlet DataBase</a>
<br/>
<p> Questa via porta a una pagina i cui valori sono presi da un DataBase.</p>
<br/>
<a href="servlet-File">Servlet File</a>
<br/>
<p> Questa via porta a una pagina i cui valori sono presi da un File che deve però essere caricato
(non lo so fare dal Browser, spiace).</p>
<br/>
<a href="servlet-Array">Servlet Array</a>
<p> Questa via porta a una pagina i cui valori sono presi da una lista pre-memorizzata. Si assicura che il metodo
di scansione funzioni correttamente.</p>
</body>
</html>