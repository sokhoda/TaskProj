/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     23.06.2016 14:29:23                          */
/*==============================================================*/
drop database if exists cargoCarrier;
create database  cargoCarrier;
use cargoCarrier;

drop table if exists AGENTS;

drop table if exists AG_TYPES;

drop table if exists CARGOS;

drop table if exists CARGO_TREE;

drop table if exists COUNTRIES;

drop table if exists DOCUMENTS;

drop table if exists JOURNAL;

drop table if exists MISC;

drop table if exists REGIONS;

drop table if exists USERS;

drop table if exists USER_TYPES;

/*==============================================================*/
/* Table: AGENTS                                                */
/*==============================================================*/
create table AGENTS
(
   agId                 bigint not null AUTO_INCREMENT,
   agtId                bigint not null,
   agName               varchar(255) not null,
   agAddress            varchar(255),
   agPhone              varchar(50),
   agEmail              varchar(50),
   agPassport           varchar(255),
   agWww                varchar(255),
   agCountry            varchar(50),
   agDate               datetime,
   agTag                varchar(255),
   primary key (agId)
);
create table AG_TYPES
(
   agtId                bigint not null AUTO_INCREMENT,
   agtName              varchar(255) not null,
   agtTag               varchar(255),
   primary key (agtId)
);
/*==============================================================*/
/* Table: CARGOS                                                */
/*==============================================================*/
create table CARGOS
(
   cargId               bigint not null AUTO_INCREMENT,
   cargType             int not null,
   cargName             varchar(255) not null,
   cargTag              varchar(255),
   primary key (cargId)
);

/*==============================================================*/
/* Table: CARGO_TREE                                            */
/*==============================================================*/
create table CARGO_TREE
(
   cgtrId              bigint not null AUTO_INCREMENT,
   cargId               bigint not null,
   cgtrF0               bigint not null,
   cgtrF1               bigint not null,
   cgtrF2               bigint not null,
   primary key (cgtrId)
);

/*==============================================================*/
/* Table: COUNTRIES                                             */
/*==============================================================*/
create table COUNTRIES
(
   cntrId               bigint not null AUTO_INCREMENT,
   cntrName             varchar(50) not null,
   cntrTag              varchar(255),
   primary key (cntrId)
);

/*==============================================================*/
/* Table: DOCUMENTS                                             */
/*==============================================================*/
create table DOCUMENTS
(
   docId                bigint not null AUTO_INCREMENT,
   mcId                 bigint,
   mgrId                bigint not null,
   custId               bigint,
   cargId               bigint,
   docNo                varchar(50),
   docDate              datetime not null,
   docName              varchar(255),
   docSum               float(8,2),
   docStatus            int not null,
   docTag               varchar(255),
   docCargWeight        float(8),
   docCargVolume        float(8),
   docLoadDate          datetime,
   docUnloadDate        datetime,
   docLoadAddress       varchar(255),
   docUnloadAddress     varchar(255),
   docCustContactPersPhone varchar(255),
   docCustContactPersName varchar(255),
   primary key (docId)
);

/*==============================================================*/
/* Table: JOURNAL                                               */
/*==============================================================*/
create table JOURNAL
(
   jId                  bigint not null AUTO_INCREMENT,
   docId                bigint not null,
   mscId                bigint not null,
   primary key (jId)
);

/*==============================================================*/
/* Table: MISC                                                  */
/*==============================================================*/
create table MISC
(
   mscId                bigint not null AUTO_INCREMENT,
   regId                bigint,
   mscNo                int not null,
   mscName              varchar(255) not null,
   mscTag               varchar(255),
   primary key (mscId)
);

/*==============================================================*/
/* Table: REGIONS                                               */
/*==============================================================*/
create table REGIONS
(
   regId                bigint not null AUTO_INCREMENT,
   cntrId               bigint not null,
   regName              varchar(255) not null,
   regTag               varchar(255),
   primary key (regId)
);

/*==============================================================*/
/* Table: USERS                                                 */
/*==============================================================*/
create table USERS
(
   userId               bigint not null AUTO_INCREMENT,
   uTypeId              bigint not null,
   userLogin            varchar(50) not null,
   userPass             varchar(50) not null,
   primary key (userId)
);

/*==============================================================*/
/* Table: USER_TYPES                                            */
/*==============================================================*/
create table USER_TYPES
(
   uTypeId              bigint not null AUTO_INCREMENT,
   uTypeCode            int not null,
   uTypeName            varchar(50) not null,
   primary key (uTypeId)
);
alter table AGENTS add constraint FK_AGENTS_AG_TYPES foreign key (agtId)
      references AG_TYPES (agtId) on delete restrict on update restrict;
      
alter table CARGO_TREE add constraint FK_CARGOS_CARGO_TREE foreign key (cargId)
      references CARGOS (cargId) on delete restrict on update restrict;

alter table DOCUMENTS add constraint FK_DOCUMENTS_AGENTS_CUSTOMER foreign key (mcId)
      references AGENTS (agId) on delete restrict on update restrict;

alter table DOCUMENTS add constraint FK_DOCUMENTS_AGENTS_MANAGER foreign key (mgrId)
      references AGENTS (agId) on delete restrict on update restrict;

alter table DOCUMENTS add constraint FK_DOCUMENTS_AGENTS_MYCOMPANY foreign key (custId)
      references AGENTS (agId) on delete restrict on update restrict;

alter table DOCUMENTS add constraint FK_DOCUMENTS_CARGOS foreign key (cargId)
      references CARGOS (cargId) on delete restrict on update restrict;

