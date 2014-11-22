package bruno.android.utils.simple;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dev on 28/10/2014.
 */
public abstract class SimplePreferences {

    private static final String TAG = "SimplePreferences";
    private String preferenceName;
    private Context context;


    protected SimplePreferences(Context context) {
        this.context = context;
        preferenceName = this.getClass().getName();
        init();
    }

    protected SimplePreferences(String preferenceName, Context context) {
        this.preferenceName = preferenceName;
        this.context = context;
        init();
    }

    private void init() {
        try {
            reload();
        } catch (IllegalAccessException e) {
        }
    }

    public void reload() throws IllegalAccessException {
        Log.d(TAG, "reload");
        SharedPreferences preferences = context
                .getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        for (Field field : this.getClass().getDeclaredFields())
            if (field.isAnnotationPresent(Property.class))
                loadField(preferences, field);
        Log.d(TAG, "reloaded");
    }

    protected void loadField(SharedPreferences preferences, Field field) throws IllegalAccessException {
        Class<?> fieldType = field.getType();
        for (Resolver resolver : resolverList)
            if (resolver.canResolve(fieldType)) {
                resolver.load(preferences, field, this, field.getAnnotation(Property.class));
                return;
            }
    }

    public void commit() throws IllegalAccessException, UnknownFieldTypeException {
        Log.d(TAG, "commit");
        SharedPreferences.Editor editor = context
                .getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
                .edit();
        for (Field field : this.getClass().getDeclaredFields())
            if (field.isAnnotationPresent(Property.class))
                saveField(editor, field);
        editor.commit();
        Log.d(TAG, "commited");
    }

    protected void saveField(SharedPreferences.Editor editor, Field field) throws IllegalAccessException, UnknownFieldTypeException {
        Class<?> fieldType = field.getType();
        for (Resolver resolver : resolverList)
            if (resolver.canResolve(fieldType)) {
                resolver.save(editor, field, this, field.getAnnotation(Property.class));
                return;
            }
        throw new UnknownFieldTypeException();
    }

    private static List<Resolver> resolverList;

    static {
        resolverList = new ArrayList<Resolver>();
        resolverList.add(new IntegerResolver());
        resolverList.add(new LongResolver());
        resolverList.add(new BooleanResolver());
        resolverList.add(new FloatResolver());
        resolverList.add(new StringResolver());
    }

    public static abstract class Resolver {
        abstract boolean canResolve(Class<?> fieldType);

        abstract void save(SharedPreferences.Editor editor, Field field, Object object, Property annotation) throws IllegalAccessException;

        abstract void load(SharedPreferences preferences, Field field, Object object, Property annotation) throws IllegalAccessException;

        protected static String resolveKey(Field field) {
            String name = field.getAnnotation(Property.class).name();
            if ("".equalsIgnoreCase(name))
                name = field.getName();
            return name;
        }
    }

    public static class IntegerResolver extends Resolver {

        @Override
        public boolean canResolve(Class<?> fieldType) {
            return fieldType.isAssignableFrom(Integer.class) || fieldType.isAssignableFrom(Integer.TYPE);
        }

        @Override
        void save(SharedPreferences.Editor editor, Field field, Object object, Property annotation) throws IllegalAccessException {
            editor.putInt(resolveKey(field), field.getInt(object));
        }

        @Override
        void load(SharedPreferences preferences, Field field, Object object, Property annotation) throws IllegalAccessException {
            field.setInt(object, preferences.getInt(resolveKey(field), Integer.valueOf(annotation.defaultValue())));
        }
    }

    public static class LongResolver extends Resolver {

        @Override
        public boolean canResolve(Class<?> fieldType) {
            return fieldType.isAssignableFrom(Long.class) || fieldType.isAssignableFrom(Long.TYPE);
        }

        @Override
        void save(SharedPreferences.Editor editor, Field field, Object object, Property annotation) throws IllegalAccessException {
            editor.putLong(resolveKey(field), field.getLong(object));
        }

        @Override
        void load(SharedPreferences preferences, Field field, Object object, Property annotation) throws IllegalAccessException {
            field.setLong(object, preferences.getLong(resolveKey(field), Long.valueOf(annotation.defaultValue())));
        }
    }

    public static class BooleanResolver extends Resolver {

        @Override
        public boolean canResolve(Class<?> fieldType) {
            return fieldType.isAssignableFrom(Boolean.class) || fieldType.isAssignableFrom(Boolean.TYPE);
        }

        @Override
        void save(SharedPreferences.Editor editor, Field field, Object object, Property annotation) throws IllegalAccessException {
            editor.putBoolean(resolveKey(field), field.getBoolean(object));
        }

        @Override
        void load(SharedPreferences preferences, Field field, Object object, Property annotation) throws IllegalAccessException {
            field.setBoolean(object, preferences.getBoolean(resolveKey(field), Boolean.valueOf(annotation.defaultValue())));
        }
    }

    public static class FloatResolver extends Resolver {

        @Override
        public boolean canResolve(Class<?> fieldType) {
            return fieldType.isAssignableFrom(Float.class) || fieldType.isAssignableFrom(Float.TYPE);
        }

        @Override
        void save(SharedPreferences.Editor editor, Field field, Object object, Property annotation) throws IllegalAccessException {
            editor.putFloat(resolveKey(field), field.getFloat(object));
        }

        @Override
        void load(SharedPreferences preferences, Field field, Object object, Property annotation) throws IllegalAccessException {
            field.setFloat(object, preferences.getFloat(resolveKey(field), Float.valueOf(annotation.defaultValue())));
        }
    }

    public static class StringResolver extends Resolver {

        @Override
        public boolean canResolve(Class<?> fieldType) {
            return fieldType.isAssignableFrom(String.class);
        }

        @Override
        void save(SharedPreferences.Editor editor, Field field, Object object, Property annotation) throws IllegalAccessException {
            editor.putString(resolveKey(field), String.valueOf(field.get(object)));
        }

        @Override
        void load(SharedPreferences preferences, Field field, Object object, Property annotation) throws IllegalAccessException {
            field.set(object, preferences.getString(resolveKey(field), annotation.defaultValue()));
        }
    }

    public static final class UnknownFieldTypeException extends Exception {

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    protected static @interface Property {
        String name() default "";

        String defaultValue();
    }
}
