package com.hlh.gateway.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author hlh
 */
@Slf4j
@Configuration
public class DynamicRoutesLoader implements InitializingBean {
    @Resource
    private NacosConfigManager configService;

    @Resource
    private NacosConfigProperties configProperties;

    @Resource
    private DynamicRoutesListener dynamicRoutesListener;

    private static final String ROUTES_CONFIG = "routes-config.json";

    @Override
    public void afterPropertiesSet() throws Exception {
        String routes = configService.getConfigService().getConfig(ROUTES_CONFIG, configProperties.getGroup(), 1000);
        dynamicRoutesListener.receiveConfigInfo(routes);
        configService.getConfigService().addListener(ROUTES_CONFIG, configProperties.getGroup(), dynamicRoutesListener);
    }
}
