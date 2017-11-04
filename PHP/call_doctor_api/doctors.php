<?php 
            
	include 'db.php';
	// make this as post method
	$spe = trim($_POST['spe']);
	$dis = trim($_POST['dist']);
	$divi = trim($_POST['divi']);
	//$spe= trim($_POST['spe']);

	mysqli_set_charset($con,"utf8");

	//echo $spe;
	//echo $dist . '<br>';
	$result = '';

	if($dis == 0 && $divi == 0 && $spe != 'all'){
		
		$spec_id_array = explode(',', $spe);
		$query_part = "";
		foreach($spec_id_array as $item) {
			$query_part = $query_part . ' doctor_specialist_relation.specialist_id = ' . $item. ' or ';
		}
		//echo $query_part . '<br>';
		$query_part = preg_replace('/'. preg_quote(' or ', '/') . '$/', '', $query_part);
		//echo $query_part . '<br>';

		$result = mysqli_query($con,"select doctor.name as doc_name, doctor.id as id, doctor.phone as phone, doctor.degree as degree, doctor.latitude as latitude, doctor.longitude as longitude, doctor.address as address, division.name as division, district.name as district, specialist.name as specialist
        from doctor, specialist, doctor_specialist_relation, district, division 
        where 
        doctor_specialist_relation.doctor_id = doctor.id 
        and specialist.id = doctor_specialist_relation.specialist_id 
        and doctor.district_id = district.id 
        and doctor.division_id=division.id 
        and ($query_part)");

	} 
	else if($dis == 0 && $divi != 0 && $spe == 'all'){
		//echo 'I am 0 !0 all';
		// all dis
		$result = mysqli_query($con,"select doctor.name as doc_name, doctor.id as id, doctor.phone as phone, doctor.degree as degree, doctor.latitude as latitude, doctor.longitude as longitude, doctor.address as address, division.name as division, district.name as district, specialist.name as specialist
		from doctor, specialist, doctor_specialist_relation, district, division 
		where doctor_specialist_relation.doctor_id = doctor.id and specialist.id = doctor_specialist_relation.specialist_id and doctor.district_id = district.id and doctor.division_id=division.id and doctor.division_id = '$divi' ");

	} else if($divi == 0 && $dis !=0 && $spe == 'all'){
		//echo 'I am 0 dis !0';
		// all divi
		$result = mysqli_query($con,"select doctor.name as doc_name, doctor.id as id, doctor.phone as phone, doctor.degree as degree, doctor.latitude as latitude, doctor.longitude as longitude, doctor.address as address, division.name as division, district.name as district, specialist.name as specialist
		from doctor, specialist, doctor_specialist_relation, district, division 
		where doctor_specialist_relation.doctor_id = doctor.id and specialist.id = doctor_specialist_relation.specialist_id and doctor.district_id = district.id and doctor.division_id=division.id and doctor.district_id = '$dist' ");


	} else if($spe == 'all'){
		//echo 'all';
		$result = mysqli_query($con,"select doctor.name as doc_name, doctor.id as id, doctor.phone as phone, doctor.degree as degree, doctor.latitude as latitude, doctor.longitude as longitude, doctor.address as address, division.name as division, district.name as district, specialist.name as specialist
				from doctor, specialist, doctor_specialist_relation, district, division 
				where doctor_specialist_relation.doctor_id = doctor.id and specialist.id = doctor_specialist_relation.specialist_id and doctor.district_id = district.id and doctor.division_id=division.id ");


	} else if($dis == 0 && $divi != 0){
		//echo 'I am 0 !0 ' . $spe . ' <br>';

		$spec_id_array = explode(',', $spe);
		$query_part = "";
		foreach($spec_id_array as $item) {
			$query_part = $query_part . ' doctor_specialist_relation.specialist_id = ' . $item. ' or ';
		}
		//echo $query_part . '<br>';
		$query_part = preg_replace('/'. preg_quote(' or ', '/') . '$/', '', $query_part);
		//echo $query_part . '<br>';

		$result = mysqli_query($con,"select doctor.name as doc_name, doctor.id as id, doctor.phone as phone, doctor.degree as degree, doctor.latitude as latitude, doctor.longitude as longitude, doctor.address as address, division.name as division, district.name as district, specialist.name as specialist
        from doctor, specialist, doctor_specialist_relation, district, division 
        where 
        doctor_specialist_relation.doctor_id = doctor.id 
        and specialist.id = doctor_specialist_relation.specialist_id 
        and doctor.district_id = district.id 
        and doctor.division_id=division.id 
        and doctor.division_id = '$divi'
        and ($query_part)");

	} else if($divi == 0 && $dis !=0 ){
		$spec_id_array = explode(',', $spe);
		$query_part = "";
		foreach($spec_id_array as $item) {
			$query_part = $query_part . ' doctor_specialist_relation.specialist_id = ' . $item. ' or ';
		}
		//echo $query_part . '<br>';
		$query_part = preg_replace('/'. preg_quote(' or ', '/') . '$/', '', $query_part);
		//echo $query_part . '<br>';

		$result = mysqli_query($con,"select doctor.name as doc_name, doctor.id as id, doctor.phone as phone, doctor.degree as degree, doctor.latitude as latitude, doctor.longitude as longitude, doctor.address as address, division.name as division, district.name as district, specialist.name as specialist
        from doctor, specialist, doctor_specialist_relation, district, division 
        where 
        doctor_specialist_relation.doctor_id = doctor.id 
        and specialist.id = doctor_specialist_relation.specialist_id 
        and doctor.district_id = district.id 
        and doctor.division_id=division.id 
        and doctor.district_id = '$dis'
        and ($query_part)");
	} else{
		$spec_id_array = explode(',', $spe);
		$query_part = "";
		foreach($spec_id_array as $item) {
			$query_part = $query_part . ' doctor_specialist_relation.specialist_id = ' . $item. ' or ';
		}
		//echo $query_part . '<br>';
		$query_part = preg_replace('/'. preg_quote(' or ', '/') . '$/', '', $query_part);
		//echo $query_part . '<br>';

		$result = mysqli_query($con,"select doctor.name as doc_name, doctor.id as id, doctor.phone as phone, doctor.degree as degree, doctor.latitude as latitude, doctor.longitude as longitude, doctor.address as address, division.name as division, district.name as district, specialist.name as specialist
        from doctor, specialist, doctor_specialist_relation, district, division 
        where 
        doctor_specialist_relation.doctor_id = doctor.id 
        and specialist.id = doctor_specialist_relation.specialist_id 
        and doctor.district_id = district.id 
        and doctor.division_id=division.id 
        and doctor.division_id = '$divi'
        and doctor.district_id = '$dis'
        and ($query_part)");
	}
	
	//echo $result . ' <br>';
	
	$doctors = array();
	$spec = array();
	$name = '';
	$flag = 0;
	$doc_info['id'] = 0;
	$doc_info['name'] = "";
	$doc_info['phone'] = "";
	$doc_info['degree'] = "";
	$doc_info['latitude'] = "";
	$doc_info['longitude'] = "";
	$doc_info['address'] = "";
	$doc_info['division'] = "";
	$doc_info['district']  = "";

	//echo $result ->num_rows .' rows <br>';
	$total_rows = $result ->num_rows;
	$current_iteration = 1;
	
	if($total_rows > 0){
		
		while($rows = mysqli_fetch_array($result)){
		
	//echo $rows['specialist'] . ' -- spec<br>';
		//$name = $rows['doc_name'];

		//echo $doc_info['id'] . $doc_info['name'] .' -- <br>' ;

		if($flag == 0){
			$name = $rows['doc_name'];
			$doc_info['id'] = $rows['id'];
			$doc_info['name'] = $rows['doc_name'];
			$doc_info['phone'] = $rows['phone'];
			$doc_info['degree'] = $rows['degree'];
			$doc_info['latitude'] = $rows['latitude'];
			$doc_info['longitude'] = $rows['longitude'];
			$doc_info['address'] = $rows['address'];
			$doc_info['division'] = $rows['division'];
			$doc_info['district'] = $rows['district'];
			$spec[] = $rows['specialist'];
			//print_r(array_values($spec));
			//echo $spec.' <br>';
			$flag = $flag+1;
			if ($total_rows == 1) {
				$doc_info['spec'] = $spec;
				array_push($doctors, $doc_info);
			}
			//echo $rows['specialist'] . ' in flag <br>';
			//echo "I am in zero " . $flag . ' ' . $doc_info['id'] . $doc_info['name'] . ' <br> ';

		}
		else if($total_rows == $current_iteration && $name != $rows['doc_name']){
			//echo $rows['specialist']  . ' total ';
			$spec[] = $rows['specialist'];
			$doc_info['spec'] = $spec;
			array_push($doctors, $doc_info);
			$spec = array();

			$name = $rows['doc_name'];
			$doc_info['id'] = $rows['id'];
			$doc_info['name'] = $rows['doc_name'];
			$doc_info['phone'] = $rows['phone'];
			$doc_info['degree'] = $rows['degree'];
			$doc_info['latitude'] = $rows['latitude'];
			$doc_info['longitude'] = $rows['longitude'];
			$doc_info['address'] = $rows['address'];
			$doc_info['division'] = $rows['division'];
			$doc_info['district'] = $rows['district'];
			$spec[] = $rows['specialist'];
			$doc_info['spec'] = $spec;
			array_push($doctors, $doc_info);
		}else if($total_rows == $current_iteration){
			//echo $rows['specialist']  . ' total ';
			$spec[] = $rows['specialist'];
			$doc_info['spec'] = $spec;
			array_push($doctors, $doc_info);
			$spec = array();

			/*$name = $rows['doc_name'];
			$doc_info['id'] = $rows['id'];
			$doc_info['name'] = $rows['doc_name'];
			$doc_info['district'] = $rows['district'];
			$spec[] = $rows['specialist'];
			$doc_info['spec'] = $spec;
			array_push($doctors, $doc_info);*/
		}
		//specialist[] = $rows['specialist.name'];
		else if($name == $rows['doc_name']){
			//echo $rows['specialist'] . ' name ck <br>';;
			$spec[] = $rows['specialist'];

			//echo $spec.' <br>';
			//echo $doc_info['id'] . ' ' . $doc_info['name'] . $spec. ' else if <br>';
			//echo "I am in if " . $flag . ' <br>';

		}
		/*else if($name != $rows['doc_name'] && $total_rows == $current_iteration){
			$doc_info['spec'] = $spec;
			array_push($doctors, $doc_info);
			$spec = array();


			$name = $rows['doc_name'];
			$doc_info['id'] = $rows['id'];
			$doc_info['name'] = $rows['doc_name'];
			$doc_info['district'] = $rows['district'];
			$spec[] = $rows['specialist'];
			$doc_info['spec'] = $spec;
			array_push($doctors, $doc_info);

		}
		
		else if($name != $rows['doc_name'] ){
			$doc_info['spec'] = $spec;
			array_push($doctors, $doc_info);
			$spec = array();


			$name = $rows['doc_name'];
			$doc_info['id'] = $rows['id'];
			$doc_info['name'] = $rows['doc_name'];
			$doc_info['district'] = $rows['district'];
			$spec[] = $rows['specialist'];
			//$doc_info['spec'] = $spec;
			//array_push($doctors, $doc_info);

		} 
*/
		 else{
			//echo $rows['specialist'] . ' else <br>';;

			//echo $doc_info['id'] . ' ' . $doc_info['name'] . 'else <br>';

			$doc_info['spec'] = $spec;
			array_push($doctors, $doc_info);
			$spec = array();

			$name = $rows['doc_name'];
			$doc_info['id'] = $rows['id'];
			$doc_info['name'] = $rows['doc_name'];
			$doc_info['phone'] = $rows['phone'];
			$doc_info['degree'] = $rows['degree'];
			$doc_info['latitude'] = $rows['latitude'];
			$doc_info['longitude'] = $rows['longitude'];
			$doc_info['address'] = $rows['address'];
			$doc_info['division'] = $rows['division'];
			$doc_info['district'] = $rows['district'];
			$spec[] = $rows['specialist'];
			//echo "I am after pushing " . $flag . ' <br>';

		}
		

		
		//echo $rows['id'] . '<br>';
		//echo $rows['id'] . '<br>';
		
		$current_iteration ++;
		
	}
		//echo $spec;
		echo json_encode(array_merge(array('type' => TRUE, 'doc_list' => $doctors)), JSON_PRETTY_PRINT);
		
	} else {
		// No data found
		$response["type"] = FALSE;
		$response["msg"] = "No data found!";
		echo json_encode($response);
	}
?>