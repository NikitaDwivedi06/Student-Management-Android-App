<?php

include_once 'connection.php';
//Adding header to indicate that the response is in json format
header('Content-Type: application/json');

class User {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
			#connection has now been established
		}
		
		public function does_user_exist($username,$password)
		{
			$query = "Select * from USERS where username='$username' and password = '$password' ";
			//$query = "Select * from USERS  ";			
			$result = mysqli_query($this->connection, $query);
			//echo("  After query execution   ");
			if(mysqli_num_rows($result)>0)
			{
				#user exists and has entered correct credentials				
				$json['success'] = ' Welcome '.$username;
				echo json_encode($json);
				#echo("Success");
				#echo($result);
				mysqli_close($this -> connection);
			}
			else
			{
				#Incorrect credentials
				$json['error'] = 'Wrong credentials';
				
				echo json_encode($json);
				mysqli_close($this->connection);
			}
			
		}
		
	}
	
#########################End of user class#################################

	
	$user = new User();			#This calls the constructor which connects to the database
	
	//REMEMBER TO CONVERT THIS TO 'POST'
	if(isset($_POST['username'],$_POST['password'])) 
	{	
				
		#extract parameters from the HTTP POST Request
		$usernameInRequest = $_POST['username'];
		$passwordInRequest = $_POST['password'];
		#echo($usernameInRequest);
		#echo($passwordInRequest);
		if(!empty($usernameInRequest) && !empty($passwordInRequest))
		{
			
			$encrypted_password = md5($passwordInRequest);		#Encrypt the password
			$user-> does_user_exist($usernameInRequest,$passwordInRequest);
			
		}
		else
		{
			echo json_encode("Fields can't be null!");
		}
		
	}


?>
