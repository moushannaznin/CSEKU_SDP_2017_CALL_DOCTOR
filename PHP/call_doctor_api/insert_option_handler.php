<?php 
            
	include 'db.php';
	// make this as post method
	

	$req_type = trim($_POST['req_type']);

	$name = trim($_POST['name']);
	$phone = trim($_POST['phone']);
	$address = trim($_POST['address']);
	$district_id = trim($_POST['district_id']);
	$division_id = trim($_POST['division_id']);

	mysqli_set_charset($con,"utf8");
	
	if($req_type == 1){
	//Hospital
	$insert_pt = "INSERT INTO `hospital` (`id`, `name`,`phone`, `address`, `district_id`, `division_id`) VALUES (NULL, '$name', '$phone', '$address', '$district_id', '$division_id')";


	if ($con->query($insert_pt) === TRUE) {
			//Inserted
			$response["type"] = TRUE;
			$response["msg"] = "Successfully added";
			echo json_encode($response);

			} else {
				$response["type"] = FALSE;
				$response["msg"] = "Server error";
				echo json_encode($response);
			}
		
	} else if($req_type == 2){
	//Ambulance
	$insert_pt = "INSERT INTO `ambulance` (`id`, `name`,`phone`, `address`, `district_id`, `division_id`) VALUES (NULL, '$name', '$phone', '$address', '$district_id', '$division_id')";


	if ($con->query($insert_pt) === TRUE) {
			//Inserted
			$response["type"] = TRUE;
			$response["msg"] = "Successfully added";
			echo json_encode($response);

			} else {
				$response["type"] = FALSE;
				$response["msg"] = "Server error";
				echo json_encode($response);
			}
		
	} else if ($req_type == 3){
	//Diagnostic Center
	$insert_pt = "INSERT INTO `diagnostic_center` (`id`, `name`,`phone`, `address`, `district_id`, `division_id`) VALUES (NULL, '$name', '$phone', '$address', '$district_id', '$division_id')";


	if ($con->query($insert_pt) === TRUE) {
			//Inserted
			$response["type"] = TRUE;
			$response["msg"] = "Registration successfull";
			echo json_encode($response);

			} else {
				$response["type"] = FALSE;
				$response["msg"] = "Server error";
				echo json_encode($response);
			}
		
	}

?>