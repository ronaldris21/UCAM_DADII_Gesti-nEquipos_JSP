<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Probar API REST</title>
<style>
		body {
			font-family: Arial, sans-serif;
			margin: 0;
			padding: 20px;
			background-color: #f5f5f5;
		}

		.container {
			max-width: 600px;
			margin: 0 auto;
			background-color: #fff;
			padding: 20px;
			border-radius: 5px;
			box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
		}

		h1 {
			text-align: center;
			font-size: 24px;
			font-weight: bold;
			margin-bottom: 20px;
		}

		form {
			margin-bottom: 20px;
		}

		label {
			display: block;
			margin-bottom: 5px;
			font-weight: bold;
		}

		input[type="text"], select {
			width: 100%;
			padding: 10px;
			border: 1px solid #ccc;
			border-radius: 5px;
			margin-bottom: 10px;
		}

		button {
			padding: 10px 20px;
			font-size: 16px;
			background-color: #BD4B4B;;
			color: white;
			border: none;
			border-radius: 5px;
			cursor: pointer;
		}

		button:hover {
			background-color: #e41919;
		}

		ul {
			list-style-type: none;
			margin: 0;
			padding: 0;
			margin-bottom: 20px;
		}

		ul li {
			margin-bottom: 5px;
		}
	</style>
	<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
	
	<script type="text/javascript">
	
		function load(X,Y,Z ){
        
			var entry = document.createElement('li');
			
			var a = document.createElement('button');
			
			var linkText = document.createTextNode(" [Borrar]");
			
			a.appendChild(linkText);
			
			var urlBorrar = 'rest/servicio/Club' + X;
			
			a.onclick = function () {
			    $.ajax({
			        url: urlBorrar, // Usar la variable urlBorrar en lugar de "rest/servicio/"+X
			       
			        type: 'DELETE',
			        dataType: "json",
			        success: function(result) {
			        	$(entry).remove();
			        	cargarClubes();
			        },
			        error: function(xhr, status, error) {
		                console.log(xhr.responseText); // Imprime la respuesta de error en la consola del navegador
		                alert("Error al crear el club: " + error); // Muestra el mensaje de error en una alerta
		            }
			    });
			};
	
			entry.id = 'club-' + X;
            
			entry.appendChild(document.createTextNode("ID: ("+ X + ") | Nombre: " + Y ));
			
			entry.appendChild(a);
			
			$('#listado').append(entry);
			
		}
	
		$(document).ready(function(){
        
			$("#sendButton").click(function(){
            	console.log("Crear Club");
            	var urlLink = 'rest/servicio/Club';
            	console.log(urlLink);
				var sendInfo = {id: $('#X').val(),nombre: $('#Y').val()};
				
				   $.ajax({
			            url: 'rest/servicio/Club',
			            headers: {
			                'Accept': 'application/json',
			                'Content-Type': 'application/json'
			            },
			            type: 'POST',
			            dataType: "json",
			            success: function(result) {
			                if (result)
			                    load(result.id, result.nombre);
			                else
			                    alert("Ya existe un club con ese id");
			            },
			            error: function(jqXhr, textStatus, errorMessage) {
			                alert('Error: ' + jqXhr.responseJSON.resultado);
			            },
			            data: JSON.stringify(sendInfo)
			        });
			    });
			
			$("#updateButton").click(function(){
	            
				var sendInfo = {id: $('#X').val(),nombre: $('#Y').val()};
				
			    $.ajax({
					    url: 'rest/servicio/Club',
					    headers: { 
				               'Accept': 'application/json',
				               'Content-Type': 'application/json' 
				           },
					    type: 'PUT',
					    dataType: "json", 
					    success: function(result) {
					    	if(result)
					    		{
					    			document.getElementById('club-' + result.id).outerHTML = "";
					    			load(result.id, result.nombre);
					    		}

					    	else
					    		alert("No existe un club con ese ID");
					    },
				    	error: function(jqXhr, textStatus, errorMessage){
					    	alert('Error: ' + jqXhr.responseJSON.resultado);	
					    },
					    data:  JSON.stringify(sendInfo)
					    
					    
					});
			    
			    
			    
			    });
		
			$.ajax({
			    url: 'rest/servicio/Club',
			    type: 'GET',
			    dataType: "json",
			    success: function(result) {
			    	jQuery.each(result.clubes, function(i, val) {
			    		  load(val.id, val.nombre);
			    		});
			    	
			    }
			});
			
		});