alter table JOURNAL add constraint FK_DOCUMENTS_JOURNAL foreign key (docId)
      references DOCUMENTS (docId) on delete restrict on update restrict;

alter table JOURNAL add constraint FK_MISC_JOURNAL foreign key (mscId)
      references MISC (mscId) on delete restrict on update restrict;

alter table MISC add constraint FK_MISC_REGIONS foreign key (regId)
      references REGIONS (regId) on delete restrict on update restrict;

alter table REGIONS add constraint FK_COUNTRIES_REGIONS foreign key (cntrId)
      references COUNTRIES (cntrId) on delete restrict on update restrict;

alter table USERS add constraint FK_UTYPES_USERS foreign key (uTypeId)
      references USER_TYPES (uTypeId) on delete restrict on update restrict;


/*----------------STORED PROCEDURES-----------------------------------*/

DROP PROCEDURE IF EXISTS getRandDATETIME;

DELIMITER $$
CREATE PROCEDURE `getRandDATETIME` (OUT date1 datetime)
BEGIN

declare year					int default 2009;
   declare month 				int default 0;
   declare day					int default 1;
   declare hour					int default 0;
   declare minute 				int default 0;
   declare second 				int default 0;
   
    set year = year + ROUND(RAND() * 7);
      set month = month + ROUND(RAND() * 10);
      set day = day + ROUND(RAND() * 28);
       set hour = hour + ROUND(RAND() * 23);
       set minute = minute + ROUND(RAND() * 58);
       set second = second + ROUND(RAND() * 58);
              
        set date1 = timestamp(Concat(year ,'-', month ,'-', day,' ' , hour,':', minute ,':', second)); 
        
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS getPhoneNumber;
DELIMITER $$

CREATE PROCEDURE `getPhoneNumber` (OUT PhoneNumber VARCHAR(55))
BEGIN
declare myArrayOfValue  VARCHAR(225);
declare maxCount int default 6;
declare count, i int ;
declare STR,res  VARCHAR(255);
SET myArrayOfValue = '095,063,097,050,067,093';

set i = 1;
set count = Round(RAND()* (maxCount-1) + 1);
myloop: WHILE (i <= maxCount)
DO
/*SET @value = ELT(1, myArrayOfValue);*/
    SET STR = SUBSTRING(myArrayOfValue, 1, LOCATE(',', myArrayOfValue)-1);
    SET myArrayOfValue = SUBSTRING(myArrayOfValue, LOCATE(',', myArrayOfValue) + 1);

    if i = count THEN
			if LOCATE(',', myArrayOfValue) = 0 then
				set STR = myArrayOfValue;
            end if;
		LEAVE myloop;
    end if;
		set i = i + 1;
	
END WHILE;
	set PhoneNumber = concat(STR, ' ',
        Round(RAND()* 9), Round(RAND()* 9), Round(RAND()* 9), ' ',
        Round(RAND()* 9), Round(RAND()* 9), ' ',
        Round(RAND()* 9), Round(RAND()* 9));
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS getRandPassport;
DELIMITER $$

CREATE PROCEDURE `getRandPassport` (IN startASCIICode int, IN range1 int, IN cyr int, OUT Passport VARCHAR(55))
BEGIN
declare myArrayOfValue  VARCHAR(225);
declare maxCount int default 6;
declare count, i int ;
declare STR  VARCHAR(255) default '';
SET myArrayOfValue = '095,063,097,050,067,093';

set i = 1;
set count = Round(RAND()* (maxCount-1) + 1);
if cyr > 0 then
		SET STR = Concat (STR, Upper(Cast(char(round(rand()*range1 + startASCIICode)) as Char(1) character set 'koi8u')));
		SET STR = Concat (STR, upper(cast(char(round(rand()*range1 + startASCIICode)) as Char(1) character set 'koi8u')),'-');
else
		SET STR = Concat (STR, upper(Cast(char(round(rand()*range1 + startASCIICode)) as Char(1))));
		SET STR = Concat (STR, upper(cast(char(round(rand()*range1 + startASCIICode)) as Char(1))),'-');
end if;
    SET STR = Concat (STR, round(rand()*9), round(rand()*9), round(rand()*9), '-');
    SET STR = Concat (STR, round(rand()*9), round(rand()*9), round(rand()*9));
    set Passport = STR;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS getRandEmail;
DELIMITER $$

CREATE PROCEDURE `getRandEmail` (OUT email VARCHAR(55))
BEGIN
declare name1, surNames, server1 VARCHAR(225);
declare maxCount int default 6;
declare count, i int ;
declare STR,res  VARCHAR(255);
SET name1 = 'alex,zoya,oksana,mykola,yurij,dmytro';
SET surNames = 'yasinskyi,shapoval,brusko,shwets,boyko,vilchynskyi';
SET server1 = 'gmail,mail,yahoo,ukr,epam,gov';
set i = 1;
set count = Round(RAND()* (maxCount-1) + 1);
myloop: WHILE (i <= maxCount)
DO
/*SET @value = ELT(1, myArrayOfValue);*/
    SET STR = SUBSTRING(name1, 1, LOCATE(',', name1)-1);
    SET name1 = SUBSTRING(name1, LOCATE(',', name1) + 1);

    if i = count THEN
			if LOCATE(',', name1) = 0 then
				set STR = name1;
            end if;
		LEAVE myloop;
    end if;
		set i = i + 1;
	
END WHILE;
	set email = concat(STR, '.');
    /*--------------------------------SurName-------------------///////////*/
