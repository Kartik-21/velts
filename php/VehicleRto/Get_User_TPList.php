<?php

require_once 'dbconfig.php';

$conn = mysql_connect("$host","$username","$password");
mysql_select_db("$dbname");

$uid =$_GET['uid'];
$utype =$_GET['utype'];
$sstype =$_GET['sstype'];

if($utype=="Employee")
{

		$sql = "SELECT tbl_user_penalty.UP_ID,tbl_user_penalty.User_ID,tbl_user_penalty.RID,tbl_user_penalty.PID,Fine,tbl_user_penalty.Date,tbl_user_penalty.Status, 
		tbl_register.Name,tbl_register.Address FROM tbl_user_penalty INNER JOIN tbl_register ON tbl_register.User_ID=tbl_user_penalty.User_ID 
		WHERE RID='$uid' ORDER BY tbl_user_penalty.Date DESC";//GROUP BY tbl_user_penalty.Date DESC, tbl_register.Name";

	//	echo $sql;
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
			echo "";
		}


}
else if($utype=="User")
{
	if($sstype=="All")
	{
		$sql = "SELECT tbl_user_penalty.UP_ID,tbl_user_penalty.User_ID,tbl_user_penalty.RID,tbl_user_penalty.PID,Fine,tbl_user_penalty.Date,tbl_user_penalty.Status,
				tbl_register.Name,tbl_register.Address
				FROM tbl_user_penalty 
				INNER JOIN tbl_register ON tbl_register.User_ID=tbl_user_penalty.User_ID
				WHERE tbl_user_penalty.User_ID='$uid' GROUP BY tbl_user_penalty.Date DESC";

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
			echo "";
		}


	}
	else
	{
		$sql = "SELECT tbl_user_penalty.UP_ID,tbl_user_penalty.User_ID,tbl_user_penalty.RID,tbl_user_penalty.PID,Fine,tbl_user_penalty.Date,tbl_user_penalty.Status,
				tbl_register.Name,tbl_register.Address
				FROM tbl_user_penalty 
				INNER JOIN tbl_register ON tbl_register.User_ID=tbl_user_penalty.User_ID
				WHERE tbl_user_penalty.User_ID='$uid' AND tbl_user_penalty.Status='$sstype' GROUP BY tbl_user_penalty.Date DESC";

	//	echo $sql;
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
			echo "";
		}


	}
}
?>

