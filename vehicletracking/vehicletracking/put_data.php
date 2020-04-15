<?php
//////////////////////////////////////////////////////////////////////////
$con = mysqli_connect('localhost','skinfoso','e#EY1C9ip)c1C3','skinfoso_velts');
///////////////////////////////////////////////////////////////////////////
if(!empty($_REQUEST))
{
    
     $Date = date_default_timezone_set('Asia/Kolkata');

$acc = @$_REQUEST['acc'];
$acl = @$_REQUEST['acl'];
$vib = @$_REQUEST['vib'];
$ptime = date("g:i a");


$query = "INSERT INTO `mav` (`acc`, `acl`, `vib`,`currtime`)
 VALUES ('".$acc."', '".$acl."', '".$vib."', '".$ptime."')";

mysqli_query($con,$query);

///////////////////////////////////////////////////////////////////////
}
?>
