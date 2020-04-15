<?php

require_once 'dbconfig.php';

$conn = mysql_connect("$host","$username","$password");
mysql_select_db("$dbname");

$sno =$_POST['v1'];

if($sno!="")
{

		$query = "SELECT * FROM tbl_register WHERE Aadhar='$sno' OR Mobile='$sno' OR vehicalno='$sno'";
		
		$result = mysql_query($query);	
		if (mysql_num_rows($result) > 0) 
		{
			$row = mysql_fetch_array($result, MYSQLI_ASSOC);
			$uid=$row['User_ID'];	
		}
		else
		{
			$uid="";
			echo "No";
		}

	if($uid!="")
	{
		$sql = "SELECT * FROM tbl_uploads WHERE User_ID='$uid' ";
		
		//echo $sql;
		$result = mysql_query($sql);
		
		if(mysql_num_rows($result) > 0)
		{
		while( $row = mysql_fetch_assoc($result)) 
		{	
		$output['data'][] = $row;
		}
		print(json_encode($output));
		}
		else
		{
			echo"";
		}
	}

}
?>

