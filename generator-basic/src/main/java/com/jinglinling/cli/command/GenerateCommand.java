package com.jinglinling.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.jinglinling.generator.MainGenerator;
import com.jinglinling.model.MainTemplateConfig;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/6
 * @Description 代码生成器的核心命令，作用是接受参数并生成代码。
 */
@Command(name = "generate",description = "生成代码",mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {
    /*
    * 实现步骤如下：

        1）定义参数选项。和之前动态生成代码定义的数据模型 MainTemplateConfig 属性一致即可。使用 Picocli 提供的注解来交互式获取参数信息（interactive = true），并且对用户显示输入信息（echo = true）。

        2）使用 BeanUtil.copyProperties 快速将通过命令接受到的属性复制给 MainTemplateConfig 配置对象。

        3）调用之前开发好的 MainGenerator 代码生成类来生成代码。
    * */

    @Option(names = {"-l","--loop"},arity = "0..1",description = "是否循环",interactive = true,echo = true)
    private boolean loop;

    @Option(names = {"-a","--author"},arity = "0..1",description = "作者",interactive = true,echo = true)
    private String author = "xiejunhui";

    @Option(names = {"-o","--outputText"},arity = "0..1",description = "输出文本",interactive = true,echo = true)
    private String outputText = "sum = ";

    public Integer call() throws Exception {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        BeanUtil.copyProperties(this,mainTemplateConfig);
        System.out.println("配置信息：" + mainTemplateConfig);
        MainGenerator.doGenerator(mainTemplateConfig);
        return 0;
    }
}
