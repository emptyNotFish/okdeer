package com.okdeer.mall;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.eureka.EurekaServerContext;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 对服务的配置信息进行操作管理
 * TODO 弄明白eureka服务注册 同步的整个流程 读懂源码
 * TODO 搞清楚为什么controller写在这里
 * TODO post请求clean refresh的请求是eureka实现还是自己实现，以及实现方式
 * Created by Administrator on 2018/3/29 0029.
 */
@RestController
@RequestMapping("/services")
public class ConfigController {

    //服务配置
    private static final String CONFIGSERVER = "config-server";

    // spring提供的访问rest服务的客户端
    RestTemplate restTemplate = new RestTemplate();

    /**
     * 获取当前有效服务列表
     * @return 多行文本 每行格式：service-name:url,[url2...urlN]
     */
    @RequestMapping(value = "listEnableServer", method = RequestMethod.POST)
    public Object listEnableServer(){
        StringBuilder stringBuilder = new StringBuilder();
        getRegistry().getSortedApplications().forEach(app -> {
            stringBuilder.append(app.getName()).append(":");
            app.getInstances().forEach(instance -> {
                // 判断服务实例状态是否上线
                if (InstanceInfo.InstanceStatus.UP == instance.getStatus()){
                    stringBuilder.append(instance.getHomePageUrl()).append(":");
                }
            });
            stringBuilder.append("\r\n");
        });
        return stringBuilder.toString();

    }

    /**
     * 刷新服务配置
     * @return 多行文本 每行格式：service-name[Surl]：result
     */
    @RequestMapping(value = "refreshConfig", method = RequestMethod.POST)
    public Object refreshConfig(){
        return postAllUpServices("refresh");

    }

    /**
     * 清除本地缓存
     * @return 多行文本 每行格式：service-name[url]:result
     */
    @RequestMapping(value = "clearLocalCache", method = RequestMethod.POST)
    public Object clearLocalCache(){
        return postAllUpServices("local-cache/clear");

    }

    /**
     * 提交处理具体的请求
     * @param uri
     * @return
     */
    private String postAllUpServices(String uri) {
        StringBuilder stringBuilder = new StringBuilder();
        getRegistry().getSortedApplications().forEach(app -> {
            app.getInstances().forEach(instance -> {
                // 服务名称
                String name = instance.getAppName();
                if (CONFIGSERVER.equalsIgnoreCase(name))
                    return;
                // 服务实例在线
                if (InstanceInfo.InstanceStatus.UP == instance.getStatus()){
                    // 实例访问地址
                    String url = instance.getHomePageUrl();
                    stringBuilder.append(name).append("[").append(url).append("]");

                    try{
                        // post请求获取对象
                        ResponseEntity<String> response = restTemplate.postForEntity(
                                String.format("%s%s", url, uri),null, String.class);
                        // 返回成功
                        if (response.getStatusCode().is2xxSuccessful()){
                            stringBuilder.append(response.getBody());
                        }else {
                            stringBuilder.append(response.getStatusCodeValue());
                        }
                    } catch (Throwable throwable) {
                        stringBuilder.append(throwable.getMessage());
                    }
                    stringBuilder.append("\r\n");
                }
            });
        });
        return stringBuilder.toString();


    }

    /**
     * 获取注册的服务信息
     * @return
     */
    private PeerAwareInstanceRegistry getRegistry(){
        return getServerContext().getRegistry();
    }

    private EurekaServerContext getServerContext(){
        return EurekaServerContextHolder.getInstance().getServerContext();
    }
}
