INSERT INTO CC_CUSTOMER(ID,FIRST_NAME,MIDDLE_NAME,LAST_NAME,GENDER,INITIALS,MARITAL_STATUS,IS_EXISTING_CUSTOMER,CEDIT_RATING,STATUS,CREATED_BY,CREATED_DATETIME,UPDATED_BY,UPDATED_DATETIME,VERSION) VALUES (customer_seq.NEXTVAL,'SUNIL','KRISHNA','BEHERA','MALE','SKB','MARRIED','Y',10,'ENABLED','system',CURRENT_TIMESTAMP,'sunil',CURRENT_TIMESTAMP,1)
INSERT INTO CC_ADDRESS (id,REF_CUSTOMER_ID, unit, street_Name, suburb, city, state, country, pin_Code, IS_PRIMARY_ADDRESS, IS_MAILING_ADDRESS, status,CREATED_BY,CREATED_DATETIME,UPDATED_BY,UPDATED_DATETIME,VERSION) VALUES (ADDRESS_SEQ.NEXTVAL,1,1,'spencer','CBD','Melbourne','State','Australia',3000,true,true,'ENABLED','system',CURRENT_TIMESTAMP,'sunil',CURRENT_TIMESTAMP,1)