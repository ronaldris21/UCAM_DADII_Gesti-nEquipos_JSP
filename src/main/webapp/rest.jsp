<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@ taglib uri="misTags" prefix="tags" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <jsp:include page="common/head.jsp" />
    <title>Rest</title>
    <link rel="stylesheet" type="text/css" href="css/rest.css" />
    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <!-- SCRIPT PARA CLUBES -->
    <script type="text/javascript">
      function loadClub(id, nombre) {
        var entry = document.createElement("li");

        var a = document.createElement("button");
        a.appendChild(document.createTextNode(" [Borrar]"));
        var urlBorrar = "rest/servicio/Club/" + id;
        a.onclick = function () {
          $.ajax({
            url: urlBorrar, // Usar la variable urlBorrar en lugar de "rest/servicio/"+X

            type: "DELETE",
            dataType: "json",
            success: function (result) {
              cargarClubes();
            },
            error: function (xhr, status, error) {
              console.log(xhr.responseText); // Imprime la respuesta de error en la consola del navegador
              alert("Error al crear el club: " + error); // Muestra el mensaje de error en una alerta
            },
          });
        };
        var updatebtn = document.createElement("button");
        updatebtn.appendChild(document.createTextNode(" [ACTUALIZAR]"));
        updatebtn.onclick = function () {
          $.ajax({
            url: "rest/servicio/Club/" + id, // Usar la variable urlBorrar en lugar de "rest/servicio/"+X

            type: "GET",
            dataType: "json",
            success: function (result) {
              console.log(result);
              $("#X").val(result.id);
              $("#Y").val(result.nombre);
            },
            error: function (xhr, status, error) {
              console.log(xhr.responseText); // Imprime la respuesta de error en la consola del navegador
              alert("Error al crear el club: " + error); // Muestra el mensaje de error en una alerta
            },
          });
        };

        entry.id = "club-" + id;

        entry.appendChild(
          document.createTextNode("ID: (" + id + ") | Nombre: " + nombre)
        );

        entry.appendChild(a);
        entry.appendChild(updatebtn);

        $("#listaClubes").append(entry);
      }

      //   SCRIPT PARA CLUBES
      $(document).ready(function () {
        $("#btnCrearClub").click(function () {
          console.log("Crear Club");
          var urlLink = "rest/servicio/Club";
          console.log(urlLink);
          var sendInfo = { id: $("#X").val(), nombre: $("#Y").val() };

          $.ajax({
            url: "rest/servicio/Club",
            headers: {
              Accept: "application/json",
              "Content-Type": "application/json",
            },
            type: "POST",
            dataType: "json",
            success: function (result) {
              if (result) cargarClubes();
              else alert("Ya existe un club con ese id");
            },
            error: function (jqXhr, textStatus, errorMessage) {
              alert("Error: " + jqXhr.responseJSON.resultado);
            },
            data: JSON.stringify(sendInfo),
          });
        });

        $("#btnActualizarClub").click(function () {
          var sendInfo = { id: $("#X").val(), nombre: $("#Y").val() };

          $.ajax({
            url: "rest/servicio/Club",
            headers: {
              Accept: "application/json",
              "Content-Type": "application/json",
            },
            type: "PUT",
            dataType: "json",
            success: function (result) {
              if (result) {
                cargarClubes();
              } else alert("No existe un club con ese ID");
            },
            error: function (jqXhr, textStatus, errorMessage) {
              alert("Error: " + jqXhr.responseJSON.resultado);
            },
            data: JSON.stringify(sendInfo),
          });
        });
      });
    </script>

    <!-- SCRIPT PARA JUGADORES -->
    <script type="text/javascript">
      function loadJugador(X1, Y1, Z1, A1, B1) {
        var entry = document.createElement("li");

        var a = document.createElement("button");
        var linkText = document.createTextNode(" [Borrar]");
        a.appendChild(linkText);
        var urlBorrar = "rest/servicio/Jugador/" + X1;
        a.onclick = function () {
          $.ajax({
            url: urlBorrar,
            type: "DELETE",
            dataType: "json",
            success: function (result) {
              $(entry).remove();
            },
            error: function (xhr, status, error) {
              console.log(xhr.responseText);
              alert("Error al crear el jugador: " + error);
            },
          });
        };

        var updatebtn = document.createElement("button");
        updatebtn.appendChild(document.createTextNode(" [ACTUALIZAR]"));
        updatebtn.onclick = function () {
          $.ajax({
            url: "rest/servicio/Jugador/" + X1, // Usar la variable urlBorrar en lugar de "rest/servicio/"+X
            type: "GET",
            dataType: "json",
            success: function (result) {
              console.log(result);
              $("#X1").val(result.id);
              $("#Y1").val(result.nombre);
              $("#Z1").val(result.apellidos);
              $("#A1").val(result.numeroGoles);
              $("#B1").val(result.clubId);
            },
            error: function (xhr, status, error) {
              console.log(xhr.responseText); // Imprime la respuesta de error en la consola del navegador
              alert("Error al crear el club: " + error); // Muestra el mensaje de error en una alerta
            },
          });
        };

        entry.id = "jugador-" + X1;
        entry.appendChild(
          document.createTextNode(
            "ID: (" +
              X1 +
              ") | Nombre: " +
              Y1 +
              " | Apellidos: " +
              Z1 +
              " | numeroGoles: " +
              A1 +
              " | club: " +
              getClubName(B1)
          )
        );
        entry.appendChild(a);
        entry.appendChild(updatebtn);

        $("#listaJugadores").append(entry);
      }

      function getClubName(clubId) {
        var clubName = "Ninguno";
        var clubOptions = $("#B1 option");
        clubOptions.each(function () {
          if ($(this).val() == clubId) {
            clubName = $(this).text();
            return false;
          }
        });
        return clubName;
      }

      function cargarClubes() {
        console.log("cargarClubes");
        $("#listaClubes").empty(); //ELIMINA LO HIJOS

        $.ajax({
          url: "rest/servicio/Club",
          type: "GET",
          dataType: "json",
          success: function (result) {
            ///CARGA LOS CLUBES EN EL SELECT
            $("#B1").empty(); // Limpiar el select de clubes
            var select = $("#B1");
            select.append("<option value='0'>Ninguno</option>");
            $.each(result.clubes, function (i, club) {
              select.append(
                "<option value='" + club.id + "'>" + club.nombre + "</option>"
              );
            });

            ///LLENA LA LISTA DE CLUBES
            $.each(result.clubes, function (i, club) {
              loadClub(club.id, club.nombre);
            });
          },
        });
      }

      function cargarJugadores() {
        console.log("cargarJugadores");
        $("#listaJugadores").empty(); //ELIMINA LO HIJOS

        $.ajax({
          url: "rest/servicio/Jugador",
          type: "GET",
          dataType: "json",
          success: function (result) {
            jQuery.each(result.jugadores, function (i, jugador) {
              loadJugador(
                jugador.id,
                jugador.nombre,
                jugador.apellidos,
                jugador.numeroGoles,
                jugador.clubId
              );
            });
          },
        });
      }

      $(document).ready(function () {
        cargarClubes();
        cargarJugadores();

        $("#btnCrearJugador").click(function () {
          var sendInfo = {
            id: $("#X1").val(),
            nombre: $("#Y1").val(),
            apellidos: $("#Z1").val(),
            numeroGoles: $("#A1").val(),
            clubId: $("#B1").val(),
          };

          $.ajax({
            url: "rest/servicio/Jugador",
            headers: {
              Accept: "application/json",
              "Content-Type": "application/json",
            },
            type: "POST",
            dataType: "json",
            success: function (result) {
              cargarJugadores();
            },
            error: function (jqXhr, textStatus, errorMessage) {
              alert("Error: " + jqXhr.responseJSON.resultado);
            },
            data: JSON.stringify(sendInfo),
          });
        });

        $("#btnUpdateJugador").click(function () {
          var sendInfo = {
            id: $("#X1").val(),
            nombre: $("#Y1").val(),
            apellidos: $("#Z1").val(),
            numeroGoles: $("#A1").val(),
            clubId: $("#B1").val(),
          };

          $.ajax({
            url: "rest/servicio/Jugador",
            headers: {
              Accept: "application/json",
              "Content-Type": "application/json",
            },
            type: "PUT",
            dataType: "json",
            success: function (result) {
              cargarJugadores();
            },
            error: function (jqXhr, textStatus, errorMessage) {
              alert("Error: " + jqXhr.responseJSON.resultado);
            },
            data: JSON.stringify(sendInfo),
          });
        });
      });
    </script>
  </head>
  <body>
    <jsp:include page="common/header.jsp" />

    <div class="container-fluid d-flex">
      <div class="container">
        <h1 id="title-main">Clubes</h1>
        <form>
          <label for="X">ID:</label>
          <input type="number" id="X" />

          <label for="Y">Nombre:</label>
          <input type="text" id="Y" />

          <button class="btn btn-success" id="btnCrearClub">Crear</button>
          <button class="btn" id="btnActualizarClub">Modificar</button>
        </form>

        <h3>Listado de clubes creados</h3>
        <ul id="listaClubes"></ul>
      </div>

      <div class="container">
        <h1 id="title-main">Jugadores</h1>

        <form>
          <label for="X1">ID:</label>
          <input type="number" id="X1" />

          <label for="Y1">Nombre:</label>
          <input type="text" id="Y1" />

          <label for="Z1">Apellidos:</label>
          <input type="text" id="Z1" />

          <label for="A1">NumeroGoles:</label>
          <input type="number" min="0" id="A1" />

          <label for="B1">Club:</label>
          <select id="B1"></select>

          <button class="btn btn-success" id="btnCrearJugador">Crear</button>
          <button class="btn" id="btnUpdateJugador">Modificar</button>
        </form>

        <h3>Listado de jugadores creados</h3>
        <ul id="listaJugadores"></ul>
      </div>
    </div>
  </body>
</html>
