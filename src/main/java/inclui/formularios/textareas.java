package inclui.formularios;

import innui.modelos.errores.oks;

/**
 *
 * @author emilio
 */
public class textareas extends entradas {
    
    @Override
    public boolean iniciar(String tipo_entrada, oks ok, Object ... extras_array) throws Exception {
        _entrada_tipo = k_entradas_tipo_texto;
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
        return objeto_a_convertir.toString();
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
    public String _capturar_textos(Object objeto_a_capturar, oks ok, Object ... extras_array) throws Exception {
        String texto = "";
        String linea = null;
        try {
            if (ok.es == false) { return null; }
            while (true) {
                linea = _clui_lectura.leer_linea(ok, extras_array);
                if (linea.equals(k_entradas_codigo_cancelar)) {
                    break;
                }
                texto = texto + linea;
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
                String texto;
                if (objeto_a_presentar == null) { 
                    texto = "";
                } else {
                    texto = objeto_a_presentar.toString();
                }
            }            
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }

}
