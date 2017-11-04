<?php 
            
	include 'db.php';
	// make this as post method
	$dis = trim($_POST['dist']);
	$divi = trim($_POST['divi']);
	//$spe= trim($_GET['spe']);

	mysqli_set_charset($con,"utf8");

	//echo $spe;
	//echo $dist . '<br>';
	$result = '';

	if($dis == 0 && $divi == 0){

		$result = mysqli_query($con,"SELECT diagnostic_center.id as id, diagnostic_center.name as name, diagnostic_center.phone as phone, diagnostic_center.address as address from diagnostic_center, district, division
									where diagnostic_center.district_id = district.id
									AND diagnostic_center.division_id = division.id");

	} else if($dis != 0 && $divi == 0){

		$result = mysqli_query($con,"SELECT diagnostic_center.id as id, diagnostic_center.name as name, diagnostic_center.phone as phone, diagnostic_center.address as address from diagnostic_center, district, division
									where diagnostic_center.district_id = district.id
									AND diagnostic_center.division_id = division.id
									AND diagnostic_center.district_id = '$dis' ");

	} else if($dis == 0 && $divi != 0){

		$result = mysqli_query($con,"SELECT diagnostic_center.id as id, diagnostic_center.name as name, diagnostic_center.phone as phone, diagnostic_center.address as address from diagnostic_center, district, division
									where diagnostic_center.district_id = district.id
									AND diagnostic_center.division_id = division.id
									AND diagnostic_center.division_id = '$divi' ");


	}else{
		$result = mysqli_query($con,"SELECT diagnostic_center.id as id, diagnostic_center.name as name, diagnostic_center.phone as phone, diagnostic_center.address as address from diagnostic_center, district, division
									where diagnostic_center.district_id = district.id
									AND diagnostic_center.division_id = division.id
									AND diagnostic_center.division_id = '$divi' 
									AND diagnostic_center.district_id = '$dis' ");
	}
	
	//echo $result . ' <br>';
	
	$diagnostic_centers = array();

	$total_rows = $result ->num_rows;

	if($total_rows > 0){
		while($rows = mysqli_fetch_array($result)){

			$hos_info['id'] = $rows['id'];
			$hos_info['name'] = $rows['name'];
			$hos_info['phone'] = $rows['phone'];
			$hos_info['address'] = $rows['address'];

			array_push($diagnostic_centers, $hos_info);

		}
		echo json_encode(array_merge(array('type' => TRUE, 'result' => $diagnostic_centers)), JSON_PRETTY_PRINT);
		
	} else {
		// No data found
		$response["type"] = FALSE;
		$response["msg"] = "No data found!";
		echo json_encode($response);
	}
?>