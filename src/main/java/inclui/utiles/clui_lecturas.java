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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *
 * @author emilio
 */
public class clui_lecturas extends bases {
    public static String k_in_ruta = "in/inclui/utiles/in";
    public BufferedReader _bufferedReader = null;
    public Scanner _scanner = null;
    public InputStream _anterior_inputStream = null;
            
    public clui_lecturas() {
        try {
            oks ok = new oks();
            iniciar(ok);
        } catch (Exception e_ignorar) {}
    }
    /**
     * Pone System.in como origen de datos
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception 
     */
    public boolean iniciar(oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return ok.es; }
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            _bufferedReader = new BufferedReader(inputStreamReader);
            _scanner = new Scanner(_bufferedReader);
            return ok.es;
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * Pone System.in como origen de datos
     * @param bufferedReader Cambiar la corriente de texto de entrada
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception 
     */
    public boolean iniciar(BufferedReader bufferedReader, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return ok.es; }
            _bufferedReader = bufferedReader;
            _scanner = new Scanner(_bufferedReader);
            return ok.es;
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * Cambia la entrada estandar por otra corriente de datos
     * @param inputStream Nueva corriente de datos
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return La anterior corriente de datos
     * @throws Exception Opción de notificar errores de excepción
     */
    public InputStream poner_entrada_estandar(InputStream inputStream, oks ok, Object ... extra_array) throws Exception {
        try {
            _anterior_inputStream = System.in;
            System.setIn(inputStream);
            iniciar(ok);
            return _anterior_inputStream;
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * Cambia la entrada estandar por otra corriente de datos
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return La anterior corriente de datos
     * @throws Exception Opción de notificar errores de excepción
     */
    public InputStream reponer_entrada_estandar(oks ok, Object ... extra_array) throws Exception {
        try {
            InputStream inputStream = System.in;
            System.setIn(_anterior_inputStream);
            iniciar(ok);
            return inputStream;
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * Lee una línea
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * - posición 0: Boolean true -> Descartar lo que hubiera pendiente de leer antes
     * - posición 1: Boolean true -> Descartar lo que hubiera pendiente de leer después
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public String leer_linea(oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return null; }
            String linea = null;
            ResourceBundle in = null;
            in = ResourceBundles.getBundle(k_in_ruta);
            try {
                if (extra_array.length > 0) {
                    if ((Boolean) extra_array[0]) {
                        while (_bufferedReader.ready()) {
                            _bufferedReader.skip(1);
                        }
                    }
                }
                linea = _scanner.nextLine();
//                linea = bufferedReader.readLine();
                if (extra_array.length > 1) {
                    if ((Boolean) extra_array[1]) {
                        while (_bufferedReader.ready()) {
                            _bufferedReader.skip(1);
                        }
                    }
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
     * Lee un BigDecimal
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * - posición 0: Boolean true -> Descartar lo que hubiera pendiente de leer antes
     * - posición 1: Boolean true -> Descartar lo que hubiera pendiente de leer después
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public BigDecimal leer_bigdecimal(oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        BigDecimal bigDecimal = null;
        ResourceBundle in = null;
        in = ResourceBundles.getBundle(k_in_ruta);
        try {
            if (extra_array.length > 0) {
                if ((Boolean) extra_array[0]) {
                    while (_bufferedReader.ready()) {
                        _bufferedReader.skip(1);
                    }
                }
            }
            bigDecimal = _scanner.nextBigDecimal();
            if (extra_array.length > 1) {
                if ((Boolean) extra_array[1]) {
                    while (_bufferedReader.ready()) {
                        _bufferedReader.skip(1);
                    }
                }
            }
        } catch (Exception e) {
            ok.setTxt(tr.in(in, "Excepción leyendo bigdecimal. "), e);
            bigDecimal = null;
        }
        return bigDecimal;
    }
    /**
     * Lee un BigDecimal y descarta el resto de la linea
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * - posición 0: Boolean true -> Descartar lo que hubiera pendiente de leer antes
     * - posición 1: Boolean true -> Descartar lo que hubiera pendiente de leer después
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public BigDecimal leer_bigdecimal_linea(oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        BigDecimal retorno;    
        retorno = leer_bigdecimal(ok, extra_array);
        leer_linea(ok, extra_array);
        return retorno;
    }
}
