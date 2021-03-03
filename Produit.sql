DROP TABLE PRODUIT;


create table PRODUIT
(
	id int
		constraint PRODUIT_pk
			primary key,
	nom varchar not null,
	prix_unitaire_HT double not null,
	taux_tva double not null,
	quantite int not null
);



create unique index PRODUIT_nom_uindex
	on PRODUIT (nom);

