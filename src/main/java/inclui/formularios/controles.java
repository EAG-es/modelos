package inclui.formularios;

import innui.bases;
import innui.modelos.errores.oks;
import java.util.Map;

/**
 *
 * @author emilio
 */
public class controles extends bases {
    public static String k_in_base = "in/inclui/formularios/in";
    public static String k_fase_captura = "fase_captura";
    public static String k_fase_procesamiento = "fase_procesamiento";
    public static String k_opciones_mapa_requerido = "requerido";
    public static String k_opciones_mapa_patron_regex = "pattern";
    public static String k_opciones_mapa_min = "min";
    public static String k_opciones_mapa_max = "max";
    public formularios _formulario = null;
    public Object valor_del_objeto = null;
    public String nombre = null;
    public String mensaje_de_captura = null;
    public Map<String, Object> opciones_mapa = null;

    /**
     * Valida que el objeto obtenido en el control es válido
     * @param modo_operacion
     * @param objeto_a_validar
     * @param ok
     * @param extras_array
     * @return true si todo es correcto
     * @throws Exception 
     */
    public boolean _validar(String modo_operacion, Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            
        } catch (Exception e) {
            throw e;
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
    public Object _convertir(String modo_operacion, Object objeto_a_convertir, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
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
            _formulario.escribir_linea(mensaje_de_captura, ok, extras_array);
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Inicia el proceso de capruta y procesamiento de la información
     * @param modo_operacion
     * @param objeto_a_iniciar
     * @param ok
     * @param extras_array
     * @return Un objeto iniciado, o null si hay error.
     * @throws Exception 
     */
    public Object _iniciar(String modo_operacion, Object objeto_a_iniciar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return null; }
            
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
    public Object _capturar(String modo_operacion, Object objeto_a_capturar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return null; }
            
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
    public Object procesar(String modo_operacion, Object objeto_a_procesar, oks ok, Object ... extras_array) throws Exception {
        Object object = null;
        try {
            if (ok.es == false) { return null; }
            object = _iniciar(modo_operacion, objeto_a_procesar, ok, extras_array);
            if (ok.es == false) { return null; }
            ok.no_nul(object);
            if (ok.es == false) { return null; }
            while (true) {
                ok.iniciar();
                _presentar(modo_operacion, object, ok, extras_array);
                if (ok.es == false) { break; }
                if (_formulario._es_terminar) {
                    break;
                }
                object = _capturar(modo_operacion, object, ok, extras_array);
                if (ok.es == false) { break; }
                ok.no_nul(object);
                if (ok.es == false) { break; }
                if (_formulario._es_terminar) {
                    break;
                }
                _validar(modo_operacion, object, ok, extras_array);
                if (ok.es == false) { 
                    oks ok_1 = new oks();
                    _formulario.escribir_linea_error(ok.getTxt(), ok_1);
                    if (ok_1.es == false) { break; }
                    continue; 
                }
                if (_formulario._es_terminar) {
                    break;
                }
                object = _convertir(modo_operacion, object, ok, extras_array);
                if (ok.es == false) { break; }
                if (opciones_mapa.containsKey(k_opciones_mapa_requerido)) {
                    if (object == null) {
                        continue;
                    } else if (object instanceof String) {
                        if (((String) object).isBlank()) {
                            continue;
                        }
                    }
                }
                ok.no_nul(object);
                if (ok.es == false) { break; }
                break;
            }
            oks ok_final = new oks();
            object = _terminar(modo_operacion, object, ok_final, extras_array);
            ok.setTxt(ok.getTxt(), ok_final.getTxt());
            if (ok.es == false) { return null; }
            ok_final.no_nul(object);
            if (ok_final.es == false) { return null; }
        } catch (Exception e) {
            throw e;
        }
        return object;
    }
    /**
     * Termina los procesos que iniciar dejó abiertos
     * @param modo_operacion
     * @param objeto_a_terminar
     * @param ok
     * @param extras_array
     * @return Un objeto resultante, o null si hay error.
     * @throws Exception 
     */
    public Object _terminar(String modo_operacion, Object objeto_a_terminar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return null; }
            
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Pone el control en un _formulario
     * @param formulario Formulario al que pertenecerá
     * @param nombre Identificador del control
     * @param mensaje_de_captura Mensaje antes de la captura de datos del control
     * @param opciones_mapa Mapa con opciones adicionales para el control
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean poner_en_formulario(formularios formulario, String nombre, String mensaje_de_captura, Map<String, Object> opciones_mapa, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            this._formulario = formulario;
            this.nombre = nombre;
            this.mensaje_de_captura = mensaje_de_captura;
            this.opciones_mapa = opciones_mapa;
            formulario._poner_control(this, ok, extras_array);            
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }

    public Object getValor_del_objeto() {
        return valor_del_objeto;
    }

    public void setValor_del_objeto(Object valor_del_objeto) {
        this.valor_del_objeto = valor_del_objeto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
        
}
