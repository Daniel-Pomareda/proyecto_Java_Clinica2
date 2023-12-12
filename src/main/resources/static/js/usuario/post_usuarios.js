window.addEventListener('load', function () {

     //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
     //los datos que el usuario cargará del nuevo estudiante
    const formulario = document.querySelector('#add_usuarios');
console.log ("Trajo el formulario de usuarios");
    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {

        //creamos un JSON que tendrá los datos del nuevo estudiante
        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            nombreUsuario: document.querySelector('#nombreUsuario').value,
            contrasenia: document.querySelector('#contrasenia').value,
            rol: document.querySelector('#rol').value,

        };
console.log ("ingreso los usuarios al fomulario");
        //invocamos utilizando la función fetch la API estudiantes con el método POST
        //que guardará al estudiante que enviaremos en formato JSON
        const url = '/usuarios';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
console.log ("Se conecto correctamente");
        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
               //Si no hay ningun error se muestra un mensaje diciendo que el estudiante
               //se agrego bien
                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Usuario agregado </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 //se dejan todos los campos vacíos por si se quiere ingresar otro estudiante
                 resetUploadForm();

            })
            .catch(error => {
                 //Si hay algun error se muestra un mensaje diciendo que el estudiante
                 //no se pudo guardar y se intente nuevamente
                  let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente</strong> </div>'

                   document.querySelector('#response').innerHTML = errorAlert;
                   document.querySelector('#response').style.display = "block";
                   //se dejan todos los campos vacíos por si se quiere ingresar otro estudiante
                   resetUploadForm();})
    });

    function resetUploadForm(){
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#nombreUsuario').value = "";
        document.querySelector('#contrasenia').value = "";
        document.querySelector('#rol').value = "";
    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").classList.add("active");
        } else if (pathname == "/listaPacientes.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();
});