<?php
require_once 'dbconfig.php';
$conn = mysql_connect("$host","$username","$password");
mysql_select_db("$dbname");

$did = $_POST['v1'];

echo $did=000;
if($did!="")
{

		$str = "delete FROM tbl_uploads WHERE Upload_ID=$did";
		
echo $str;
		 if(mysql_query($str))
		{
			echo "Deleted";
		}
		else
		{
			echo "Error";
		}
	
?>
  