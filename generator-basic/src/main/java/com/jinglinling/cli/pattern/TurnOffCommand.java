package com.jinglinling.cli.pattern;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/6
 * @Description 关闭设备命令
 */
public class TurnOffCommand implements Command {
    private Device device;

    public TurnOffCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOff();
    }
}
