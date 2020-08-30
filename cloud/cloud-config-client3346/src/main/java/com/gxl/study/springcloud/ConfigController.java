package com.gxl.study.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gouxi
 * @description
 * @since 2020/8/14
 */
@RefreshScope   //配置刷新功能
@RestController
public class ConfigController {

    // 因为config仓库以rest形式暴露，所以所有客户端都可以通过config服务端访问到github上对应的文件信息：根据uri+name+profile+label 获取文件
    //所以这里已经是拿到了一个配置文件，比如application-dev.yml。  然后取里面的config.info的配置
    @Value("${config.info}")
    private String configInfo;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/configInfo")
    public String getConfigInfo() {
        return "serverPort: " + serverPort + ", \t\n\n configInfo: " + configInfo;
    }
}
