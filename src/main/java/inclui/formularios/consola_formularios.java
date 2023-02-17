package inclui.formularios;

import static inclui.formularios.controles.k_fase_procesamiento;
import static inclui.formularios.entradas.k_entradas_clui_lectura;
import inclui.utiles.clui_lecturas;
import innui.modelos.errores.oks;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author emilio
 */
public class consola_formularios extends formularios {
    public static String k_error_nombres_repetidos = "error_nombres_repetidos";
    public clui_lecturas _clui_lectura = new clui_lecturas();
    
    @Override
    public boolean _poner_control(controles control, oks ok, Object ... extras_array) throws Exception {
        super._poner_control(control, ok, extras_array);
        if (ok.es) {
            control.opciones_mapa.put(k_entradas_clui_lectura, _clui_lectura);
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
    @Override
    public boolean _iniciar_formulario(String modo_operacion, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (modo_operacion.equals(k_fase_procesamiento)) {
                super._iniciar_formulario(modo_operacion, ok, extras_array);
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * No hace nada
     * @param modo_operacion Es la fase de operación de los controles del _formulario
     * @param ok
     * @param extras_array
     * @return 
     * @throws Exception 
     */
    public boolean capturar(String modo_operacion, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return false; }
        return ok.es;        
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
            if (modo_operacion.equals(k_fase_procesamiento)) {
                Object object;
                while (true) {
                    ok.iniciar();
                    super.procesar(modo_operacion, ok, extras_array);
                    if (ok.es) {
                        revisar_nombres_repetidos(ok, extras_array);
                        if (ok.id.equals(k_error_nombres_repetidos)) {
                            oks ok_1 = new oks();
                            escribir_linea_error(ok.getTxt(), ok_1);
                            if (ok_1.es == false) {
                                break;
                            }
                            continue;
                        }
                    }
                    break;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }    
    
    public boolean revisar_nombres_repetidos(oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            Map<String, controles> nombres_mapa = new HashMap<>();
            Map<String, List<controles>> lista_controles_mapa = new HashMap<>();
            for (controles control: _controles_list) {
                if (nombres_mapa.containsKey(control.nombre) == false) {
                    nombres_mapa.put(control.nombre, control);
                } else {
                    if (lista_controles_mapa.containsKey(control.nombre)) {
                        lista_controles_mapa.get(control.nombre).add(control);
                    } else {
                        lista_controles_mapa.put(control.nombre, new ArrayList<>());
                        lista_controles_mapa.get(control.nombre).add(nombres_mapa.get(control.nombre));
                    }
                }
            }
            ok.iniciar();
            for (Entry<String, List<controles>> entry: lista_controles_mapa.entrySet()) {
                int i = 0;
                for (controles control: entry.getValue()) {
                    if (control.valor_del_objeto != null) {
                        i = i + 1;
                    }
                    if (i > 1) {
                        ok.id = k_error_nombres_repetidos;
                        ok.setTxt("Grupo de controles con mismo nombre y más de un valor: " + entry.getKey() + " \n", ok.getTxt());
                        break;
                    }
                }
            }                
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    
}
