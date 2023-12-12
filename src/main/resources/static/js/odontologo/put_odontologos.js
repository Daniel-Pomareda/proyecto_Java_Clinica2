window.addEventListener('load', function () {

    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado del estudiante
    const formularioPut = document.querySelector('#formulario_actualizacion_odontologo');
    formularioPut.addEventListener('submit', function (event) {
        // let studentId = document.querySelector('#odontologo_id').value;

        //creamos un JSON que tendrá los datos del estudiante
        //a diferencia de un estudiante nuevo en este caso enviamos el id
        //para poder identificarlo y modificarlo para no cargarlo como nuevo
        const formData = {
            id: document.querySelector('#odontologo_id').value,
            nombre: document.querySelector('#nombre_m').value,
            apellido: document.querySelector('#apellido_m').value,
            matricula: document.querySelector('#matricula_m').value,
        };

        //invocamos utilizando la función fetch la API estudiantes con el método PUT
        //que modificará al estudiante que enviaremos en formato JSON
        const url = '/odontologos';
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
          const url = '/odontologos'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let odontologo = data;
              document.querySelector('#odontologo_id').value = odontologo.id;
              document.querySelector('#nombre_m').value = odontologo.nombre;
              document.querySelector('#apellido_m').value = odontologo.apellido;
              document.querySelector('#matricula_m').value = odontologo.matricula;

              // nombre: document.querySelector('#nombre').value;
              // apellido: document.querySelector('#apellido').value;
              // matricula: document.querySelector('#matricula').value;

            //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_actualizacion_odontologo').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }