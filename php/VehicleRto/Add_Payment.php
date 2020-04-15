<?php
require_once 'dbconfig.php';

$conn = mysql_connect("$host","$username","$password");
mysql_select_db("$dbname");

$uid = $_POST['v1'];
$amount = $_POST['v2'];
$date = $_POST['v3'];
$time = $_POST['v4'];
$type = $_POST['v5'];
$ssdate = $_POST['v6'];

if($type=="Wallet")
{
if($uid!="")
{

		$query = "SELECT * FROM tbl_register WHERE User_ID='$uid' ";
		
		$result = mysql_query($query);	
		if (mysql_num_rows($result) > 0) 
		{
			$row = mysql_fetch_array($result, MYSQLI_ASSOC);
			$wamount=$row['Wallet'];	
		}
		else
		{
			$uid="";
			echo "No";
		}

	if($wamount!="")
	{

						$sql = "INSERT INTO tbl_payment(User_ID,Amount,Date,Time) VALUES 
						('$uid','$amount','$date','$time')";
					//	echo $sql;
						if(mysql_query($sql))
						{
							$fwallet = $wamount + $amount;
							
							$sql = "UPDATE tbl_register SET Wallet= '$fwallet' WHERE User_ID='$uid'";
			
							if(mysql_query($sql))
							{
								echo "Added..!";
							}
							else
							{
								echo"ERROR ";
							}
						}
						else
						{
							echo"ERROR";
						}
	}
}
}

else if($type=="Payment")
{
				$sql = "UPDATE tbl_user_penalty SET Status= 'Paid', Payment_Date='$date', Time='$time' WHERE User_ID='$uid' AND Date='$ssdate'";
			
				if(mysql_query($sql))
				{
					echo "Added..!";
				}
				else
				{
					echo"ERROR ";
				}
}
else if($type=="WP")
{
		$query = "SELECT * FROM tbl_register WHERE User_ID='$uid' ";
		
		$result = mysql_query($query);	
		if (mysql_num_rows($result) > 0) 
		{
			$row = mysql_fetch_array($result, MYSQLI_ASSOC);
			$wamount=$row['Wallet'];	
		}
		else
		{
			$uid="";
			echo "No";
		}
		
		if($wamount!="")
		{
			$fwallet = $wamount - $amount;
						
				$sql = "UPDATE tbl_register SET Wallet='$fwallet' WHERE User_ID='$uid'";
			
				if(mysql_query($sql))
				{
					
					$sql = "UPDATE tbl_user_penalty SET Status= 'Paid', Payment_Date='$date', Time='$time' WHERE User_ID='$uid' AND Date='$ssdate'";
			
					if(mysql_query($sql))
					{
						echo "Done";
					}
					else
					{
						echo"ERROR ";
					}
				}
				else
				{
					echo"ERROR ";
				}
		}
				
				
}

mysql_close($conn);
?>