<?php

require_once 'dbconfig.php';

$conn = mysql_connect("$host","$username","$password");
mysql_select_db("$dbname");

$uid =$_GET['uid'];
$date =$_GET['date'];

if($uid!="")
{

		$sql = "SELECT UP_ID,User_ID,RID,PID,tbl_penalty.Fine,Date,Status,tbl_penalty.Offence,tbl_penalty.Section 
				FROM tbl_user_penalty 
				INNER JOIN tbl_penalty ON tbl_penalty.P_ID=tbl_user_penalty.PID
				WHERE User_ID='$uid' AND Date='$date'";

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
?>

