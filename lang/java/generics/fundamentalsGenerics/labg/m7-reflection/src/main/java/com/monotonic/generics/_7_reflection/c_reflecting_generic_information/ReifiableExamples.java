package com.monotonic.generics._7_reflection.c_reflecting_generic_information;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReifiableExamples
{
    public static class StringList extends ArrayList<String> { }

    public static void main(String[] args)
    {
        List<String> strings = new ArrayList<>();
        Class<?> arrayListClass = strings.getClass();
        System.out.println("stringClass: " + arrayListClass);

        TypeVariable<?>[] typeParameters = arrayListClass.getTypeParameters();
        System.out.println("typeParameters: " + Arrays.toString(typeParameters));

        System.out.println(StringList.class.toGenericString());
        ParameterizedType superclass = (ParameterizedType) StringList.class.getGenericSuperclass();
        Type typeVariable = superclass.getActualTypeArguments()[0];
        System.out.println("typeVariable: " + typeVariable);
    }
}
