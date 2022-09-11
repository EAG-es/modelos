/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package innui.utiles.strings;

import innui.bases;
import innui.modelos.errores.oks;

/**
 *
 * @author emilio
 */
public class Strings extends bases {

    public static String hacer_nul_vacio(String texto, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return null; }
            if (texto == null) {
                return "";
            } else {
                return texto;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