set i = 1;
set count = Round(RAND()* (maxCount-1) + 1);
myloop1: WHILE (i <= maxCount)
DO
/*SET @value = ELT(1, myArrayOfValue);*/
    SET STR = SUBSTRING(surNames, 1, LOCATE(',', surNames)-1);
    SET surNames = SUBSTRING(surNames, LOCATE(',', surNames) + 1);

    if i = count THEN
			if LOCATE(',', surNames) = 0 then
				set STR = surNames;
            end if;
		LEAVE myloop1;
    end if;
		set i = i + 1;
	
END WHILE;
	set email = concat(email, STR, '@');
    /*--------------------------------Server-------------------///////////*/
    set i = 1;
set count = Round(RAND()* (maxCount-1) + 1);
myloop2: WHILE (i <= maxCount)
DO
/*SET @value = ELT(1, myArrayOfValue);*/
    SET STR = SUBSTRING(server1, 1, LOCATE(',', server1)-1);
    SET server1 = SUBSTRING(server1, LOCATE(',', server1) + 1);

    if i = count THEN
			if LOCATE(',', server1) = 0 then
				set STR = server1;
            end if;
		LEAVE myloop2;
    end if;
		set i = i + 1;
	
END WHILE;
	set email = concat(email, STR, '.ua');
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS getRandName;
DELIMITER $$

CREATE PROCEDURE `getRandName` (OUT name VaRCHAR(255))
BEGIN
declare myArrayOfValue, STR  VARCHAR(225);
declare maxCount int default 6;
declare count, i int ;

SET myArrayOfValue = 'Василь,Петро,Максим,Віктор,Віталій,Надія';

set i = 1;
set count = Round(RAND()* (maxCount-1) + 1);
myloop: WHILE (i <= maxCount)
DO

    SET STR = SUBSTRING(myArrayOfValue, 1, LOCATE(',', myArrayOfValue)-1);
    SET myArrayOfValue = SUBSTRING(myArrayOfValue, LOCATE(',', myArrayOfValue) + 1);

    
    if i = count THEN
		if LOCATE(',', myArrayOfValue) = 0 then
				set STR = myArrayOfValue;
            end if;
		LEAVE myloop;
    ELSE 
		set i =i+1;
	end if;
END WHILE;
set name = STR;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS getRandMcIdMgrIdCustId;
DELIMITER $$

CREATE PROCEDURE `getRandMcIdMgrIdCustId` (OUT mcId1 Int, OUT mgrId1 int, OUT custId1 int)
start1:BEGIN
  declare minAgId, maxAgId,AgQuantM1 int default null;
  declare z int;
select MIN(agId), Max(agId) Into minAgId, maxAgId from AGENTS;
 if minAgId =null Or maxAgId =null Then 
	Leave start1;
	end if;
    set AgQuantM1 = maxAgId - minAgId;
    set mcId1 = ROUND((RAND() * AgQuantM1) + minAgId);
    set mgrId1 = ROUND((RAND() * AgQuantM1) + minAgId);
    set custId1 = ROUND((RAND() * AgQuantM1) + minAgId);
    set z = 1;
    while mgrId1 = mcId1 and z < 100 Do
		set mgrId1 = ROUND((RAND() * AgQuantM1) + minAgId);
		set z = z + 1;
    end while;
   set z = 1;
    while (custId1 = mcId1 OR custId1 = mgrId1) and z < 100 Do
		set custId1 = ROUND((RAND() * AgQuantM1) + minAgId);
		set z = z + 1;
    end while;
  
end$$
DELIMITER ;

DROP PROCEDURE IF EXISTS getRandCargId;
DELIMITER $$

CREATE PROCEDURE `getRandCargId` (OUT cargId1 Int)
start1:BEGIN
  declare minCargId, maxCargId,AgQuantM1 int default null;
  declare z int;
 select MIN(cargId),  MAX(cargId) Into minCargId,maxCargId from CARGOS;
 if minCargId =null Or maxCargId =null Then 
	Leave start1;
end if;

    set AgQuantM1 = maxCargId - minCargId;
    set cargId1 = ROUND((RAND() * AgQuantM1) + minCargId);
   
  
end$$

DELIMITER ;


DROP PROCEDURE IF EXISTS getTestMscId;
DELIMITER $$

CREATE PROCEDURE `getTestMscId` (In mscNo1 int, OUT mscId1 Int)
s:BEGIN
  declare AgQuantM1, maxMscId, minMscId int default -1;
  declare z int;

 select MIN(mscId), MAX(mscId) into minMscId, maxMscId from misc Where mscNo =mscNo1;
 select minMscId, maxMscId;
	 if minMscId =-1 Or maxMscId =-1 Then 
		Leave s;
	end if;

    set AgQuantM1 = maxMscId - minMscId;
    set mscId1 = ROUND(RAND() * AgQuantM1 + minMscId);
  
end$$
DELIMITER ;

DROP PROCEDURE IF EXISTS getRandMscId;
DELIMITER $$

CREATE PROCEDURE `getRandMscId` (In mscNo1 int, OUT mscId1 Int)
s:BEGIN
  declare AgQuantM1, maxMscId, minMscId int default null;
  declare z int;

 select MIN(mscId), MAX(mscId) into minMscId, maxMscId from misc Where mscNo =mscNo1;

	 if minMscId =null Or maxMscId =null Then 
		Leave s;
	end if;

    set AgQuantM1 = maxMscId - minMscId;
    set mscId1 = ROUND(RAND() * AgQuantM1 + minMscId);
