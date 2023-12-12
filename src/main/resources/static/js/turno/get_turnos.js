window.addEventListener('load', function () {

    (function(){
      //con fetch invocamos a la API de estudiantes con el método GET
      //nos devolverá un JSON con una colección de estudiantes
      const url = '/reservaDeTurnos';
      const settings = {
        method: 'GET'
    }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
         //recorremos la colección de estudiantes del JSON
         for(turno of data){
          //por cada estudiante armaremos una fila de la tabla
          //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos
          //el turno

          var table = document.getElementById("tablaturnos");
          var studentRow = table.insertRow();
          let tr_id = 'tr_' + turno.id;
          studentRow.id = tr_id;


          //por cada estudiante creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
          //dicho boton invocara a la funcion de java script deleteByKey que se encargará
          //de llamar a la API para eliminar al estudiante
           let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                                      ' type="button" onclick="deleteBy('+turno.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';

           //por cada estudiante creamos un boton que muestra el id y que al hacerle clic invocará
           //a la función de java script findBy que se encargará de buscar al estudiante que queremos
           //modificar y mostrar los datos del mismo en un formulario.
          let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                                      ' type="button" onclick="findBy('+turno.id+')" class="btn btn-info btn_id">' +
                                      turno.id +
                                      '</button>';


          //armamos cada columna de la fila
          //como primer columna pondremos el boton modificar
          //luego los datos del estudiante
          //como ultima columna el boton eliminar
         studentRow.innerHTML = '<td>' + updateButton + '</td>' +
                              '<td class=\"td_odontologoid\">' + turno.odontologo.id + '</td>' +
                              '<td class=\"td_pacienteid\">' + turno.paciente.id + '</td>' +
                              '<td class=\"td_fecha\">' + turno.fecha + '</td>' +
                              '<td class=\"td_hora\">' + turno.hora + '</td>' +
                              '<td>' + deleteButton + '</td>';
        };

})
})

(function(){
  let pathname = window.location.pathname;
  if (pathname == "/listaPacientes.html") {
      document.querySelector(".nav .nav-item a:last").classList.add("active");
  }
})


})