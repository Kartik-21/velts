<?php
$con = mysqli_connect('localhost','skinfoso','e#EY1C9ip)c1C3','skinfoso_velts');

if(!empty($_REQUEST))
{
   $Date = date_default_timezone_set('Asia/Kolkata');

	$lat = @$_REQUEST['lat'];
	$lon = @$_REQUEST['lon'];
	$pid = @$_REQUEST['pid'];
	$ptime = date("g:i a");

	
	
	$deg = substr($lat,0,2);

	$min_temp = explode(',',substr($lat, 2));

	$min = $min_temp[0];

	$lat = round($deg+($min/60),6);


	
		$lon = substr($lon,1);
	$deg = substr($lon,0,2);

	$min_temp = explode(',',substr($lon, 2));

	$min = $min_temp[0];

	$lon = round($deg+($min/60),6);
	
	
	
	
	$sql = "UPDATE plcc SET lat='".$lat."',lon='".$lon."',pid='".$pid."',currtime='".$ptime."' WHERE pid=".$pid; 




mysqli_query($con, $sql);
}
?>