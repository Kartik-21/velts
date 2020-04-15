<?php

require_once 'dbconfig.php';

$conn = mysql_connect("$host","$username","$password");
mysql_select_db("$dbname");

$email =$_GET['email'];

if($email!="")
{

		$sql = "SELECT * FROM tbl_register WHERE Email='$email' OR Mobile='$email' OR Aadhar='$email' OR vehicalno='$email'";

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
			$sql = "SELECT * FROM tbl_employee WHERE Email='$email'";

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

