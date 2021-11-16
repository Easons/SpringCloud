package com.pangpan.feignprovider;

import com.pangpan.feignapi.feignapi.UserApi;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

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

    @Override
    @GetMapping("/getById")
    public String getById(String s) {
        return "ID="+s;
    }

    @GetMapping("getMap")
    public  String getMap(@RequestParam(value = "Id") String Id){
        return "Id="+ Id;
    }

    /**
     * 多参数
     * @param map
     * @return
     */
    @GetMapping("/getMap3")
    public Map<Integer, String> getMap3(@RequestParam Map<String, Object> map) {
        // TODO Auto-generated method stub
        System.out.println(map);
        return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
    }


    /**
     * Post请求
     * @param map
     * @return
     */
    @PostMapping("postMap4")
    public Map<Integer, String> getMap4(@RequestBody  Map<String, Object> map){

        return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
    }

}
