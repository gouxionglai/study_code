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
     * 2.有-->是否超过次数限制
     *      2.1-->有，查询剩余时间，返回
     *      2.2-->无，登录验证
     *              -->失败, error++
     *                  -->是否超过次数限制
     *                      -->是，设置失效时间，3mins
     *              -->成功，over
     * 3.无-->登录验证
     *      3.1-->成功，over
     *      3.2-->失败，新建key，返回剩余次数
     * @param uname
     * @param upass
     * @return
     */
    public Object checkFilter(String uname, String upass){
        Map<String,Object> result = new HashMap<>();

        //1 判断是否有erroKey, 区分大小写
        Boolean erroKey = stringRedisTemplate.hasKey("erroKey");
        if(null != erroKey){
            //2 有key
            if(erroKey){
                String erroKey1 = stringRedisTemplate.opsForValue().get("erroKey");
                if(erroKey1 != null){
                    //2.1
                    if(Integer.parseInt(erroKey1) >=3){
                        result.put("code",0);
                        result.put("msg","登录失败");
                        result.put("expire_time","还有多少秒后可以重新登录："+stringRedisTemplate.getExpire("erroKey"));
                    }else{
                        //2.2 登录验证
                        boolean b = checkUser(uname, upass);
                        // 成功
                        if(b){
                            result.put("code",1);
                            result.put("msg","登录成功");
                        }else{
                            //失败
                            Long erroKey2 = stringRedisTemplate.opsForValue().increment("erroKey");
                            //是否超过次数
                            if(3<=erroKey2){
                                //设置失效时间 180s
                                stringRedisTemplate.expire("erroKey",180, TimeUnit.SECONDS);
                                //返回剩余时间
                                result.put("code",0);
                                result.put("msg","登录失败");
                                //重试时间
                                result.put("expire_time",180);
                            }else{
                                result.put("code",0);
                                result.put("msg","登录失败");
                                //剩余次数
                                result.put("expire_times",3-erroKey2);
                            }
                        }
                    }
                }
            }else {
                //3 无key
                boolean b = checkUser(uname, upass);
                //2.1
                if(b){
                    result.put("code",1);
                    result.put("msg","登录成功");
                }else{
                    //2.2
                    Long erroKey1 = stringRedisTemplate.opsForValue().increment("erroKey");
                    System.out.println(erroKey1);
                    try{
                        result.put("code",0);
                        result.put("msg","登录失败");
                        //剩余次数
                        result.put("expire_times",3-erroKey1);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }
}
