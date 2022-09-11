/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package innui.modelos.configuraciones;

import innui.bases;
import innui.modelos.errores.Loggers;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public abstract class iniciales extends bases {
    public static String k_ruta_relativa_recursos = "/re"; //NOI18N
    public static String k_ruta_relativa_internacionalizacion = "/in"; //NOI18N
    public static String k_ruta_relativa_properties = "/re/configuraciones.properties";  //NOI18N
    public static String k_in_ruta = "in/innui/modelos/configuraciones/in";  //NOI18N
    public Loggers logger = null;
    public Properties properties = null;
    
    public boolean iniciar(Class<?> mainclass, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            ResourceBundle in;
            in = ResourceBundles.getBundle(k_in_ruta);
            logger = Loggers.getLogger(null);
            ok.no_nul(logger, tr.in(in, "Logger nulo. "));
            if (ok.es == false) { return false; }
            jars.instalar_carpeta_fuera(mainclass
                    , k_ruta_relativa_recursos
                    , ok);
            if (ok.es == false) { return false; }
            jars.instalar_carpeta_fuera(mainclass
                    , k_ruta_relativa_internacionalizacion
                    , ok);
            if (ok.es == false) { return false; }
            properties = new Properties();
            // load a properties file
            InputStream inputStream = mainclass.getResourceAsStream(k_ruta_relativa_properties);
            properties.load(inputStream);
            return ok.es;
        } catch (Exception e) {
            throw e; // Ayuda para la depuración
        }
    }

    public abstract boolean run(oks ok, Object ... extra_array) throws Exception;
    
    public abstract boolean iniciar_dependencias(oks ok, Object ... extra_array) throws Exception;
}
