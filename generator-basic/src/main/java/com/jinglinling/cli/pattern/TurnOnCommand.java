package com.jinglinling.cli.pattern;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/6
 * @Description 打开设备命令
 */
public class TurnOnCommand implements Command{
    private Device device;

    public TurnOnCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOn();
    }
}
