package org.azir.game.common.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

/**
 * @author zhangshukun
 * @since 2024/09/06
 */
public class YamlUtils {

    /**
     * 解析yaml配置
     *
     * @param configPath yaml文件路径
     * @param clazz      解析的class
     * @param <T>        解析后的对象类型
     * @return 解析后的对象
     */
    public static <T> T resolve(String configPath, Class<T> clazz) {
        Yaml yaml = new Yaml();
        InputStream yamlInputStream = YamlUtils.class.getClassLoader().getResourceAsStream(configPath);
        return yaml.loadAs(yamlInputStream, clazz);
    }
}
