create database cloud; 
--encore sujet à d'autres modifications
--table referencement de tout les propriétaires
create table proprietaire 
(
    id_proprietaire serial primary key,
    nom varchar(150),
    mail varchar(150),
    mdp varchar(20) ,
    dtn date,
    corbeille int default 0 
);
insert into proprietaire (nom,mail,mdp,dtn) values ('alice','alice@gmail.com','mdp','2002-10-02');
--table referencement de tout les terrain corbeille (0 inserer) (1 valider) (2 supprimer)
create table terrain(
    id_terrain serial primary key,
    id_proprietaire int,
    desc_terrain TEXT,
    coord_location varchar(40),
    longueur int,
    largeur int,
    corbeille int default 0 
);

insert into terrain (id_proprietaire,desc_terrain,coord_location,surface) values (1,'description','coordessai',10);

--table referencement des photos des terrains
create table photos_terrain(
    id_photos_terrain serial primary key,
    id_terrain int,
    photo varchar(50),
    FOREIGN KEY(id_terrain) 
    REFERENCES terrain(id_terrain)
);

--table referencement des différentes categories de culture
create table categorie_culture(
    id_categorie_culture serial primary key,
    nomcategorie varchar(50),
    corbeille int default 0
);
alter table categorie_culture add column corbeille int default 0;

--table referencement des différent types de plantes appartenant à la categorie
create table plante(
    id_plante serial primary key,
    id_categorie_culture int,
    nom_plante varchar(50),
    prixachat float,
    prixvente float,
    sprite_plante varchar(30),
    placeingamemaker int,
    corbeille int default 0,
    FOREIGN KEY(id_categorie_culture) 
    REFERENCES categorie_culture(id_categorie_culture)
);

--table referencement de toutes les parcelles d'un terrain
--rendement quantité de culture récoltée par cette parcelle
create table parcelle(
    id_parcelle serial primary key,
    id_terrain int,
    longueur int,
    largeur int,
    rendement float,
    FOREIGN KEY(id_terrain) 
    REFERENCES terrain(id_terrain)
);
insert into parcelle (id_terrain,rendement) values (1,200);

--table referencement des categories qu'une parcelle peut accepter 
create table categories_parcelle(
    id_categories_parcelle serial primary key,
    id_parcelle int,
    id_plante int,
    corbeille int default 0,
    FOREIGN KEY(id_parcelle) 
    REFERENCES parcelle(id_parcelle),
    FOREIGN KEY (id_plante)
    REFERENCES plante(id_plante)
);

--types d'action planter/recolter
--séparer en 2 tables recolte et achat

--rendement en kg par metre carré
-- create table rendement(

--     id_rendement serial primary key,
--     id_plante int,
--     rendement float,
--     daterendement timestamp,
--     FOREIGN KEY (id_plante)
--     REFERENCES plante(id_plante)

-- );

create table planter (

    id_plantation serial primary key,
    id_parcelle int,
    id_plante int,
    dateaction timestamp,
    etat int,  
    FOREIGN KEY(id_parcelle) 
    REFERENCES parcelle(id_parcelle),
    FOREIGN KEY (id_plante)
    REFERENCES plante(id_plante)

);

--int etat => 0 = non récolté , 1 = récolté

create table recolte(
    id_recolte serial primary key,
    id_parcelle int,
    id_plantation int,
    dateaction timestamp,
    rendement float,
    FOREIGN KEY(id_parcelle) 
    REFERENCES parcelle(id_parcelle),
    FOREIGN KEY (id_plantation)
    REFERENCES planter(id_plantation)
);

create table vente(
    id_vente serial primary key,
    id_recolte int,
    id_plante int,
    dateaction timestamp,
    FOREIGN KEY(id_recolte) 
    REFERENCES recolte(id_recolte),
    FOREIGN KEY (id_plante)
    REFERENCES plante(id_plante)
);

-- create table historique

--view terrain et parcelle
create or replace view v_terrain_parcelle as
select p.id_parcelle,t.id_terrain,p.longueur,p.largeur,t.id_proprietaire,t.corbeille as corbeilleterrain,p.corbeille as corbeilleparcelle
from parcelle as p
join terrain as t on p.id_terrain=t.id_terrain;

--view terrain/parcelle/proprietaire
create or replace view v_parcelle_proprietaire as 
select p.id as idproprietaire,p.nom,p.mail,v.id_parcelle,v.id_terrain,v.longueur,v.largeur,v.corbeilleterrain,v.corbeilleparcelle
from v_terrain_parcelle as v
join proprietaire as p on v.id_proprietaire=p.id;

--view categories_parcelle/plante

create or replace view v_categorie_parcelle_plante as
select p.id_categories_parcelle,p.id_parcelle,pl.id_plante,pl.nom_plante,pl.prixachat,pl.prixvente,pl.corbeille as corbeilleplante,p.corbeille as corbeilleparcelle
from categories_parcelle as p
join plante as pl on p.id_plante=pl.id_plante;

--grosse view ^^
create or replace view v_alldata as 
select vp.id_parcelle,vpl.id_plante,vpl.nom_plante,vp.idproprietaire,vp.nom,vp.mail,vp.id_terrain
from v_categorie_parcelle_plante as vpl
join v_parcelle_proprietaire as vp on vpl.id_parcelle = vp.id_parcelle;

