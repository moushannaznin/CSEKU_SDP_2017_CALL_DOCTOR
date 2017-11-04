<?php 
            
	include 'db.php';
        
	// make this as post method

	$email = trim($_POST['email']);
	$password = trim($_POST['password']);
	

	$db_email = "calldoctor@gmail.com";
	$db_pass = "1234";
	

	if($email == $db_email  && $password == $db_pass){
		$response["error"] = FALSE;
		$response["msg"] = "Login succesfull";
		echo json_encode($response);
	}
	else{
		
		$response["error"] = TRUE;
		$response["msg"] = "Email or password not matched";
		echo json_encode($response);
	}

?>