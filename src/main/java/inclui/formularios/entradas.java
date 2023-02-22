package inclui.formularios;

import innui.formularios.formularios;
import innui.formularios.controles;
import inclui.utiles.clui_lecturas;
import innui.modelos.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.errores.patrones;
import static innui.modelos.errores.patrones.k_patrones_formato_fecha;
import static innui.modelos.errores.patrones.k_patrones_formato_hora;
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
    public static String k_in_ruta = "in/inclui/formularios/in";
    public static String k_entradas_clui_lectura = "clui_lectura";
    public static String k_entradas_tipo_texto = "text";
    public static String k_entradas_tipo_color = "color";
    public static String k_entradas_tipo_fecha = "date";
    public static String k_entradas_tipo_fecha_y_hora = "datetime-local";
    public static String k_entradas_tipo_email = "email";
    public static String k_entradas_tipo_mes = "month";
    public static String k_entradas_tipo_numero = "number";
    public static String k_entradas_tipo_rango = "range";
    public static String k_opciones_mapa_rango_min = "min";
    public static String k_opciones_mapa_rango_max = "max";
    public static String k_entradas_tipo_search = "search";
    public static String k_entradas_tipo_telefono = "tel";
    public static String k_entradas_tipo_hora = "time";
    public static String k_entradas_tipo_url = "url";
    public static String k_entradas_tipo_semana = "week";    
    public static String k_entradas_tipo_ruta_archivo = "file";
    public static String k_entradas_tipo_password = "password";
    public static String k_entradas_tipo_hidden = "hidden";
    public static String k_entradas_tipo_submit = "submit";
    public static String k_entradas_tipo_reset = "reset";
    public static String k_entradas_tipo_imagen = "image";
    public static String k_entradas_tipo_boton = "button";
    public static String k_entradas_tipo_checkbox = "checkbox";
    public static String k_entradas_tipo_radio = "radio";
    public static String k_entradas_tipo_cero = "0";
    public static String k_entradas_tipo_uno = "1";
    public static String k_entradas_codigo_cancelar = ".X.";
    public static String k_entradas_patron_color = "#[0-9ABCDEF][0-9ABCDEF][0-9ABCDEF][0-9ABCDEF][0-9ABCDEF][0-9ABCDEF]";
    public static String k_entradas_formato_color = "#xxxxxx";
    public static String k_entradas_formato_duopcion = "0 | 1";
    public static String k_opciones_mapa_patron_regex = "pattern";
    public static String k_opciones_mapa_min = "min";
    public static String k_opciones_mapa_max = "max";
    public String _entrada_tipo;
    public clui_lecturas _clui_lectura;
    
    public boolean iniciar(String tipo_entrada, oks ok, Object ... extras_array) throws Exception {
        _entrada_tipo = tipo_entrada;
        return ok.es;
    }
    /**
     * Valida un valor vacío, si hay valor por defecto
     * @param objeto_a_validar
     * @param ok
     * @param extras_array
     * @return true si hay que utilizar el valor previo, por defecto.
     * @throws Exception 
     */
    public boolean utilizar_valor_por_defecto(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (valor != null) {
                // Existe un valor por defecto
                if (objeto_a_validar != null) {
                    if (objeto_a_validar instanceof String) {
                        if (objeto_a_validar.toString().isBlank()) {
                            return true;
                        }
                    }
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }    
    /**
     * Indica si el valor es vacío
     * @param objeto_a_validar
     * @param ok
     * @param extras_array
     * @return true si hay que utilizar el valor previo, por defecto.
     * @throws Exception 
     */
    public boolean ser_valor_vacio(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (objeto_a_validar != null) {
                if (objeto_a_validar instanceof String) {
                    if (objeto_a_validar.toString().isBlank()) {
                        return true;
                    }
                }
            } else {
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
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
        ResourceBundle in;
        try {
            if (ok.es == false) { return false; }
            if (modo_operacion.equals(k_fase_procesamiento)) {
                if (utilizar_valor_por_defecto(objeto_a_validar, ok, extras_array)) {
                    return true;
                } else if (ser_valor_vacio(objeto_a_validar, ok, extras_array)) {
                    if (this.opciones_mapa.containsKey(k_opciones_mapa_requerido)) {
                        in = ResourceBundles.getBundle(k_in_ruta);
                        ok.setTxt(tr.in(in, "Debe introducir un valor. "));
                    } 
                    return ok.es;
                }
                if (_entrada_tipo.equals(k_entradas_tipo_ruta_archivo)) {
                    return _validar_ruta_archivo(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_texto)
                        || _entrada_tipo.equals(k_entradas_tipo_search)) {
                    return _validar_texto(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_password)) {
                    return _validar_texto(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_color)) {
                    return _validar_color(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_fecha)) {
                    return _validar_fecha(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_fecha_y_hora)) {
                    return _validar_fecha_y_hora(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_email)) {
                    return _validar_email(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_mes)) {
                    return _validar_mes(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_numero)) {
                    return _validar_numero(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_rango)) {
                    return _validar_rango(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_telefono)) {
                    return _validar_telefono(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_hora)) {
                    return _validar_hora(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_url)) {
                    return _validar_url(objeto_a_validar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_semana)) {
                    return _validar_semana(objeto_a_validar, ok, extras_array);
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
                return patrones.validar_patron(objeto_a_validar.toString(), regex, ok);
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    
    public boolean _validar_color(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        return patrones.validar_patron(objeto_a_validar.toString(), k_entradas_patron_color, ok);
    }
    
    public boolean _validar_fecha(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        return patrones.validar_fecha(objeto_a_validar.toString(), ok);
    }
    
    public boolean _validar_fecha_y_hora(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        return patrones.validar_fecha_y_hora(objeto_a_validar.toString(), ok);
    }
    
    public boolean _validar_hora(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        return patrones.validar_hora(objeto_a_validar.toString(), ok);
    }
    
    public boolean _validar_url(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        return patrones.validar_url_de_navegador(objeto_a_validar.toString(), ok);
    }
    
    public boolean _validar_email(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        return patrones.validar_email(objeto_a_validar.toString(), ok);
    }
    
    public boolean _validar_mes(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        ResourceBundle in;
        try {
            if (ok.es == false) { return false; }
            int num;
            num = Integer.parseInt(objeto_a_validar.toString());
            if (num >= 1 && num <= 12) {
                ok.es = true;
            } else {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "El mes debe ser un número entre 1 y 12. "));
            }
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "El mes debe ser un número entre 1 y 12. "), e);
        }
        return ok.es;
    }
    
    public boolean _validar_numero(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return false; }
        ResourceBundle in;
        Double doble;
        try {
            doble = Double.valueOf(objeto_a_validar.toString());
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "El valor no se corresponde con un número (con o sin decimales). "));
        }
        return ok.es;
    }
    
    public boolean _validar_rango(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        ResourceBundle in;
        try {
            if (ok.es == false) { return false; }
            String min = (String) opciones_mapa.get(k_opciones_mapa_min);
            String max = (String) opciones_mapa.get(k_opciones_mapa_max);
            Double min_double;
            Double max_double;
            Double valor;
            valor = Double.valueOf(objeto_a_validar.toString());
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
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "El valor está fuera del rango ") + "(" + min_double + ", " + max_double + ")");
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
    
    public boolean _validar_telefono(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        return patrones.validar_telefono(objeto_a_validar.toString(), ok);
    }
    
    public boolean _validar_semana(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        ResourceBundle in;
        try {
            if (ok.es == false) { return false; }
            int num;
            num = Integer.parseInt(objeto_a_validar.toString());
            if (num >= 1 && num <= 52) {
                ok.es = true;
            } else {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "La semana debe ser un número entre 1 y 52. "));
            }
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "La semana debe ser un número entre 1 y 52. "), e);
        }
        return ok.es;
    }
    
    public boolean _validar_ruta_archivo(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        ResourceBundle in;
        try {
            if (ok.es == false) { return false; }
            File file;
            file = new File(objeto_a_validar.toString());
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "Error en el formato de ruta y archivo. "), e);
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
        ResourceBundle in;
        try {
            if (ok.es == false) { return false; }
            if (modo_operacion.equals(k_fase_procesamiento)) {
                if (utilizar_valor_por_defecto(objeto_a_convertir, ok, extras_array)) {
                    return valor;
                }
                if (_entrada_tipo.equals(k_entradas_tipo_ruta_archivo)) {
                    return new File(objeto_a_convertir.toString());
                } else if (_entrada_tipo.equals(k_entradas_tipo_numero)
                        || _entrada_tipo.equals(k_entradas_tipo_mes)
                        || _entrada_tipo.equals(k_entradas_tipo_semana)
                        || _entrada_tipo.equals(k_entradas_tipo_rango)) {
                    BigDecimal bigDecimal = null;
                    if (ser_valor_vacio(objeto_a_convertir, ok, extras_array) == false) {
                        Double doble = Double.valueOf(objeto_a_convertir.toString());
                        bigDecimal = new BigDecimal(doble);
                    }
                    return bigDecimal;
                } else if (_entrada_tipo.equals(k_entradas_tipo_fecha)) {
                    Date date;
                    date = patrones.convertir_fecha(objeto_a_convertir.toString(), ok);
                    return date;
                } else if (_entrada_tipo.equals(k_entradas_tipo_fecha_y_hora)) {
                    Date date;
                    date = patrones.convertir_fecha_y_hora(objeto_a_convertir.toString(), ok);
                    return date;
                } else if (_entrada_tipo.equals(k_entradas_tipo_hora)) {
                    Date date;
                    date = patrones.convertir_hora(objeto_a_convertir.toString(), ok);
                    return date;
                } else if (_entrada_tipo.equals(k_entradas_tipo_submit)
                        || _entrada_tipo.equals(k_entradas_tipo_reset)
                        || _entrada_tipo.equals(k_entradas_tipo_imagen)
                        || _entrada_tipo.equals(k_entradas_tipo_boton)
                        || _entrada_tipo.equals(k_entradas_tipo_checkbox)
                        || _entrada_tipo.equals(k_entradas_tipo_radio)) {
                    Boolean bool;
                    String texto;
                    if (objeto_a_convertir == null) {
                        texto = "";
                    } else {
                        texto = objeto_a_convertir.toString();
                    }
                    texto = texto.trim();
                    if (texto.equals(k_entradas_tipo_cero)) {
                        bool = false;
                    } else {
                        bool = true;
                    }
                    return bool;
                } else {
                    return objeto_a_convertir.toString();
                }
            }
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "Error al convertir el valor digitalmente. "), extras_array);
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
                } else if (_entrada_tipo.equals(k_entradas_tipo_imagen)) {
                    object = _capturar_boton(objeto_a_capturar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_boton)) {
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
            linea = _clui_lectura.leer_linea(ok);
            if (linea.equals(k_entradas_codigo_cancelar)) {
                _formulario.terminar(ok, extras_array);
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
                    _formulario.terminar(ok, extras_array);
                }
                if (linea.equals(k_entradas_tipo_cero) || linea.equals(k_entradas_tipo_uno)) {
                    break;
                } else if (utilizar_valor_por_defecto(linea, ok, extras_array) == false) {
                    in = ResourceBundles.getBundle(k_in_ruta);
                    _formulario.escribir_linea_error(tr.in(in, "0 = libre, no; 1 = marcado, sí. "), ok);
                } else {
                    break;
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
                _formulario.terminar(ok, extras_array);
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
                if (_entrada_tipo.equals(k_entradas_tipo_fecha)) {
                    return _presentar_fecha(modo_operacion, objeto_a_presentar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_fecha_y_hora)) {
                    return _presentar_fecha_y_hora(modo_operacion, objeto_a_presentar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_hora)) {
                    return _presentar_hora(modo_operacion, objeto_a_presentar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_color)) {
                    return _presentar_color(modo_operacion, objeto_a_presentar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_submit)
                        || _entrada_tipo.equals(k_entradas_tipo_reset)
                        || _entrada_tipo.equals(k_entradas_tipo_imagen)
                        || _entrada_tipo.equals(k_entradas_tipo_boton)
                        || _entrada_tipo.equals(k_entradas_tipo_checkbox)
                        || _entrada_tipo.equals(k_entradas_tipo_radio)) {
                    return _presentar_duopcion(modo_operacion, objeto_a_presentar, ok, extras_array);
                } else if (_entrada_tipo.equals(k_entradas_tipo_password)) {
                    return _presentar_password(modo_operacion, objeto_a_presentar, ok, extras_array);
                } else {
                    super._presentar(modo_operacion, objeto_a_presentar, ok, extras_array);
                    if (ok.es == false) { return false; }
                    if (utilizar_valor_por_defecto(objeto_a_presentar, ok, extras_array)) {
                        objeto_a_presentar = valor;
                    }
                    if (ok.es == false) { return false; }
                    String texto;
                    if (objeto_a_presentar == null) { 
                        texto = "";
                    } else {
                        texto = objeto_a_presentar.toString();
                    }
                    _formulario.escribir(k_entradas_codigo_cancelar
                            + " [" + texto + "] > "
                            , ok);
                }
            }            
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Presenta información antes de capturar una contraseña
     * @param modo_operacion
     * @param objeto_a_presentar
     * @param ok
     * @param extras_array
     * @return true si todo es correcto
     * @throws Exception 
     */
    public boolean _presentar_password(String modo_operacion, Object objeto_a_presentar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (modo_operacion.equals(k_fase_procesamiento)) {
                super._presentar(modo_operacion, objeto_a_presentar, ok, extras_array);
                if (ok.es == false) { return false; }
                if (utilizar_valor_por_defecto(objeto_a_presentar, ok, extras_array)) {
                    objeto_a_presentar = valor;
                }
                if (ok.es == false) { return false; }
                String texto;
                if (objeto_a_presentar == null) { 
                    texto = "";
                } else  { 
                    texto = (String) objeto_a_presentar;
                    texto = texto.replaceAll(".", "*");
                }
                _formulario.escribir(k_entradas_codigo_cancelar
                        + " [" + texto + "] > "
                        , ok);
            }            
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Presenta información antes de capturar una fecha
     * @param modo_operacion
     * @param objeto_a_presentar
     * @param ok
     * @param extras_array
     * @return true si todo es correcto
     * @throws Exception 
     */
    public boolean _presentar_duopcion(String modo_operacion, Object objeto_a_presentar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (modo_operacion.equals(k_fase_procesamiento)) {
                super._presentar(modo_operacion, objeto_a_presentar, ok, extras_array);
                if (ok.es == false) { return false; }
                if (utilizar_valor_por_defecto(objeto_a_presentar, ok, extras_array)) {
                    objeto_a_presentar = valor;
                }
                if (ok.es == false) { return false; }
                String texto;
                if (objeto_a_presentar == null) { 
                    texto = "";
                } else if (objeto_a_presentar instanceof String) { 
                    texto = (String) objeto_a_presentar;
                } else if ((Boolean) objeto_a_presentar) {
                    texto = "1";
                } else {
                    texto = "0";
                }
                _formulario.escribir(k_entradas_codigo_cancelar
                        + " <" + k_entradas_formato_duopcion + ">"
                        + " [" + texto + "] > "
                        , ok);
            }            
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Presenta información antes de capturar una fecha
     * @param modo_operacion
     * @param objeto_a_presentar
     * @param ok
     * @param extras_array
     * @return true si todo es correcto
     * @throws Exception 
     */
    public boolean _presentar_fecha(String modo_operacion, Object objeto_a_presentar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (modo_operacion.equals(k_fase_procesamiento)) {
                super._presentar(modo_operacion, objeto_a_presentar, ok, extras_array);
                if (ok.es == false) { return false; }
                if (utilizar_valor_por_defecto(objeto_a_presentar, ok, extras_array)) {
                    objeto_a_presentar = valor;
                }
                if (ok.es == false) { return false; }
                String texto;
                if (objeto_a_presentar == null) { 
                    texto = "";
                } else if (objeto_a_presentar instanceof String) { 
                    texto = (String) objeto_a_presentar;
                } else {
                    texto = formar_texto(null, "%1$td/%1$tm/%1$tY", ok, (Date) objeto_a_presentar);
                }
                _formulario.escribir(k_entradas_codigo_cancelar
                        + " <" + k_patrones_formato_fecha + ">"
                        + " [" + texto + "] > "
                        , ok);
            }            
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Presenta información antes de capturar una hora
     * @param modo_operacion
     * @param objeto_a_presentar
     * @param ok
     * @param extras_array
     * @return true si todo es correcto
     * @throws Exception 
     */
    public boolean _presentar_hora(String modo_operacion, Object objeto_a_presentar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (modo_operacion.equals(k_fase_procesamiento)) {
                super._presentar(modo_operacion, objeto_a_presentar, ok, extras_array);
                if (ok.es == false) { return false; }
                if (utilizar_valor_por_defecto(objeto_a_presentar, ok, extras_array)) {
                    objeto_a_presentar = valor;
                }
                if (ok.es == false) { return false; }
                String texto;
                if (objeto_a_presentar == null) { 
                    texto = "";
                } else if (objeto_a_presentar instanceof String) { 
                    texto = (String) objeto_a_presentar;
                } else {
                    texto = formar_texto(null, "%1$tH:%1$tM", ok, objeto_a_presentar);
                }
                _formulario.escribir(k_entradas_codigo_cancelar
                        + " <" + k_patrones_formato_hora + ">"
                        + " [" + texto + "] > "
                        , ok);
            }            
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Presenta información antes de capturar una fecha y hora
     * @param modo_operacion
     * @param objeto_a_presentar
     * @param ok
     * @param extras_array
     * @return true si todo es correcto
     * @throws Exception 
     */
    public boolean _presentar_fecha_y_hora(String modo_operacion, Object objeto_a_presentar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (modo_operacion.equals(k_fase_procesamiento)) {
                super._presentar(modo_operacion, objeto_a_presentar, ok, extras_array);
                if (ok.es == false) { return false; }
                if (utilizar_valor_por_defecto(objeto_a_presentar, ok, extras_array)) {
                    objeto_a_presentar = valor;
                }
                if (ok.es == false) { return false; }
                String texto;
                if (objeto_a_presentar == null) { 
                    texto = "";
                } else if (objeto_a_presentar instanceof String) { 
                    texto = (String) objeto_a_presentar;
                } else {
                    texto = formar_fecha(null, (Date) objeto_a_presentar, ok);
                }
                _formulario.escribir(k_entradas_codigo_cancelar
                        + " <(" + k_patrones_formato_fecha + ") (" + k_patrones_formato_hora + ")>"
                        + " [" + texto + "] > "
                        , ok);
            }            
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Presenta información antes de capturar un color
     * @param modo_operacion
     * @param objeto_a_presentar
     * @param ok
     * @param extras_array
     * @return true si todo es correcto
     * @throws Exception 
     */
    public boolean _presentar_color(String modo_operacion, Object objeto_a_presentar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (modo_operacion.equals(k_fase_procesamiento)) {
                super._presentar(modo_operacion, objeto_a_presentar, ok, extras_array);
                if (ok.es == false) { return false; }
                if (utilizar_valor_por_defecto(objeto_a_presentar, ok, extras_array)) {
                    objeto_a_presentar = valor;
                }
                if (ok.es == false) { return false; }
                String texto;
                if (objeto_a_presentar == null) { 
                    texto = "";
                } else {
                    texto = objeto_a_presentar.toString();
                }
                _formulario.escribir(k_entradas_codigo_cancelar
                        + " <" + k_entradas_formato_color + ">"
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
                    if (_entrada_tipo.equals(k_entradas_tipo_submit)) {
                        if((Boolean) object) {
                            _formulario.terminar(ok, extras_array);
                        } else {
                            _formulario.cancelar(ok, extras_array);
                        }
                    } else if (_entrada_tipo.equals(k_entradas_tipo_reset)
                           && (Boolean) object) {
                            _formulario.repetir(ok, extras_array);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return object;
    }
    /**
     * Conecta un control con un formulario
     * @param formulario Formulario donde incorporar el control (por orden de inclusión)
     * @param nombre Nombre identificador del formulario (en un grupo exclusivo el clave puede repetirse)
     * @param mensaje_de_captura Mensaje que presentar en la captura
     * @param opciones_mapa Opciones que va a utilizar el control en algunas de sus fases (puede ser null)
     * @param ok
     * @param extras_array
     * @return true si no hay errores
     * @throws Exception 
     */
    public boolean poner_en_formulario(formularios formulario, String nombre, String mensaje_de_captura, Map<String, Object> opciones_mapa, oks ok, Object ... extras_array) throws Exception {
        ResourceBundle in;
        try {
            super.poner_en_formulario(formulario, nombre, mensaje_de_captura, opciones_mapa, ok, extras_array);
            if (ok.es) {
                _clui_lectura = (clui_lecturas) this.opciones_mapa.get(k_entradas_clui_lectura);
                if (_clui_lectura == null) {
                    in = ResourceBundles.getBundle(k_in_ruta);
                    ok.setTxt(tr.in(in, "Falta la entrada del mapa: ") + k_entradas_clui_lectura);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }}
