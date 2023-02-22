/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package innui;

import innui.modelos.errores.SystemLogger_utils;
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
    public static StringBuilder k_logger_nombre = new StringBuilder();
    public static String k_logger_nombre_por_defecto = "logger.log";
    /**
     * Límite del nivel de log global por defecto
     */
    public static System.Logger.Level _logger_limite_global = System.Logger.Level.ALL;
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
     * Objeto de log por defeto
     */
    public SystemLogger_utils _logger = null;
    /**
     * Límite del nivel de log por defeto
     */
    public System.Logger.Level _logger_limite = System.Logger.Level.ALL;
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

    public void setMapa(Map<String, Object> mapa) {
        this.mapa = mapa;
    }
    
    public Map<String, Object> getMapa() {
        return mapa;
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
     * @param id Identificador para facilitar el cambio de formato (puede ser null)
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
    /**
     * Escribe una línea de texto en la salida de log, si no se sustituye.
     * @param texto Testo que escribir
     * @param nivel Nivel de mensaje de log (puede ser null, entonces por defecto es: INFO)
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return true si tiene éxito.
     */
    @Override
    public boolean escribir_log(String texto, System.Logger.Level nivel, oks ok, Object... extras_array) {
        if (this.escritura != null) {
            return this.escritura.escribir_log(texto, nivel, ok, extras_array);
        } else {
            try {
                if (_logger == null) {
                    if (k_logger_nombre.isEmpty()) {
                        k_logger_nombre.append(k_logger_nombre_por_defecto);
                    }
                    _logger = SystemLogger_utils.getLogger(k_logger_nombre.toString());
                }
                if (nivel == null) {
                    nivel = System.Logger.Level.INFO;
                }
                if (ser_posible_log(nivel, ok)) {
                    _logger.log(nivel, texto);
                }
            } catch (Exception e) {
                ok.setTxt(e);
                return false;
            }
        }
        return true;
    }
    /**
     * Escribe una línea de texto INFO en la salida de log, si no se sustituye.
     * @param texto Testo que escribir
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return true si tiene éxito.
     */
    @Override
    public boolean escribir_log(String texto, oks ok, Object... extras_array) {
        return escribir_log(texto, null, ok, extras_array);
    }
    /**
     * Limita los mensaje de log que son registrados. OFF los quita todos, ALL los pone todos, etc...
     * @param limite Nivel de mensaje de log (puede ser null, entonces por defecto es quitar log: OFF)
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return true si tiene éxito.
     */
    @Override
    public boolean limitar_log(System.Logger.Level limite, oks ok, Object... extras_array) {
        if (this.escritura != null) {
            return this.escritura.limitar_log(limite, ok, extras_array);
        }
        if (limite == null) {
            _logger_limite = System.Logger.Level.OFF;
        }
        _logger_limite = limite;
        return true;
    }
    /**
     * Limita los mensaje de log que son registrados. OFF los quita todos, ALL los pone todos, etc...
     * @param limite Nivel de mensaje de log (puede ser null, entonces por defecto es quitar log: OFF)
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return true si tiene éxito.
     */
    public boolean limitar_log_global(System.Logger.Level limite, oks ok, Object... extras_array) {
        if (this.escritura != null) {
            return this.escritura.limitar_log(limite, ok, extras_array);
        }
        if (limite == null) {
            _logger_limite_global = System.Logger.Level.OFF;
        }
        _logger_limite_global = limite;
        return true;
    }
    /**
     * Responde true si el nivel es posible que se registre, segun el nivel límite establecido
     * @param limite Nivel de mensaje de log (puede ser null, entonces por defecto es quitar log: OFF)
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return true el log se puede hacer para ese limite.
     */
    @Override
    public boolean ser_posible_log(System.Logger.Level limite, oks ok, Object... extras_array) {
        if (_logger_limite.getSeverity() != System.Logger.Level.OFF.getSeverity()
         && _logger_limite_global.getSeverity() != System.Logger.Level.OFF.getSeverity()) {
            if (_logger_limite.getSeverity() <= limite.getSeverity()
             && _logger_limite_global.getSeverity() <= limite.getSeverity()) {
                return true;
            }
        }
        return false;
    }
    /**
     * Traduce un literal: ALL, TRACE, DEBUG, INFO, WARNING, ERROR, OUT
     * A su equivalente enumerado de nivel de log
     * @param nombre_nivel_de_log Nombre del nivel de log
     * @return El nivel de log correspondiente (o el de ALL, si no encaja con ninguno)
     */
    public System.Logger.Level traducir_nivel_de_log(String nombre_nivel_de_log) {
        return SystemLogger_utils.traducir_nivel_de_log(nombre_nivel_de_log);
    }
}
