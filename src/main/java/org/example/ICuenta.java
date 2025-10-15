package org.example;

public interface ICuenta {
    void consignar(double monto);
    boolean retirar(double monto);
    double obtenerSaldo();
    String getDescripcion();
}
