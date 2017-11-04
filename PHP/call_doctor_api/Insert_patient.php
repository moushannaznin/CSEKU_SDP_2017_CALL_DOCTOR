<?php 
            
	include 'db.php';
	// make this as post method

	$name = trim($_POST['name']);
	$email = trim($_POST['email']);
	$password = trim($_POST['pass']);

	mysqli_set_charset($con,"utf8");
	

	$search_pt = $con->query("SELECT * FROM `patient` WHERE email = '$email'");
	if($search_pt ->num_rows == 0) {

		// new doctor
		$insert_pt = "INSERT INTO `patient` (`id`, `name`,`email`, `password`) VALUES (NULL, '$name', '$email', '$password')";
 

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

	} else{
		// user already existed
        $response["type"] = FALSE;
        $response["msg"] = "User already exist";
		echo json_encode($response);
	}


?>