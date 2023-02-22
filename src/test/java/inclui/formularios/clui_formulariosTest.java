package inclui.formularios;

import static inclui.formularios.entradas.k_entradas_tipo_color;
import static inclui.formularios.entradas.k_entradas_tipo_email;
import static inclui.formularios.entradas.k_entradas_tipo_texto;
import innui.formularios.controles;
import innui.modelos.errores.oks;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import static inclui.formularios.entradas.k_entradas_tipo_fecha;
import static inclui.formularios.entradas.k_entradas_tipo_fecha_y_hora;
import static inclui.formularios.entradas.k_entradas_tipo_hora;
import static inclui.formularios.entradas.k_entradas_tipo_numero;
import static inclui.formularios.entradas.k_entradas_tipo_password;
import static inclui.formularios.entradas.k_entradas_tipo_radio;
import static inclui.formularios.entradas.k_entradas_tipo_reset;
import static inclui.formularios.entradas.k_entradas_tipo_submit;
import static inclui.formularios.entradas.k_entradas_tipo_telefono;
import java.util.Date;

/**
 *
 * @author emilio
 */
public class clui_formulariosTest {
    
    public clui_formulariosTest() {
    }

    /**
     * Test of _poner_control method, of class clui_formularios.
     */
    @Ignore
    public void test_poner_control() throws Exception {
        System.out.println("_poner_control");
        controles control = null;
        oks ok = null;
        Object[] extras_array = null;
        clui_formularios instance = new clui_formularios();
        boolean expResult = false;
        boolean result = instance._poner_control(control, ok, extras_array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of _iniciar_formulario method, of class clui_formularios.
     */
    @Ignore
    public void test_iniciar_formulario() throws Exception {
        System.out.println("_iniciar_formulario");
        String modo_operacion = "";
        oks ok = null;
        Object[] extras_array = null;
        clui_formularios instance = new clui_formularios();
        boolean expResult = false;
        boolean result = instance._iniciar_formulario(modo_operacion, ok, extras_array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of capturar method, of class clui_formularios.
     */
    @Ignore
    public void testCapturar() throws Exception {
        System.out.println("capturar");
        String modo_operacion = "";
        oks ok = null;
        Object[] extras_array = null;
        clui_formularios instance = new clui_formularios();
        boolean expResult = false;
        boolean result = instance.capturar(modo_operacion, ok, extras_array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of procesar method, of class clui_formularios.
     * @throws java.lang.Exception
     */
    @Test
    public void testProcesar() throws Exception {
        System.out.println("procesar");
        oks ok = new oks();
        clui_formularios clui_formulario = new clui_formularios();
        boolean expResult = true;
        entradas entrada_texto = new entradas();
        entrada_texto.iniciar(k_entradas_tipo_texto, ok);
        assertEquals(true, ok.es);
        entrada_texto.poner_en_formulario(clui_formulario, "nombre_apellidos", "Introduzca su nombre y sus apellidos. ", null, ok);
        assertEquals(true, ok.es);
        entradas entrada_radio = new entradas();
        entrada_radio.iniciar(k_entradas_tipo_radio, ok);
        assertEquals(true, ok.es);
        entrada_radio.poner_en_formulario(clui_formulario, "sexo", "Opción 1/2: ¿Sexo XY (macho)?. ", null, ok);
        assertEquals(true, ok.es);
        entrada_radio = new entradas();
        entrada_radio.iniciar(k_entradas_tipo_radio, ok);
        assertEquals(true, ok.es);
        entrada_radio.poner_en_formulario(clui_formulario, "sexo", "Opción 2/2: ¿Sexo XX (hembra)?. ", null, ok);
        assertEquals(true, ok.es);
        entradas entrada_fecha = new entradas();
        entrada_fecha.iniciar(k_entradas_tipo_fecha, ok);
        assertEquals(true, ok.es);
        entrada_fecha.poner_en_formulario(clui_formulario, "fecha", "Introduzca su fecha de nacimiento. ", null, ok);
        assertEquals(true, ok.es);
        entradas entrada_hora = new entradas();
        entrada_hora.iniciar(k_entradas_tipo_hora, ok);
        assertEquals(true, ok.es);
        entrada_hora.poner_en_formulario(clui_formulario, "hora", "Introduzca la hora actual. ", null, ok);
        assertEquals(true, ok.es);
        entradas entrada_color = new entradas();
        entrada_color.iniciar(k_entradas_tipo_color, ok);
        assertEquals(true, ok.es);
        entrada_color.poner_en_formulario(clui_formulario, "color", "Introduzca su color favorito. ", null, ok);
        assertEquals(true, ok.es);
        entradas entrada_email = new entradas();
        entrada_email.iniciar(k_entradas_tipo_email, ok);
        assertEquals(true, ok.es);
        entrada_email.poner_en_formulario(clui_formulario, "email", "Introduzca su email. ", null, ok);
        assertEquals(true, ok.es);
        entradas entrada_contraseña = new entradas();
        entrada_contraseña.iniciar(k_entradas_tipo_password, ok);
        assertEquals(true, ok.es);
        entrada_contraseña.poner_en_formulario(clui_formulario, "password", "Introduzca la contraseña que desea establecer. ", null, ok);
        assertEquals(true, ok.es);
        entrada_contraseña = new entradas();
        entrada_contraseña.iniciar(k_entradas_tipo_password, ok);
        assertEquals(true, ok.es);
        entrada_contraseña.poner_en_formulario(clui_formulario, "password_repetida", "Repita la contraseña que desea establecer. ", null, ok);
        assertEquals(true, ok.es);
        entradas entrada_telefono = new entradas();
        entrada_telefono.iniciar(k_entradas_tipo_telefono, ok);
        assertEquals(true, ok.es);
        entrada_telefono.poner_en_formulario(clui_formulario, "telefono", "Introduzca su teléfono. ", null, ok);
        assertEquals(true, ok.es);
        entrada_texto = new entradas();
        entrada_texto.iniciar(k_entradas_tipo_texto, ok);
        assertEquals(true, ok.es);
        entrada_texto.poner_en_formulario(clui_formulario, "calle", "Introduzca primera parte de su dirección (solo nombre de la calle). ", null, ok);
        assertEquals(true, ok.es);
        entradas entrada_numero = new entradas();
        entrada_texto.iniciar(k_entradas_tipo_numero, ok);
        assertEquals(true, ok.es);
        entrada_numero.poner_en_formulario(clui_formulario, "portal_num", "Introduzca el número del portal de su dirección. ", null, ok);
        assertEquals(true, ok.es);
        entrada_texto = new entradas();
        entrada_texto.iniciar(k_entradas_tipo_texto, ok);
        assertEquals(true, ok.es);
        entrada_texto.poner_en_formulario(clui_formulario, "calle_extra", "Introduzca parte extra de su dirección (piso y puerta se piden a continuación). ", null, ok);
        assertEquals(true, ok.es);
        entrada_numero = new entradas();
        entrada_texto.iniciar(k_entradas_tipo_numero, ok);
        assertEquals(true, ok.es);
        entrada_numero.poner_en_formulario(clui_formulario, "piso_num", "Introduzca el piso de su dirección (0 si no hay). ", null, ok);
        assertEquals(true, ok.es);
        entrada_numero = new entradas();
        entrada_texto.iniciar(k_entradas_tipo_numero, ok);
        assertEquals(true, ok.es);
        entrada_numero.poner_en_formulario(clui_formulario, "puerta_num", "Introduzca la puerta de su dirección (0 si no hay). ", null, ok);
        assertEquals(true, ok.es);
        entrada_texto = new entradas();
        entrada_texto.iniciar(k_entradas_tipo_texto, ok);
        assertEquals(true, ok.es);
        entrada_texto.poner_en_formulario(clui_formulario, "vivienda_extra", "Introduzca parte extra de identificacion de su vivienda (la ciudad se pide a continuación). ", null, ok);
        assertEquals(true, ok.es);
        entrada_texto = new entradas();
        entrada_texto.iniciar(k_entradas_tipo_texto, ok);
        assertEquals(true, ok.es);
        entrada_texto.poner_en_formulario(clui_formulario, "ciudad", "Introduzca la ciudad. ", null, ok);
        assertEquals(true, ok.es);
        entrada_texto = new entradas();
        entrada_texto.iniciar(k_entradas_tipo_texto, ok);
        assertEquals(true, ok.es);
        entrada_texto.poner_en_formulario(clui_formulario, "codigo_postal", "Introduzca el código postal. ", null, ok);
        assertEquals(true, ok.es);
        entrada_texto = new entradas();
        entrada_texto.iniciar(k_entradas_tipo_texto, ok);
        assertEquals(true, ok.es);
        entrada_texto.poner_en_formulario(clui_formulario, "provincia_estado", "Introduzca la provincia/estado. ", null, ok);
        assertEquals(true, ok.es);
        entrada_texto = new entradas();
        entrada_texto.iniciar(k_entradas_tipo_texto, ok);
        assertEquals(true, ok.es);
        entrada_texto.poner_en_formulario(clui_formulario, "pais", "Introduzca el país. ", null, ok);
        assertEquals(true, ok.es);
        entradas entrada_fecha_y_hora = new entradas() {
            @Override
            public boolean _validar(String modo_operacion, Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
                try {
                    if (ok.es == false) { return false; }
                    super._validar(modo_operacion, objeto_a_validar, ok, extras_array);
                    if (ok.es == false) { return false; }
                    Date date;
                    date = (Date) super._convertir(modo_operacion, objeto_a_validar, ok, extras_array);
                    if (ok.es == false) { return false; }
                    Date actual_date = new Date();
                    if (actual_date.getTime() < date.getTime()) {
                        return true;
                    } else {
                        ok.setTxt("La fecha introducida no puede ser anterior a la fecha actual. ");
                    }
                } catch (Exception e) {
                    throw e;
                }
                return ok.es;
            }

        };
        entrada_fecha_y_hora.iniciar(k_entradas_tipo_fecha_y_hora, ok);
        assertEquals(true, ok.es);
        entrada_fecha_y_hora.poner_en_formulario(clui_formulario, "fecha_y_hora_disponibilidad", "Introduzca la fecha y la hora a partir de la que desea que la información proporcionada quede disponible . ", null, ok);
        assertEquals(true, ok.es);
        entradas entrada_reset = new entradas();
        entrada_reset.iniciar(k_entradas_tipo_reset, ok);
        assertEquals(true, ok.es);
        entrada_reset.poner_en_formulario(clui_formulario, "reset", "¿Desea revisar o volver a hacer el formulario?. ", null, ok);
        assertEquals(true, ok.es);
        entradas entrada_submit = new entradas();
        entrada_submit.iniciar(k_entradas_tipo_submit, ok);
        assertEquals(true, ok.es);
        entrada_submit.poner_en_formulario(clui_formulario, "submit", "¿Desea enviar el formulario? (Si no es así, se cancelará). ", null, ok);
        assertEquals(true, ok.es);
        boolean result = clui_formulario.procesar(ok);
        assertEquals(expResult, result);
    }

    /**
     * Test of revisar_nombres_repetidos method, of class clui_formularios.
     */
    @Ignore
    public void testRevisar_nombres_repetidos() throws Exception {
        System.out.println("revisar_nombres_repetidos");
        oks ok = null;
        Object[] extras_array = null;
        clui_formularios instance = new clui_formularios();
        boolean expResult = false;
        boolean result = instance.revisar_nombres_repetidos(ok, extras_array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
