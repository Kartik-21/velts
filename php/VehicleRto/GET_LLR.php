<?php

require_once 'dbconfig.php';

$conn = mysql_connect("$host","$username","$password");
mysql_select_db("$dbname");

$uid =$_GET['uid'];

if($uid!="")
{

		$sql = "SELECT LLR_ID,FName,LName,tbl_llr.Aadhar,BDate,tbl_district.District_Name,tbl_llr.District_ID,tbl_city.City_Name,tbl_llr.City_ID,tbl_llr.Address,tbl_llr.Pincode,VType,BGroup,tbl_llr.User_ID,tbl_llr.Status 
				FROM tbl_llr 
				INNER JOIN tbl_district ON tbl_district.District_ID=tbl_llr.District_ID
				INNER JOIN tbl_city ON tbl_city.City_ID=tbl_llr.City_ID
				WHERE User_ID='$uid'";

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

