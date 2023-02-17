package inclui.formularios;

import static inclui.formularios.controles.k_fase_captura;
import static inclui.formularios.controles.k_fase_procesamiento;
import innui.bases;
import innui.modelos.errores.oks;
import java.util.LinkedList;

/**
 *
 * @author emilio
 */
public class formularios extends bases {
    public static String k_in_base = "in/inclui/formularios/in";
    
    public LinkedList<controles> _controles_list = new LinkedList<>();
    public boolean _es_terminar = false;
    public boolean _es_repetir = false;
    
    public boolean _poner_control(controles control, oks ok, Object ... extras_array) throws Exception {
        if (_controles_list.contains(control) == false) {
            _controles_list.add(control);
        }
        return ok.es;
    }
    /**
     * Inicia el proceso de capruta y procesamiento de la información
     * @param modo_operacion Es la fase de operación de los controles del _formulario
     * @param ok
     * @param extras_array
     * @return 
     * @throws Exception 
     */
    public boolean _iniciar_formulario(String modo_operacion, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            _es_terminar = false;
            _es_repetir = false;
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Realiza los procesos necesarios para capturar los datos
     * @param ok
     * @param extras_array
     * @return 
     * @throws Exception 
     */
    public boolean capturar(oks ok, Object ... extras_array) throws Exception {
        return capturar(k_fase_captura, ok, extras_array);
    }
    /**
     * Realiza los procesos necesarios para capturar los datos
     * @param modo_operacion Es la fase de operación de los controles del _formulario
     * @param ok
     * @param extras_array
     * @return 
     * @throws Exception 
     */
    public boolean capturar(String modo_operacion, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            Object object;
            while (true) {
                ok.iniciar();
                _iniciar_formulario(modo_operacion, ok, extras_array);
                if (ok.es == false) { return false; }
                for (controles control: _controles_list) {
                    if (_es_terminar) {
                        break;
                    }
                    object = control.procesar(modo_operacion, control.valor_del_objeto, ok, extras_array);
                    if (ok.es == false) { break; }
                    if (_es_repetir) {
                        break;
                    }
                    ok.no_nul(object);
                    if (ok.es == false) { break; }
                    control.valor_del_objeto = object;
                }
                oks ok_final = new oks();
                _terminar_formulario(modo_operacion, ok_final, extras_array);
                ok.setTxt(ok.getTxt(), ok_final.getTxt());
                if (ok.es == false) { return false; }
                if (_es_repetir == false) {
                    break;
                }
            }                       
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /** 
     * Repite la captura del _formulario
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean repetir_capturar(oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            _es_repetir = true;
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /** 
     * Evita que se continúe con la captura del _formulario
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean terminar_capturar(oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            _es_terminar = true;
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Realiza los procesos necesarios para capturar los datos
     * @param ok
     * @param extras_array
     * @return El objeto procesado, o null si hay error o no hay captura.
     * @throws Exception 
     */
    public Object procesar(oks ok, Object ... extras_array) throws Exception {
        return procesar(k_fase_procesamiento, ok, extras_array);
    }
    /**
     * Realiza los procesos posteriores a la captura de los datos
     * @param modo_operacion Es la fase de operación de los controles del _formulario
     * @param ok
     * @param extras_array
     * @return 
     * @throws Exception 
     */
    public boolean procesar(String modo_operacion, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            Object object;
            while (true) {
                ok.iniciar();
                _iniciar_formulario(modo_operacion, ok, extras_array);
                if (ok.es == false) { return false; }
                for (controles control: _controles_list) {
                    if (_es_terminar) {
                        break;
                    }
                    object = control.procesar(modo_operacion, control.valor_del_objeto, ok, extras_array);
                    if (ok.es == false) { break; }
                    if (_es_repetir) {
                        break;
                    }
                    ok.no_nul(object);
                    if (ok.es == false) { break; }
                    control.valor_del_objeto = object;
                }
                oks ok_final = new oks();
                _terminar_formulario(modo_operacion, ok_final, extras_array);
                ok.setTxt(ok.getTxt(), ok_final.getTxt());
                if (ok.es == false) { return false; }
                if (_es_repetir == false) {
                    break;
                }
            }                       
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }    
    /** 
     * Repite el procesamiento del _formulario
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean repetir_procesar(oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            _es_repetir = true;            
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /** 
     * Evita que se continúe con el procesamiento del _formulario
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean terminar_procesar(oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            _es_terminar = true;
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Termina los procesos que iniciar dejó abiertos
     * @param modo_operacion Es la fase de operación de los controles del _formulario
     * @param ok
     * @param extras_array
     * @return 
     * @throws Exception 
     */
    public boolean _terminar_formulario(String modo_operacion, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }

}
