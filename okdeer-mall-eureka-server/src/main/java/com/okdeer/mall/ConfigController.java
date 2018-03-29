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

import static java.rmi.registry.LocateRegistry.getRegistry;

/**
 * 对服务的配置信息进行操作管理
 * Created by Administrator on 2018/3/29 0029.
 */
@RestController
@RequestMapping("/services")
public class ConfigController {

    //服务配置
    private static final String CONFIGSERVER = "config-server";

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
                String name = instance.getAppName();
                if (CONFIGSERVER.equalsIgnoreCase(name))
                    return;
                if (InstanceInfo.InstanceStatus.UP == instance.getStatus()){
                    String url = instance.getHomePageUrl();
                    stringBuilder.append(name).append("[").append(url).append("]");

                    try{
                        ResponseEntity<String> response = restTemplate.postForEntity(
                                String.format("%s%s", url, uri),null, String.class);
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

    private PeerAwareInstanceRegistry getRegistry(){
        return getServerContext().getRegistry();
    }

    private EurekaServerContext getServerContext(){
        return EurekaServerContextHolder.getInstance().getServerContext();
    }
}
