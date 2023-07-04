package innui.modelos.configuraciones;

import innui.bases;
import innui.modelos.errores.Loggers;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public abstract class iniciales extends bases {
    public static String k_ruta_relativa_recursos = "/re"; //NOI18N
    public static String k_ruta_relativa_internacionalizacion = "/in"; //NOI18N
    public static String k_extension_properties = ".properties";
    public static String k_ruta_relativa_properties = "/re/configuraciones" + k_extension_properties;  //NOI18N
    public static String k_in_ruta = "in/innui/modelos/configuraciones/in";  //NOI18N
    public static String k_propiedad_duplicada = "propiedad_duplicada";
    public static String k_iniciales_reinstalacion_num = "iniciales.reinstalacion_num";
    public Properties properties = null;
    
    /**
     * Este método debería ser llamado desde iniciar. 
     * Realiza la configuración por defecto del logger, 
     * instala las carpetas de recursos (k_ruta_relativa_recursos) y de internacionalización (k_ruta_relativa_internacionalizacion) fuera del jar, 
     * lee el archivo de propiedades desde k_ruta_relativa_properties
     * @param mainclass Clase desde donde localizar el origen de las rutas relativas.
     * @param ok Comunicar resultados
     * @param extras_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public boolean _iniciar_desde_clase(Class<?> mainclass, oks ok, Object ... extras_array) throws Exception {
        return _iniciar_desde_clase(false, mainclass, ok, extras_array);
    }
    /**
     * _iniciar_desde_clase con opción de reinstalacion
     * @param es_reinstalacion
     * @param mainclass
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean _iniciar_desde_clase(boolean es_reinstalacion, Class<?> mainclass, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            String ruta_properties;
            File file;
            String reinstalacion_tex;
            InputStream inputStream;
            int reinstalacion_num = -1;
            int reinstalacion_previa_num = -1;
            boolean es_reinstalacion_realizada = false;
            ResourceBundle in;
            in = ResourceBundles.getBundle(k_in_ruta);
            _logger = Loggers.getLogger(null);
            bases.k_logger_nombre.setLength(0);
            bases.k_logger_nombre.append(_logger.getName());
            ok.no_nul(_logger, tr.in(in, "Logger nulo. "));
            if (ok.es == false) { return false; }
            // Cargar un archivo properties
            if (es_reinstalacion == false) {
                ruta_properties = mainclass.getPackageName();
                file = new File(k_ruta_relativa_recursos, ruta_properties + k_extension_properties);
                ruta_properties = file.getCanonicalPath();
                inputStream = mainclass.getResourceAsStream(ruta_properties);
                if (inputStream == null) {
                    ruta_properties = k_ruta_relativa_properties;
                    inputStream = mainclass.getResourceAsStream(ruta_properties);
                }
                if (inputStream != null) {
                    Properties propertie = new Properties();
                    propertie.load(inputStream);
                    reinstalacion_tex = propertie.getProperty(k_iniciales_reinstalacion_num);
                    if (reinstalacion_tex != null) {
                        reinstalacion_num = Integer.parseInt(reinstalacion_tex);
                    }
                }
            }
            recursos_modificables.instalar_carpeta_fuera(es_reinstalacion, mainclass
                    , k_ruta_relativa_recursos
                    , ok);
            if (ok.es == false) { return false; }
            recursos_modificables.instalar_carpeta_fuera(es_reinstalacion, mainclass
                    , k_ruta_relativa_internacionalizacion
                    , ok);
            if (ok.es == false) { return false; }
            if (properties == null) {
                properties = new Properties();
            }
            // Cargar un archivo properties
            ruta_properties = mainclass.getPackageName();
            file = new File(k_ruta_relativa_recursos, ruta_properties + k_extension_properties);
            ruta_properties = file.getCanonicalPath();
            inputStream = Resources.getResourceAsStream(mainclass, ruta_properties);
            if (inputStream == null) {
                ruta_properties = k_ruta_relativa_properties;
                inputStream = Resources.getResourceAsStream(mainclass, ruta_properties);
            }
            if (inputStream != null) {
                Properties propertie = new Properties();
                propertie.load(inputStream);
                inputStream.close();
                if (es_reinstalacion == false) {
                    reinstalacion_tex = propertie.getProperty(k_iniciales_reinstalacion_num);
                    if (reinstalacion_tex != null) {
                        reinstalacion_previa_num = Integer.parseInt(reinstalacion_tex);
                        if (reinstalacion_previa_num < reinstalacion_num) {
                            _iniciar_desde_clase(true, mainclass, ok, extras_array);
                            if (ok.es == false) { return false; }
                            es_reinstalacion_realizada = true;
                            properties.setProperty(k_iniciales_reinstalacion_num
                                    , String.valueOf(reinstalacion_num));
                            _terminar_desde_clase(mainclass, ok, extras_array);
                            if (ok.es == false) { return false; }
                            properties.remove(k_iniciales_reinstalacion_num);
                        }
                    }
                }
                if (es_reinstalacion_realizada == false) {
                    String clave;
                    for (Entry<Object, Object> entry : propertie.entrySet()) {
                        clave = entry.getKey().toString();
                        if (clave.equals(k_iniciales_reinstalacion_num) == false) {
                            if (properties.containsKey(entry.getKey())) {
                                ok.id = k_propiedad_duplicada;
                                ok.gravedad = oks.k_gravedad_baja;
                                ok.setTxt(tr.in(in, "Propiedad duplicada: ") + entry.getKey());
                                break;
                            }
                            properties.setProperty((String) entry.getKey(), (String) entry.getValue());
                        }
                    }
                }
            }
            return ok.es;
        } catch (Exception e) {
            throw e; // Ayuda para la depuración
        }
    }
    /**
     * Este método debería ser llamado desde iniciar. 
     * Realiza la configuración por defecto del logger, 
     * instala las carpetas de recursos (k_ruta_relativa_recursos) y de internacionalización (k_ruta_relativa_internacionalizacion) fuera del jar, 
     * lee el archivo de propiedades desde k_ruta_relativa_properties
     * @param mainclass Clase desde donde localizar el origen de las rutas relativas.
     * @param ok Comunicar resultados
     * @param extras_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public boolean _terminar_desde_clase(Class<?> mainclass, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            ResourceBundle in;
            in = ResourceBundles.getBundle(k_in_ruta);
            // load a properties file
            String ruta_properties;
            ruta_properties = mainclass.getPackageName();
            File file = new File(k_ruta_relativa_recursos, ruta_properties + k_extension_properties);
            ruta_properties = file.getCanonicalPath();
            InputStream inputStream = Resources.getResourceAsStream(mainclass, ruta_properties);
            if (inputStream == null) {
                ruta_properties = k_ruta_relativa_properties;
                inputStream = Resources.getResourceAsStream(mainclass, ruta_properties);
            }
            if (inputStream != null) {
                boolean es_cambio = false;
                Properties propertie = new Properties();
                propertie.load(inputStream);
                for (Entry<Object, Object> entry: properties.entrySet()) {
                    if (propertie.containsKey(entry.getKey())) {
                        es_cambio = true;
                        propertie.put(entry.getKey(), entry.getValue());
                    }
                }
                inputStream.close();
                if (es_cambio) {
                    ruta_properties = rutas.crear_ruta_desde_clase(mainclass, ruta_properties, ok);
                    if (ok.es == false) { return false; }
                    ok.no_nul(ruta_properties);
                    if (ok.es == false) { return false; }
                    try (OutputStream outputStream = new FileOutputStream(ruta_properties)) {
                        propertie.store(outputStream, "");
                    }
                }
            }
            return ok.es;
        } catch (Exception e) {
            throw e; // Ayuda para la depuración
        }
    }
    /**
     * Método que se debería ejecutar después de iniciar.
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public abstract boolean run(oks ok, Object ... extra_array) throws Exception;
    /**
     * Método para iniciar los modelos (deben llamar a _iniciar_desde_clase).
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public abstract boolean iniciar(oks ok, Object ... extra_array) throws Exception;
    /**
     * Método para terminar los modelos (deben llamar a _terminar_desde_clase).
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public abstract boolean terminar(oks ok, Object ... extra_array) throws Exception;
}
