/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package innui.modelos;

import innui.modelos.configuraciones.iniciales;
import innui.modelos.errores.oks;
import static java.lang.System.exit;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class modelos extends iniciales {
    /**
     * Inicio de la aplicación
     * @param args 
     */
    public static void main(String[] args) {
        oks ok = new oks();
        try {
            ResourceBundle in = null;
            modelos modelo = null;
            try {
                modelo = new modelos();
                modelo.run(ok);
            } catch (Exception e) {
                ok.setTxt(e);
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        if (ok.es == false) {
            System.err.println(ok.txt);
            exit(1);
        } else {
            exit(0);
        }
    }    
    /**
     * Inicio de la aplicación desde un objeto instanciado
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public boolean run(oks ok, Object ... extra_array) throws Exception {
        try {
            while (true) {
                if (ok.es == false) { break; }
                iniciar_dependencias(ok);
                break;
            }
            oks ok_local = new oks();
            ok.setTxt(ok.getTxt(), ok_local.getTxt());
            return ok.es;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean iniciar_dependencias(oks ok, Object... extra_array) throws Exception {
        // Iniciar clase principal de la librería
        iniciar(this.getClass(), ok);
        return ok.es;
    }
    
}
