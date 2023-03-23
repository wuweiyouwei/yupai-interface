package com.yupi.yupaiinterface;

import com.yupi.yupiclientsdk.client.YuApiClient;
import com.yupi.yupiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class YupaiInterfaceApplicationTests {


    @Resource
    private YuApiClient yuApiClient;

    @Test
    void contextLoads() {

        String result = yuApiClient.getNameByGet("yupi");

        User user = new User();
        user.setUsername("yupi");
        String userNameByPost = yuApiClient.getUserNameByPost(user);

        System.out.println(result);
        System.out.println(userNameByPost);
    }

}
