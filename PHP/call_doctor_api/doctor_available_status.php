<?php
include 'db.php';

// make this as post method
$doc_id = trim($_POST['doc_id']);


mysqli_set_charset($con, "utf8");
$list = array();

$query = $con->query("SELECT * FROM `doctor_absent` WHERE `doc_id` = $doc_id");
	if ($query->num_rows > 0) {

		while ($rows = mysqli_fetch_array($query))
		{	
			$days['days'] = $rows['days'];
			$days['dates'] = $rows['dates'];
			$days['range'] = $rows['range'];
		
		}
		
		echo json_encode($days);

	}else{
			$days['days'] = '-1';
			$days['dates'] = '-1';
			$days['range'] = '-1';
			echo json_encode($days);
	}



?>
