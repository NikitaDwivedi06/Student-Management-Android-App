<?php

include_once 'connection.php';
//Adding header to indicate that the response is in json format
header('Content-Type: application/json');

class Student {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
			#connection has now been established
		}
		
		public function InsertStudent($enrollmentid,$name,$course,$phone,$email,$address,$gender,$dob)
		{
			
			#echo("Before query");
			$query = "INSERT INTO STUDENTS VALUES ('$enrollmentid','$name','$course',$phone,'$email','$address','$gender','$dob')";	
			#echo $query;			
			#$query = "INSERT INTO STUDENTS (`ENROLLMENTID`,`NAME`,`COURSE`,`PHONE`,`EMAIL`,`ADDRESS`,`GENDER`,`DOB`) VALUES ('$enrollmentid','$name','$course',$phone,'$email','$address','$gender','$dob')";
			//$query = "Select * from USERS  ";			
			$result = mysqli_query($this->connection, $query);
			#echo("  After query execution \n  ");
			if($result)
			{
				#Record inserted successfully				
				$json['success'] = 'Student created';
				#echo("Success");
				echo json_encode($json);
				mysqli_close($this -> connection);
			}
			else
			{
				
				$json['error'] = 'Something went wrong, please try again';
				
				echo json_encode($json);
				mysqli_close($this->connection);
			}
			
		}

		public function RetrieveStudent($enrollmentid)
		{
			
			#echo("Before query");
			$query = " SELECT * FROM STUDENTS WHERE ENROLLMENTID='$enrollmentid' ";				
						
			$result = mysqli_query($this->connection, $query);
			#echo("  After query execution \n  ");
			
			if(mysqli_num_rows($result)>0)
			{
				
				
				while($row = mysqli_fetch_array($result)) {
     				   #echo $row[7];
			           $newstring = $row[0] ."\n". $row[1] ."\n".$row[2] ."\n". $row[3] ."\n". $row[4] ."\n". $row[5] ."\n". $row[6] ."\n". $row[7] ."\n"; 
				   #$json['success'] = $row[0];
    				}
				
				#echo $row[1];
				#echo json_encode($json);
				$json['success'] = $newstring;
				echo json_encode($json);
				mysqli_close($this -> connection);
			}
			else
			{
				
				$json['error'] = 'Student does not exist';
				
				echo json_encode($json);
				mysqli_close($this->connection);
			}
			
		}
		public function RetrieveStudentOnNamePhone($name,$phone)
		{
			
			#echo("Before query");
			$query = " SELECT * FROM STUDENTS WHERE NAME='$name' AND PHONE=$phone ";				
						
			$result = mysqli_query($this->connection, $query);
			#echo("  After query execution \n  ");
			
			if(mysqli_num_rows($result)>0)
			{
				
				
				while($row = mysqli_fetch_array($result)) {
     				   #echo $row[7];
			           $newstring = $row[0] ."\n". $row[1] ."\n".$row[2] ."\n". $row[3] ."\n". $row[4] ."\n". $row[5] ."\n". $row[6] ."\n". $row[7] ."\n"; 
				   #$json['success'] = $row[0];
    				}
				
				#echo $row[1];
				#echo json_encode($json);
				$json['success'] = $newstring;
				echo json_encode($json);
				mysqli_close($this -> connection);
			}
			else
			{
				
				$json['error'] = 'Student does not exist';
				
				echo json_encode($json);
				mysqli_close($this->connection);
			}
			
		}

		public function DeleteStudent($enrollmentid)
		{
			
			#echo("Before query");
			$query = " SELECT NAME FROM STUDENTS WHERE ENROLLMENTID='$enrollmentid' ";				
						
			$result = mysqli_query($this->connection, $query);
			#echo("  After query execution \n  ");
			
			if(mysqli_num_rows($result)>0)
			{
				
				#Note: Delete query returns true even if student does not exist! FIX THIS
				$query2 = " DELETE FROM STUDENTS WHERE ENROLLMENTID='$enrollmentid' ";				
						
				$result2 = mysqli_query($this->connection, $query2);
				$json['success'] = "Student deleted successfully";
				echo json_encode($json);
				mysqli_close($this -> connection);
			}
			else
			{
				
				$json['error'] = 'Student does not exist';
				
				echo json_encode($json);
				mysqli_close($this->connection);
			}
			
		}

		public function DeleteStudentOnNamePhone($name,$phone)
		{	
			$query = " SELECT NAME FROM STUDENTS WHERE NAME='$name' AND PHONE=$phone ";				
						
			$result = mysqli_query($this->connection, $query);
			#echo("  After query execution \n  ");
			
			if(mysqli_num_rows($result)>0)
			{
				
				#Note: Delete query returns true even if student does not exist! FIX THIS
				$query2 = " DELETE FROM STUDENTS WHERE NAME='$name' AND PHONE=$phone ";				
						
				$result2 = mysqli_query($this->connection, $query2);
				$json['success'] = "Student deleted successfully";
				echo json_encode($json);
				mysqli_close($this -> connection);
			}
			else
			{
				
				$json['error'] = 'Student does not exist';
				
				echo json_encode($json);
				mysqli_close($this->connection);
			}
		}

		public function UpdateStudent($enrollmentid,$email,$address)
		{

			$query = " SELECT NAME FROM STUDENTS WHERE ENROLLMENTID='$enrollmentid' ";				
						
			$result = mysqli_query($this->connection, $query);
			if(mysqli_num_rows($result)>0)
			{
				$query2 = "UPDATE STUDENTS SET EMAIL='$email',ADDRESS='$address' WHERE ENROLLMENTID='$enrollmentid' ";
				$result2 = mysqli_query($this->connection, $query2);
				$json['success'] = "Student updated successfully";
				echo json_encode($json);
				mysqli_close($this -> connection);
			}
	
			else
			{
				
				$json['error'] = 'Student does not exist';
				
				echo json_encode($json);
				mysqli_close($this->connection);
			}
			
			
				
			
			
		}
		public function UpdateStudentOnNamePhone($name,$phone,$email,$address)
		{

			$query = " SELECT NAME FROM STUDENTS WHERE NAME='$name' AND PHONE=$phone ";				
						
			$result = mysqli_query($this->connection, $query);
			if(mysqli_num_rows($result)>0)
			{
				$query2 = "UPDATE STUDENTS SET EMAIL='$email',ADDRESS='$address' WHERE NAME='$name' AND PHONE=$phone ";
				$result2 = mysqli_query($this->connection, $query2);
				$json['success'] = "Student updated successfully";
				echo json_encode($json);
				mysqli_close($this -> connection);
			}
	
			else
			{
				
				$json['error'] = 'Student does not exist';
				
				echo json_encode($json);
				mysqli_close($this->connection);
			}
			
			
				
			
			
		}
		
	}
	
