package com.jinglinling.cli.pattern;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/6
 * @Description 设备类(接收者)
 */
public class Device {
    private String name;

    public Device(String name) {
        this.name = name;
    }

    public void turnOn() {
        System.out.println(name + "设备打开");
    }

    public void turnOff() {
        System.out.println(name + "设备关闭");
    }
}
