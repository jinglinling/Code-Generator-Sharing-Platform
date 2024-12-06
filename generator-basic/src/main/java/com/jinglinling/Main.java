package com.jinglinling;

import com.jinglinling.cli.CommandExecutor;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/6
 * @Description
 */
public class Main {
    public static void main(String[] args) {
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }
}
