/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innui.modelos.errores;

import innui.modelos.configuraciones.ResourceBundles;
import innui.modelos.internacionalizacion.tr;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 *
 * @author emilio
 */
public class Strings_max_Handler extends Handler {

    public static String k_in_ruta = "in/innui/modelos/errores/in";
    public LinkedList<String> linkedList;
    public Formatter formatter;
    public int max_tam = 0;
    public java.util.logging.Level pushLevel;

    public Strings_max_Handler(int max_tam, java.util.logging.Level pushLevel) {
        linkedList = new LinkedList<>();
        formatter = new java.util.logging.SimpleFormatter();
        this.max_tam = max_tam;
        this.pushLevel = pushLevel;
    }

    @Override
    public void publish​(LogRecord record) {
        if (pushLevel.intValue() <= record.getLevel().intValue()) {
            String mensaje = formatter.format(record);
            if (max_tam != 0) {
                if (linkedList.size() >= max_tam) {
                    linkedList.removeFirst();
                }
                linkedList.addLast(mensaje);
            }
        }
    }

    @Override
    public String toString() {
        String mensaje = "";
        for (String texto: linkedList) {
            if (mensaje.isEmpty() == false) {
                mensaje = mensaje + "\n\t";
            }
            mensaje = mensaje + texto;
        }
        return mensaje;
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() throws SecurityException {
    }

    public static Strings_max_Handler extraer(Loggers logger, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return null; }
            ResourceBundle in;
            Strings_max_Handler strings_max_Handler = null;
            java.util.logging.Handler [] handlers_array = logger.logger.getHandlers();
            for (java.util.logging.Handler handler: handlers_array) {
                if (handler instanceof Strings_max_Handler) {
                    strings_max_Handler = (Strings_max_Handler) handler;
                    break;
                }
            }
            if (strings_max_Handler == null) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.txt = tr.in(in, "NO SE HA ENCONTRADO NINGUN OBJETO DE LA CLASE. ");
            }
            return strings_max_Handler;
        } catch (Exception e) {
            throw e;
        }
    }

};
