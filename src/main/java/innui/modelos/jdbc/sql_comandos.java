package innui.modelos.jdbc;

import innui.modelos.errores.oks;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author emilio
 */
public class sql_comandos {
    public static String k_sql_comandos_create = "create ";
    public static String k_sql_comandos_select = "select ";
    public static String k_sql_comandos_update = "update ";
    public static String k_sql_comandos_delete = "delete ";
    public static String k_sql_comandos_marcador_columnas_lista = "<columnas>";
    public static String k_sql_comandos_id_error_iniciar = "sql_comandos_iniciar";
    public String clase_driver_jdbc;
    public String clase_driver_jdbc_uri_conexion;
    public String crear_sql;
    public String leer_sql;
    public String actualizar_sql;
    public String borrar_sql;
    public String _leer_sql;
    public List<String> columnas_leer_lista;
    public List<String> columnas_leer_clave_nueva_lista;
    public List<Map<String, Object>> lecturas_lista;
    public List<Map<String, Object>> lecturas_nueva_clave_lista;
    public Map<String, Object> peticiones_mapa;
    public String error_msg;
    public String error_id;
    /**
     * Crea la lista de columnas que se van a leer.
     * @param ok
     * @param extra_array Nombres de las columnas que poner en la lista
     * @return
     * @throws Exception 
     */
    public boolean crear_columnas_leer_lista(oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        try {
            if (columnas_leer_lista == null) {
                columnas_leer_lista = new LinkedList<>();
            } else {
                columnas_leer_lista.clear();
            }
            for (Object object: extra_array) {
                columnas_leer_lista.add(object.toString());
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
    /**
     * Crea la lista de columnas de una clave nueva automática creada.
     * @param ok
     * @param extra_array Nombres de las columnas que poner en la lista
     * @return
     * @throws Exception 
     */
    public boolean crear_columnas_leer_clave_nueva_lista(oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        try {
            if (columnas_leer_clave_nueva_lista == null) {
                columnas_leer_clave_nueva_lista = new LinkedList<>();
            } else {
                columnas_leer_clave_nueva_lista.clear();
            }
            for (Object object: extra_array) {
                columnas_leer_clave_nueva_lista.add(object.toString());
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
    /**
     * Crea o vacia lecturas_lista
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean crear_lecturas_lista(oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        try {
            if (lecturas_lista == null) {
                lecturas_lista = new LinkedList<>();
            } else {
                lecturas_lista.clear();
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
    /**
     * Crea o vacia lecturas_nueva_clave_lista
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean crear_lecturas_nueva_clave_lista(oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        try {
            if (lecturas_nueva_clave_lista == null) {
                lecturas_nueva_clave_lista = new LinkedList<>();
            } else {
                lecturas_nueva_clave_lista.clear();
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
    /**
     * Crea un mapa para una fila resultante de una lectura
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public Map<String, Object> crear_columnas_mapa(oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        Map<String, Object> columnas_mapa = null;
        try {
            columnas_mapa = new LinkedHashMap<>();
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return columnas_mapa;
    }
    /**
     * Valida y completa las instrucciones CLAB
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean iniciar(oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        try {
            String texto;
            String columnas_tex;
            texto = crear_sql;
            if (texto != null) {
                texto = texto.toLowerCase().trim();
                if (texto.startsWith(k_sql_comandos_create) == false) {
                    ok.setTxt(ok.getTxt(), "Se esperaba una orden: " + k_sql_comandos_create);
                }
            }
            texto = leer_sql;
            if (texto != null) {
                texto = texto.toLowerCase().trim();
                if (texto.startsWith(k_sql_comandos_select) == false) {
                    ok.setTxt(ok.getTxt(), "Se esperaba una orden: " + k_sql_comandos_select);
                }
                if (texto.contains(k_sql_comandos_marcador_columnas_lista) == false) {
                    ok.setTxt(ok.getTxt(), "Falta el marcador para la lista de columnas: " + k_sql_comandos_marcador_columnas_lista);
                }
                columnas_tex = "";
                for (String columna: columnas_leer_lista) {
                    if (columnas_tex.isBlank()) {
                        columnas_tex = columnas_tex + columna;
                    } else {
                        columnas_tex = columnas_tex + ", " + columna;
                    }
                }
                _leer_sql = texto.replace(k_sql_comandos_marcador_columnas_lista, columnas_tex);
            }
            texto = actualizar_sql;
            if (texto != null) {
                texto = texto.toLowerCase().trim();
                if (texto.startsWith(k_sql_comandos_update) == false) {
                    ok.setTxt(ok.getTxt(), "Se esperaba una orden: " + k_sql_comandos_update);
                }
            }
            texto = borrar_sql;
            if (texto != null) {
                texto = texto.toLowerCase().trim();
                if (texto.startsWith(k_sql_comandos_delete) == false) {
                    ok.setTxt(ok.getTxt(), "Se esperaba una orden: " + k_sql_comandos_delete);
                }
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        if (ok.es == false) {
            error_msg = ok.getTxt();
            error_id = k_sql_comandos_id_error_iniciar;
        }
        return ok.es;
    }

    public String getLeer_sql() {
        return _leer_sql;
    }
    
}
