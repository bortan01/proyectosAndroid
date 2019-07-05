<?php

//$conexion = new mysqli('localhost', 'root', '', 'aerolinea');
$conexion = new mysqli('comues.com', 'comuesco_ma14049', 'MsA1!C%_kb(*', 'comuesco_ma14049');

if ($conexion->connect_errno) {
    echo "Error de conexion";
}

$accion = $_REQUEST["accion"];
if($accion == "consultar"){
     $criterio = $_REQUEST["criterio"];
  
    $result = $conexion->query("select * from datos where nombre like '%".$criterio."%'");
    if($result){
        while ($fila = $result->fetch_object()){
            $datos[]= $fila;
           
        }
        echo json_encode($datos);
    }
    
}

$accion = $_REQUEST["accion"];
if($accion == "consultarLogin"){
     $user = $_REQUEST["user"];
	 $pass = $_REQUEST["pass"];
  
  $sql = "SELECT * FROM pasajero where user = '".$user."' and pass = '".$pass."'";

    $result = $conexion->query($sql);
	if($result){
		while ($fila = $result->fetch_object()){
            $datos[]= $fila;
           
        }
		if(isset($datos)){
			echo json_encode($datos);
		}
	}
    
}

if($accion == "obtenerListaAvion"){
     
  
    $result = $conexion->query("select * from avion");
    if($result){
        while ($fila = $result->fetch_object()){
            $datos[]= $fila;
           
        }
        if(isset($datos)){
			echo json_encode($datos);
		}
    }
  
}

if($accion == "misVuelos"){
     $pasajero = $_REQUEST["pasajero"];
  
    $result = $conexion->query("SELECT
viaje.clase,
viaje.id_vuelo,
viaje.id_pasajero
FROM
pasajero
INNER JOIN viaje ON viaje.id_pasajero = pasajero.id_pasajero
where pasajero.id_pasajero = '".$pasajero."'");
    if($result){
        while ($fila = $result->fetch_object()){
            $datos[]= $fila;
           
        }
        if(isset($datos)){
			echo json_encode($datos);
		}
    }
  
}


if($accion == "obtenerListaVuelos"){
     
  
    $result = $conexion->query("SELECT v.id_vuelo,v.fecha,v.costoEconomica,v.costoEjecutivo,v.costoPrimera,
(select e.nombre from aeropuerto e where v.origen = e.idAeropuerto) as origen,
(select e.nombre from aeropuerto e where v.destino = e.idAeropuerto) as destino,
(select a.id_avion from avion a where v.id_avion = a.id_avion)as  id_avion,
(select a.modelo from avion a where v.id_avion = a.id_avion) as modelo
FROM vuelo as v");
    if($result){
        while ($fila = $result->fetch_object()){
            $datos[]= $fila;
           
        }
        if(isset($datos)){
			echo json_encode($datos);
		}
    }
  
}

if($accion == "obtenerListaAeropuerto"){
     
  
    $result = $conexion->query("select * from aeropuerto");
    if($result){
        while ($fila = $result->fetch_object()){
            $datos[]= $fila;
           
        }
        if(isset($datos)){
			echo json_encode($datos);
		}
    }
  
}

if($accion == "guardarAeropuerto"){
    $nombre = $_REQUEST["nombre"];
	$la =  $_REQUEST["la"];
	$lo =  $_REQUEST["lo"];
      
    $consulta  = "insert into aeropuerto values (null, '".$nombre."', '".$la."', '".$lo."')";
	//echo $consulta;
    $resultado = $conexion->query($consulta);
}

if($accion == "guardarViaje"){
    $idPasajero = $_REQUEST["idPasajero"];
	$idVuelo =  $_REQUEST["idVuelo"];
	$clase =  $_REQUEST["clase"];
      
    $consulta  = "insert into viaje values ('".$idPasajero."', '".$idVuelo."','0' ,'".$clase."', '0.0')";
	//echo $consulta;
    $resultado = $conexion->query($consulta);
}

if($accion == "guardarVuelo"){
    $origen = $_REQUEST["origen"];
	$destino =  $_REQUEST["destino"];
	$id_avion =  $_REQUEST["id_avion"];
	$fecha = $_REQUEST["fecha"];
	$economica =$_REQUEST["economica"];
	$ejecutiva =$_REQUEST["ejecutiva"];
	$primera =$_REQUEST["primera"];
	$modelo = $_REQUEST["modelo"];
      
    $consulta  = "insert into vuelo values (null, '".$origen."', '".$destino."', '".$id_avion."','".$fecha."','".$economica."','".$ejecutiva."','".$primera."', '".$modelo."' )";
	echo $consulta;
    $resultado = $conexion->query($consulta);
}

if($accion == "guardarUsuario"){
    $nombre = $_REQUEST["nombre"];
	$apellido =  $_REQUEST["apellido"];
	$nacionalidad =  $_REQUEST["nacionalidad"];
	$edad = $_REQUEST["edad"];
	$user =$_REQUEST["user"];
	$pass =$_REQUEST["pass"];
	$genero =$_REQUEST["genero"];
	      
    $consulta  = "insert into pasajero values (null, '".$nombre."', '".$apellido."', '".$nacionalidad."','".$edad."','".$genero."','".$pass."', '".$user."' , '0')";
	echo $consulta;
    $resultado = $conexion->query($consulta);
}

if($accion == "guardarAvion"){
    $modelo = $_REQUEST["modelo"];
	$ejecutiva =  $_REQUEST["ejecutiva"];
	$economica =  $_REQUEST["economica"];
	$primera =  $_REQUEST["primera"];
      
    $consulta  = "insert into avion values (null, '".$economica."', '".$ejecutiva."', '".$primera."', '".$modelo."')";
	//echo $consulta;
    $resultado = $conexion->query($consulta);
}


if ($accion == "eliminar") {
    $iddatos = $_REQUEST["criterio"];
    
    $consulta = "delete from datos where iddatos = " .$iddatos;
    $resultadoX = $conexion->query("select * from datos");
    
    if ($resultadoX) {
        while ($filaX = $resultadoX->fetch_object()) {
            $datos[] = $filaX;
        }
        echo json_decode($datos);
    }
}

?>