<?php
require_once 'dbconfig.php';

$conn = mysql_connect("$host","$username","$password");
mysql_select_db("$dbname");

$fname = $_POST['v1'];
$lname = $_POST['v2'];
$aadhar = $_POST['v3'];
$bdate = $_POST['v4'];
$did = $_POST['v5'];
$cid = $_POST['v6'];
$address = $_POST['v7'];
$pincode = $_POST['v8'];
$vtype =$_POST['v9'];
$bgrp =$_POST['v10'];
$uid =$_POST['v11'];
$post_date =date("Y-m-d H:i:s");


if($aadhar!="")
{
	$query = "SELECT Aadhar FROM tbl_llr WHERE Aadhar='$aadhar'";
	$result = mysql_query($query);

	if(mysql_num_rows($result) > 0)	
	{
    	$row = mysql_fetch_array($result, MYSQL_ASSOC);
		echo"Aadhar is Already Registered.";	
	}
	else
	{
						$sql = "INSERT INTO tbl_llr(FName,LName,Aadhar,BDate,District_ID,City_ID,Address,Pincode,VType,BGroup,User_ID,Status,Apply_Date) VALUES 
						('$fname','$lname','$aadhar','$bdate','$did','$cid','$address','$pincode','$vtype','$bgrp','$uid','Pending','$post_date')";
						//echo $sql;
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