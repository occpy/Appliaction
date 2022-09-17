package com.example.demo12.service.Impl;

import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.Digester;
import com.example.demo12.commo.Constants;
import com.example.demo12.commo.ErrorType;
import com.example.demo12.domain.User;
import com.example.demo12.domain.UserInfo;
import com.example.demo12.domain.UserInfoDefault;
import com.example.demo12.domain.UserRole;
import com.example.demo12.mapper.UserInfoDefaultMapper;
import com.example.demo12.mapper.UserInfoMapper;
import com.example.demo12.mapper.UserMapper;
import com.example.demo12.mapper.UserRoleMapper;
import com.example.demo12.service.ISysUserService;
import com.example.demo12.utils.RedisCache;
import com.example.demo12.vo.UserAP;
import com.example.demo12.vo.UserSignForm;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(rollbackFor = Exception.class)
public class ISysUserServiceImpl implements ISysUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisCache redisCache;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private UserInfoDefaultMapper userInfoDefaultMapper;

    /**
     * 加密密码
     */
    public String sm3ForPassWord(String pass){
        Digester digester = DigestUtil.digester("sm3");
        return digester.digestHex(pass);
    }

    /**
     * 验证码校验
     * @param verifyCode
     * @param code
     * @return
     */
    public void verifyCode (String verifyCode,String code){
        String cacheObject = (String) redisCache.getCacheObject(Constants.CAPTCHA_CODE_KEY + verifyCode);
        boolean equals = Objects.equals(code, cacheObject);
        if (!equals) {
            throw new SaTokenException(ErrorType.VERIFY_CODE_ERROR.getCode(),ErrorType.VERIFY_CODE_ERROR.getMsg());
        }
    }

    /**
     * 检测账号可用性
     * @param user
     */
    public void verifyUsability(User user){
        if (user.getStatus() == 0) {
            throw new SaTokenException(ErrorType.ACCOUNT_CANT_USE.getCode(),ErrorType.ACCOUNT_CANT_USE.getMsg());
        }
        if (user.getStatus() == 2) {
            throw new SaTokenException(ErrorType.ACCOUNT_CANCEL_USE.getCode(),ErrorType.ACCOUNT_CANCEL_USE.getMsg());
        }
    }

    /**
     *  获取用户信息
     * @param id
     * @return
     */
    public UserInfo getUserInfo(Integer id){
        return userInfoMapper.searchAllByUserid(Long.valueOf(id));
    }

    @Override
    public SaResult login(UserAP userAP) {
        UserAP userAP1 = new UserAP();
        BeanUtils.copyProperties(userAP,userAP1);
        verifyCode(userAP1.getKey(),userAP.getCode());
        List<User> users = userMapper.selectAllByEmailOrUserOrUser(userAP1.getUsername());
        if (users.isEmpty()){
            throw new SaTokenException(ErrorType.USER_UNDEFINED_ERROR.getCode(),ErrorType.USER_UNDEFINED_ERROR.getMsg());
        }
        User user = users.get(0);
        verifyUsability(user);
        String s = sm3ForPassWord(userAP1.getPassword());
        boolean verify = Objects.equals(s, user.getPwd());
        if (!verify){
            throw new SaTokenException(ErrorType.USER_PASSWORD_ERROR.getCode(),ErrorType.USER_PASSWORD_ERROR.getMsg());
        }
        UserInfo userInfo = getUserInfo(user.getId());
        StpUtil.login(user.getId(), SaLoginConfig.setExtra("info",userInfo));
        System.out.println(StpUtil.getPermissionList());
        return SaResult.ok().setData(StpUtil.getTokenInfo());
    }

    /**
     * 验证邮箱验证码
     * @param code
     * @param email
     */
    @Async
    public void verifyEmailCode(String code,String email){
        if (redisCache.getCacheObject(Constants.EMAIL_CODE_KEY+email) != code ){
            throw new SaTokenException(ErrorType.VERIFY_CODE_ERROR.getMsg()).setCode(ErrorType.VERIFY_CODE_ERROR.getCode());
        }
        redisCache.deleteObject(Constants.EMAIL_CODE_KEY+email);
    }

    /**
     * 验证邮箱注册情况
     * @param email
     */
    @Async
    public void  verifyEmailAccount(String email){
        List<User> users = userMapper.selectAllByEmailOrUserOrUser(email);
        if (!users.isEmpty()){
            throw new SaTokenException(ErrorType.USER_IS_HAVING.getMsg()).setCode(ErrorType.USER_IS_HAVING.getCode());
        }
    }

    /**
     * 校验密码
     * @param pass0
     * @param pass1
     */
    @Async
    public void verifyPassword(String pass0,String pass1){
        if (!Objects.equals(pass0,pass1)){
            throw new SaTokenException(ErrorType.PASSWORD_UN_SAME.getMsg()).setCode(ErrorType.PASSWORD_UN_SAME.getCode());
        }
    }

    /**
     * 存储账号
     * @param user
     * @return
     */
    public int insertUser(User user){
        return userMapper.insertSelective(user);
    }
    /**
     *   生成id
     */
