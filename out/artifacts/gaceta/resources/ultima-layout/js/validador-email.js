/**
 * Created by Fundempresa on 7/4/2017.
 */
function validar(event) {
    tecla = (document.all) ? event.keyCode : event.which;
    if (tecla == 86 && event.ctrlKey) {
        return false;
    }
}
