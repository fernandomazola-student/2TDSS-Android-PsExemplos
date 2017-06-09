package com.example.logonrm.ps;

public class SemestralServer {


    public static String getAreas() {

        final String json = "[{\"id\":10, \"area\":\"Banco de Dados\", \"nivel\":\"Junior\"},\n" +
                             "{\"id\":20,\"area\":\"Desenvolvimento Mobile\", \"nivel\":\"Senior\"},\n" +
                             "{\"id\":30,\"area\":\"Analista de Sistemas\", \"nivel\":\"Pleno\"}]";

        return json;
    }

    public static String getDetalhes(int idArea) {

        String ret = "";

        if (idArea == 10) {
            ret = "{\"cidade\":\"São Paulo\", \"salario\":2000.0}";

        } else if (idArea == 20) {
            ret = "{\"cidade\":\"Rio de Janeiro\", \"salario\":5000.0}";
        } else if (idArea == 30) {
            ret = "{\"cidade\":\"Porto Alegre\", \"salario\":8000.0}";
        }

        return ret;

    }

}
