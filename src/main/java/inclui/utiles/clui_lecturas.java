/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inclui.utiles;

import innui.bases;
import innui.modelos.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.System.out;
import java.math.BigDecimal;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *
 * @author emilio
 */
public class clui_lecturas extends bases {
    public static String k_in_ruta = "in/inclui/utiles/in";
    
    /**
     * Lee una línea de la entrada estándar
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static String leer_linea(oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return null; }
            String linea = null;
            ResourceBundle in = null;
            in = ResourceBundles.getBundle(k_in_ruta);
            BufferedReader bufferedReader = null;
            InputStreamReader inputStreamReader;
            try {
                out.flush();
                inputStreamReader = new InputStreamReader(System.in);
                bufferedReader = new BufferedReader(inputStreamReader);
                while (System.in.available() > 0) {
                    System.in.skip(1);
                }
                linea = bufferedReader.readLine();
                while (System.in.available() > 0) {
                    System.in.skip(1);
                }
            } catch (Exception e) {
                ok.setTxt(e.getMessage(), tr.in(in, "Excepción leyendo línea de la entrada estándar. "));
                linea = null;
            }
            return linea;
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * Lee una línea
     * @param inputStreamReader Corriente de entrada
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static String leer_linea(InputStreamReader inputStreamReader, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return null; }
            String linea = null;
            ResourceBundle in = null;
            in = ResourceBundles.getBundle(k_in_ruta);
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(inputStreamReader);
                while (inputStreamReader.ready()) {
                    inputStreamReader.skip(1);
                }
                linea = bufferedReader.readLine();
                while (inputStreamReader.ready()) {
                    inputStreamReader.skip(1);
                }
            } catch (Exception e) {
                ok.setTxt(e.getMessage(), tr.in(in, "Excepción leyendo línea. "));
                linea = null;
            }
            return linea;
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Lee una linea de la entrada estándar
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static BigDecimal leer_bigdecimal(oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return null; }
            InputStreamReader inputStreamReader;
            inputStreamReader = new InputStreamReader(System.in);
            return leer_bigdecimal(inputStreamReader, ok, extra_array);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Lee una linea
     * @param inputStreamReader Corriente de entrada
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static BigDecimal leer_bigdecimal(InputStreamReader inputStreamReader, oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        BigDecimal bigDecimal = null;
        ResourceBundle in = null;
        in = ResourceBundles.getBundle(k_in_ruta);
        try {
            Scanner scanner = new Scanner(inputStreamReader);
            while (inputStreamReader.ready()) {
                inputStreamReader.skip(1);
            }
            bigDecimal = scanner.nextBigDecimal();
            while (inputStreamReader.ready()) {
                inputStreamReader.skip(1);
            }
        } catch (Exception e) {
            ok.setTxt(e.getMessage(), tr.in(in, "Excepción leyendo bigdecimal. "));
            bigDecimal = null;
        }
        return bigDecimal;
    }
}
