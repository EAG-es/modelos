/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package innui.modelos.configuraciones;

import innui.bases;
import innui.modelos.errores.Loggers;
import innui.modelos.errores.oks;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author emilio
 */
public class iniciales extends bases {
    public static String k_ruta_relativa_recursos = "/"; //NOI18N
    public static String k_ruta_relativa_internacionalizacion = "/in"; //NOI18N
    public static String k_ruta_relativa_properties = "/configuraciones.properties";  //NOI18N
//    public static String k_in_ruta = "in/innui/modelos/configuraciones/in";  //NOI18N
    public Loggers logger = null;
    public Properties properties = null;
    
    public boolean iniciar(Class<?> mainclass, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
//          ResourceBundle in = null;
//            in = ResourceBundles.getBundle(k_in_ruta);
//            ok.no_nul(in);
            if (ok.es) {
                logger = Loggers.getLogger(null);
                ok.no_nul(logger);
            }
            if (ok.es) {
                logger.log(System.Logger.Level.INFO, "");
//                String test_logger;
//                test_logger = logger.toString();
//                Strings_max_Handler test_strings_max_Handler = null;
//              test_strings_max_Handler = extraer(logger, ok);
                ok.es = jars.instalar_carpeta_fuera(mainclass
                        , k_ruta_relativa_recursos
                        , ok);
            }
            if (ok.es) {
                ok.es = jars.instalar_carpeta_fuera(mainclass
                        , k_ruta_relativa_internacionalizacion
                        , ok);
//              ok.setTxt("Prueba de internacionalización", tr.in(in, "Excepción en el método principal. "));
            }
            if (ok.es) {
                properties = new Properties();
                // load a properties file
                InputStream inputStream = mainclass.getResourceAsStream(k_ruta_relativa_properties);
                properties.load(inputStream);
            }
        return ok.es;
        } catch (Exception e) {
            throw e; // Ayuda para la depuración
        }
    }
    
}
