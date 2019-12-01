package com.caibinbing.config_client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class PropertiesController {

    @Value("${app.username}")
    private String username;

    @Value("${app.password}")
    private String password;

    @RequestMapping("/getProperties")
    public String getProperties() {
        return "用户名：" + this.username + " ;口令：" + this.password;
    }
}
