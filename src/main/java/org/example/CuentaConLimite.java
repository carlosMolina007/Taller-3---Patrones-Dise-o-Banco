package org.example;

public class CuentaConLimite extends CuentaDecorator {
    private final double limiteSobregiro;


    public CuentaConLimite(ICuenta cuentaDecorada, double limiteSobregiro) {
        super(cuentaDecorada);
        this.limiteSobregiro = limiteSobregiro;
    }


    @Override
    public boolean retirar(double monto) {
        double saldoActual = cuentaD.obtenerSaldo();
        if (monto <= 0)
            return false;
        if (saldoActual + limiteSobregiro >= monto) {
            boolean exito = cuentaD.retirar(monto);
            if (!exito) {
                if (cuentaD instanceof Cuenta) {
                    Cuenta cuenta = (Cuenta) cuentaD;
                     saldoActual-= monto;
                    return true;
                }
                return false;
            }
            return true;
        }
        return false;
    }
}
