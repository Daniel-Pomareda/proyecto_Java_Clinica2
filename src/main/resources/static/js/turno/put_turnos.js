window.addEventListener('load', function () {

    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado del estudiante
    const formularioPut = document.querySelector('#formulario_actualizacion_turno');
    formularioPut.addEventListener('submit', function (event) {
        //let studentId = document.querySelector('#turno_id').value;

        //creamos un JSON que tendrá los datos del estudiante
        //a diferencia de un estudiante nuevo en este caso enviamos el id
        //para poder identificarlo y modificarlo para no cargarlo como nuevo
        const formData = {
            id: document.querySelector('#turno_id').value,
            odontologo: {"id": document.querySelector('#odontologoid_m').value},
            paciente: {"id": document.querySelector('#pacienteid_m').value},
            fecha: document.querySelector('#fecha_m').value,
            hora: document.querySelector('#hora_m').value,

        };

        //invocamos utilizando la función fetch la API estudiantes con el método PUT
        //que modificará al estudiante que enviaremos en formato JSON
        const url = '/reservaDeTurnos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())

    })
 })

    //Es la funcion que se invoca cuando se hace click sobre el id de un estudiante del listado
    //se encarga de llenar el formulario con los datos del estudiante
    //que se desea modificar
    function findBy(id) {
          const url = '/reservaDeTurnos'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let turno = data;
              document.querySelector('#turno_id').value = turno.id;
              document.querySelector('#odontologoid_m').value = turno.odontologo.id;
              document.querySelector('#pacienteid_m').value = turno.paciente.id;
              document.querySelector('#fecha_m').value = turno.fecha;
              document.querySelector('#hora_m').value = turno.hora;

            //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_actualizacion_turno').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }