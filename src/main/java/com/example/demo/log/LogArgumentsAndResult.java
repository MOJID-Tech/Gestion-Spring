package com.example.demo.log;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation permettant de logger les arguments et le résultat d'une méthode.<br>
 * La classe et la méthode doivent avoir une visiblité public.
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface LogArgumentsAndResult {

}
