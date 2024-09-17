package org.azir.game.common.io.serializable;

import com.esotericsoftware.kryo.Kryo;
import org.azir.game.common.event.Event;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * kryo序列化构造器类
 *
 * @author zhangshukun
 * @since 2024/09/06
 */
public class KryoBuilder {

    /**
     * 需要序列化的类{@link org.azir.game.common.event.Event}所在目录下的包
     */
    private static final Class<Event> EVENT_CLASS = Event.class;

    private static final List<Class<?>> REGISTER_CLASSES = new ArrayList<>();

    static {
        REGISTER_CLASSES.add(LocalDateTime.class);
    }

    public static Kryo build() {
        Kryo res = new Kryo();
        List<Class<?>> classList = getClassList(EVENT_CLASS);
        for (Class<?> aClass : classList) {
            res.register(aClass);
        }
        REGISTER_CLASSES.forEach(res::register);
        return res;
    }

    /**
     * 获取指定类所在目录下的所有类
     */
    public static List<Class<?>> getClassList(Class<?> knownClass) {
        // 获取该类的ClassLoader
        ClassLoader classLoader = knownClass.getClassLoader();

        // 获取该类的资源（.class文件的URL）
        String className = knownClass.getName().replace('.', '/') + ".class";
        URL classUrl = classLoader.getResource(className);

        if (classUrl == null) {
            throw new IllegalArgumentException("没有找到这个类：" + knownClass.getName());
        }

        // 获取类所在的目录
        String classFilePath = URLDecoder.decode(classUrl.getFile(), StandardCharsets.UTF_8);
        String baseDir = classFilePath.substring(0, classFilePath.indexOf(knownClass.getSimpleName()));

        // 获取目录路径并扫描
        File directory = new File(baseDir);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Directory not found: " + directory.getAbsolutePath());
        }

        // 获取该目录下的所有类
        try {
            return findClassesInDirectory(directory, knownClass.getPackage().getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("加载：" + directory + "目录下的类时异常", e);
        }
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
                    // 当前是文件夹
                    classes.addAll(findClassesInDirectory(file, packageName + "." + file.getName()));
                }
                if (file.getName().endsWith(".class")) {
                    // 去掉 ".class" 扩展名，获取类名
                    String className = file.getName().substring(0, file.getName().length() - 6);
                    classes.add(Class.forName(packageName + '.' + className));
                }
            }
        }
        return classes;
    }
}
