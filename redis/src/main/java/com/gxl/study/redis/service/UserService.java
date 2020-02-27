package com.gxl.study.redis.service;

import com.gxl.study.redis.mapper.UserMapper;
import com.gxl.study.redis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author gouxi
 * @description
 * @since 2020/2/20
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public User getUser(Integer id) {
        //
        return userMapper.selectByPrimaryKey(id);
    }

    public boolean checkUser(String uname, String upass){
        boolean flag =false;
        HashMap searchMap = new HashMap<>();
        searchMap.put("uname",uname);
        searchMap.put("upass",upass);
        User dbUser = userMapper.findSelective(searchMap);
        if(dbUser != null){
            flag = true;
        }
        return flag;
    }

    /**
     * 1.是否有erroKey
     * 2.没有-->登录验证
     *      2.1.true, ok
     *      2.2.false, 创建erroKey, 自增1
     * 3.有-->是否超过登录限制次数
     *      3.1.没有-->登录验证
     *          -->true, ok
     *          -->false, erroKeyValue+1
     *      3.2.有-->查看剩余失效时间，返回
     * @param uname
     * @param upass
     * @return
     */
    public Object checkFilter(String uname, String upass){
        Map result = new HashMap();

        //1
        Boolean erroKey = stringRedisTemplate.hasKey("erroKey");
        if(null != erroKey){
            //2
            if(!erroKey){
                boolean b = checkUser(uname, upass);
                //2.1
                if(b){
                   result.put("code",1);
                   result.put("msg","登录成功");
                }else{
                    //2.2
                    Long erroKey1 = stringRedisTemplate.opsForValue().increment("erroKey");
                    System.out.println(erroKey1);
                    result.put("code",0);
                    result.put("msg","登录失败");
                    //剩余次数
                    result.put("expire_times",5-erroKey1);
                }
            }else {
                //3

                String erroKey1 = stringRedisTemplate.opsForValue().get("erroKey");
                if(erroKey1 != null){
                    if(Integer.parseInt(erroKey1) >=5){
                        result.put("code",0);
                        result.put("msg","登录失败");
                        result.put("expire_time","还有多少秒后可以重新登录："+stringRedisTemplate.getExpire("erroKey"));
                    }else{
                        boolean b = checkUser(uname, upass);
                        //3.1
                        if(b){
                            result.put("code",1);
                            result.put("msg","登录成功");
                        }else{
                            //3.2
                            Long erroKey2 = stringRedisTemplate.opsForValue().increment("erroKey");
                            result.put("code",0);
                            result.put("msg","登录失败");
                            //剩余次数
                            result.put("expire_times",5-erroKey2);
                        }
                    }
                }
            }

        }
        return result;
    }
}
