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
select v.id_plantation,p.id_parcelle,v.id_plante,v.nom_plante,v.rendement, (p.longueur*p.largeur) as surfacetotale, (p.longueur*p.largeur)*v.rendement as recolte,v.dateaction
from v_planter_plante as v 
join parcelle as p on v.id_parcelle=p.id_parcelle;

select p.id_parcelle,p.longueur,p.largeur
from parcelle as p
join proprietaire as pr on p.id_terrain=pr.id_terrain;