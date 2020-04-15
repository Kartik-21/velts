<?php

require_once 'dbconfig.php';

$conn = mysql_connect("$host","$username","$password");
mysql_select_db("$dbname");

$email =$_POST['v1'];

if($email!="")
{

		$sql = "DELETE FROM tbl_uploads WHERE Upload_ID=$email";

//echo $sql;

		if (mysql_query($sql) > 0) 
		{
			echo "Deleted..";
		}
		else
		{
			echo "Error";
		}
}
else
{
	echo "No DID";
}
?>

