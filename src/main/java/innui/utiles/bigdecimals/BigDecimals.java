/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package innui.utiles.bigdecimals;

import innui.bases;
import innui.modelos.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class BigDecimals extends bases {
    public static String k_in_ruta = "in/innui/utiles/bigdecimals/in";
    /**
     * Quita decimales a un número
     * @param bigDecimal Número al que quitar decimales
     * @param decimales_a_quitar Cuantos decimales a quitar
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return El número con decimales quitados.
     * @throws Exception Opción de notificar errores de excepción
     */
    public static BigDecimal quitar_decimales(BigDecimal bigDecimal, int decimales_a_quitar, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return null; }
            BigDecimal retorno;
            bigDecimal =  bigDecimal.stripTrailingZeros();
            BigDecimal [] bigDecimal_array = bigDecimal.divideAndRemainder(BigDecimal.ONE);
            bigDecimal_array[1] = bigDecimal_array[1].stripTrailingZeros();
            String decimales_txt = bigDecimal_array[1].toPlainString();
            int decimales_num = decimales_txt.length() - 2; // "0."
            decimales_num = decimales_num - decimales_a_quitar;
            if (decimales_num >= 0) {
                BigDecimal nuevo_divisor = BigDecimal.valueOf(1, decimales_num);
                bigDecimal_array = bigDecimal.divideAndRemainder(nuevo_divisor);
                retorno = bigDecimal.subtract(bigDecimal_array[1]);
                retorno =  retorno.stripTrailingZeros();
            } else {
                retorno = bigDecimal;
            }
            return retorno;
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * Divide comprobando la división entre 0 y retornando un valor cercano a Double.POSITIVE_INFINITY o Double.NEGATIVE_INFINITY según corresponda, si hay división por 0 (convertido a BigInteger)
     * @param dividendo 
     * @param divisor
     * @param ok Comunicar resultados
     * @param extra_array Hasta 2 parámetros, según la división de BigDecimal que se desee hacer.
     * @return El resultado de la división o un valor cercano a Double.POSITIVE_INFINITY o Double.NEGATIVE_INFINITY
     * @throws Exception Opción de notificar errores de excepción
     */
    @SuppressWarnings( "deprecation" )
    public static BigDecimal divide_0(BigDecimal dividendo, BigDecimal divisor, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return null; }
            ResourceBundle in;
            in = ResourceBundles.getBundle(k_in_ruta);
            BigDecimal retorno = null;
            if (divisor.compareTo(BigDecimal.ZERO) == 0) {
                ok.setTxt(tr.in(in, "División entre 0. "));
                if (dividendo.compareTo(BigDecimal.ZERO) >= 0) {
                    if (divisor.compareTo(BigDecimal.ZERO) >= 0) {
                        retorno = BigDecimal.valueOf(1.0*Double.MAX_VALUE);
                    } else {
                        retorno = BigDecimal.valueOf(-1.0*Double.MAX_VALUE);
                    }
                } else {
                    if (divisor.compareTo(BigDecimal.ZERO) >= 0) {
                        retorno = BigDecimal.valueOf(-1.0*Double.MAX_VALUE);
                    } else {
                        retorno = BigDecimal.valueOf(1.0*Double.MAX_VALUE);
                    }
                }
            } else {
                if (extra_array == null || extra_array.length == 0) {
                    return dividendo.divide(divisor);
                } else if (extra_array.length == 1) {
                    if (extra_array[0] instanceof MathContext) {
                        retorno = dividendo.divide(divisor, (MathContext) extra_array[0]);
                    } else if (extra_array[0] instanceof RoundingMode) {
                        retorno = dividendo.divide(divisor, (RoundingMode) extra_array[0]);
                    } else {
                        retorno = dividendo.divide(divisor, (Integer) extra_array[0]);
                    }
                } else if (extra_array.length == 2) {
                    if (extra_array[1] instanceof RoundingMode) {
                        retorno = dividendo.divide(divisor, (Integer) extra_array[0], (RoundingMode) extra_array[1]);
                    } else {
                        retorno = dividendo.divide(divisor, (Integer) extra_array[0], (Integer) extra_array[1]);
                    }
                } else {
                    ok.setTxt(tr.in(in, "Parámetros opcionales no válidos. "));
                }
            }
            return retorno;
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Divide comprobando la división entre 0 y retornando un valor cercano a Double.POSITIVE_INFINITY o Double.NEGATIVE_INFINITY según corresponda, si hay división por 0 (convertido a BigInteger)
     * @param dividendo 
     * @param divisor
     * @param ok Comunicar resultados
     * @param extra_array Hasta 1 parámetro, según la división de BigDecimal que se desee hacer.
     * @return El resultado de la división o o un valor cercano a Double.POSITIVE_INFINITY o Double.NEGATIVE_INFINITY
     * @throws Exception Opción de notificar errores de excepción
     */
    public static BigDecimal divideToIntegralValue_0(BigDecimal dividendo, BigDecimal divisor, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return null; }
            ResourceBundle in;
            in = ResourceBundles.getBundle(k_in_ruta);
            BigDecimal retorno = null;
            if (divisor.toBigInteger().compareTo(BigInteger.ZERO) == 0) {
                ok.setTxt(tr.in(in, "División entre 0. "));
                if (dividendo.compareTo(BigDecimal.ZERO) >= 0) {
                    if (divisor.compareTo(BigDecimal.ZERO) >= 0) {
                        retorno = BigDecimal.valueOf(1.0*Double.MAX_VALUE);
                    } else {
                        retorno = BigDecimal.valueOf(-1.0*Double.MAX_VALUE);
                    }
                } else {
                    if (divisor.compareTo(BigDecimal.ZERO) >= 0) {
                        retorno = BigDecimal.valueOf(-1.0*Double.MAX_VALUE);
                    } else {
                        retorno = BigDecimal.valueOf(1.0*Double.MAX_VALUE);
                    }
                }
            } else {
                if (extra_array == null || extra_array.length == 0) {
                    return dividendo.divide(divisor);
                } else if (extra_array.length == 1) {
                    if (extra_array[0] instanceof MathContext) {
                        retorno = dividendo.divideToIntegralValue(divisor, (MathContext) extra_array[0]);
                    }
                } else {
                    ok.setTxt(tr.in(in, "Parámetros opcionales no válidos. "));
                }
            }
            return retorno;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static BigDecimal nulo_es_0(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return BigDecimal.ZERO;
        } else {
            return bigDecimal;
        }
    }
}
