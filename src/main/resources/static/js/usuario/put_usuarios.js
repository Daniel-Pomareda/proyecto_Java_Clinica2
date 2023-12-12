window.addEventListener('load', function () {

    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado del estudiante
    const formularioPut = document.querySelector('#formulario_actualizacion_usuario');
    formularioPut.addEventListener('submit', function (event) {
        //let studentId = document.querySelector('#usuario_id').value;

        //creamos un JSON que tendrá los datos del estudiante
        //a diferencia de un estudiante nuevo en este caso enviamos el id
        //para poder identificarlo y modificarlo para no cargarlo como nuevo
        const formData = {
            id: document.querySelector('#usuario_id').value,
            nombre: document.querySelector('#nombre_m').value,
            apellido: document.querySelector('#apellido_m').value,
            nombreUsuario: document.querySelector('#nombreUsuario_m').value,
            contrasenia: document.querySelector('#contrasenia_m').value,
            rol: document.querySelector('#rol_m').value,

        };

        //invocamos utilizando la función fetch la API estudiantes con el método PUT
        //que modificará al estudiante que enviaremos en formato JSON
        const url = '/usuarios';
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
          const url = '/usuarios'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let usuario = data;
              document.querySelector('#usuario_id').value = usuario.id;
              document.querySelector('#nombre_m').value = usuario.nombre;
              document.querySelector('#apellido_m').value = usuario.apellido;
              document.querySelector('#nombreUsuario_m').value = usuario.nombreUsuario;
              document.querySelector('#contrasenia_m').value = usuario.contrasenia;
              document.querySelector('#rol_m').value = usuario.rol;

            //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_actualizacion_usuario').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }