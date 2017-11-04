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

		$result = mysqli_query($con,"SELECT hospital.id as id, hospital.name as name, hospital.phone as phone, hospital.address as address from hospital, district, division
									where hospital.district_id = district.id
									AND hospital.division_id = division.id");

	} else if($dis != 0 && $divi == 0){

		$result = mysqli_query($con,"SELECT hospital.id as id, hospital.name as name, hospital.phone as phone, hospital.address as address from hospital, district, division
									where hospital.district_id = district.id
									AND hospital.division_id = division.id
									AND hospital.district_id = '$dis' ");

	} else if($dis == 0 && $divi != 0){

		$result = mysqli_query($con,"SELECT hospital.id as id, hospital.name as name, hospital.phone as phone, hospital.address as address from hospital, district, division
									where hospital.district_id = district.id
									AND hospital.division_id = division.id
									AND hospital.division_id = '$divi' ");


	}else{
		$result = mysqli_query($con,"SELECT hospital.id as id, hospital.name as name, hospital.phone as phone, hospital.address as address from hospital, district, division
									where hospital.district_id = district.id
									AND hospital.division_id = division.id
									AND hospital.division_id = '$divi' 
									AND hospital.district_id = '$dis' ");
	}
	
	//echo $result . ' <br>';
	
	$hospitals = array();

	$total_rows = $result ->num_rows;

	if($total_rows > 0){
		while($rows = mysqli_fetch_array($result)){

			$hos_info['id'] = $rows['id'];
			$hos_info['name'] = $rows['name'];
			$hos_info['phone'] = $rows['phone'];
			$hos_info['address'] = $rows['address'];

			array_push($hospitals, $hos_info);

		}
		echo json_encode(array_merge(array('type' => TRUE, 'result' => $hospitals)), JSON_PRETTY_PRINT);
		
	} else {
		// No data found
		$response["type"] = FALSE;
		$response["msg"] = "No data found!";
		echo json_encode($response);
	}
?>