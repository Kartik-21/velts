<?php

require_once 'dbconfig.php';

$conn = mysql_connect("$host","$username","$password");
mysql_select_db("$dbname");

$uid =$_POST['v1'];

if($uid!="")
{
	$query = "SELECT * FROM tbl_register WHERE User_ID='$uid'";
	$result = mysql_query($query);	
	if (mysql_num_rows($result) > 0) 
	{
			$row = mysql_fetch_array($result, MYSQLI_ASSOC);
			$uid=$row['Wallet'];
			
			echo $uid;
	}
	else
	{
		echo "";
	}
}
	

?>