end$$
DELIMITER ;

DROP PROCEDURE IF EXISTS getCargSTR;
DELIMITER $$

CREATE PROCEDURE `getCargSTR` (In No1 int, OUT cargoStr varchar(255))
BEGIN 
  declare cargoCount int default 1;
  declare cargoss varchar(255) default 'пісок річковий,олія соняшникова,яблука сортові';
  declare cargcurr varchar(255) default cargoss;
  declare i int default 0; 
  declare z int ; 
  
	While (LOCATE(',', cargcurr)) do
		set cargcurr = SUBSTRING(cargcurr, LOCATE(',', cargcurr) + 1);
		set cargoCount = cargoCount + 1;
    end while;
    
	SET z = No1 Mod (cargoCount + 1);   
    if z = 0 then
		SET z = 1;
    end if;
    
	While i < z do
		SET cargoStr= SUBSTRING(cargoss, 1, LOCATE(',', cargoss) - 1);
	
		if LOCATE(',', cargoss) = 0 then
			set cargoStr = cargoss;
		end if;
        SET cargoss = SUBSTRING(cargoss, LOCATE(',', cargoss) + 1);
    
        SET i = i + 1;
	end while;	
    
end$$

DELIMITER ;

DROP PROCEDURE IF EXISTS KHO_INIT_DOCUMENTS;
DELIMITER $$

CREATE PROCEDURE `KHO_INIT_DOCUMENTS` (In No1 int)
BEGIN
   
    declare count int default 0;
    
    declare docId               bigint;
   declare mcId                 bigint;
   declare mgrId                bigint ;
   declare custId               bigint;
   declare cargId               bigint;
   declare docNo                varchar(50);
   declare docDate              datetime;
     declare date1 datetime;
   declare docName              varchar(255);
   declare docSum               float(8,2);
   declare docStatus            int ;
   declare docTag               varchar(255);
   declare docCargWeight        float(8);
   declare docCargVolume        float(8);
   declare docLoadDate          datetime;
   declare docUnloadDate        datetime;
   declare docLoadAddress       varchar(255);
   declare docUnloadAddress     varchar(255);
   declare docCustContactPersPhone varchar(255);
   declare docCustContactPersName varchar(255);
	
    DECLARE str VARCHAR(50);
    DECLARE date DateTime DEFAULT CURRENT_DATE;
    Declare mscNo, mscId int default 1;
    declare MAX_CITIES_PER_DOC int default 4;
	declare i,j int;
    declare startASCIICode int default 65;
    declare range1 int default 25;
   
     
   while count < No1 do
   call getRandMcIdMgrIdCustId(mcId, mgrId, custId);
   call getRandCargId(cargId);
   
   set docNo = Concat(ROUND((RAND() * 2) + 1) , '/' , ROUND((RAND() * 9) + 1) );
     SET docNo = Concat (docNo,'-', upper(Cast(char(round(rand()*range1 + startASCIICode)) as Char(1))));
     
	set docSum =ROUND(RAND() * 8000);
	set docStatus =ROUND(RAND() * 3 + 1);		
	set docCargWeight = ROUND(RAND() * 10 + 1, 1);
    set docCargVolume = ROUND(RAND() * 50 + 1, 1);
    
    call setRandDATETIME(CurDate(), docLoadDate);
	set docLoadDate = Date_add(docLoadDate, interval ROUND(RAND() * 3 + 1) day);        
        
    set docUnloadDate = Date_add(docLoadDate, interval ROUND(RAND() * 3 + 1) day);
    
    set docLoadAddress = Concat('load Address ', ROUND(RAND() * 50 + 1, 1));
    set docUnloadAddress = Concat('Unload Address ', ROUND(RAND() * 50 + 1, 1));
    
    call getRandDATETIME(date1);
    call getPhoneNumber(docCustContactPersPhone);
    call getRandName(docCustContactPersName);
    /*
    select    0, mcId, mgrId, custId, cargId,  docNo, date1, 'Договір-замовлення', 
    docSum, docStatus, '', docCargWeight, docCargVolume,  docLoadDate, docUnloadDate, docLoadAddress, docUnloadAddress, 
    docCustContactPersPhone, docCustContactPersName;
    */
    call getCargSTR(count + 1, docTag);
    
    INSERT INTO Documents VALUES 
    (0, mcId, mgrId, custId, cargId,  docNo, date1, 'Договір-замовлення', 
    docSum, docStatus, docTag, docCargWeight, docCargVolume,  docLoadDate, docUnloadDate, docLoadAddress, docUnloadAddress, 
    docCustContactPersPhone, docCustContactPersName);
    
    set docId = last_insert_id();
    set i = Round(rand() * (MAX_CITIES_PER_DOC - 1) + 1);
    set j= 1;
    while j <= i do
		call getRandMscId(mscNo, mscId);	
         INSERT INTO JOURNAL VALUES(0, docId, mscId);
         set  j =j +1;
    end while;
    set count = count + 1;
  end while;
END$$

DELIMITER ;
DROP PROCEDURE IF EXISTS KHO_INIT_AGTYPES_AGENTS;
DELIMITER $$

