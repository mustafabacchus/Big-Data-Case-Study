#CDW_SAPP_BRANCH
select branch_code, branch_name, branch_street, branch_city, branch_state,
#transform zip
case 
	when branch_zip is null then 999999
	else branch_zip
end as 'branch_zip',
#transform phone
concat('(', 
	substring(branch_phone, 1, 3), 
	')', 
	substring(branch_phone, 4, 3), 
	'-', 
	substring(branch_phone, 7, 4))
as 'branch_phone', branch_phone,
last_updated
from cdw_sapp_branch;




#CDW_SAPP_CREIDTCARD
select transaction_id, credit_card_no as 'cust_cc_no',
#transform day, month, year
concat(year, month, day) as 'timeid',
cust_ssn, branch_code, transaction_type, transaction_value
from cdw_sapp_creditcard;




#CDW_SAPP_TIME
select 
concat(year, month, day) as 'timeid',
day, month,
case 
	when month between 1 and 3 then 'Quarter 1'
    when month between 4 and 6 then 'Quarter 2'
    when month between 7 and 9 then 'Quarter 3'
    else 'Quarter 4'
end as 'quarter',
year
from cdw_sapp_creditcard;




#CDW_SAPP_CUSTOMER
select ssn as 'cust_ssn', 
#transform first name
concat(
	ucase(substring(first_name, 1, 1)), 
	lcase(substring(first_name, 2)))
as 'cust_f_name',
#transform middle name
lcase(middle_name) as 'cust_m_name',
#transform last name
concat(
	ucase(substring(last_name, 1, 1)), 
    lcase(substring(last_name, 2)))
as 'cust_l_name',
credit_card_no as 'cust_cc_no',
#transfom apt no and street name
concat(
	apt_no, 
	' ',
	street_name) 
as 'cust_street',
cust_city, cust_state, cust_country,
#transform zip
convert(cust_zip, unsigned) as 'cust_zip',
#transform phone
concat(
	substring(cust_phone, 1, 3), 
	'-', 
    substring(cust_phone, 4, 4))
as 'cust_phone',
cust_email, last_updated
from cdw_sapp_customer;
