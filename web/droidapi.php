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

  //--------------------------------------------------------------------------
  // 1) Connect to mysql database
  //--------------------------------------------------------------------------
  //include 'DB.php';
  $con = mysql_connect($host,$user,$pass);
  $dbs = mysql_select_db($databaseName, $con);

  //--------------------------------------------------------------------------
  // 2) Query database for data
  //--------------------------------------------------------------------------
  $result = mysql_query("SELECT * FROM $tableName ORDER BY Event_ZIP_Code LIMIT $param[1],100");          //query
  if(strlen($param[2]) > 1)
    $result = mysql_query("SELECT * FROM $tableName WHERE Event_ZIP_Code = $param[2] LIMIT $param[1],100");

  $data = array();
  while ( $row = mysql_fetch_row($result) )
  {
    $data[] = $row;
   // print_r($row);
  }  

  //--------------------------------------------------------------------------
  // 3) echo result as json 
  //--------------------------------------------------------------------------
  //$moo = explode("],\\[", json_encode($data));
  //echo $moo;
  echo count($data) . "%%%" . str_replace("],[", "%%%", json_encode($data));

?>