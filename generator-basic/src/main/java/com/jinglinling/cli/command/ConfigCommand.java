package com.jinglinling.cli.command;

import cn.hutool.core.util.ReflectUtil;
import com.jinglinling.model.MainTemplateConfig;
import picocli.CommandLine.Command;

import java.lang.reflect.Field;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/6
 * @Description 是一个辅助命令，作用是输出允许用户传入的动态参数的信息
 */
@Command(name = "config",description = "查看参数信息",mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable{
    @Override
    public void run() {
        //实现config命令的逻辑
        System.out.println("查看参数信息");

        //(通过 Java 的反射机制，在程序运行时动态打印出对象属性的信息。)

        /* JDK 原生反射语法
        // 获取要打印属性信息的类
        Class<?> myClass = MainTemplateConfig.class;
        //获取类的所有字段
        Field[] fields = myClass.getDeclaredFields();
        */

        //Hutool的反射工具类
        Field[] fields = ReflectUtil.getFields(MainTemplateConfig.class);

        for (Field field : fields) {
            System.out.println("字段名称：" + field.getName());
            System.out.println("字段类型：" + field.getType());
            System.out.println("---");
        }
    }
}
