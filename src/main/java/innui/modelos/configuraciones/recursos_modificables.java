/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innui.modelos.configuraciones;

import innui.bases;
import static innui.modelos.configuraciones.rutas.crear_rutas_padre;
import static innui.modelos.configuraciones.rutas.k_no_jar;
import static innui.modelos.configuraciones.rutas.leer_ruta_jar_desde_clase;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author emilio
 */
public class recursos_modificables extends bases {

    public static String k_in_ruta = "in/innui/modelos/configuraciones/in";
    /**
     * Copia un recurso localizado relativo a una clase, a una ruta de destino.
     * @param clase Clase de referencia relativa
     * @param ruta_origen_recurso Ruta relativa del recurso
     * @param ruta_destino Ruta de destino del recurso
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static boolean copiar_recurso(Class<?> clase, String ruta_origen_recurso, String ruta_destino, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            boolean ret = true;
            ResourceBundle in;
            InputStream inputStream;
            inputStream = clase.getResourceAsStream(ruta_origen_recurso);
            if (inputStream != null) {
                ret = copiar(inputStream, ruta_destino, ok);
            } else {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.txt = tr.in(in, "NO SE HA ENCONTRADO EL RECURSO SOLICITADO. ")
                        + ruta_origen_recurso;
                ret = false;
            }
            return ret;
        } catch (Exception e) {
            throw e; // Ayuda para la depuración
        }
    }
    /**
     * Copia un recurso localizado relativo a una clase, a una ruta de destino.
     * @param clase Clase de referencia relativa
     * @param ruta_origen_recurso Ruta relativa del recurso
     * @param ruta_destino_relativo Ruta de destino del recurso
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static boolean copiar_recurso_a_destino_relativo(Class<?> clase, String ruta_origen_recurso, String ruta_destino_relativo, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            InputStream inputStream;
            String ruta_absoluta_destino;
            inputStream = clase.getResourceAsStream(ruta_origen_recurso);
            ruta_absoluta_destino = rutas.crear_ruta_desde_clase(clase, ruta_destino_relativo, ok);
            ok.no_nul(ruta_absoluta_destino);
            if (ok.es == false) { return false; }
            copiar(inputStream, ruta_absoluta_destino, ok);
            return ok.es;
        } catch (Exception e) {
            throw e; // Ayuda para la depuración
        }
    }
    /**
     * Copia una serie de entrada en una ruta de destino
     * @param inputstream Serie de entrada
     * @param ruta_destino Ruta de destino del recurso
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static boolean copiar(InputStream inputstream, String ruta_destino, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            ResourceBundle in;
            Path path;
            try {
                path = Paths.get(ruta_destino);
                Files.copy(inputstream, path, REPLACE_EXISTING);
            } catch (Exception e) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "ERROR AL COPIAR LA SERIE DE ENTRADA (PUEDE SER UN PROBLEMA CON LOS PERMISOS DE LA CARPETA). ")
                    , ok.txt);
                ok.setTxt(ok.getTxt(), e);
                ok.es = false;
            }
            return ok.es;
        } catch (Exception e) {
            throw e; // Ayuda para la depuración
        }
    }
    /**
     * Copia un recurso fuera de un archivo jar (si no existe), manteniendo la misma estructura de carpetas.
     * @param clase Clase de referencia relativa
     * @param ruta_recurso Ruta relativa del recurso
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static boolean instalar_fuera(Class<?> clase, String ruta_recurso, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            String ruta_absoluta_destino;
            File file;
            ruta_absoluta_destino = rutas.crear_ruta_desde_clase(clase, ruta_recurso, ok);
            ok.no_nul(ruta_absoluta_destino);
            if (ok.es == false) { return false; }
            file = new File(ruta_absoluta_destino);
            if (file.exists() == false) {
                crear_rutas_padre(file, ok);
                if (ok.es == false) { return false; }
                copiar_recurso(clase, ruta_recurso, ruta_absoluta_destino, ok);
            }
            return ok.es;
        } catch (Exception e) {
            throw e; // Ayuda para la depuración
        }
    }
    /**
     * Copia un recurso fuera de un archivo jar (si no existe), manteniendo la misma estructura de carpetas.
     * @param clase Clase de referencia relativa
     * @param ruta_origen_recurso Ruta relativa del recurso
     * @param ruta_destino_recurso Ruta relativa desde la referencia de la clase, donde reproducir la estructura de carpetas y copiar el recurso
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static boolean instalar_fuera(Class<?> clase, String ruta_origen_recurso, String ruta_destino_recurso, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            String ruta_absoluta_destino = null;
            File file;
            ruta_absoluta_destino = rutas.crear_ruta_desde_clase(clase, ruta_destino_recurso, ok);
            ok.no_nul(ruta_absoluta_destino);
            if (ok.es == false) { return false; }
            file = new File(ruta_absoluta_destino);
            if (file.exists() == false) {
                ok.es = crear_rutas_padre(file, ok);
                if (ok.es == false) { return false; }
                copiar_recurso(clase, ruta_origen_recurso, ruta_absoluta_destino, ok);
            }
            return ok.es;
        } catch (Exception e) {
            throw e; // Ayuda para la depuración
        }
    }
    /**
     * Copia una carpeta fuera de un archivo jar (si no existe ya), manteniendo la misma estructura de carpetas.
     * @param clase Clase de referencia relativa
     * @param ruta_origen_recurso Ruta relativa del recurso
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static boolean instalar_carpeta_fuera(Class<?> clase, String ruta_origen_recurso, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            List<String> contenido_lista = new ArrayList<>();
            listar_contenido_de_jar(clase, contenido_lista, ok);
            if (ok.es) {
                if (ruta_origen_recurso.endsWith(File.separator) == false) {
                    ruta_origen_recurso=ruta_origen_recurso + File.separator;
                }
                for (String ruta: contenido_lista) {
                    if (ruta.startsWith(ruta_origen_recurso)) {
                        ok.es = instalar_fuera(clase, ruta, ok);
                        if (ok.es == false) {
                            break;
                        }
                    }
                }
            } else if (ok.id.equals(k_no_jar)) {
                ok.iniciar();
            }
            return ok.es;
        } catch (Exception e) {
            throw e; // Ayuda para la depuración
        }
    }
    /**
     * Lista el contenido de un archivo jar
     * @param clase Clase contnida en el archivo jar
     * @param contenido_lista Lista con el contenido del jar
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static boolean listar_contenido_de_jar(Class<?> clase, List<String> contenido_lista, oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        ResourceBundle in;
        ZipEntry zipentry;
        String nombre;
        String ruta;
        ZipInputStream zip;
        List<String> directorios_lista = new ArrayList<>();
        int pos_barra;
        try {
            ruta = leer_ruta_jar_desde_clase(clase, ok);
            ok.no_nul(ruta);
            if (ok.es == false) { return false; }
            ok.iniciar();
            zip = new ZipInputStream(new FileInputStream(ruta));
            while (true) {
                zipentry = zip.getNextEntry();
                if (zipentry == null) {
                    break;
                } 
                if (zipentry.isDirectory()) {
                    nombre = zipentry.getName();
                    directorios_lista.add(nombre);
                }
            }
            zip = new ZipInputStream(new FileInputStream(ruta));
            while (true) {
                zipentry = zip.getNextEntry();
                if (zipentry == null) {
                    break;
                } 
                if (zipentry.isDirectory() == false) {
                    nombre = zipentry.getName();
                    nombre = nombre.replace('.', '/');
                    while (true) {
                        pos_barra = nombre.lastIndexOf("/");
                        if (pos_barra >= 0) {
                            if (directorios_lista.contains(nombre.substring(0, pos_barra+1)) == false) {
                                nombre = nombre.substring(0, pos_barra) + "." + nombre.substring(pos_barra + 1);
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    contenido_lista.add("/" + nombre);
                }
            }
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "ERROR DE EXCEPCIÓN AL LISTAR EL CONTENIDO DE UN JAR. "), e);
        }
        return ok.es;
    }
}
