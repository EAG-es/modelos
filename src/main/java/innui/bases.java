/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package innui;

import innui.modelos.errores.oks;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import innui.utiles.strings.i_formatos;

/**
 *
 * @author emilio
 */
public class bases implements i_formatos, i_escrituras {
    /**
     * Atributo para extender el funcionamiento de una clase derivada de la clase: bases
     */
    public Object o = null;
    /**
     * Atributo para añadir atributos, identificándolos con un nombre único.
     */
    public Map<String, Object> mapa = null;
    /**
     * Atributo para cambiar el funcionamiento de los formatos
     */
    public i_formatos formato = null;
    /**
     * Atributo para cambiar el funcionamiento de las escrituras
     */
    public i_escrituras escritura = null;
    /**
     * Objeto de formato de fecha por defeto
     */
    public DateFormat _dateFormat = null;
    /**
     * Objeto de formato de número por defeto
     */
    public NumberFormat _numberFormat = null;
    /**
     * Devuelve un objeto de la clase base, el indicado por el atributo base (si no es null), o el propio.
     * @return un objeto base
     * @throws Exception Opción de notificar errores de excepción
     */
    public Object o() throws Exception {
        try {
            if (o != null) {
                return o;
            } else {
                return this;
            }
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * Informa del atributo o.
     * @return true si el atributo base es distinto de null, false en caso contrario.
     * @throws Exception Opción de notificar errores de excepción
     */
    public boolean o_es() throws Exception {
        try {
            return (o != null);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * Devuelve un objeto de la clase base adaptado (casting) al tipo genérico, el indicado por el atributo base (si no es null ni hay excpción), o el propio.
     * @param <t_generico> Tipo genérico al que hacer el casting
     * @return un objeto base
     */
    @SuppressWarnings("unchecked")
    public <t_generico> t_generico _o() {
        try {
            if (o != null) {
                return (t_generico) o;
            } else {
                return (t_generico) this;
            }
        } catch (Exception e) {
            return (t_generico) this;
        }
    }
    
    public void setO(Object o) {
        this.o = o;
    }
    
    public Object getO() {
        return o;
    }
    /**
     * Método formato que se puede sustituir para cambiar los formatos de presentación por defecto dado con su uso
     * @param id Identificador para facilitar el cambio de formato
     * @param formato Formato
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return el texto formateado
     * @throws Exception Opción de notificar errores de excepción
     */
    @Override
    public String formar_texto(String id, String formato, oks ok, Object... extras_array) throws Exception {
        if (this.formato != null) {
            return this.formato.formar_texto(id, formato, ok, extras_array);
        }
        return String.format(formato, extras_array);
    }
    /**
     * Método formato que se puede sustituir para cambiar los formatos de presentación por defecto dado con su uso
     * @param id Identificador para facilitar el cambio de formato
     * @param locale Información específica para la internacionalización.
     * @param formato Formato
     * @param extras_array Parámetros para el formato
     * @return el texto formateado
     * @throws Exception Opción de notificar errores de excepción
     */
    @Override
    public String formar_texto​(String id, Locale locale, String formato, oks ok, Object... extras_array) throws Exception {
        if (this.formato != null) {
            return this.formato.formar_texto(id, locale, formato, ok, extras_array);
        }
        return String.format(locale, formato, extras_array);
    }
    /**
     * Método de formato de fecha y hora que se puede sustituir para cambiar los formatos de presentación por defecto dado con su uso
     * @param id Identificador para facilitar el cambio de formato
     * @param date fecha a la que dar formato
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return el texto formateado
     * @throws Exception Opción de notificar errores de excepción
     */
    @Override
    public String formar_fecha(String id, Date date, oks ok, Object... extras_array) throws Exception {
        if (this.formato != null) {
            return this.formato.formar_fecha(id, date, ok, extras_array);
        }
        if (_dateFormat == null) {
            _dateFormat = DateFormat.getInstance();
        }
        return _dateFormat.format(date);
    }
    /**
     * Método de formato de fecha y hora que se puede sustituir para cambiar los formatos de presentación por defecto dado con su uso
     * @param id Identificador para facilitar el cambio de formato
     * @param locale Información específica para la internacionalización.
     * @param date fecha a la que dar formato
     * @param extras_array Parámetros para el formato
     * @return el texto formateado
     * @throws Exception Opción de notificar errores de excepción
     */
    @Override
    public String formar_fecha​(String id, Locale locale, Date date, oks ok, Object... extras_array) throws Exception {
        if (this.formato != null) {
            return this.formato.formar_fecha(id, locale, date, ok, extras_array);
        }
        if (_dateFormat == null) {
            _dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,
                             DateFormat.SHORT,
                             locale);
        }
        return _dateFormat.format(date);
    }
    /**
     * Método de formato de numeros decimales que se puede sustituir para cambiar los formatos de presentación por defecto dado con su uso
     * @param id Identificador para facilitar el cambio de formato
     * @param numero Número doble
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return el texto formateado
     * @throws Exception Opción de notificar errores de excepción
     */
    @Override
    public String formar_numero(String id, double numero, oks ok, Object... extras_array) throws Exception {
        if (this.formato != null) {
            return this.formato.formar_numero(id, numero, ok, extras_array);
        }
        if (_numberFormat == null) {
            _numberFormat = NumberFormat.getNumberInstance();
        }
        return _numberFormat.format(numero);
    }
    /**
     * Método de formato de numeros decimales que se puede sustituir para cambiar los formatos de presentación por defecto dado con su uso
     * @param id Identificador para facilitar el cambio de formato
     * @param locale Información específica para la internacionalización.
     * @param numero Número doble
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return el texto formateado
     * @throws Exception Opción de notificar errores de excepción
     */
    @Override
    public String formar_numero​(String id, Locale locale, double numero, oks ok, Object... extras_array) throws Exception {
        if (this.formato != null) {
            return this.formato.formar_numero(id, locale, numero, ok, extras_array);
        }
        if (_numberFormat == null) {
            _numberFormat = NumberFormat.getNumberInstance();
        }
        return _numberFormat.format(numero);
    }
    /**
     * Escribe texto en la salida estándar, si no se sustituye.
     * @param texto Testo que escribir
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return true si tiene éxito.
     * @throws Exception Opción de notificar errores de excepción
     */
    @Override
    public boolean escribir(String texto, oks ok, Object... extras_array) throws Exception {
        if (this.escritura != null) {
            return this.escritura.escribir(texto, ok, extras_array);
        }
        System.out.print(texto);
        return true;
    }
    /**
     * Escribe una línea de texto en la salida estándar, si no se sustituye.
     * @param texto Testo que escribir
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return true si tiene éxito.
     * @throws Exception Opción de notificar errores de excepción
     */
    @Override
    public boolean escribir_linea(String texto, oks ok, Object... extras_array) throws Exception {
        if (this.escritura != null) {
            return this.escritura.escribir_linea(texto, ok, extras_array);
        }
        System.out.println(texto);
        return true;
    }
    /**
     * Escribe una línea de texto en la salida de error, si no se sustituye.
     * @param texto Testo que escribir
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return true si tiene éxito.
     */
    @Override
    public boolean escribir_error(String texto, oks ok, Object... extras_array) {
        if (this.escritura != null) {
            return this.escritura.escribir_error(texto, ok, extras_array);
        }
        System.err.print(texto);
        return true;
    }
    /**
     * Escribe una línea de texto en la salida de error, si no se sustituye.
     * @param texto Testo que escribir
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return true si tiene éxito.
     */
    @Override
    public boolean escribir_linea_error(String texto, oks ok, Object... extras_array) {
        if (this.escritura != null) {
            return this.escritura.escribir_linea_error(texto, ok, extras_array);
        }
        System.err.println(texto);
        return true;
    }
}
