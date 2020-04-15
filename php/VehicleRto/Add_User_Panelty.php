<?php
require_once 'dbconfig.php';

$conn = mysql_connect("$host","$username","$password");
mysql_select_db("$dbname");

$sno = $_POST['v1'];
$pid = $_POST['v2'];
$fine = $_POST['v3'];
$date = $_POST['v4'];
$rid = $_POST['v5'];

if($sno!="")
{

		$query = "SELECT * FROM tbl_register WHERE Aadhar='$sno' OR Mobile='$sno' OR vehicalno='$sno' ";
		
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

						$sql = "INSERT INTO tbl_user_penalty(User_ID,PID,Fine,Date,Status,RID) VALUES 
						('$uid','$pid','$fine','$date','Pending','$rid')";
					//	echo $sql;
						if(mysql_query($sql))
						{
							echo "Added..!";
						}
						else
						{
							echo"ERROR";
						}
	}
}
mysql_close($conn);
?>