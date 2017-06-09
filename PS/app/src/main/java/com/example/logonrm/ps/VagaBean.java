package com.example.logonrm.ps;

public class VagaBean {
    private int id;
    private String area;
    private String nivel;

    public VagaBean(int id, String area, String nivel) {
        this.id = id;
        this.area = area;
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return area + "(" + nivel + ")";
    }
}
