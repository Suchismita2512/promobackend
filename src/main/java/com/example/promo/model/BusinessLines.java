package com.example.promo.model;

public class BusinessLines {
    private java.util.List<String> lines;
    private Medicines medicines;
    private Diagnostics diagnostics;

    public BusinessLines() {
    }

    public java.util.List<String> getLines() {
        return lines;
    }

    public void setLines(java.util.List<String> lines) {
        this.lines = lines;
    }

    public Medicines getMedicines() {
        return medicines;
    }

    public void setMedicines(Medicines medicines) {
        this.medicines = medicines;
    }

    public Diagnostics getDiagnostics() {
        return diagnostics;
    }

    public void setDiagnostics(Diagnostics diagnostics) {
        this.diagnostics = diagnostics;
    }
}
