use pozoriste_is;

call dodavanjeAdministrativnogRadnika("Pero", "Peric", "1111111111111",
"065056999", "admin", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918","Administrator",@pero);

call dodavanjeBiletara("Simo", "Simic", "2222222222222",
"065333333", "biletar", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918","Biletar",@pero);

call dodavanjeAdministrativnogRadnika('Djordje','Palavestra','1234554321555',
'065198900','djole','8b392d8575e5d482197f24340aba5d1b14eba2cc1007890274ec0f283303cb03','Administrator',@pero);

call dodavanjeBiletara('Milica','Medan','4545456767678',
'065777888','mina','9bb840df7f699d6547f49fd6f9ed17b2d9dd34148f0b01e798d7c51da897ea1a','Biletar',@pero);

insert into scena
values
(0,"Petar Kočić");


insert into repertoar values (0,'2019-03-01');
insert into repertoar values (0,'2019-04-01');
insert into repertoar values (0,'2019-05-01');


insert into igranje
values
('2019-3-1',(select id from scena where scena.nazivScene="Petar Kocic"),(select id from gostujuca_predstava where gostujuca_predstava.naziv="Kafa i cigarete"),null,(select id from repertoar where repertoar.mjesecIGodina='2019-3-1')),
('2019-3-2',(select id from scena where scena.nazivScene="Petar Kocic"),null,(select id from predstava where predstava.naziv="O, kakav divan dan"),(select id from repertoar where repertoar.mjesecIGodina='2019-3-1')),
('2019-3-3',(select id from scena where scena.nazivScene="Petar Kocic"),null,(select id from predstava where predstava.naziv="Slavna Florens"),(select id from repertoar where repertoar.mjesecIGodina='2019-3-1'));
