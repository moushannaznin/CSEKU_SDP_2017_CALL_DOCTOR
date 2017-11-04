<?php 
            
	include 'db.php';
	// make this as post method

	$name = trim($_POST['name']);
	$phone = trim($_POST['phone']);
	$email = trim($_POST['email']);
	$password = trim($_POST['pass']);
	$degree = trim($_POST['degree']);
	$latitude = trim($_POST['latitude']);
	$longitude = trim($_POST['longitude']);
	$address = trim($_POST['address']);
	$division_id = trim($_POST['division_id']);
	$district_id = trim($_POST['district_id']);

	$spec_id = trim($_POST['spec_id']);
	$spec_id_array = explode(',', $spec_id);

	mysqli_set_charset($con,"utf8");
	

	//echo $spe;
	//echo $dist . '<br>';
	
	

	/*$result = mysqli_query($con,"select doctor.name as doc_name, doctor.id as id, district.name as district, specialist.name as specialist
		from doctor, specialist, doctor_specialist_relation, district, division 
		where doctor_specialist_relation.doctor_id = doctor.id and specialist.id = doctor_specialist_relation.specialist_id and doctor.district_id = district.id and doctor.division_id=division.id and doctor.district_id = '$dist' ");
	*/

	//SELECT * FROM `doctor` WHERE phone = '01912463200'

	$search_doc = $con->query("SELECT * FROM `doctor` WHERE email = '$email'");
	if($search_doc ->num_rows == 0) {

		// new doctor
		$insert_doc = "INSERT INTO `doctor` (`id`, `name`,`email`,`password`, `phone`, `degree`, `latitude`, `longitude`, `address`, `district_id`, `division_id`) VALUES (NULL, '$name', '$email', '$password', '$phone', '$degree', '$latitude', '$longitude', '$address', '$district_id', '$division_id')";


		if ($con->query($insert_doc) === TRUE) {
				$doc_id = $con->insert_id;

					foreach($spec_id_array as $item) {

						$query = "INSERT INTO `doctor_specialist_relation` (`id`, `doctor_id`, `specialist_id`) VALUES (NULL, '$doc_id', '$item')";
						if ($con->query($query) === TRUE) {
							//Inserted
							$response["type"] = TRUE;
							$response["msg"] = "Registration successfull";
							echo json_encode($response);
						}else{
							$response["type"] = FALSE;
							$response["msg"] = "Server error";
							echo json_encode($response);
						}

					    
					}

				} else {
					// echo "Error: " . $insert_doc . "<br>" . $con->error;
					$response["type"] = FALSE;
					$response["msg"] = "Error: " . $insert_doc . "" . $con->error;
					echo json_encode($response);
				}

	} else{
		// user already existed
        $response["type"] = FALSE;
        $response["msg"] = "User already exist";
		echo json_encode($response);
	}


?>