<?php
require_once 'dbconfig.php';

$conn = mysql_connect("$host","$username","$password");
mysql_select_db("$dbname");

$uname = $_POST['v1'];
$password = $_POST['v2'];
 
if($uname!="")
{
	$query = "SELECT * FROM tbl_register WHERE Email='$uname' AND Password='$password' ";
	echo $query;
	$result = mysql_query($query);

	if (mysql_num_rows($result) > 0) 
	{
			$row = mysql_fetch_array($result, MYSQL_ASSOC);
			echo"User";
	}
	else
	{
		$query = "SELECT * FROM tbl_employee WHERE Email='$uname' AND Password='$password' ";
		echo $query;
		$result = mysql_query($query);

		if (mysql_num_rows($result) > 0) 
		{
			$row = mysql_fetch_array($result, MYSQL_ASSOC);
			echo"Employee";
		}
		else
		{
			echo"Error";
		}
	}
}
else
{
	echo"Error";
}

mysql_close($conn);
?>