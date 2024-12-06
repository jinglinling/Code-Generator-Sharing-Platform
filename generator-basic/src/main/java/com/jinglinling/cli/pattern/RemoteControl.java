package com.jinglinling.cli.pattern;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/6
 * @Description 遥控器类(接收客户端的命令并执行)
 */
public class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}
