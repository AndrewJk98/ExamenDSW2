package Json;
public class Cuenta {

    private String banco;
    private String nro_cuenta;
    private String saldo;

    public Cuenta(String banco, String nro_cuenta, String saldo) {
        this.banco = banco;
        this.nro_cuenta = nro_cuenta;
        this.saldo = saldo;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getNro_cuenta() {
        return nro_cuenta;
    }

    public void setNro_cuenta(String nro_cuenta) {
        this.nro_cuenta = nro_cuenta;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
}