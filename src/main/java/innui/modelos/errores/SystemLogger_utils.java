/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innui.modelos.errores;

import java.util.ResourceBundle;
import java.util.function.Supplier;
import static innui.modelos.errores.Loggers.traducir;
import java.util.logging.Logger;

/**
 *
 * @author emilio
 */
public class SystemLogger_utils implements System.Logger {

    public java.util.logging.Logger logger;
    /**
     * Obtiene un logger con el nombre indicado
     * @param nombre Nombre del logger qeu obtener
     * @return Un nuevo objeto SystemLogger_utils.
     */
    public static SystemLogger_utils getLogger(String nombre) throws Exception {
        SystemLogger_utils systemLogger_utils = new SystemLogger_utils();
        systemLogger_utils.logger = java.util.logging.Logger.getLogger(nombre);
        return systemLogger_utils;
    }
    /**
     * Obtiene un logger con el nombre indicado
     * @param nombre Nombre del logger qeu obtener
     * @param resourceBundleName Nombre del archivo de propiedades con los recursos de traducción
     * @return Un nuevo objeto SystemLogger_utils.
     */
    public static SystemLogger_utils getLogger(String nombre, String resourceBundleName) throws Exception {
        SystemLogger_utils systemLogger_utils = new SystemLogger_utils();
        systemLogger_utils.logger = java.util.logging.Logger.getLogger(nombre, resourceBundleName);
        return systemLogger_utils;
    }
    /**
     * Traduce un literal: ALL, TRACE, DEBUG, INFO, WARNING, ERROR, OUT
     * A su equivalente enumerado de nivel de log
     * @param nombre_nivel_de_log Nombre del nivel de log
     * @return El nivel de log correspondiente (o el de ALL, si no encaja con ninguno)
     */
    public static System.Logger.Level traducir_nivel_de_log(String nombre_nivel_de_log) {
        if (nombre_nivel_de_log.equals(System.Logger.Level.ALL.name())) {
            return System.Logger.Level.ALL;
        } else if (nombre_nivel_de_log.equals(System.Logger.Level.TRACE.name())) {
            return System.Logger.Level.TRACE;
        } else if (nombre_nivel_de_log.equals(System.Logger.Level.DEBUG.name())) {
            return System.Logger.Level.DEBUG;
        } else if (nombre_nivel_de_log.equals(System.Logger.Level.INFO.name())) {
            return System.Logger.Level.INFO;
        } else if (nombre_nivel_de_log.equals(System.Logger.Level.WARNING.name())) {
            return System.Logger.Level.WARNING;
        } else if (nombre_nivel_de_log.equals(System.Logger.Level.ERROR.name())) {
            return System.Logger.Level.ERROR;
        } else if (nombre_nivel_de_log.equals(System.Logger.Level.OFF.name())) {
            return System.Logger.Level.OFF;
        }
        return System.Logger.Level.ALL;
    }
    
    @Override
    public String getName() {
        return logger.getName();
    }

    @Override
    public boolean isLoggable(System.Logger.Level level) {
        return logger.isLoggable(traducir(level));
    }

    @Override
    public void log(System.Logger.Level level, ResourceBundle bundle, String msg, Throwable thrown) {
        logger.logrb(traducir(level), bundle, msg, thrown);
    }

    @Override
    public void log(System.Logger.Level level, ResourceBundle bundle, String format, Object... params) {
        logger.logrb(traducir(level), bundle, format, params);
    }

    @Override
    public void log(System.Logger.Level level, String msg) {
        logger.log(traducir(level), msg);
    }

    @Override
    public void log(System.Logger.Level level, Supplier<String> msgSupplier) {
        logger.log(traducir(level), msgSupplier);
    }

    @Override
    public void log(System.Logger.Level level, Object obj) {
        logger.log(traducir(level), "", obj);
    }

    @Override
    public void log(System.Logger.Level level, String msg, Throwable thrown) {
        logger.log(traducir(level), msg, thrown);
    }

    @Override
    public void log(System.Logger.Level level, Supplier<String> msgSupplier, Throwable thrown) {
        logger.log(traducir(level), thrown, msgSupplier);
    }

    @Override
    public void log(System.Logger.Level level, String format, Object... params) {
        logger.log(traducir(level), format, params);
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public static System.Logger.Level traducir(java.util.logging.Level level) throws Exception {
        System.Logger.Level logger_level = System.Logger.Level.ALL;
        if (level == java.util.logging.Level.ALL) {
            logger_level = System.Logger.Level.ALL;
        } else if (level == java.util.logging.Level.FINER) {
            logger_level = System.Logger.Level.TRACE;
        } else if (level == java.util.logging.Level.FINE) {
            logger_level = System.Logger.Level.DEBUG;
        } else if (level == java.util.logging.Level.INFO) {
            logger_level = System.Logger.Level.INFO;
        } else if (level == java.util.logging.Level.WARNING) {
            logger_level = System.Logger.Level.WARNING;
        } else if (level == java.util.logging.Level.SEVERE) {
            logger_level = System.Logger.Level.ERROR;
        } else if (level == java.util.logging.Level.OFF) {
            logger_level = System.Logger.Level.OFF;
        }
        return logger_level;
    }

    public static java.util.logging.Level traducir(System.Logger.Level level) {
        java.util.logging.Level logger_level = java.util.logging.Level.ALL;
        if (level == System.Logger.Level.ALL) {
            logger_level = java.util.logging.Level.ALL;
        } else if (level == System.Logger.Level.TRACE) {
            logger_level = java.util.logging.Level.FINER;
        } else if (level == System.Logger.Level.DEBUG) {
            logger_level = java.util.logging.Level.FINE;
        } else if (level == System.Logger.Level.INFO) {
            logger_level = java.util.logging.Level.INFO;
        } else if (level == System.Logger.Level.WARNING) {
            logger_level = java.util.logging.Level.WARNING;
        } else if (level == System.Logger.Level.ERROR) {
            logger_level = java.util.logging.Level.SEVERE;
        } else if (level == System.Logger.Level.OFF) { 
            logger_level = java.util.logging.Level.OFF;
        }
        return logger_level;
    }

}
