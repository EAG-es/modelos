package innui.modelos.jdbc;

import innui.modelos.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import innui.utiles.strings.literales;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class sql_comandos {
    public static String k_in_ruta = "in/innui/modelos/jdbc/in";
    public static String k_sql_comandos_insert = "insert ";
    public static String k_sql_comandos_select = "select ";
    public static String k_sql_comandos_delete = "delete ";
    public static String k_sql_comandos_marcador_columnas_lista = "<columnas>";
    public static String k_sql_comandos_marcador_para_valor = "?";
    public static String k_sql_comandos_id_error_iniciar = "sql_comandos_iniciar";
    public static String k_sql_comandos_formato_date = "%1$tF %1$tT";
    public static String k_sql_comandos_formato_time = "%1$tT:%1$tL";
    public String clase_driver_jdbc;
    public String clase_driver_jdbc_uri_conexion;
    public String crear_sql;
    public String leer_sql;
    public String actualizar_sql;
    public String borrar_sql;
    public String _leer_sql;
    public LinkedList<String> columnas_leer_lista;
    public LinkedList<String> columnas_leer_clave_nueva_lista;
    public LinkedList<LinkedHashMap<String, Object>> lecturas_lista;
    public LinkedHashMap<String, Object> clave_nueva_mapa;
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
     * Crea un mapa para una fila resultante de una lectura
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public LinkedHashMap<String, Object> crear_columnas_mapa(oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        LinkedHashMap<String, Object> columnas_mapa = null;
        try {
            columnas_mapa = new LinkedHashMap<>();
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return columnas_mapa;
    }
    /**
     * Valida y completa las instrucciones CLAB, antes de pasarle los datos en k_sql_comandos_marcador_para_valor
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean iniciar(oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        ResourceBundle in;
        try {
            String texto;
            String columnas_tex;
            in = ResourceBundles.getBundle(k_in_ruta);
            texto = crear_sql;
            if (texto != null) {
                texto = texto.toLowerCase().trim();
                if (texto.contains(k_sql_comandos_insert) == false) {
                    ok.setTxt(ok.getTxt(), tr.in(in, "Se esperaba una orden: ") + k_sql_comandos_insert);
                }
            }
            texto = leer_sql;
            if (texto != null) {
                texto = texto.toLowerCase().trim();
                if (texto.contains(k_sql_comandos_select) == false) {
                    ok.setTxt(ok.getTxt(), tr.in(in, "Se esperaba una orden: ") + k_sql_comandos_select);
                }
                if (texto.contains(" " + k_sql_comandos_marcador_columnas_lista + " ") == false) {
                    ok.setTxt(ok.getTxt(), tr.in(in, "Falta el marcador para la lista de columnas: ") + k_sql_comandos_marcador_columnas_lista);
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
            texto = borrar_sql;
            if (texto != null) {
                texto = texto.toLowerCase().trim();
                if (texto.contains(k_sql_comandos_delete) == false) {
                    ok.setTxt(ok.getTxt(), tr.in(in, "Se esperaba una orden: ") + k_sql_comandos_delete);
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
    /**
     * Completa las marcas k_sql_comandos_marcador_para_valor de una instrucción SQL, con los valores correspondientes
     * @param texto Texto donde poner el valor
     * @param valor Valor que poner (en función de su clase)
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public String _poner_valor_en_marcas(String texto, Object valor, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        ResourceBundle in;
        try {
            int inicio_busqueda_pos;
            in = ResourceBundles.getBundle(k_in_ruta);
            if (ok.es == false) { return null; }
            inicio_busqueda_pos = 0;
            int i = 1;
            int tam = texto.length();
            char letra;
            while (true) {
                if (i > 1) {
                    break;
                }
                inicio_busqueda_pos = literales.buscar_sin_texto_literal(texto, true
                , k_sql_comandos_marcador_para_valor, inicio_busqueda_pos, ok, extra_array);
                if (ok.es == false) { break; }
                if (inicio_busqueda_pos < 0) {
                    ok.setTxt(tr.in(in,"No se ha encontrado la posición: " + k_sql_comandos_marcador_para_valor));
                    break;
                } else {
                    if (inicio_busqueda_pos + 1 < tam) {
                        letra = texto.charAt(inicio_busqueda_pos + 1);
                        if (Character.isAlphabetic(letra)
                         || Character.isDigit(letra)) {
                            i = i -1;
                        }
                    } 
                    if (inicio_busqueda_pos - 1 >= 0) {
                        letra = texto.charAt(inicio_busqueda_pos - 1);
                        if (Character.isAlphabetic(letra)
                         || Character.isDigit(letra)) {
                            i = i -1;
                        }
                    }
                }
                inicio_busqueda_pos = inicio_busqueda_pos + 1;
                i = i + 1;
            }
            texto = texto.substring(0, inicio_busqueda_pos - 1)
            + pasar_a_texto_sql_segun_tipo(valor, ok)
            + texto.substring(inicio_busqueda_pos - 1 + k_sql_comandos_marcador_para_valor.length());
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return texto;
    }
    /** 
     * Convierte a texto el valor pasado, en función de su clase
     * @param valor
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public String pasar_a_texto_sql_segun_tipo(Object valor, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        String texto = null;
        try {
            if (valor == null) {
                texto = "null";
            } else if (valor instanceof String) {
                texto = "'" + valor + "'";
            } else if (valor instanceof Date valor_date) {
                texto = "'" + String.format(k_sql_comandos_formato_date, valor_date) + "'";
            } else if (valor instanceof Time valor_time) {
                texto = "'" + String.format(k_sql_comandos_formato_time, valor_time) + "'";
            } else if (valor instanceof Integer valor_integer) {
                texto = String.valueOf(valor_integer);
            } else if (valor instanceof Long valor_long) {
                texto = String.valueOf(valor_long);
            } else if (valor instanceof Double valor_double) {
                texto = String.valueOf(valor_double);
            } else if (valor instanceof Float valor_float) {
                texto = String.valueOf(valor_float);
            } else if (valor instanceof BigDecimal valor_bigdecimal) {
                texto = valor_bigdecimal.toPlainString();
            } else if (valor instanceof BigInteger valor_biginteger) {
                texto = valor_biginteger.toString();
            } else {
                texto = valor.toString();
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return texto;        
    }
    /**
     * Completa las marcas k_sql_comandos_marcador_para_valor de una instrucción SQL, con los valores correspondientes
     * @param valor Valor que poner (en función de su clase)
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean poner_valor_crear(Object valor, oks ok, Object... extra_array) throws Exception {
        crear_sql = _poner_valor_en_marcas(crear_sql, valor, ok, extra_array);
        return ok.es;
    }
    /**
     * Completa las marcas k_sql_comandos_marcador_para_valor de una instrucción SQL, con los valores correspondientes
     * @param valor Valor que poner (en función de su clase)
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean poner_valor_leer(Object valor, oks ok, Object... extra_array) throws Exception {
        _leer_sql = _poner_valor_en_marcas(_leer_sql, valor, ok, extra_array);
        return ok.es;
    }
    /**
     * Completa las marcas k_sql_comandos_marcador_para_valor de una instrucción SQL, con los valores correspondientes
     * @param valor Valor que poner (en función de su clase)
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean poner_valor_actualizar(Object valor, oks ok, Object... extra_array) throws Exception {
        actualizar_sql = _poner_valor_en_marcas(actualizar_sql, valor, ok, extra_array);
        return ok.es;
    }
    /**
     * Completa las marcas k_sql_comandos_marcador_para_valor de una instrucción SQL, con los valores correspondientes
     * @param valor Valor que poner (en función de su clase)
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean poner_valor_borrar(Object valor, oks ok, Object... extra_array) throws Exception {
        borrar_sql = _poner_valor_en_marcas(borrar_sql, valor, ok, extra_array);
        return ok.es;
    }
}
