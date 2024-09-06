package org.azir.game.common.io.serializable;

import com.esotericsoftware.kryo.Kryo;
import org.azir.game.common.event.AbstractEvent;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangshukun
 * @since 2024/09/06
 */
public class KryoBuilder {

    private static final Class<AbstractEvent> ABSTRACT_EVENT_CLASS = AbstractEvent.class;

    private Kryo res = new Kryo();

    public Kryo build() {
        // todo 获取某个目录下的所有类进行注册，加快序列化速度
        return res;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        List<Class<?>> classesInSameDirectory = getClassesInSameDirectory(ABSTRACT_EVENT_CLASS);
        System.out.println();
    }

    /**
     * 获取指定类所在目录下的所有类
     */
    public static List<Class<?>> getClassesInSameDirectory(Class<?> knownClass) throws ClassNotFoundException {
        // 获取该类的ClassLoader
        ClassLoader classLoader = knownClass.getClassLoader();

        // 获取该类的资源（.class文件的URL）
        String className = knownClass.getName().replace('.', '/') + ".class";
        URL classUrl = classLoader.getResource(className);

        if (classUrl == null) {
            throw new IllegalArgumentException("Class file for " + knownClass.getName() + " not found.");
        }

        // 获取类所在的目录
        String classFilePath = URLDecoder.decode(classUrl.getFile(), StandardCharsets.UTF_8);
        String baseDir = classFilePath.substring(0, classFilePath.length() - className.length());

        // 获取目录路径并扫描
        File directory = new File(baseDir);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Directory not found: " + directory.getAbsolutePath());
        }

        // 获取该目录下的所有类
        return findClassesInDirectory(directory, knownClass.getPackage().getName());
    }

    /**
     * 递归获取目录下的所有类
     */
    private static List<Class<?>> findClassesInDirectory(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // 递归扫描子目录
                    classes.addAll(findClassesInDirectory(file, packageName + "." + file.getName()));
                } else if (file.getName().endsWith(".class")) {
                    // 去掉 ".class" 扩展名，获取类名
                    String className = file.getName().substring(0, file.getName().length() - 6);
                    classes.add(Class.forName(packageName + '.' + className));
                }
            }
        }
        return classes;
    }
}
