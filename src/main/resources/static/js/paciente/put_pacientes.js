window.addEventListener('load', function () {

    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado del estudiante
    const formularioPut = document.querySelector('#formulario_actualizacion_paciente');
    formularioPut.addEventListener('submit', function (event) {
        // let studentId = document.querySelector('#paciente_id').value;

        //creamos un JSON que tendrá los datos del estudiante
        //a diferencia de un estudiante nuevo en este caso enviamos el id
        //para poder identificarlo y modificarlo para no cargarlo como nuevo
        const formData = {
            id: document.querySelector('#paciente_id').value,
            nombre: document.querySelector('#nombre_m').value,
            apellido: document.querySelector('#apellido_m').value,
            domicilio: document.querySelector('#domicilio_m').value,
            dni: document.querySelector('#dni_m').value,
            fechaAlta: document.querySelector('#fechaAlta_m').value,
        };

        //invocamos utilizando la función fetch la API estudiantes con el método PUT
        //que modificará al estudiante que enviaremos en formato JSON
        const url = '/pacientes';
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
          const url = '/pacientes'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let paciente = data;
              document.querySelector('#paciente_id').value = paciente.id;
              document.querySelector('#nombre_m').value = paciente.nombre;
              document.querySelector('#apellido_m').value = paciente.apellido;
              document.querySelector('#domicilio_m').value = paciente.domicilio;
              document.querySelector('#dni_m').value = paciente.dni;
              document.querySelector('#fechaAlta_m').value = paciente.fechaAlta;

            //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_actualizacion_paciente').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }