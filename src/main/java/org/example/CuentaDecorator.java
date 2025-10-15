package org.example;

public abstract class CuentaDecorator implements ICuenta {
    protected final ICuenta cuentaD;

    protected CuentaDecorator(ICuenta cuentaD) {
        this.cuentaD = cuentaD;
    }


    @Override
    public void consignar(double monto) {
        cuentaD.consignar(monto);
    }


    @Override
    public boolean retirar(double monto) {
        return cuentaD.retirar(monto);
    }


    @Override
    public double obtenerSaldo() {
        return cuentaD.obtenerSaldo();
    }


    @Override
    public String getDescripcion() {
        return cuentaD.getDescripcion();
    }
}
