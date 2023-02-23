package inclui.formularios;

import innui.formularios.formularios;
import static innui.formularios.formularios.k_fase_procesamiento;
import innui.modelos.configuraciones.ResourceBundles;
import innui.modelos.configuraciones.Resources;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author emilio
 */
public class control_selecciones extends control_entradas {
    public static String k_in_ruta = "in/inclui/formularios/in";
    public static String k_control_selecciones_opciones_mapa = "control_selecciones_opciones_mapa";
    public static String k_control_selecciones_letras_por_linea_num = "control_selecciones_letras_por_linea_num";
    public Map<String, Object> control_selecciones_mapa;
    public Integer _letras_por_linea = 0;
    
    public control_selecciones() {
        _control_tipo = k_entradas_tipo_numero;
    }

    public Map<String, Object> getControl_selecciones_mapa() {
        return control_selecciones_mapa;
    }

    public void setControl_selecciones_mapa(Map<String, Object> control_selecciones_mapa) {
        this.control_selecciones_mapa = control_selecciones_mapa;
    }
    
    /**
     * Inicia una textarea.
     * @param tipo_entrada Se ignora este parámetro
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    @Override
    public boolean iniciar(String tipo_entrada, oks ok, Object ... extras_array) throws Exception {
        _control_tipo = k_entradas_tipo_numero;
        return ok.es;
    }
    
    public Integer obtener_formato_para_formar_texto(StringBuffer formato, oks ok, Object ... extras_array) throws Exception {
        Integer tam = 0;
        String formato_tex = "";
        try {
            if (ok.es == false) { return null; }
            int max_tam = 0;
            String texto;
            Object valor_objeto;
            for (Entry<String, Object> entry: control_selecciones_mapa.entrySet()) {
                valor_objeto = entry.getValue();
                if (valor_objeto != null) {
                    texto = valor_objeto.toString();
                    tam = tam + texto.length();
                    if (max_tam < texto.length()) {
                        max_tam = texto.length();
                    }
                }
            }
            tam = tam / control_selecciones_mapa.size();
            if (tam*2 < max_tam) {
                tam = tam*2;
            }
            if (tam > _letras_por_linea) {
                tam = _letras_por_linea;
            }
            Double doble = Math.log10(control_selecciones_mapa.size()) + 1;
            formato_tex = "(%" + doble.intValue() + "d)%-" + tam + "s ";
        } catch (Exception e) {
            throw e;
        }
        formato.setLength(0);
        formato.append(formato_tex);
        return tam;
    }
    @Override
    public boolean _presentar(String modo_operacion, Object objeto_a_presentar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (modo_operacion.equals(k_fase_procesamiento)) {
                int columnas_num = 0;
                Object valor_objeto;
                String texto;
                String formato_tex;
                int tam;
                StringBuffer formato = new StringBuffer();
                tam = obtener_formato_para_formar_texto(formato, ok);
                if (ok.es == false) { return false; }
                formato_tex = formato.toString();
                columnas_num = _letras_por_linea / tam;
                int i = 0;
                int posicion = 1;
                for (Entry<String, Object> entry: control_selecciones_mapa.entrySet()) {
                    valor_objeto = entry.getValue();
                    if (valor_objeto != null) {
                        texto = valor_objeto.toString();
                        if (texto.length() > tam) {
                            texto = texto.substring(0, tam);
                        }
                    } else {
                        texto = "";
                    }
                    texto = _formulario.formar_texto(null, formato_tex, ok, posicion, texto);
                    if (ok.es == false) { return false; }
                    _formulario.escribir(texto, ok, extras_array);
                    i = i + 1;
                    if (i >= columnas_num) {
                        escribir_linea("", ok);
                        i = 0;
                    }
                    posicion = posicion + 1;
                }
                if (i > 0) {
                    escribir_linea("", ok);
                }
                super._presentar(modo_operacion, objeto_a_presentar, ok, extras_array);
            }
        } catch (Exception e) {
            throw e;
        }
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
        ResourceBundle in;
        try {
            if (ok.es == false) { return false; }
            if (modo_operacion.equals(k_fase_procesamiento)) {
                if (utilizar_valor_por_defecto(objeto_a_validar, ok, extras_array)) {
                    return true;
                } else if (ser_valor_vacio(objeto_a_validar, ok, extras_array)) {
                    if (this.opciones_mapa.containsKey(k_opciones_mapa_no_requerido) == false) {
                        in = ResourceBundles.getBundle(k_in_ruta);
                        ok.setTxt(tr.in(in, "Debe introducir un valor. "));
                    } 
                    return ok.es;
                }
            }
            int num;
            num = Integer.parseInt(objeto_a_validar.toString());
            if (num <= 0 || num >= control_selecciones_mapa.size()) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "El valor no es una opción válida. "));
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }    
    /**
     * Conecta un control con un formulario
     * @param formulario Formulario donde incorporar el control (por orden de inclusión)
     * @param clave Nombre identificador del formulario (en un grupo exclusivo el clave puede repetirse)
     * @param mensaje_de_captura Mensaje que presentar en la captura
     * @param opciones_mapa Opciones que va a utilizar el control en algunas de sus fases (debe contener: 
     * k_control_selecciones_opciones_mapa, k_control_selecciones_letras_por_linea_num)
     * @param ok
     * @param extras_array
     * @return true si no hay errores
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean poner_en_formulario(formularios formulario, String clave, Object valor, String mensaje_de_captura, Map<String, Object> opciones_mapa, oks ok, Object ... extras_array) throws Exception {
        ResourceBundle in;
        try {
            if (ok.es == false) { return false; }
            super.poner_en_formulario(formulario, clave, valor, mensaje_de_captura, opciones_mapa, ok, extras_array);
            if (ok.es) {
                control_selecciones_mapa = (Map<String, Object>) opciones_mapa.get(k_control_selecciones_opciones_mapa);
                if (control_selecciones_mapa == null) {
                    in = ResourceBundles.getBundle(k_in_ruta);
                    ok.setTxt(tr.in(in, "Falta la entrada del mapa: ") + k_control_selecciones_opciones_mapa);
                }
                _letras_por_linea = (Integer) opciones_mapa.get(k_control_selecciones_letras_por_linea_num);
                if (_letras_por_linea == null) {
                    in = ResourceBundles.getBundle(k_in_ruta);
                    ok.setTxt(tr.in(in, "Falta la entrada del mapa: ") + k_control_selecciones_letras_por_linea_num);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    
    public boolean cargar_propiedades(String ruta, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            InputStream inputStream;
            Properties properties;
            properties = new Properties();
            inputStream = Resources.getResourceAsStream(ruta, ok);
            properties.load(inputStream);
            Set<Entry<Object, Object>> objetos_set = properties.entrySet();
            control_selecciones_mapa = new TreeMap<>();
            Object clave_objeto;
            for (Entry<Object, Object> entry: objetos_set) {
                clave_objeto = entry.getKey();
                if (entry.getKey() != null) {
                    control_selecciones_mapa.put(clave_objeto.toString(), entry.getValue());
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    
    public Object leer_seleccion(oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (valor == null) {
                return null;
            } else {
                return control_selecciones_mapa.get(valor.toString());
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
