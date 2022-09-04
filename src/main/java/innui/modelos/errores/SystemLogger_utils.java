/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innui.modelos.errores;

import innui.bases;
import java.util.ResourceBundle;
import java.util.function.Supplier;
import static innui.modelos.errores.Loggers.traducir;

/**
 *
 * @author emilio
 */
public class SystemLogger_utils extends bases implements System.Logger {

    public static String k_in_ruta = "in/innui/modelos/errores/in";
    public java.util.logging.Logger logger;

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


    public static System.Logger.Level traducir(java.util.logging.Level level) {
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
