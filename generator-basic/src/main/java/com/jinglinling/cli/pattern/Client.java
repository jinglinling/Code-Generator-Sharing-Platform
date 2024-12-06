package com.jinglinling.cli.pattern;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/6
 * @Description 客户端类(使用遥控器的人)
 */
public class Client {
    public static void main(String[] args) {
        //接收者对象
        Device tv = new Device("TV");
        Device stereo = new Device("Stereo");

        //创建具体命令对象，可绑定不同设备
        TurnOnCommand turnOnCommand = new TurnOnCommand(tv);
        TurnOffCommand turnOffCommand = new TurnOffCommand(stereo);

        //创建调用者
        RemoteControl remoteControl = new RemoteControl();

        //执行命令
        remoteControl.setCommand(turnOnCommand);
        remoteControl.pressButton();

        remoteControl.setCommand(turnOffCommand);
        remoteControl.pressButton();
    }
}
