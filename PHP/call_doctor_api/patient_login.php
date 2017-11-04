<?php 
            
	include 'db.php';

	if (isset($_POST['email']) and isset($_POST['password'])) {
        
	// make this as post method

	$email = trim($_POST['email']);
	$password = trim($_POST['password']);

	mysqli_set_charset($con,"utf8");
	

	$db_id = "";
	$db_email = "";
	$db_pass = "";
	

	$query = $con->query("SELECT id, email, password FROM `patient` WHERE email = '$email'");

	if($query ->num_rows > 0) {

			while($rows = mysqli_fetch_array($query)){
				$db_id = $rows['id'];
				$db_email = $rows['email'];
				$db_pass = $rows['password'];

				if($email == $db_email  && $password == $db_pass){
					$response["error"] = FALSE;
					$response["msg"] = "Login succesfull";
					$response["user_id"] = $db_id;
					echo json_encode($response);
				}
				else{
					
					$response["error"] = TRUE;
					$response["msg"] = "Email or password not matched";
					echo json_encode($response);
				}
			}
		
			

	} else{
		// user not found
        $response["error"] = TRUE;
        $response["msg"] = "User not found";
		echo json_encode($response);
	}
}

?>