package io.milles.gen.dtogenerator.dto;

public class ExampleClassDTO implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private int aRenamedPrimitiveInt;

    private java.lang.Integer aBoxedInteger;

    private java.util.List<java.lang.Double> aList;

    private java.util.Set<?> aWildCard;

    private java.util.Map<java.lang.String, java.lang.Integer> aMap;

    private io.milles.gen.dtogenerator.dto.ExampleDependencyClassDTO aDependentClass;

    private java.util.Collection<io.milles.gen.dtogenerator.dto.ExampleDependencyClassDTO> aDependentClassCollection;

    private java.lang.Integer aFinalInteger;

    private io.milles.gen.dtogenerator.dto.ExampleDependencyClassDTO[] aDependentClassArray;

    public int getARenamedPrimitiveInt() {
        return this.aRenamedPrimitiveInt;
    }

    public java.lang.Integer getABoxedInteger() {
        return this.aBoxedInteger;
    }

    public java.util.List<java.lang.Double> getAList() {
        return this.aList;
    }

    public java.util.Set<?> getAWildCard() {
        return this.aWildCard;
    }

    public java.util.Map<java.lang.String, java.lang.Integer> getAMap() {
        return this.aMap;
    }

    public io.milles.gen.dtogenerator.dto.ExampleDependencyClassDTO getADependentClass() {
        return this.aDependentClass;
    }

    public java.util.Collection<io.milles.gen.dtogenerator.dto.ExampleDependencyClassDTO> getADependentClassCollection() {
        return this.aDependentClassCollection;
    }

    public java.lang.Integer getAFinalInteger() {
        return this.aFinalInteger;
    }

    public io.milles.gen.dtogenerator.dto.ExampleDependencyClassDTO[] getADependentClassArray() {
        return this.aDependentClassArray;
    }

    public void setARenamedPrimitiveInt(int aRenamedPrimitiveInt) {
        this.aRenamedPrimitiveInt = aRenamedPrimitiveInt;
    }

    public void setABoxedInteger(java.lang.Integer aBoxedInteger) {
        this.aBoxedInteger = aBoxedInteger;
    }

    public void setAList(java.util.List<java.lang.Double> aList) {
        this.aList = aList;
    }

    public void setAWildCard(java.util.Set<?> aWildCard) {
        this.aWildCard = aWildCard;
    }

    public void setAMap(java.util.Map<java.lang.String, java.lang.Integer> aMap) {
        this.aMap = aMap;
    }

    public void setADependentClass(io.milles.gen.dtogenerator.dto.ExampleDependencyClassDTO aDependentClass) {
        this.aDependentClass = aDependentClass;
    }

    public void setADependentClassCollection(java.util.Collection<io.milles.gen.dtogenerator.dto.ExampleDependencyClassDTO> aDependentClassCollection) {
        this.aDependentClassCollection = aDependentClassCollection;
    }

    public void setAFinalInteger(java.lang.Integer aFinalInteger) {
        this.aFinalInteger = aFinalInteger;
    }

    public void setADependentClassArray(io.milles.gen.dtogenerator.dto.ExampleDependencyClassDTO[] aDependentClassArray) {
        this.aDependentClassArray = aDependentClassArray;
    }

}