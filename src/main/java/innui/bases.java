/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package innui;

import java.util.Map;

/**
 *
 * @author emilio
 */
public class bases {
    /**
     * Atributo para extender el funcionamiento de una clase derivada de la clase: bases
     */
    public Object o = null;
    /**
     * Atributo para añadir atributos, identificándolos con un nombre único.
     */
    public Map<String, Object> mapa = null;
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
     * @param <t> Tipo genérico al que hacer el casting
     * @return un objeto base
     */
    public <t> t _o() {
        try {
            if (o != null) {
                return (t) o;
            } else {
                return (t) this;
            }
        } catch (Exception e) {
            return (t) this;
        }
    }
    
    public void setO(Object o) {
        this.o = o;
    }
    public Object getO() {
        return o;
    }
}
