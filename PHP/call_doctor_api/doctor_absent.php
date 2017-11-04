<?php
include 'db.php';

// make this as post method

$doc_id = trim($_POST['doc_id']);
$days = trim($_POST['days']);
$dates = trim($_POST['dates']);
$range = trim($_POST['range']);

// need user id who is requesting -------------------
// insert to db

mysqli_set_charset($con, "utf8");
$donors = array();

$query = $con->query("SELECT * FROM `doctor_absent` WHERE `doc_id` = $doc_id");
	if ($query->num_rows == 0) {
		// let's insert this row
		$insert_query = "INSERT INTO `doctor_absent` (`id`, `doc_id`, `days`, `dates`, `range`) VALUES (NULL, '$doc_id', '$days', '$dates', '$range') ";
		if ($con->query($insert_query) === TRUE) {
			// inserted
			$response["error"] = False;
			$response["msg"] = "Successfully submitted";

		}else{
			$response["error"] = True;
			$response["msg"] = "Something went wrong!";
		}


	}else{
		// update row
		$update_query = "UPDATE `doctor_absent` SET `days` = '$days', `dates` = $dates, `range` = '$range' WHERE `doctor_absent`.`doc_id` = $doc_id ";
		if ($con->query($update_query) === TRUE) {
			// Update
			$response["error"] = False;
			$response["msg"] = "Successfully updated";

		}else{
			// failed
			$response["error"] = True;
			$response["msg"] = "Something went wrong!";
		}


	}
	echo json_encode($response);

?>
