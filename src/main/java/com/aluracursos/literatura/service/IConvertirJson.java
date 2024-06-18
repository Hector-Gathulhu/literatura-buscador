package com.aluracursos.literatura.service;

public interface IConvertirJson {

    <T> T obternerDatos(String json, Class<T> clase);

}
