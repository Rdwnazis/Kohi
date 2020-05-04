<?php
    if ($_SERVER['REQUEST_METHOD'] =='POST'){
        $fullname = $_POST['fullname'];
        $email = $_POST['email'];
        $phonenumber = $_POST['phonenumber'];
        $password = $_POST['password'];

        $password = password_hash($password, PASSWORD_DEFAULT);

        require_once 'connect.php';
        
        $sql = "INSERT INTO user (fullname, email, phonenumber, password) VALUES ('$fullname', '$email', '$phonenumber', '$password')";

        if(mysqli_query($conn, $sql) ){
            $result["success"] = "1";
            $result["message"] = "success";

            echo json_encode($result);
            mysqli_close($conn);
        } else{
            $result["success"] = "0";
            $result["message"] = "error";

            echo json_encode($result);
            mysqli_close($conn);
        }


    }

?>