create or replace view v_all_concat as
SELECT
    id_parcelle,
    STRING_AGG(id_plante::TEXT, ', ') AS id_plantes,
    STRING_AGG(nom_plante, ', ') AS nom_plantes,
    idproprietaire,
    nom,
    mail,
    id_terrain
FROM
    v_alldata
GROUP BY
    id_parcelle, idproprietaire, nom, mail,id_terrain;

--requête filtrage parcelle
SELECT * FROM v_all_concat WHERE id_plantes LIKE '%2%';

--concat + terrain

select v.id_parcelle,v.id_plantes,v.nom_plantes,v.idproprietaire,v.nom,v.mail,t.id_terrain
from v_all_concat as v
join terrain as t on v.id_parcelle=t.id_parcelle;

--objectif = view simulation 

--view planter / plante
create or replace view v_planter_plante as 
select plt.id_plantation,plt.id_parcelle,p.id_plante,p.nom_plante,p.rendement,plt.dateaction
from planter as plt 
join plante as p on plt.id_plante=p.id_plante;

create or replace view simulations as
select v.id_plantation,p.id_parcelle,v.id_plante,v.nom_plante,v.rendement, (p.longueur*p.largeur) as surfacetotale, (p.longueur*p.largeur)*v.rendement as recolte,v.dateaction,p.id_terrain
from v_planter_plante as v 
join parcelle as p on v.id_parcelle=p.id_parcelle;

--view pour totale surface des terrains du proprietaire

create or replace view v_surface as 
select id_terrain,sum(longueur*largeur) as surface from parcelle group by id_terrain;

create or replace view v_all_surface as
select p.idproprietaire,p.nom,p.id_terrain,s.surface
from v_parcelle_proprietaire as p
join v_surface as s on p.id_terrain=s.id_terrain;

--view pour totale de terrain possèdé

create or replace view v_count_terrain as 
select count(id_terrain) as nbterrain, id_proprietaire from terrain where corbeille=1 group by id_proprietaire;

--view total recolte effectués

create or replace view v_recoltes as 
select sum(rendement) as recoltes, id_parcelle from recolte group by id_parcelle;

create or replace view v_recoltes_proprio as 
select p.id_parcelle,p.idproprietaire,r.recoltes,p.nom
from v_parcelle_proprietaire as p
join v_recoltes as r on p.id_parcelle = r.id_parcelle;

create or replace view v_total_recolte as
select sum(recoltes) as recoltes , idproprietaire from v_recoltes_proprio group by idproprietaire;

-----

select p.id_parcelle,p.longueur,p.largeur
from parcelle as p
join proprietaire as pr on p.id_terrain=pr.id_terrain;


---stats categorie de plante
SELECT 
    nom_plante,
    COUNT(*) AS nombre_utilisations,
    ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM vue_planter_plante_parcelle_terrain WHERE id_proprietaire = 1), 2) AS pourcentage_utilisation
FROM 
    vue_planter_plante_parcelle_terrain
WHERE 
    id_proprietaire = 1
GROUP BY 
    nom_plante;

--stats de recoltes 

CREATE OR REPLACE VIEW vue_recolte_planter_parcelle_plante AS
SELECT 
    r.id_recolte, 
    p.id_plantation, 
    p.id_parcelle AS id_parcelle_plantation, 
    pl.id_plante, 
    pa.id_parcelle AS id_parcelle_recolte,
    pa.rendement AS rendement_parcelle,
    r.dateaction
FROM 
    recolte r
JOIN 
    planter p ON r.id_plantation = p.id_plantation
JOIN 
    parcelle pa ON r.id_parcelle = pa.id_parcelle
JOIN
    plante pl ON p.id_plante = pl.id_plante;


CREATE VIEW v_combine_recolte_proprietaire AS
SELECT 
    r.id_recolte, 
    r.rendement_parcelle AS rendement, 
    r.dateaction, 
    p.idproprietaire, 
    p.nom
FROM 
    vue_recolte_planter_parcelle_plante r
JOIN 
    v_parcelle_proprietaire p ON r.id_parcelle_recolte = p.id_parcelle;

-----

SELECT 
    TO_CHAR(DATE_TRUNC('month', dateaction), 'Month') AS mois,
    SUM(rendement) AS total_recolte
FROM 
    v_combine_recolte_proprietaire
WHERE 
    idproprietaire = 4
GROUP BY 
    DATE_TRUNC('month', dateaction)
ORDER BY 
    DATE_TRUNC('month', dateaction);


WITH all_month AS (
    SELECT generate_series(
        DATE_TRUNC('year', CURRENT_DATE),
        DATE_TRUNC('year', CURRENT_DATE) + INTERVAL '1 year' - INTERVAL '1 day',
        INTERVAL '1 month'
    ) AS month
)
SELECT 
    TO_CHAR(am.month, 'Month') AS mois,
    COALESCE(SUM(v.rendement), 0) AS total_recolte
FROM 
    all_month am
LEFT JOIN 
    v_combine_recolte_proprietaire v ON DATE_TRUNC('month', v.dateaction) = am.month
    AND v.idproprietaire = 1
GROUP BY 
    am.month
ORDER BY 
    am.month;





