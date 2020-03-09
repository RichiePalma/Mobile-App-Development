package com.itesm.listview;

// bean
// clase que solo contiene datos
public class Estudiante {
    private String nombre;
    private float calificacion;
    private long studentId;

    public Estudiante(String nombre, float calificacion, long studentId) {
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.studentId = studentId;
    }

    public String getNombre() {
        return nombre;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public long getStudentId() {
        return studentId;
    }
}
