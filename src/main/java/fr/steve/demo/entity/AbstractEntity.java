package fr.steve.demo.entity;

import java.lang.reflect.Field;

public abstract class AbstractEntity<T> {

    public AbstractEntity<T> merge(T incoming) {
        try {
            Field[] fields = incoming.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object incomingValue = field.get(incoming);
                Object currentValue = field.get(this);
                if (!(incomingValue != null && !incomingValue.equals(currentValue)))
                    continue;
                if (field.getType().isPrimitive() || field.getType().equals(String.class)) {
                    field.set(this, incomingValue);
                } else {
                    mergeObjects(currentValue, incomingValue);
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return this;
    }

    private void mergeObjects(Object current, Object incoming) throws IllegalAccessException {
        Field[] fields = incoming.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object incomingValue = field.get(incoming);
            Object currentValue = field.get(current);
            if (!(incomingValue != null && !incomingValue.equals(currentValue)) || (int) incomingValue == 0)
                continue;
            if (field.getType().isPrimitive() || field.getType().equals(String.class)) {
                field.set(current, incomingValue);
            } else {
                mergeObjects(currentValue, incomingValue);
            }
        }
    }
}