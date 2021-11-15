package com.pangpan.feignprovider;

import com.pangpan.feignapi.feignapi.UserApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pangpan
 * @date 2021-11-12
 */
@RestController
public class UserController implements UserApi {


    @Override
    @GetMapping("/alive")
    public String alive()
    {
        return "ok";
    }

}
