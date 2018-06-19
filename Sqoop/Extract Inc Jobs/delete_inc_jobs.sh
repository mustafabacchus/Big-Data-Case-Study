#!/bin/bash


#CDW_SAPP_BRANCH
sqoop job --meta-connect jdbc:hsqldb:hsql://localhost:16000/sqoop --delete exBranch &> /dev/null;
if [ $? -eq 0 ]
then
	echo "SUCCESS! Job exBranch deleted."
else
	echo "FAIL! Job exBranch NOT deleted."
fi


#CDW_SAPP_CREDITCARD
sqoop job --meta-connect jdbc:hsqldb:hsql://localhost:16000/sqoop --delete exCC &> /dev/null;
if [ $? -eq 0 ]
then
	echo "SUCCESS! Job exCC deleted."
else
	echo "FAIL! Job exCC NOT deleted."
fi


#CDW_SAPP_TIMEID
sqoop job --meta-connect jdbc:hsqldb:hsql://localhost:16000/sqoop --delete exTime &> /dev/null;
if [ $? -eq 0 ]
then
	echo "SUCCESS! Job exTime deleted."
else
	echo "FAIL! Job exTime NOT deleted."
fi



#CDW_SAPP_CUSTOMER
sqoop job --meta-connect jdbc:hsqldb:hsql://localhost:16000/sqoop --delete exCust &> /dev/null;
if [ $? -eq 0 ]
then
	echo "SUCCESS! Job exCust deleted."
else
	echo "FAIL! Job exCust NOT deleted."
fi


echo ""
sqoop job --meta-connect jdbc:hsqldb:hsql://localhost:16000/sqoop --list
echo ""
echo "COMPLETE"