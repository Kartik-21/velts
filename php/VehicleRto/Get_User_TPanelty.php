<?php

require_once 'dbconfig.php';

$conn = mysql_connect("$host","$username","$password");
mysql_select_db("$dbname");

$sno =$_POST['v1'];
$date =$_POST['v2'];

if($sno!="")
{

		$query = "SELECT * FROM tbl_register WHERE Aadhar='$sno' OR Mobile='$sno' OR User_ID='$sno' ";
		
		$result = mysql_query($query);	
		if (mysql_num_rows($result) > 0) 
		{
			$row = mysql_fetch_array($result, MYSQLI_ASSOC);
			$uid=$row['User_ID'];	
		}
		else
		{
			$uid="";
			echo "00";
		}

	if($uid!="")
	{
		$sql = "SELECT SUM(Fine) FROM tbl_user_penalty WHERE User_ID='$uid' AND Date='$date'";
		
	//	echo $sql;
		$result = mysql_query($sql);	
	
		if (mysql_num_rows($result) > 0) 
		{
			$row = mysql_fetch_array($result, MYSQLI_ASSOC);
			$tid=$row['SUM(Fine)'];
			echo "$tid";
		}
		else
		{
			echo"00";
		}
	}
}

?>


