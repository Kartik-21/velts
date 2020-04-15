<?php

require_once 'dbconfig.php';

$conn = mysql_connect("$host","$username","$password");
mysql_select_db("$dbname");

$email =$_GET['uid'];

if($email!="")
	{
			$query = "SELECT * FROM tbl_register WHERE Email='$email'";	
			$result = mysql_query($query);	

			if (mysql_num_rows($result) > 0) 
			{
				$row = mysql_fetch_array($result, MYSQLI_ASSOC);
				$uid=$row['User_ID'];		
			}
			else
			{	
			//	echo"error1";
				$uid="";
			}	
	}
	
	
if($uid!="")
{
		$query = "SELECT * FROM tbl_uploads WHERE User_ID='$uid'";
		$result = mysql_query($query);

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
			echo "";
		}
			
}


?>