//    @Async("generateId")
    public static Integer generateId(){
        long id =  IdUtil.getSnowflake().nextId();//得到id 很长且高位很长部分是一样的数
        StringBuilder sb=new StringBuilder(id+"");
        StringBuilder reverse = sb.reverse();//将id翻转：我们发现id很长，且高位很长部分是一样的数
        id=Long.parseLong(reverse.toString())/1000;//切去部分长度
        while(id>1999999999){//1999999999以内的10位或9位或8位id;....
            id/=10;
        }
        return Integer.parseInt(id+"");
    }

    /**
     * 角色
     * @param id
     */
    @Async
    public void insertRole(Integer id){
        UserRole userRole = new UserRole();
        userRole.setUserId(id);
        userRoleMapper.insertSelective(userRole);
    }

    /**
     *
     * @param id
     */
    @Async
    public void insertUserInfo(Integer id){
        UserInfoDefault userInfoDefault = userInfoDefaultMapper.selectByDefaultFlog();
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoDefault,userInfo);
        userInfo.setId(null);
        userInfo.setUserid(id);
        userInfoMapper.insertSelective(userInfo);
    }

    /**
     * 注册
     * @param form
     * @return
     */
    @Override
    public SaResult sign(UserSignForm form) {
        verifyEmailCode(form.getVerifyCode(), form.getEmail());
        verifyEmailAccount(form.getEmail());
        verifyPassword(form.getPassword(), form.getVerifyPassword());
        User user = new User();
        user.setCreateAt(new Date());
        user.setUpdateAt(new Date());
        user.setPwd(sm3ForPassWord(form.getPassword()));
        user.setEmail(form.getEmail());
        user.setUser(Convert.toStr(generateId()));
//        user.setStatus(1);
        insertUser(user);
        insertRole(user.getId());
        insertUserInfo(user.getId());
//        System.out.println(user.toString());
        return SaResult.ok();
    }

    /**
     * 密码修好前置验证
     * @param email
     * @param code
     * @param verifyCode
     * @param verifyKey
     * @return
     */
    @Override
    public SaResult preVerification(String email, String code, String verifyCode, String verifyKey) {
        verifyEmailAccount(email);
        verifyCode(verifyKey,verifyCode);
        verifyEmailCode(code,email);
        List<User> users = userMapper.selectAllByEmailOrUserOrUser(email);
        User user = users.get(0);
        Integer integer = generateId();
        redisCache.setCacheObject(Constants.JWT_USERID + integer,user.getId());
        return SaResult.ok().setData(integer);
    }

    /**
     * 密码修改前置验证
     * @param password
     * @param verifyPassword
     * @param verifyKey
     * @return
     */
    @Override
    public SaResult postVerification(String password, String verifyPassword, String verifyKey) {
        verifyPassword(password,verifyPassword);
        Integer cacheObject = (Integer) redisCache.getCacheObject(Constants.JWT_USERID + verifyKey);
        User user = new User();
        user.setId(cacheObject);
        user.setPwd(password);
        redisCache.deleteObject(Constants.JWT_USERID + verifyKey);
        return SaResult.ok();
    }

}
