package innui.modelos;

/**
 *
 * @author emilio
 */
public class tipos_valores {
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
}
