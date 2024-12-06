package com.jinglinling.cli;

import com.jinglinling.cli.command.ConfigCommand;
import com.jinglinling.cli.command.GenerateCommand;
import com.jinglinling.cli.command.ListCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/6
 * @Description 负责绑定所有的子命令，提供执行命令的方法
 */
@Command(name = "jinglinling",mixinStandardHelpOptions = true)
public class CommandExecutor implements Runnable{
    private final CommandLine commandLine;

    /*
     * 实例初始化块的执行顺序是在构造函数之前，但是在所有字段初始化之后，可用来执行更复杂的初始化逻辑。
     * */
    {
        commandLine = new CommandLine(this)
                .addSubcommand(new GenerateCommand())
                .addSubcommand(new ConfigCommand())
                .addSubcommand(new ListCommand());
    }

    @Override
    public void run() {
        System.out.println("请输入具体命令，或者输入 --help 查看命令提示");
    }

    /**
     * @param args
     * @return:
     * @Author: 谢骏晖
     * @date: 2024/12/6
     * @Description: 执行命令
     */
    public Integer doExecute(String[] args) {
        return commandLine.execute(args);
    }
}
