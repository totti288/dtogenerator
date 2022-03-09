package com.pslife.test;

public class GeneratorDto<A> implements java.io.Serializable {

    private java.util.List<java.util.List<java.util.Map<A[], java.lang.String>>> test;

    private java.util.Map<java.lang.String, java.lang.Integer>[] bla;

    private java.util.Set<?> x;

    private java.util.List<com.pslife.test.GeneratorDto<?>> lol;

    private java.util.List<java.util.List<java.util.Map<A[], java.lang.String>>> getTest() {
        return this.test;
    }

    private java.util.Map<java.lang.String, java.lang.Integer>[] getBla() {
        return this.bla;
    }

    private java.util.Set<?> getX() {
        return this.x;
    }

    private java.util.List<com.pslife.test.GeneratorDto<?>> getLol() {
        return this.lol;
    }

    private void setTest(java.util.List<java.util.List<java.util.Map<A[], java.lang.String>>> test) {
        this.test = test;
    }

    private void setBla(java.util.Map<java.lang.String, java.lang.Integer>[] bla) {
        this.bla = bla;
    }

    private void setX(java.util.Set<?> x) {
        this.x = x;
    }

    private void setLol(java.util.List<com.pslife.test.GeneratorDto<?>> lol) {
        this.lol = lol;
    }

}