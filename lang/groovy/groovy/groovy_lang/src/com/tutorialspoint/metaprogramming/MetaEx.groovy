package com.tutorialspoint.metaprogramming


public interface GroovyInterceptable {
    public Object invokeMethod(String methodName, Object args)
    public Object getProperty(String propertyName)
    public void setProperty(String propertyName, Object newValue)
    public MetaClass getMetaClass()
    public void setMetaClass(MetaClass metaClass)
}

class Student implements  GroovyInterceptable{

    protected dynamicProps = [:]

    @Override
    void setProperty(String propertyName, Object newValue){
        dynamicProps[propertyName] = newValue
    }

    @Override
    Object getProperty(String propertyName) {
        dynamicProps[propertyName]
    }

    @Override
    def invokeMethod(String name, Object args) {
        return "called invokeMethod $name $args"
    }

    void Display(){
        dynamicProps.each {println "${it.key} : ${it.value}"}
    }
}


def s = new Student()
s.Name = "Joe"
s.ID = 1
s.Marks = [3.9, 4.3, 5.5]
s.Display()
println "marks: ${s.getProperty('Marks')}"
s.AddMarks()
