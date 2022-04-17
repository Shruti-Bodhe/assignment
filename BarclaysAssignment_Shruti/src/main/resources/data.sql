create table trade (trade_id varchar(255) not null, version integer not null, book_id varchar(255), countr_party_id varchar(255), created_date date, is_expired boolean, maturity_date date, primary key (trade_id, version));

INSERT INTO TRADE(TRADE_ID,VERSION,COUNTR_PARTY_ID,BOOK_ID,MATURITY_DATE,CREATED_DATE,IS_EXPIRED) 
VALUES ('T1',1,'CP-1','B1','2021-03-20',current_date,false);
INSERT INTO TRADE(TRADE_ID,VERSION,COUNTR_PARTY_ID,BOOK_ID,MATURITY_DATE,CREATED_DATE,IS_EXPIRED) 
VALUES ('T2',2,'CP-2','B1','2022-05-20',current_date,false);
INSERT INTO TRADE(TRADE_ID,VERSION,COUNTR_PARTY_ID,BOOK_ID,MATURITY_DATE,CREATED_DATE,IS_EXPIRED) 
VALUES ('T2',1,'CP-1','B1','2022-05-20',current_date,false);
INSERT INTO TRADE(TRADE_ID,VERSION,COUNTR_PARTY_ID,BOOK_ID,MATURITY_DATE,CREATED_DATE,IS_EXPIRED) 
VALUES ('T3',3,'CP-3','B2','2014-05-20',current_date,true);