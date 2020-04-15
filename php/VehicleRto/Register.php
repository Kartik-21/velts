<?php
require_once 'dbconfig.php';

$conn = mysql_connect("$host","$username","$password");
mysql_select_db("$dbname");

$name = $_POST['v1'];
$did = $_POST['v2'];
$cid = $_POST['v3'];
$address = $_POST['v4'];
$email =$_POST['v5'];
$mobile =$_POST['v6'];
$password =$_POST['v7'];
$aadhar =$_POST['v8'];
$vehicalno =$_POST['vehicalno'];


if($name!="")
{
	$query = "SELECT Email FROM tbl_register WHERE Email='$email'";
	$result = mysql_query($query);

	if(mysql_num_rows($result) > 0)	
	{
    	$row = mysql_fetch_array($result, MYSQL_ASSOC);
		echo"Email is Already Registered.";	
	}
	else
	{
		$query = "SELECT Mobile FROM tbl_register WHERE Mobile='$mobile'";
		$result = mysql_query($query);

		if(mysql_num_rows($result) > 0)	
		{
			$row = mysql_fetch_array($result, MYSQL_ASSOC);
			echo"Mobile is Already Registered.";	
		}
		else
		{
				$query = "SELECT Aadhar FROM tbl_register WHERE Aadhar='$aadhar'";
				$result = mysql_query($query);

				if(mysql_num_rows($result) > 0)	
				{
					$row = mysql_fetch_array($result, MYSQL_ASSOC);
					echo"Aadhar number is Already Registered.";	
				}
				else
				{
						$sql = "INSERT INTO tbl_register(Name,District_ID,City_ID,Address,Aadhar,Email,Mobile,Password,Wallet, vehicalno) 
						VALUES ('$name','$did','$cid','$address','$aadhar','$email','$mobile','$password','0','$vehicalno')";
						//echo $sql;
						if(mysql_query($sql))
						{
							echo "Registration Successful..!";
						}
						else
						{
							echo"ERROR";
						}
				}				
		}
	}
}
mysql_close($conn);
?>