CREATE PROCEDURE `KHO_INIT_AGTYPES_AGENTS` (IN subCats VARCHAR(255), 
IN items VARCHAR(255), IN itemNoPerSubCat int)
BEGIN

  declare STR, itemTag, subCats2  VARCHAR(225);
	declare subCatsCount int default 1;
    declare i,j, itemType int;
    declare mscNo int default 1;
    DECLARE topCatId int;
    DECLARE subCatsId varchar(1000) default '';
    
    set subCats2= subCats;
    
    DELETE FROM AGENTS WHERE agid >0;
	Delete FROM ag_types WHERE agtID >0;
    
    While( LOCATE(',', subCats2)) do
		set subCats2 = SUBSTRING(subCats2, LOCATE(',', subCats2) + 1);
		set subCatsCount = subCatsCount +1;
    end while;
    
    set itemTag ='';
    /*------INSERT subCats------------*/
set i = 1;
WHILE (i <= subCatsCount) DO
    SET STR = SUBSTRING(subCats, 1, LOCATE(',', subCats) - 1);
    
	if LOCATE(',', subCats) = 0 then
		set STR = subCats;
	end if;
    SET subCats = SUBSTRING(subCats, LOCATE(',', subCats) + 1);
    /*replace all cR*/
    Set STR = trim(REPLACE(STR,'\n',''));
    INSERT INTO ag_types VALUES  ( 0, STR, itemTag);
    set  subCatsId =  LAST_INSERT_ID(); 
      
    set j = 1;
    WHILE (j <= itemNoPerSubCat) DO
		call KHO_INIT_AGENTS (1, subCatsId);
        set j = j + 1;
   end while;
	set i = i + 1;
END WHILE;
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS KHO_INIT_AGENTS;
DELIMITER $$

CREATE PROCEDURE `KHO_INIT_AGENTS` (IN No1 int, IN agtIdPar int)
BEGIN
   
    declare count int default 0;
    
    declare agId               bigint;
    declare agtId              bigint;
    declare agName              varchar(50);
   declare date1 datetime;
   declare agType            int ;
   declare agAddress            varchar(255);
   declare agPhone            varchar(255);
   declare agEmail            varchar(255);
   declare agPassport            varchar(255);
   declare agWww               varchar(255);
   declare agCountry               varchar(255);
   declare agDate   	       datetime;
   declare agTag		       varchar(255);
   
    
    DECLARE str VARCHAR(50);
    DECLARE date DateTime DEFAULT CURRENT_DATE;
    
   while count < No1 do
    set agType = ROUND(RAND() * 5);
    set agtId = agtIdPar;
	call getRandEmail(agEmail); 
    call getRandDATE(agDate);
	call getRandName(agName);
    set agName = Concat(agName, ROUND(RAND() * 50 + 1));
    set agAddress = Concat('Адреса  ', agName, ', ',  ROUND(RAND() * 50 + 1));
	call getRandPassport(202,5,1, agPassport);        
    set agWww = 'www.bestsite.com.ua';
    set agCountry = 'Україна';
    call getPhoneNumber(agPhone);
    set agTag ='';
    
    /*select 0, agName, agType, agAddress, agPhone,  agEmail, 
    agPassport, agWww, agCountry, agDate, agTag;
    */
	INSERT INTO AGENTS VALUES 
    ( 0, agtId, agName, agAddress, agPhone,  agEmail, 
    agPassport, agWww, agCountry, agDate, agTag);
    
    set count = count + 1;
  end while;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS KHO_INIT_CARGOS_CAT_PART;
DELIMITER $$

CREATE PROCEDURE `KHO_INIT_CARGOS_CAT_PART` (IN itemType int, IN items VARCHAR(255), 
IN F0 int, IN F1 int, IN F2 int)
BEGIN
   
   declare STR, itemTag, items2  VARCHAR(225);
	declare maxCount int default 1;
    declare i,j int;
    set items2= items;
    
    While( LOCATE(',', items2)) do
		set items2 = SUBSTRING(items2, LOCATE(',', items2) + 1);
		set maxCount = maxCount +1;
    end while;
    
   /* SET cargo = 'Продукти,Матеріали,Меблі,Одяг,Взуття,Дерево';*/
    set itemTag ='';
    
set i = 1;
WHILE (i <= maxCount) DO
    SET STR = SUBSTRING(items, 1, LOCATE(',', items) - 1);
    
	if LOCATE(',', items) = 0 then
		set STR = items;
	end if;
    SET items = SUBSTRING(items, LOCATE(',', items) + 1);
	INSERT INTO CARGOS VALUES  ( 0, itemType, STR, itemTag);
    INSERT INTO cargo_tree VALUES (0, LAST_INSERT_ID(), F0, F1, F2);
	set i = i + 1;
END WHILE;
END$$

DELIMITER ; 

DROP PROCEDURE IF EXISTS KHO_INIT_COUNTRIES_REGIONS_MISC;
DELIMITER $$

CREATE PROCEDURE `KHO_INIT_COUNTRIES_REGIONS_MISC` (IN topCat VARCHAR(255), IN subCats VARCHAR(255), 
IN items VARCHAR(255), IN itemNoPerSubCat int)
BEGIN
  declare STR, itemTag, subCats2  VARCHAR(225);
	declare subCatsCount int default 1;
    declare i,j, itemType int;
    declare mscNo int default 1;
    DECLARE topCatId int;
    DECLARE subCatsId varchar(1000) default '';
    
    set subCats2= subCats;
    
    While( LOCATE(',', subCats2)) do
		set subCats2 = SUBSTRING(subCats2, LOCATE(',', subCats2) + 1);
		set subCatsCount = subCatsCount +1;
    end while;
    
    set itemTag ='';
    /*------INSERT topCat------------*/
    set itemType =0;
    INSERT INTO COUNTRIES VALUES  ( 0, topCat, itemTag);
    set topCatId = last_insert_id();
   
    /*------INSERT subCats------------*/
