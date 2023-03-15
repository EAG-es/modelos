/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innui.modelos.configuraciones;

import innui.bases;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import java.io.File;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class rutas extends bases {
    public static String k_in_ruta = "in/innui/modelos/configuraciones/in";
    public static String k_no_jar = "LA CLASE NO ESTÁ EN UN ARCHIVO JAR. ";

    /**
     * Crea una ruta uniendo la ruta base de la clase a la ruta relativa pasada como parametro
     * @param clase Clase de la que obtener la ruta fuente de la clase.
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static File leer_ruta_de_clase(Class<?> clase, oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        File file = null;
        ResourceBundle in;
        try {
            URL url;
            ProtectionDomain protectionDomain = clase.getProtectionDomain();
            CodeSource codeSource = protectionDomain.getCodeSource();
            url = codeSource.getLocation();
            try {
                file = new File(url.toURI());
            } catch (Exception e) {
                ok.es = false;
                return null;
            }
        } catch (Exception e) {
            ok.setTxt(e);
            file = null;
        }
        return file;
    }
    /**
     * Crea una ruta uniendo la ruta base de la clase a la ruta relativa pasada como parametro
     * @param clase Clase de la que obtener la ruta fuente de la clase.
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static String leer_ruta_jar_desde_clase(Class<?> clase, oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        String retorno = null;
        ResourceBundle in;
        File file;
        try {
            file = leer_ruta_de_clase(clase, ok);
            if (ok.es == false) { 
                ok.id = k_no_jar;
                retorno = null;
            }
            retorno = file.getPath();
            if (retorno.toLowerCase().endsWith(".jar")) {
                retorno = file.getPath();
            } else {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.txt = tr.in(in, "LA CLASE NO ESTÁ EN UN ARCHIVO JAR. ");
                ok.id = k_no_jar;
                retorno = null;
            }
        } catch (Exception e) {
            ok.setTxt(e);
            retorno = null;
        }
        return retorno;
    }
    /**
     * Crea una ruta uniendo la ruta base de la clase a la ruta relativa pasada como parametro
     * @param clase Clase de la que obtener la ruta fuente de la clase.
     * @param ruta_relativa Ruta relativa que unir tras la ruta fuente de la clase
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static String crear_ruta_desde_clase(Class<?> clase, String ruta_relativa, oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        String  retorno = null;
        ResourceBundle in;
        File file;
        URL url;
        try {
            ProtectionDomain protectionDomain = clase.getProtectionDomain();
            CodeSource codeSource = protectionDomain.getCodeSource();
            url = codeSource.getLocation();
            try {
                file = new File(url.toURI());
            } catch (Exception e) {
                ok.es = false;
                return null;
            }
            retorno = file.getPath();
            if (retorno.toLowerCase().endsWith(".jar")) {
                retorno = file.getParent();
            }
            file = new File(ruta_relativa);
            if (ruta_relativa.startsWith(File.separator)) {
                retorno = retorno + file.getPath();
            } else {
                retorno = retorno + File.separator + file.getPath();
            }
            file = new File(retorno);
            retorno = file.getCanonicalPath();
        } catch (Exception e) {
            ok.setTxt(e);
            retorno = null;
        }
        return retorno;
    }
    /**
     * Crear la mismas rutas que presenta la ruta del archivo pasado como parámetro (si no existe)
     * @param file Archivo con la ruta desde donde crear (si no existe)
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static boolean crear_rutas_padre(File file, oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        ResourceBundle in;
        File file_padre = null;
        File file_abuelo = null;
        try {
            file_padre = file.getParentFile();
            if (file_padre.exists() == false) {
                crear_rutas_padre(file_padre, ok);
                if (ok.es == false) { return false; }
                file_abuelo = file_padre.getParentFile();
                ok.no_nul(file_abuelo);
                if (ok.es == false) { return false; }
                if (file_abuelo.canWrite() == false) {
                    ok.es = false;
                    in = ResourceBundles.getBundle(k_in_ruta);
                    ok.txt = tr.in(in, "NO HAY PERMISOS PARA CREAR EL DIRECTORIO: ") 
                            + file_padre.getName()
                            + tr.in(in, ", EN EL DIRECTORIO: ") 
                            + file_abuelo.getCanonicalPath()
                            + ". ";
                }
                if (ok.es == false) { return false; }
                file_padre.mkdir();
            }
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "ERROR AL CREAR RUTAS PADRE. ")
                , ok.txt);
            ok.setTxt(ok.getTxt(), e);
            ok.es = false;
        }
        return ok.es;
    }
    /**
     * Lista el contenido de una carpeta
     * @param clase Clase contnida en el archivo jar
     * @param contenido_lista Lista con el contenido del jar
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static boolean listar_contenido_de_ruta(Class<?> clase, List<String> contenido_lista, oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        ResourceBundle in;
        try {
            File file;
            file = rutas.leer_ruta_de_clase(clase, ok);
            if (ok.es == false) { return false; }
            if (file.isDirectory()) {
                listar_contenido_de_ruta(file, contenido_lista, ok, extra_array);
            } else {
                contenido_lista.add(file.getCanonicalPath());
            }
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "ERROR DE EXCEPCIÓN AL LISTAR EL CONTENIDO DE UNA RUTA. "), e);
        }
        return ok.es;
    }
    /**
     * Lista el contenido de una carpeta recursivamente
     * @param carpeta Carpeta que listar
     * @param contenido_lista Lista con el contenido del jar
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static boolean listar_contenido_de_ruta(File carpeta, List<String> contenido_lista, oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        ResourceBundle in;
        try {
            if (ok.es == false) { return false; }
            if (carpeta.isDirectory()) {
                File [] files_array = carpeta.listFiles();
                for (File nodo_file: files_array) {
                    if (nodo_file.isDirectory()) {
                        listar_contenido_de_ruta(nodo_file, contenido_lista, ok, extra_array);                        
                        if (ok.es == false) { return false; }
                    } else {
                        contenido_lista.add(nodo_file.getCanonicalPath());
                    }
                }
            }
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "ERROR DE EXCEPCIÓN AL LISTAR EL CONTENIDO DE UNA RUTA. "), e);
        }
        return ok.es;
    }
        
}