#########################End of user class#################################

	
	$student = new Student();			#This calls the constructor which connects to the database
	
	//REMEMBER TO CONVERT THIS TO 'POST'
	if(isset($_POST['enrollmentid'],$_POST['name'],$_POST['course'],$_POST['phone'],$_POST['email'],$_POST['address'],$_POST['gender'],$_POST['dob'],$_POST['insert_operation'])) 
	{	
				
		#echo("HI");		
		#extract parameters from the HTTP POST Request
		#echo("Before reading data");
		$enrollmentid = $_POST['enrollmentid'];
		$name = $_POST['name'];
		$course = $_POST['course'];
		$phone = $_POST['phone'];
		$email = $_POST['email'];
		$address = $_POST['address'];
		$gender = $_POST['gender'];
		$dob = $_POST['dob'];
		#echo("HI");
		$insert = $_POST['insert_operation'];
		#echo($usernameInRequest);
		#echo($passwordInRequest);
		#echo("Read post data");
		if(!empty($enrollmentid) && !empty($name) && !empty($course) && $insert=="Yes")
		{
			
			#$encrypted_password = md5($passwordInRequest);		#Encrypt the password
			$student-> InsertStudent($enrollmentid,$name,$course,$phone,$email,$address,$gender,$dob);
			
		}
		else
		{
			echo json_encode("First three fields can't be null!");
		}
		
	}
	else if(isset($_POST['enrollmentid'],$_POST['email'],$_POST['address'],$_POST['update_operation']))
	{
		#echo("HI");		
		$enrollmentid = $_POST['enrollmentid'];
		$email = $_POST['email'];
		$address = $_POST['address'];
		$operation = $_POST['update_operation'];
		if(!empty($enrollmentid) && $operation=="Update")
		{	
			$student->UpdateStudent($enrollmentid,$email,$address);
		}
		else
		{
			$json['error'] = "Fields can't be null";
				
			echo json_encode($json);
		}
	}
	else if(isset($_POST['enrollmentid'],$_POST['operation']))
	{
		#echo("HI");		
		$enrollmentid = $_POST['enrollmentid'];
		$operation = $_POST['operation'];
		if(!empty($enrollmentid) && $operation=="Retrieve")
		{
			$student->RetrieveStudent($enrollmentid);
		}
		else if(!empty($enrollmentid) && $operation=="Delete")
		{
			$student->DeleteStudent($enrollmentid);
		}
		else
		{
			$json['error'] = "Fieldss can't be null";
				
			echo json_encode($json);
		}
	}
	else if(isset($_POST['name'],$_POST['phone'],$_POST['operation']))
	{
		#echo("HI");		
		$name = $_POST['name'];
		$phone = $_POST['phone'];
		$operation = $_POST['operation'];
		
		if(!empty($name) && !empty($phone) && $operation=="Delete")
		{
			$student->DeleteStudentOnNamePhone($name,$phone);
		}
		if(!empty($name) && !empty($phone) && $operation=="Retrieve")
		{
			$student->RetrieveStudentOnNamePhone($name,$phone);
		}
		else
		{
			$json['error'] = "Fieldss can't be null";
				
			echo json_encode($json);
		}
	}
	else if(isset($_POST['name'],$_POST['phone'],$_POST['email'],$_POST['address'],$_POST['update_operation']))
	{
		#echo("HI");		
		$name = $_POST['name'];
		$phone = $_POST['phone'];
		$email = $_POST['email'];
		$address = $_POST['address'];
		$operation = $_POST['update_operation'];  #echo("HI");
		if(!empty($name) &&  !empty($phone) && $operation=="Update")
		{	
			$student->UpdateStudentOnNamePhone($name,$phone,$email,$address);
		}
		else
		{
			$json['error'] = "Fields can't be null";
				
			echo json_encode($json);
		}
	}
	




?>
