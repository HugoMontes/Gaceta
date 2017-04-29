package bo.gaceta.rcb.presentacion.utils;

public class StringHelper {
    public static final String convertStringFirstUpperCase(String cad) {
        cad = cad.toLowerCase();
        cad = Character.toUpperCase(cad.charAt(0)) + cad.substring(1);
        char[] caracteres = cad.toCharArray();
        // el -2 es para evitar una excepción al caernos del arreglo
        for (int i = 0; i < cad.length() - 2; i++){
            // Es 'palabra'
            if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ','){
                // Reemplazamos
                caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
            }
        }
        cad=new String(caracteres);
        cad=cad.replace("De", "de");
        cad=cad.replace("En", "en");
        cad=cad.replace("El", "el");
        return cad;
    }
}
