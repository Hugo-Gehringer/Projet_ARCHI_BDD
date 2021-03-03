DROP TABLE PRODUIT;


create table PRODUIT
(
	id int
		constraint PRODUIT_pk
			primary key,
	nom varchar(256) not null,
	prix_unitaire_HT double precision not null,
	quantite int not null
)
/

create unique index PRODUIT_nom_uindex
	on PRODUIT (nom)
/

