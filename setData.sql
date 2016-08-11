use cargoCarrier;

call KHO_INIT_USER();

delete from Journal WHERE docId > 0 ;
delete from DOCUMENTS WHERE docId > 0 ;

call KHO_INIT_AGTYPES_AGENTS('Клієнт,Моя фірма,Менеджер',
'ПП"АгроБуд";ТОВ"ВелетеньТранс";ТОВ"ТорчинПродукт";
Компанія Вест;Компанія Техніка;Холдинг МіськЗеленКомфорт;
Максим;Костянтин;Олеся;',3);

call KHO_INIT_COUNTR_REG_MISC();

call KHO_INIT_CARGOS();

call KHO_INIT_DOCUMENTS(3);

