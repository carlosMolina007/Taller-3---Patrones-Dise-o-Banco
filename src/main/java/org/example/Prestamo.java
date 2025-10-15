package org.example;

public class Prestamo {
    private String numPrestamo;
    private Cuenta cuenta;
    private double deuda;

    public Prestamo(String numPrestamo, Cuenta cuenta, double deuda) {
        this.numPrestamo = numPrestamo;
        this.cuenta = cuenta;
        this.deuda = deuda;
        this.cuenta.consignar(this.deuda);
    }

    public void abonar(double abono){
        if (this.deuda >= abono){
            this.deuda -= abono;
        }
        else{
            this.cuenta.consignar(abono - this.deuda);
            this.deuda = 0;
        }
    }

    public String getNumPrestamo() {
        return numPrestamo;
    }

    public void setNumPrestamo(String numPrestamo) {
        this.numPrestamo = numPrestamo;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public double getDeuda() {
        return deuda;
    }

    public void setDeuda(double deuda) {
        this.deuda = deuda;
    }

    @Override
    public String toString() {
        return "Prestamo    #: " + numPrestamo + '\n' +
                "Cliente     : " + cuenta.getCliente().getNombre() + "\n" +
                "Saldo cuenta: " + this.getCuenta().getSaldo() + "\n" +
                "Deuda       : " + this.deuda;
    }
}
