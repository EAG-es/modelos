package inclui.formularios;

import inclui.utiles.clui_lecturas;
import innui.modelos.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.errores.patrones;
import innui.modelos.internacionalizacion.tr;
import java.io.Console;
import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class entradas extends controles {
    public static String k_in_base = "in/inclui/formularios/in";
    public static String k_entradas_clui_lectura = "clui_lectura";
    public static String k_entradas_tipo_texto = "text";
    public static String k_entradas_tipo_color = "color";
    public static String k_entradas_tipo_date = "date";
    public static String k_entradas_tipo_datetime_local = "datetime-local";
    public static String k_entradas_tipo_email = "email";
    public static String k_entradas_tipo_month = "month";
    public static String k_entradas_tipo_number = "number";
    public static String k_entradas_tipo_range = "range";
    public static String k_opciones_mapa_range_min = "min";
    public static String k_opciones_mapa_range_max = "max";
    public static String k_entradas_tipo_seach = "search";
    public static String k_entradas_tipo_tel = "tel";
    public static String k_entradas_tipo_time = "time";
    public static String k_entradas_tipo_url = "url";
    public static String k_entradas_tipo_week = "week";    
    public static String k_entradas_tipo_file = "file";
    public static String k_entradas_tipo_password = "password";
    public static String k_entradas_tipo_hidden = "hidden";
    public static String k_entradas_tipo_submit = "submit";
    public static String k_entradas_tipo_reset = "reset";
    public static String k_entradas_tipo_image = "image";
    public static String k_entradas_tipo_button = "button";
    public static String k_entradas_tipo_checkbox = "checkbox";
    public static String k_entradas_tipo_radio = "radio";
    public static String k_entradas_tipo_cero = "0";
    public static String k_entradas_tipo_uno = "1";
    public static String k_entradas_codigo_cancelar = ".X.";
    public String _entrada_tipo;
    public clui_lecturas _clui_lectura;
    
    public boolean iniciar(String tipo_entrada, oks ok, Object ... extras_array) throws Exception {
        _entrada_tipo = tipo_entrada;
        return ok.es;
    }
    /**
     * Valida que el objeto obtenido en el control es válido
     * @param modo_operacion
     * @param objeto_a_validar
     * @param ok
     * @param extras_array
     * @return true si todo es correcto
     * @throws Exception 
     */
    @Override
    public boolean _validar(String modo_operacion, Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (modo_operacion.equals(k_fase_procesamiento)) {
                if (_entrada_tipo.equals(k_entradas_tipo_file)) {
                    return _validar_file(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_texto)) {
                    return _validar_texto(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_password)) {
                    return _validar_texto(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_color)) {
                    return _validar_color(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_date)) {
                    return _validar_date(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_datetime_local)) {
                    return _validar_datetime_local(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_email)) {
                    return _validar_email(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_month)) {
                    return _validar_month(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_number)) {
                    return _validar_number(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_range)) {
                    return _validar_range(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_tel)) {
                    return _validar_telefono(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_time)) {
                    return _validar_time(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_url)) {
                    return _validar_url(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_week)) {
                    return _validar_week(objeto_a_validar, ok, extras_array);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    
    public boolean _validar_texto(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            String regex = (String) opciones_mapa.get(k_opciones_mapa_patron_regex);
            if (regex != null) {
                return patrones.validar_patron((String) objeto_a_validar, regex, ok);
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    
    public boolean _validar_color(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        return patrones.validar_patron((String) objeto_a_validar, "#dddddd", ok);
    }
    
    public boolean _validar_date(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        return patrones.validar_fecha((String) objeto_a_validar, ok);
    }
    
    public boolean _validar_datetime_local(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        return patrones.validar_fecha_y_hora((String) objeto_a_validar, ok);
    }
    
    public boolean _validar_time(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        return patrones.validar_hora((String) objeto_a_validar, ok);
    }
    
    public boolean _validar_url(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        return patrones.validar_url_de_navegador((String) objeto_a_validar, ok);
    }
    
    public boolean _validar_email(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        return patrones.validar_email((String) objeto_a_validar, ok);
    }
    
    public boolean _validar_month(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            int num;
            num = Integer.parseInt((String) objeto_a_validar);
            if (num >= 1 && num <= 12) {
                ok.es = true;
            } else {
                ok.setTxt("El mes debe ser un número entre 1 y 12. ");
            }
        } catch (Exception e) {
            ok.setTxt("El mes debe ser un número entre 1 y 12. ", e);
        }
        return ok.es;
    }
    
    public boolean _validar_number(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            Double doble;
            doble = Double.valueOf((String) objeto_a_validar);
        } catch (Exception e) {
            ok.setTxt("El valor no se corresponde con un número (con o sin decimales). ", e);
        }
        return ok.es;
    }
    
    public boolean _validar_range(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            String min = (String) opciones_mapa.get(k_opciones_mapa_min);
            String max = (String) opciones_mapa.get(k_opciones_mapa_max);
            Double min_double;
            Double max_double;
            Double valor;
            valor = Double.valueOf((String) objeto_a_validar);
            if (max != null) {
                max_double = Double.valueOf(max);
            } else {
                max_double = 100.0;
            }
            if (min != null) {
                min_double = Double.valueOf(min);
            } else {
                min_double = 0.0;
            }
            if (min_double.compareTo(valor) < 0 
                    || max_double.compareTo(valor) > 0) {
                ok.setTxt("El valor está fuera del rango " + "(" + min_double + ", " + max_double + ")");
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
    
    public boolean _validar_telefono(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        return patrones.validar_telefono((String) objeto_a_validar, ok);
    }
    
    public boolean _validar_week(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            int num;
            num = Integer.parseInt((String) objeto_a_validar);
            if (num >= 1 && num <= 52) {
                ok.es = true;
            } else {
                ok.setTxt("La semana debe ser un número entre 1 y 52. ");
            }
        } catch (Exception e) {
            ok.setTxt("La semana debe ser un número entre 1 y 52. ", e);
        }
        return ok.es;
    }
    
    public boolean _validar_file(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            File file;
            file = new File((String) objeto_a_validar);
        } catch (Exception e) {
            ok.setTxt("Error en el formato de ruta y archivo. ", e);
        }
        return ok.es;
    }
    
    
    /**
     * Convierte el objeto_a_convertir a otro objeto (puede ser otra clase)
     * @param modo_operacion
     * @param objeto_a_convertir
     * @param ok
     * @param extras_array
     * @return El objeto resultado de convertir, o null si hay error.
     * @throws Exception 
     */
    @Override
    public Object _convertir(String modo_operacion, Object objeto_a_convertir, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (modo_operacion.equals(k_fase_procesamiento)) {
                if (_entrada_tipo.equals(k_entradas_tipo_file)) {
                    return new File((String) objeto_a_convertir);
                } else if (_entrada_tipo.equals(k_entradas_tipo_date)) {
                    Date date;
                    date = patrones.convertir_fecha((String) objeto_a_convertir, ok);
                    return date;
                } else if (_entrada_tipo.equals(k_entradas_tipo_datetime_local)) {
                    Date date;
                    date = patrones.convertir_fecha_y_hora((String) objeto_a_convertir, ok);
                    return date;
                } else if (_entrada_tipo.equals(k_entradas_tipo_month)) {
                    BigDecimal bigDecimal;
                    Double doble = Double.valueOf((String) objeto_a_convertir);
                    bigDecimal = new BigDecimal(doble);
                    return bigDecimal;
                } else if (_entrada_tipo.equals(k_entradas_tipo_number)) {
                    BigDecimal bigDecimal;
                    Double doble = Double.valueOf((String) objeto_a_convertir);
                    bigDecimal = new BigDecimal(doble);
                    return bigDecimal;
                } else if (_entrada_tipo.equals(k_entradas_tipo_range)) {
                    BigDecimal bigDecimal;
                    Double doble = Double.valueOf((String) objeto_a_convertir);
                    bigDecimal = new BigDecimal(doble);
                    return bigDecimal;
                } else if (_entrada_tipo.equals(k_entradas_tipo_time)) {
                    Date date;
                    date = patrones.convertir_hora((String) objeto_a_convertir, ok);
                    return date;
                } else if (_entrada_tipo.equals(k_entradas_tipo_week)) {
                    BigDecimal bigDecimal;
                    Double doble = Double.valueOf((String) objeto_a_convertir);
                    bigDecimal = new BigDecimal(doble);
                    return bigDecimal;
                } else if (_entrada_tipo.equals(k_entradas_tipo_submit)
                        || _entrada_tipo.equals(k_entradas_tipo_reset)
                        || _entrada_tipo.equals(k_entradas_tipo_image)
                        || _entrada_tipo.equals(k_entradas_tipo_button)
                        || _entrada_tipo.equals(k_entradas_tipo_checkbox)
                        || _entrada_tipo.equals(k_entradas_tipo_radio)) {
                    Boolean bool;
                    String texto;
                    texto = (String) objeto_a_convertir;
                    texto = texto.trim();
                    if (texto.equals(k_entradas_tipo_cero)) {
                        bool = false;
                    } else {
                        bool = true;
                    }
                    return bool;
                } else {
                    return (String) objeto_a_convertir;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Procesa la información capturada
     * @param modo_operacion
     * @param objeto_a_capturar
     * @param ok
     * @param extras_array
     * @return El objeto captuado, o null si hay error.
     * @throws Exception 
     */
    @Override
    public Object _capturar(String modo_operacion, Object objeto_a_capturar, oks ok, Object ... extras_array) throws Exception {
        Object object = null;
        try {
            if (ok.es == false) { return null; }
            if (modo_operacion.equals(k_fase_procesamiento)) {
                if (_entrada_tipo.equals(k_entradas_tipo_password)) {
                    object = _capturar_contraseña(objeto_a_capturar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_hidden)) {
                } else if (_entrada_tipo.equals(k_entradas_tipo_submit)) {
                    object = _capturar_boton(objeto_a_capturar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_reset)) {
                    object = _capturar_boton(objeto_a_capturar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_image)) {
                    object = _capturar_boton(objeto_a_capturar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_button)) {
                    object = _capturar_boton(objeto_a_capturar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_checkbox)) {
                    object = _capturar_boton(objeto_a_capturar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_radio)) {
                    object = _capturar_boton(objeto_a_capturar, ok, extras_array);                    
                } else {
                    object = _capturar_texto(objeto_a_capturar, ok, extras_array);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return object;
    }
    /**
     * Captura una línea de texto.
     * @param objeto_a_capturar
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public String _capturar_texto(Object objeto_a_capturar, oks ok, Object ... extras_array) throws Exception {
        String linea = null;
        try {
            if (ok.es == false) { return null; }
            linea = _clui_lectura.leer_linea(ok, extras_array);
            if (linea.equals(k_entradas_codigo_cancelar)) {
                _formulario.terminar_capturar(ok, extras_array);
            }
        } catch (Exception e) {
            throw e;
        }
        return linea;
    }    
    /**
     * Captura un botón (0 = no se deja pulsado, 1 = se deja pulsado).
     * @param objeto_a_capturar
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public String _capturar_boton(Object objeto_a_capturar, oks ok, Object ... extras_array) throws Exception {
        String linea = null;
        ResourceBundle in;
        try {
            if (ok.es == false) { return null; }
            while (true) {
                ok.iniciar();
                linea = _clui_lectura.leer_linea(ok, extras_array);
                linea = linea.trim();
                if (linea.equals(k_entradas_codigo_cancelar)) {
                    _formulario.terminar_capturar(ok, extras_array);
                }
                if (linea.equals(k_entradas_tipo_cero) || linea.equals(k_entradas_tipo_uno)) {
                    break;
                } else {
                    in = ResourceBundles.getBundle(k_in_base);
                    _formulario.escribir_linea_error(tr.in(in, "0 = libre, 1 = marcado. "), ok);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return linea;
    }    
    /**
     * Captura una línea de texto.
     * @param objeto_a_capturar
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public String _capturar_contraseña(Object objeto_a_capturar, oks ok, Object ... extras_array) throws Exception {
        String linea = null;
        try {
            if (ok.es == false) { return null; }
            Console console = System.console();
            if (console != null) {
                char [] password_array = console.readPassword();
                linea = new String (password_array);
            } else {
                linea = _clui_lectura.leer_linea(ok, extras_array);
            }                
            if (linea.equals(k_entradas_codigo_cancelar)) {
                _formulario.terminar_capturar(ok, extras_array);
            }
        } catch (Exception e) {
            throw e;
        }
        return linea;
    }    
    /**
     * Presenta información antes de capturar información
     * @param modo_operacion
     * @param objeto_a_presentar
     * @param ok
     * @param extras_array
     * @return true si todo es correcto
     * @throws Exception 
     */
    public boolean _presentar(String modo_operacion, Object objeto_a_presentar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (modo_operacion.equals(k_fase_procesamiento)) {
                super._presentar(modo_operacion, objeto_a_presentar, ok, extras_array);
                if (ok.es == false) { return false; }
                String texto = (String) objeto_a_presentar;
                if (objeto_a_presentar == null) { 
                    texto = "";
                }
                _formulario.escribir_linea(k_entradas_codigo_cancelar
                        + " [" + texto + "] > "
                        , ok);
            }            
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Procesa la información capturada
     * @param modo_operacion
     * @param objeto_a_procesar puede ser nulo (si no es solo_procesa_control)
     * @param ok
     * @param extras_array
     * @return Un objeto resultante de procesar, o null si hay error.
     * @throws Exception 
     */
    @Override
    public Object procesar(String modo_operacion, Object objeto_a_procesar, oks ok, Object ... extras_array) throws Exception {
        Object object = null;
        try {
            if (modo_operacion.equals(k_fase_procesamiento)) {
                object = super.procesar(modo_operacion, objeto_a_procesar, ok, extras_array);
                if (ok.es) {
                    ok.no_nul(object);
                }
                if (ok.es) {
                    if (_entrada_tipo.equals(k_entradas_tipo_submit)) {
                        _formulario.terminar_procesar(ok, extras_array);
                    } else if (_entrada_tipo.equals(k_entradas_tipo_reset)) {
                        _formulario.repetir_procesar(ok, extras_array);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return object;
    }
    
    public boolean poner_en_formulario(formularios formulario, String nombre, String mensaje_de_captura, Map<String, Object> opciones_mapa, oks ok, Object ... extras_array) throws Exception {
        try {
            super.poner_en_formulario(formulario, nombre, mensaje_de_captura, opciones_mapa, ok, extras_array);
            if (ok.es) {
                _clui_lectura = (clui_lecturas) opciones_mapa.get(k_entradas_clui_lectura);
                if (_clui_lectura == null) {
                    ok.setTxt("Falta la entrada del mapa: " + k_entradas_clui_lectura);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }}
