<?php
include 'db.php';

// make this as post method

$req_type = trim($_POST['req_type']);

// if req_type = 1 ->> inserting appoinmemt
// req params: user_id, doc_id, date
// if req_type = 2 ->> showing user list to the doc
// if req_type = 3 ->> changing req status/ approve / not
// if req_type = 4 ->> showing request status to user; approve or not
// need user id who is requesting -------------------
// insert to db

mysqli_set_charset($con, "utf8");

if ($req_type == 1)
{

	// code...

	$doc_id = trim($_POST['doc_id']);
	$user_id = trim($_POST['user_id']);
	$date = trim($_POST['date']);
	$insert_query = "INSERT INTO `appoinments_table` (`id`, `user_id`, `doc_id`, `date`, `status`) VALUES (NULL, '$user_id', '$doc_id', '$date', '0')";
	if ($con->query($insert_query) === TRUE)
	{
		// inserted
		$response["error"] = False;
		$response["msg"] = "Appointment successfull";
	}
	else
	{
		$response["error"] = True;
		$response["msg"] = "Something went wrong!";
	}
	echo json_encode($response);
}

// showing user list to the doctor

else if ($req_type == 2)
{

	// code...

	$doc_id = trim($_POST['doc_id']);
	$users = array();
	$userList_query = $con->query("select appoinments_table.id as id, appoinments_table.user_id as user_id, patient.name as name, appoinments_table.date as date, appoinments_table.status as status from appoinments_table, patient where appoinments_table.user_id = patient.id and appoinments_table.doc_id = $doc_id");
	//
	if ($userList_query->num_rows > 0)
	{
		while ($rows = mysqli_fetch_array($userList_query))
		{
			$user['id'] = $rows['id']; //this is request id
			$user['name'] = $rows['name'];
			$user['date'] = $rows['date'];
			$user['status'] = $rows['status'];
			array_push($users, $user);
		}

		echo json_encode(array('type' => TRUE, 'users' => $users) , JSON_PRETTY_PRINT);
	}
} // updating status by doctor
else if ($req_type == 3)
{

	$id = trim($_POST['apponment_id']);
	$status = trim($_POST['status']); // 0 wating; 1 accepted; 2 rejected
	$update_query = "UPDATE `appoinments_table` SET `status` = '$status' WHERE `appoinments_table`.`id` = $id";
	if ($con->query($update_query) === TRUE)
	{

		// inserted

		$response["error"] = False;
		$response["msg"] = "Updated";
	}
	else
	{
		$response["error"] = True;
		$response["msg"] = "Error in Update";
	}
	echo json_encode($response);
}

// showing status to the user

else if ($req_type == 4)
{

	// code...

	$user_id = trim($_POST['user_id']);
	$userList_query = $con->query("select appoinments_table.id as id, appoinments_table.doc_id as doc_id, doctor.name as name, appoinments_table.date as date, appoinments_table.status as status from appoinments_table, doctor where appoinments_table.doc_id = doctor.id and appoinments_table.user_id = $user_id ");

	//var_dump($userList_query);
	if ($userList_query->num_rows > 0)
	{
		$users = array();
		while ($rows = mysqli_fetch_array($userList_query))
		{
			$user['id'] = $rows['id']; //this is request id
			$user['doc_id'] = $rows['doc_id'];
			$user['name'] = $rows['name'];
			$user['date'] = $rows['date'];
			$user['status'] = $rows['status'];
			array_push($users, $user);
		}

		echo json_encode(array('type' => TRUE,'users' => $users) , JSON_PRETTY_PRINT);
	}
}

?>