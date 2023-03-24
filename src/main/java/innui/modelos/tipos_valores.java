package innui.modelos;

import innui.modelos.errores.oks;
import innui.modelos.errores.patrones;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author emilio
 */
public class tipos_valores {
    public static String k_formato_date = "%1$td/%1$tm/%1$ty %1$tT";
    public static String k_formato_time = "%1$tT";
    public static String k_formato_numero = "%1$,15d";
    public static String k_formato_decimal = "%1$,15.2f";
    /**
     * El nombre simple (getSimpleName) de una clase.
     */
    public String tipo;
    /**
     * Un objeto instanciado de una clase que tiene ese nombre.
     */
    public Object valor;

    public tipos_valores() {
        
    }
    
    public tipos_valores(String tipo, Object valor) {
        this.tipo = tipo;
        this.valor = valor;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public String toString() {
        String texto;
        if (tipo == null) {
            texto = "";
        } else {
            texto = tipo.toString();
        }
        texto = texto + ",";
        if (valor == null) {
            texto = texto + "";
        } else {
            texto = texto + valor.toString();
        }
        return texto;
    }

    /**
     * Pasa a texto el dato.
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public String pasar_a_texto_segun_tipo(oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        String retorno = null;
        try {
            String tipo = getTipo();
            Object valor = getValor();
            Date date;
            Time time;
            if (valor == null) {
                retorno = "--";
            } else if (valor instanceof String) {
                if (tipo.toLowerCase().contains("date")
                 || tipo.toLowerCase().contains("timestamp")) {
                    date = _convertir_fecha_y_hora(valor.toString(), ok);
                    retorno = String.format(k_formato_date, date);
                } else if (tipo.toLowerCase().contains("time")) {
                    time = _convertir_hora(valor.toString(), ok);
                    retorno = String.format(k_formato_time, time);
                } else {
                    retorno = valor.toString();
                }
            } else if (valor instanceof Date valor_date) {
                retorno = String.format(k_formato_date, valor_date);
            } else if (valor instanceof Time valor_time) {
                retorno = String.format(k_formato_time, valor_time);
            } else if (valor instanceof Integer valor_integer) {
                retorno = String.format(k_formato_numero, valor_integer);
            } else if (valor instanceof Long valor_long) {
                retorno = String.format(k_formato_numero, valor_long);
            } else if (valor instanceof Double valor_double) {
                retorno = String.format(k_formato_decimal, valor_double);
            } else if (valor instanceof Float valor_float) {
                retorno = String.format(k_formato_decimal, valor_float);
            } else if (valor instanceof BigDecimal valor_bigdecimal) {
                if (valor_bigdecimal.remainder(BigDecimal.ONE).multiply(BigDecimal.valueOf(1000)).compareTo(BigDecimal.ZERO) > 0) {
                    retorno = String.format(k_formato_decimal, valor_bigdecimal);
                } else {
                    retorno = String.format(k_formato_numero, valor_bigdecimal.toBigIntegerExact());
                }
            } else if (valor instanceof BigInteger valor_biginteger) {
                retorno = String.format(k_formato_numero, valor_biginteger);
            } else {
                retorno = valor.toString();
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return retorno;
    }
    /**
     * Convierte una hora según los formatos de k_patrones_formato_fecha y k_patrones_formato_hora (separados por |)
     * El texto se separa por el espacio en blanco entre la fecha y la hora. Y se convierten por separado
     * @param texto
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public Date _convertir_fecha_y_hora(String texto, oks ok, Object ... extras_array) throws Exception {
        Date date;
        date = patrones.convertir_fecha_y_hora(texto, ok, extras_array);
        if (ok.es == false) {
            date = patrones.convertir_fecha(texto, ok, extras_array);
        }
        return date;
    }    
    /**
     * Convierte una hora según los formatos de k_patrones_formato_hora (separados por |)
     * @param texto
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public Time _convertir_hora(String texto, oks ok, Object ... extras_array) throws Exception {
        Time time;
        Date date;
        date = patrones.convertir_hora(texto, ok, extras_array);
        time = new Time(date.getTime());
        return time;
    }
}
