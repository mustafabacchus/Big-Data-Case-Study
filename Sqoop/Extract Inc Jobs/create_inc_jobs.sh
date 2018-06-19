#!/bin/bash

#CDW_SAPP_BRANCH 
sqoop job --meta-connect jdbc:hsqldb:hsql://localhost:16000/sqoop --create exBranch -- import --connect jdbc:mysql://localhost/CDW_SAPP --driver com.mysql.jdbc.Driver --query "select branch_code, branch_name, branch_street, branch_city, branch_state, case when branch_zip is null then 999999 else branch_zip end, concat('(', substring(branch_phone, 1, 3), ')', substring(branch_phone, 4, 3), '-', substring(branch_phone, 7, 4)), last_updated from CDW_SAPP_BRANCH where \$CONDITIONS" --fields-terminated-by ',' --append --incremental lastmodified --check-column last_updated --last-value '2018-04-18 16:51:47' --target-dir /user/maria_dev/Credit_Card_System/cdw_sapp_branch --m 1 &> /dev/null; 
if [ $? -eq 0 ]
then
 echo "SUCCESS! Job exBranch created."
else 
 echo "FAIL! Job exBranch NOT created." 
fi




#CDW_SAPP_CREDITCARD
sqoop job --meta-connect jdbc:hsqldb:hsql://localhost:16000/sqoop --create exCC -- import --connect jdbc:mysql://localhost/CDW_SAPP --driver com.mysql.jdbc.Driver --query "select transaction_id, credit_card_no, concat(year, month, day), cust_ssn, branch_code, transaction_type, transaction_value from CDW_SAPP_CREDITCARD where \$CONDITIONS" --fields-terminated-by ',' --incremental append --check-column transaction_id --last-value 0 --target-dir /user/maria_dev/Credit_Card_System/cdw_sapp_creditcard --m 1 &> /dev/null; 
if [ $? -eq 0 ]
then 
 echo "SUCCESS! Job exCC created." 
else 
 echo "FAIL! Job exCC NOT created." 
fi





#CDW_SAPP_TIMEID 
sqoop job --meta-connect jdbc:hsqldb:hsql://localhost:16000/sqoop --create exTime -- import --connect jdbc:mysql://localhost/CDW_SAPP --driver com.mysql.jdbc.Driver --query "select concat(year, month, day) as timeid, day, month, case when month between 1 and 3 then 'Quarter 1' when month between 4 and 6 then 'Quarter 2' when month between 7 and 9 then 'Quarter 3' else 'Quarter 4' end, year, transaction_id from CDW_SAPP_CREDITCARD where \$CONDITIONS" --fields-terminated-by ',' --incremental append --check-column transaction_id --last-value 0 --target-dir /user/maria_dev/Credit_Card_System/cdw_sapp_time --m 1 &> /dev/null; 
if [ $? -eq 0 ] 
then 
 echo "SUCCESS! Job exTime created." 
else 
 echo "FAIL! Job exTime NOT created." 
fi




#CDW_SAPP_CUSTOMER 
sqoop job --meta-connect jdbc:hsqldb:hsql://localhost:16000/sqoop --create exCust -- import --connect jdbc:mysql://localhost/CDW_SAPP --driver com.mysql.jdbc.Driver --query "select ssn, concat( ucase(substring(first_name, 1, 1)), lcase(substring(first_name, 2))) , lcase(middle_name), concat( ucase(substring(last_name, 1, 1)), lcase(substring(last_name, 2))), credit_card_no, concat( apt_no, ' ', street_name), cust_city, cust_state, cust_country, convert(cust_zip, unsigned), concat( substring(cust_phone, 1, 3), '-', substring(cust_phone, 4, 4)) , cust_email, last_updated from CDW_SAPP_CUSTOMER where \$CONDITIONS" --fields-terminated-by ',' --append --incremental lastmodified --check-column last_updated --last-value '2018-04-21 12:49:02' --target-dir /user/maria_dev/Credit_Card_System/cdw_sapp_customer --m 1 &> /dev/null; 
if [ $? -eq 0 ] 
then 
 echo "SUCCESS! Job exTime created." 
else 
 echo "FAIL! Job exTime NOT created." 
fi




echo ""
sqoop job --meta-connect jdbc:hsqldb:hsql://localhost:16000/sqoop --list
echo ""
echo "COMPLETE"
