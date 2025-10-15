package org.example;

public class CuentaConBeneficio extends CuentaDecorator {
    private final double porcentajeBonificacion;


    public CuentaConBeneficio(ICuenta cuentaDecorada, double porcentajeBonificacion) {
        super(cuentaDecorada);
        this.porcentajeBonificacion = porcentajeBonificacion;
    }


    @Override
    public void consignar (double monto) {
        double bonificacion = monto * porcentajeBonificacion;
        super.consignar(monto + bonificacion);
    }


    @Override
    public String getDescripcion() {
        return cuentaD.getDescripcion() + " [Bonificada: " + (porcentajeBonificacion*100) + "%]";
    }
}
