package org.example;

//Builder

public class Cliente {
    private String documento;
    private String nombre;
    private String telefono;


    private Cliente(Builder builder) {
        this.documento = builder.documento;
        this.nombre = builder.nombre;
        this.telefono = builder.telefono;
    }

    public static class Builder {
        private String documento;
        private String nombre;
        private String telefono;

        public Builder documento(String documento) {
            this.documento = documento;
            return this;
        }
        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder telefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public Cliente build() {
            return new Cliente(this);
        }
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return "Nombre Cliente: "+nombre+"\nDocumento: "+documento+"\nTeléfono: "+telefono;
    }
}