set i = 1;
WHILE (i <= subCatsCount) DO
    SET STR = SUBSTRING(subCats, 1, LOCATE(',', subCats) - 1);
    
	if LOCATE(',', subCats) = 0 then
		set STR = subCats;
	end if;
    SET subCats = SUBSTRING(subCats, LOCATE(',', subCats) + 1);
    /*replace all cR*/
    Set STR = trim(REPLACE(STR,'\n',''));
    INSERT INTO REGIONS VALUES  ( 0, topCatId, STR, itemTag);
    set  subCatsId =  LAST_INSERT_ID(); 
      
    set j = 1;
    WHILE (j <= itemNoPerSubCat) DO
		
		SET STR = SUBSTRING(items, 1, LOCATE(';', items) - 1);
		
        
		if LOCATE(';', items) = 0 then
			set STR = items;
		end if;
		SET items = SUBSTRING(items, LOCATE(';', items) + 1);
		/*replace all cR*/
        Set STR = trim(REPLACE(STR,'\n',''));
        INSERT INTO MISC VALUES  ( 0, subCatsId, mscNo, STR, itemTag);
		 
        set j = j + 1;
    end while;
	set i = i + 1;
END WHILE;
END$$

DELIMITER ;


DROP PROCEDURE IF EXISTS KHO_INIT_COUNTR_REG_MISC;
DELIMITER $$

CREATE PROCEDURE `KHO_INIT_COUNTR_REG_MISC` ()
BEGIN
   declare Countries1 VARCHAR(255) default 'Україна,Польща,Білорусь,Румунія,Словаччина,Угорщина';
   
   delete from misc Where mscId > 0;
   delete from regions where regId > 0;
   delete from countries where cntrId > 0;



