<?php

require_once 'dbconfig.php';

$conn = mysql_connect("$host","$username","$password");
mysql_select_db("$dbname");

$did =$_GET['did'];

if($did!="")
{

		$sql = "SELECT * FROM tbl_city WHERE District_ID='$did'";

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

