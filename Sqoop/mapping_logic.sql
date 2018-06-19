#CDW_SAPP_BRANCH
select branch_code, branch_name, branch_street, branch_city, branch_state, 
case when branch_zip is null then 999999
 else branch_zip 
end as branch_zip, 
concat('(', 
substring(branch_phone, 1, 3), 
')', 
substring(branch_phone, 4, 3), 
'-', 
substring(branch_phone, 7, 4))
as branch_phone,
 last_updated 
from CDW_SAPP_BRANCH;


#CDW_SAPP_CREDITCARD
select transaction_id, credit_card_no as cust_cc_no, 
concat(year, month, day) as timeid, 
cust_ssn, branch_code, transaction_type, transaction_value 
from CDW_SAPP_CREDITCARD;


#CDW_SAPP_TIMEID
select 
concat(year, month, day) as timeid, 
day, month, 
case 
	when month between 1 and 3 then 'Quarter 1' 
when month between 4 and 6 then 'Quarter 2'
	when month between 7 and 9 then 'Quarter 3'
else 'Quarter 4' 
end as quarter, 
year, transaction_id 
from CDW_SAPP_CREDITCARD;


#CDW_SAPP_CUSTOMER
select ssn as cust_ssn, 
concat(ucase(substring(first_name, 1, 1)), lcase(substring(first_name, 2)))
as cust_f_name, 
lcase(middle_name)
as cust_m_name,
concat(ucase(substring(last_name, 1, 1)), lcase(substring(last_name, 2)))
as cust_l_name,
credit_card_no as cust_cc_no, 
concat(apt_no, ' ', street_name)
as cust_street,
cust_city, cust_state, cust_country, 
convert(cust_zip, unsigned)
as cust_zip, 
concat(substring(cust_phone, 1, 3), '-', substring(cust_phone, 4, 4))
as cust_phone, 
cust_email, last_updated
from CDW_SAPP_CUSTOMER;