call KHO_INIT_COUNTRIES_REGIONS_MISC('Україна','Закарпатська,Київська,Тернопільська,Полтавська,Одеська',
'Ужгород;Мукачево;Хуст;
Київ;Біла-Церка;Фастів;
Тернопіль;Теребовля;Збараж;
Полтава;Гадяч;Пирятин;
Одеса;Кодима;Ізмаїл', 3);

call KHO_INIT_COUNTRIES_REGIONS_MISC('Польща','Любельське,Лодзинске,Підкарпатське,Сілезське',
'Люблін;Люблін2;Люблін3;
Лодзь;Лодзь2;Лодзь3;
Жешув;Жешув2;Жешув3;
Катовіце;Катовіце2;Катовіце3',3);
call KHO_INIT_COUNTRIES_REGIONS_MISC('Білорусь','Берестейська,Гомельська,Мінська',
'Брест;Брест2;Брест3;
Гомель;Гомель2;Гомель3;
Мінськ;Мінськ2;Мінськ3',3);
call KHO_INIT_COUNTRIES_REGIONS_MISC('Румунія','Румунська обл1,Румунська обл2,Румунська обл3',
'рум. обл1 місто1;рум. обл1 місто2;рум. обл1 місто3;
рум. обл2 місто1;рум. обл2 місто2;рум. обл2 місто3;
рум. обл3 місто1;рум. обл3 місто2;рум. обл3 місто3',3);
call KHO_INIT_COUNTRIES_REGIONS_MISC('Словаччина','Братиславський,Жилінський,Кошицький',
'Братислава;Братислава2;Братислава3;
Жиліна;Жиліна2;Жиліна3;
Кошице;Кошице2;Кошице3',3);
call KHO_INIT_COUNTRIES_REGIONS_MISC('Угорщина','Феєр,Пешт,Шомодь',
'Секешфегервар;Секешфегервар2;Секешфегервар3;
Будапешт;Будапешт2;Будапешт3;
Капошвар;Капошвар2;Капошвар3',3);
select * from countries;
select * from regions;
select * from misc;
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS KHO_INIT_UTYPES_USERS;
DELIMITER $$

CREATE PROCEDURE `KHO_INIT_UTYPES_USERS` (IN utypes VARCHAR(255), IN users varchar(255),
 IN usersPerUType int)
BEGIN
   
   declare STR, itemTag, items2  VARCHAR(225);
	declare maxCount int default 1;
    declare i,j, utypeId int;
    declare uLogin, uPass varchar(255);
    set items2= utypes;
    
    While( LOCATE(',', items2)) do
		set items2 = SUBSTRING(items2, LOCATE(',', items2) + 1);
		set maxCount = maxCount +1;
    end while;
    
    set itemTag ='';
    
set i = 1;
WHILE (i <= maxCount) DO
    SET STR = SUBSTRING(utypes, 1, LOCATE(',', utypes) - 1);
    
	if LOCATE(',', utypes) = 0 then
		set STR = utypes;
	end if;
    
    SET utypes = SUBSTRING(utypes, LOCATE(',', utypes) + 1);
    /*replace CR*/
    Set STR = trim(replace(STR, '\n',''));
    
	INSERT INTO user_types VALUES  ( 0, i, STR);
    set utypeId = last_insert_id();
    
    set j = 1;
  WHILE (j <= usersPerUType) DO
    SET STR = SUBSTRING(users, 1, LOCATE(';', users) - 1);
    
    if LOCATE(';', users) = 0 then
		set STR = users;
	end if;
    
    SET users = SUBSTRING(users, LOCATE(';', users) + 1);
    
    Set uLogin = SUBSTRING(STR, 1, LOCATE(',', STR) - 1);
    Set uLogin = trim(replace(uLogin, '\n',''));
    Set uPass = SUBSTRING(STR, LOCATE(',', STR) + 1);
    Set uPass = trim(replace(uPass, '\n',''));
      
    INSERT INTO USERS VALUES  ( 0, uTypeId, uLogin, uPass);
       
    set j = j +1;
  END WHILE;
	set i = i + 1;
END While;
    
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS KHO_INIT_USER;
DELIMITER $$

CREATE PROCEDURE `KHO_INIT_USER` ()
BEGIN
delete from users where userId > 0;
delete from user_types where uTypeId >0;

call KHO_INIT_UTYPES_USERS('admin,advUser,user',
'Alex,12345;
root, root;
Ponny,3321;
mykola, qwerty;
yuliya, miss124;
qw, 1;',2);
select * from user_types;
select * from users;
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS KHO_INIT_COUNTRIES_CAT_PART;
DELIMITER $$

CREATE PROCEDURE `KHO_INIT_COUNTRIES_CAT_PART` (IN items VARCHAR(255))
BEGIN
   
   declare STR, itemTag, items2  VARCHAR(225);
	declare maxCount int default 1;
    declare i,j int;
    set items2= items;
    
    While( LOCATE(',', items2)) do
		set items2 = SUBSTRING(items2, LOCATE(',', items2) + 1);
		set maxCount = maxCount +1;
    end while;
    
   /* SET cargo = 'Продукти,Матеріали,Меблі,Одяг,Взуття,Дерево';*/
    set itemTag ='';
    
set i = 1;
WHILE (i <= maxCount) DO
    SET STR = SUBSTRING(items, 1, LOCATE(',', items) - 1);
    
	if LOCATE(',', items) = 0 then
		set STR = items;
	end if;
    SET items = SUBSTRING(items, LOCATE(',', items) + 1);
    /*replace CR*/
    Set STR = trim(replace(STR, '\n',''));
	INSERT INTO COUNTRIES VALUES  ( 0, STR, itemTag);
    
	set i = i + 1;
END WHILE;
END$$

DELIMITER ;
DROP PROCEDURE IF EXISTS KHO_INIT_ONE_CATEGORY;
DELIMITER $$

CREATE PROCEDURE `KHO_INIT_ONE_CATEGORY` (IN topCat VARCHAR(255), IN subCats VARCHAR(255), 
IN items VARCHAR(255), IN itemNoPerSubCat int)
BEGIN
   
   declare STR, itemTag, subCats2  VARCHAR(225);
	declare subCatsCount int default 1;
    declare i,j, itemType int;
    DECLARE topCatId int;
    DECLARE subCatsId varchar(1000) default '';
    
    set subCats2= subCats;
    
    While( LOCATE(',', subCats2)) do
		set subCats2 = SUBSTRING(subCats2, LOCATE(',', subCats2) + 1);
		set subCatsCount = subCatsCount +1;
    end while;
    
   /* SET cargo = 'Продукти,Матеріали,Меблі,Одяг,Взуття,Дерево';*/
    set itemTag ='';
    /*------INSERT topCat------------*/
    set itemType =0;
    INSERT INTO CARGOS VALUES  ( 0, itemType, topCat, itemTag);
    set topCatId = last_insert_id();
    INSERT INTO cargo_tree VALUES (0, topCatId, 0, 0, 0);
    /*------INSERT subCats------------*/
set i = 1;
WHILE (i <= subCatsCount) DO
	set itemType =0;
    SET STR = SUBSTRING(subCats, 1, LOCATE(',', subCats) - 1);
    
	if LOCATE(',', subCats) = 0 then
		set STR = subCats;
	end if;
    SET subCats = SUBSTRING(subCats, LOCATE(',', subCats) + 1);
	/*replace CR */
	Set STR = trim(REPLACE(STR,'\n',''));
    INSERT INTO CARGOS VALUES  ( 0, itemType, STR, itemTag);
    set  subCatsId =  LAST_INSERT_ID(); 
    INSERT INTO cargo_tree VALUES (0, subCatsId, topCatId, 0, 0);
    
    set j = 1;
    set itemType =1;
    WHILE (j <= itemNoPerSubCat) DO
		
		SET STR = trim(SUBSTRING(items, 1, LOCATE(';', items) - 1));
		
		if LOCATE(';', items) = 0 then
			set STR = items;
		end if;
		SET items = SUBSTRING(items, LOCATE(';', items) + 1);
        
		/*replace CR */
		Set STR = trim(REPLACE(STR,'\n',''));
		INSERT INTO CARGOS VALUES  ( 0, itemType, STR, itemTag);
		INSERT INTO cargo_tree VALUES (0, LAST_INSERT_ID(), subCatsId, topCatId, 0);
        set j = j + 1;
    end while;
	set i = i + 1;
END WHILE;

END$$



DELIMITER ;

DROP PROCEDURE IF EXISTS KHO_INIT_CARGOS;
DELIMITER $$

CREATE PROCEDURE `KHO_INIT_CARGOS` ()
BEGIN
   
   declare cargo  VARCHAR(225);
	declare maxCount int default 0;
    declare count int default 0;
    declare i int;
    
    declare cargId             bigint;
    declare cargType            int ;
    declare cargName            varchar(50);
    declare cargTag		       varchar(255);
    DECLARE STR VARCHAR(50);
    
    SET cargo = 'Продукти,Матеріали,Одяг,Взуття';
    
delete from cargo_tree WHERE cargo_tree.cgtrId > 0;
delete from CARGOS WHERE CARGOS.cargId > 0 ;


call KHO_INIT_ONE_CATEGORY('Продукти', 'Молочні,М\'ясні,Яйця,Хлібо-булочні,Риба,Овочі,Фрукти',
'Молоко ТетраПак, 1л;Кефір, 3.2%;Ряжанка, 1%, 1.5л;
 Свинина;Говядина;Кури;
 Курячі, екстра;Курячі, Наша-ряба;Перепелині;
 Білоруський;Батон;Коровай;
 Короп, морожений;Хек, морожений;Печінка тріски;
 Буряк;Морква;Картопля;
 Груші;Банани;Яблука', 3);
 call KHO_INIT_ONE_CATEGORY('Матеріали', 'Дерево,Метал,Текстиль',
'Дошка ясенова, обрізна;Брус вільховий;Балка соснова;
 Прут легований;Сталь інструментальна;Бляха оцинкована; 
 Ситець;Шовк;Бавовна', 3);
 call KHO_INIT_ONE_CATEGORY('Одяг', 'Чоловічий,Жіночий,Дитячий',
'Брюки, класичні;Пальто, шерсть;Сорочки, бавовна/поліестер;
 Спідниця;Блузка;Костюм; 
 Комбінезон;Повзунки;Куртка', 3);
 call KHO_INIT_ONE_CATEGORY('Взуття', 'Чоловіче,Жіноче,Дитяче',
'Туфлі, класичні;Чоботи;Сандалі;
 Туфлі, шпильки;Босоніжки;Кровсовки; 
 Сандалі;Чешки;Тапочки', 3);
 
select * from CARGOS;
select * from cargo_tree;
  
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS getRandDATETIME;
DELIMITER $$
CREATE PROCEDURE `getRandDATETIME` (OUT date1 datetime)
BEGIN

declare year					int default 2009;
   declare month 				int default 0;
   declare day					int default 1;
   declare hour					int default 0;
   declare minute 				int default 0;
   declare second 				int default 0;
   
    set year = year + ROUND(RAND() * 7);
      set month = month + ROUND(RAND() * 11);
      set day = day + ROUND(RAND() * 28);
      
       set hour = hour + ROUND(RAND() * 23);
       set minute = minute + ROUND(RAND() * 59);
       set second = second + ROUND(RAND() * 59);
              
        set date1 = timestamp(Concat(year ,'-', month ,'-', day,' ' , hour,':', minute ,':', second)); 
        
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS getRandDATE;
DELIMITER $$
CREATE PROCEDURE `getRandDATE` (OUT date1 datetime)
BEGIN

declare year					int default 2009;
   declare month 				int default 0;
   declare day					int default 1;
   declare day2				varchar(10);
   
    set year = year + ROUND(RAND() * 7);
    set month = month + ROUND(RAND() * 11);
    set day = day + ROUND(RAND() * 28);
      if (day <10)  THEN 
		  set day2 = '0' + day;         
	   else
          set day2 = '0' + day;         
       end if;
     set date1 = timestamp(Concat(year ,'-', month ,'-', day2 )); 
        
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS getRandTIME;
DELIMITER $$

CREATE  PROCEDURE `getRandTIME`(OUT Time varchar(50))
BEGIN

   declare hour					int default 0;
   declare minute 				int default 0;
   declare second 				int default 0;
   

       set hour = hour + ROUND(RAND() * 23);
       set minute = minute + ROUND(RAND() * 59);
       set second = second + ROUND(RAND() * 59);
              
        set Time = Concat(hour,':', minute ,':', second); 
       
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS setRandDATETIME;
DELIMITER $$

CREATE PROCEDURE `setRandDATETIME` (IN datetimeIn datetime, OUT datetimeOut datetime)
BEGIN

   declare month 				int default 1;
   declare day					int default 1;
   declare hour					int default 0;
   declare minute 				int default 0;
   declare second 				int default 0;
   
   set datetimeOut = Date_add(datetimeIn, interval ROUND(RAND() * 23 ) hour);
   set datetimeOut = Date_add(datetimeOut, interval ROUND(RAND() * 59 ) minute);
   set datetimeOut = Date_add(datetimeOut, interval ROUND(RAND() * 59 ) second);
        
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS myCall;
DELIMITER $$

CREATE PROCEDURE `myCall` ()
BEGIN
declare date1 datetime;
declare PhoneNumber, Pass, cargoo VARCHAR(55);
declare minAgId, maxAgId,  i int default 0;
declare minMscId, maxMscId int default null;
declare MscNo1 int default 1;
declare randId int;

/*select cast( char(202) as char(1) character set 'koi8u');*/
/*delete from countries where cntrId > 0;
call KHO_INIT_REGIONS_CAT_PART('Україна,Польща,Білорусь,Румунія,Словакія,Угорщина');
select * from countries;*/

call getRandDATETIME(date1);
call getRandPassport(202, 3,1, Pass);
call getPhoneNumber(PhoneNumber);
call getCargSTR(34,cargoo);
/*call getRandDATE(date1);*/
select cargoo;
/*select date1, PhoneNumber, Pass;*/

 /*select MIN(agId), Max(agId) Into minAgId, maxAgId from AGENTS;
 select minAgId, maxAgId;*/
 /*select MIN(mscId), MAX(mscId) Into minMscId, maxMscId from misc Where MscNo =MscNo1;
 select minMscId, maxMscId;
 call getRandCargId( randId);
 select randId;
 */
 /*call getRandCargId(randId);*/
 
END$$
