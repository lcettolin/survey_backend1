/*select nome from sale where citta='pisa';*/

/*select titolo from film where regista ='fellini' and annoProduzione > 1960;*/

/*select regista from film 
where ((nazionalita = 'francese' or nazionalita = 'italiana') and annoProduzione >1960) and genere = 'fantascienza';*/

/*select titolo,nome from film,attori,recita
where attori.nazionalita ='inglese' and recita.codFilm_Rc = film.codFilm and recita.codAttore_Rc = attori.codAttore ;*/

/*select titolo,nome from film,sale,proiezioni
where  sale.citta = 'pisa' and proiezioni.dataProiezione between '2015-01-01' and '2015-01-30'; */

/*select film.titolo, sum(proiezioni.incasso) from film,proiezioni
where proiezioni.codFilm_Prz = film.codFilm and film.genere ='storico'
group by film.titolo 
having min(proiezioni.dataProiezione) > '2020-01-01';*/


/*insert into proiezioni (codProiezione,codFilm_Prz,codSala_Prz,incasso,dataProiezione)
values (12,10,'s1',110,'2020-01-02');*/


/*ESPORTAZIONE FILE CSV
SELECT codFilm,titolo, annoProduzione,nazionalita,regista,genere
INTO OUTFILE '\User\lorenzo.cettolin\Desktop\film.csv'
FIELDS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
ESCAPED BY '\\'
LINES TERMINATED BY '\n'
FROM film*/

/*FILM CON IL MAGGIORE INCASSO
select film.titolo,film.genere, proiezioni.incasso  from film, proiezioni 
where film.codFilm  = proiezioni.codFilm_Prz 
	and proiezioni.incasso = (
		select max(proiezioni.incasso)
		from proiezioni
	);*/

/*Film con più attori 
select film.titolo, attori.nome from film, attori, recita
where attori.codAttore =recita.codAttore_Rc and film.codFilm = recita.codFilm_Rc*/

/*Film ordinati per frequenza di proiezione
select titolo,genere , count(proiezioni.codFilm_Prz)
from film,proiezioni
where film.codFilm  = proiezioni.codFilm_Prz
group by film.titolo
order by count(proiezioni.codFilm_Prz) desc
limit 1*/

/*select film.titolo, sum(proiezioni.incasso) as incasso_tot, avg(proiezioni.incasso) as incasso_medio, count(proiezioni.codProiezione) as num_proiezioni, max(proiezioni.dataProiezione) as ultima_proiez
from proiezioni, film
where proiezioni.codFilm_Prz  = film.codFilm 
group by proiezioni.codFilm_Prz 
order by incasso_tot desc
limit 3*/

/*select titolo, max(sub.totale_incassi)
from(
	select titolo, sum(proiezioni.incasso) as totale_incassi 
	from film, proiezioni where film.codFilm =proiezioni.codFilm_Prz 
	group by proiezioni.codFilm_Prz 
) as sub
order by totale_incassi desc*/
 
select *
from (
      select film.titolo as titolo, sum(proiezioni.incasso) as tot_incassi
      from proiezioni, film
      where proiezioni.codFilm_Prz  = film.codFilm 
      group by proiezioni.codFilm_Prz 
       ) as sub
where sub.tot_incassi = (
       select max(sub.tot_incassi) as guadagno_max
       from (
             select film.titolo as titolo, sum(proiezioni.incasso) as tot_incassi
             from proiezioni, film
             where proiezioni.codFilm_Prz  = film.codFilm 
             group by proiezioni.codFilm_Prz 
       ) as sub
)


