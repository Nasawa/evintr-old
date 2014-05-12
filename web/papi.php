<?php 

  //--------------------------------------------------------------------------
  // Example php script for fetching data from mysql database
  //--------------------------------------------------------------------------
  ini_set('memory_limit', -1);
  $host = "###################";
  $user = "###################";
  $pass = "###################";

  $databaseName = "###################";
  $tableName = "###################";

  $param = explode("?", $_SERVER['REQUEST_URI']);
  
  if ($param[2] == "1")
  	$tableName = "private";

  //--------------------------------------------------------------------------
  // 1) Connect to mysql database
  //--------------------------------------------------------------------------
  //include 'DB.php';
  $con = mysql_connect($host,$user,$pass);
  $dbs = mysql_select_db($databaseName, $con);

  //--------------------------------------------------------------------------
  // 2) Query database for data
  //--------------------------------------------------------------------------
  $result = mysql_query("SELECT * FROM $tableName WHERE ID = $param[1]");          //query

  $data = array();
  while ( $row = mysql_fetch_row($result) )
  {
    $data[] = $row;
  }  

  //--------------------------------------------------------------------------
  // 3) echo result as json 
  //--------------------------------------------------------------------------
  echo json_encode($data);

?>