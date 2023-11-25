package com.craftsmanship.utils;

import com.craftsmanship.exceptions.BadRequestException;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class CommonUtils {

    private CommonUtils(){}

    public static <T, R> R copyObjectProperties(T sourceObject, Class<R> targetObjectClass) {
        try {
            if (sourceObject == null) {
                return null;
            }
            R targetObject = targetObjectClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(sourceObject, targetObject);
            return targetObject;
        } catch (InvocationTargetException|InstantiationException|IllegalAccessException|NoSuchMethodException e) {
            throw new BadRequestException("Caught error when copying properties: " + e.getMessage());
        }
    }
}