</script>
	
	<script type="text/javascript">
	
	function load1(X1, Y1, Z1, A1, B1) {
	    var entry = document.createElement('li');

	    var a = document.createElement('button');
	    var linkText = document.createTextNode(" [Borrar]");
	    a.appendChild(linkText);
	    var urlBorrar = 'rest/servicio/Jugador' + X1;
	    a.onclick = function () {
	        $.ajax({
	            url: urlBorrar,
	            type: 'DELETE',
	            dataType: "json",
	            success: function(result) {
	                $(entry).remove();
	            },
	            error: function(xhr, status, error) {
	                console.log(xhr.responseText);
	                alert("Error al crear el jugador: " + error);
	            }
	        });
	    };

	    entry.id = 'jugador-' + X1;
	    entry.appendChild(document.createTextNode("ID: (" + X1 + ") | Nombre: " + Y1 + " | Apellidos: " + Z1 + " | numeroGoles: " + A1 + " | club: " + getClubName(B1)));
	    entry.appendChild(a);

	    $('#listado1').append(entry);
	   
	}

	function getClubName(clubId) {
	    var clubName = "";
	    var clubOptions = $("#B1 option");
	    clubOptions.each(function() {
	        if ($(this).val() === clubId) {
	            clubName = $(this).text();
	            return false;
	        }
	    });
	    return clubName;
	}

	 function cargarClubes() {
	        $.ajax({
	            url: 'rest/servicio/Club',
	            type: 'GET',
	            dataType: "json",
	            success: function(result) {
	                $("#B1").empty(); // Limpiar el select de clubes
	                var select = $("#B1");
	                select.append("<option value=''>Ninguno</option>");
	                $.each(result.clubes, function(i, club) {
	                    select.append("<option value='" + club.id + "'>" + club.nombre + "</option>");
	                });
	                // Call the function to load players only after clubs are loaded
	                cargarJugadores();
	            }
	        });
	    }

	    function cargarJugadores() {
	        $.ajax({
	            url: 'rest/servicio/Jugador',
	            type: 'GET',
	            dataType: "json",
	            success: function(result) {
	                jQuery.each(result.jugadores, function(i, jugador) {
	                    load1(jugador.id, jugador.nombre, jugador.apellidos, jugador.numeroGoles, jugador.clubId);
	                });
	            }
	        });
	    }
	$(document).ready(function() {
	    $("#sendButton1").click(function() {
	        var sendInfo = {
	            id: $('#X1').val(),
	            nombre: $('#Y1').val(),
	            apellidos: $('#Z1').val(),
	            numeroGoles: $('#A1').val(),
	            clubId: $('#B1').val()
	        };

	        $.ajax({
	            url: 'rest/servicio/Jugador',
	            headers: {
	                'Accept': 'application/json',
	                'Content-Type': 'application/json'
	            },
	            type: 'POST',
	            dataType: "json",
	            success: function(result) {
	                if (result)
	                    load1(result.id, result.nombre, result.apellidos, result.numeroGoles, result.clubId);
	                else
	                    alert("Ya existe un Jugador con ese id");
	                // Llamada a cargarClubes() al final de la función de éxito
	            },
	            error: function(jqXhr, textStatus, errorMessage) {
	                alert('Error: ' + jqXhr.responseJSON.resultado);
	            },
	            data: JSON.stringify(sendInfo)
	        });
	    });

	    $("#updateButton1").click(function() {
	        var sendInfo = {
	            id: $('#X1').val(),
	            nombre: $('#Y1').val(),
	            apellidos: $('#Z1').val(),
	            numeroGoles: $('#A1').val(),
	            clubId: $('#B1').val()
	        };

	        $.ajax({
	            url: 'rest/servicio/Jugador',
	            headers: {
	                'Accept': 'application/json',
	                'Content-Type': 'application/json'
	            },
	            type: 'PUT',
	            dataType: "json",
	            success: function(result) {
	                if (result) {
	                    var element = document.getElementById('jugador-' + result.id);
	                    if (element) {
	                        element.outerHTML = "";
	                    }
					    			load1(result.id, result.nombre, result.apellidos, result.numeroGoles, result.clubId);
					    			
					    		}

					    	else
					    		alert("No existe un jugador con ese ID");
					    },
				    	error: function(jqXhr, textStatus, errorMessage){
					    	alert('Error: ' + jqXhr.responseJSON.resultado);	
					    },
					    data:  JSON.stringify(sendInfo)
					    
					});
	        
			    });
		
			cargarClubes();
			
			$.ajax({
			    url: 'rest/servicio/Jugador',
			    type: 'GET',
			    dataType: "json",
			    success: function(result) {
			        jQuery.each(result.jugadores, function(i, jugador) {
			            
			            
			        });
			         // Llamar a cargarClubes() después de cargar los jugadores
			    }
			});
		});
</script>
</head>
<body>
	<div class="container">
		<h1>Práctica 2</h1>

		<form>
			<label for="X">ID:</label>
			<input type="text" id="X">

			<label for="Y">Nombre:</label>
			<input type="text" id="Y">

			<button id="sendButton">Crear</button>
			<button id="updateButton">Modificar</button>
		</form>

		<h3>Listado de clubes creados</h3>
		<ul id="listado"></ul>

		<form>
			<label for="X1">ID:</label>
			<input type="text" id="X1">

			<label for="Y1">Nombre:</label>
			<input type="text" id="Y1">

			<label for="Z1">Apellidos:</label>
			<input type="text" id="Z1">

			<label for="A1">NumeroGoles:</label>
			<input type="number" id="A1">

			<label for="B1">Club:</label>
			<select id="B1"></select>

			<button id="sendButton1">Crear</button>
			<button id="updateButton1">Modificar</button>
		</form>

		<h3>Listado de jugadores creados</h3>
		<ul id="listado1"></ul>
	</div>
</body>
</html>
