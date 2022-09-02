package com.example.demo12.service;


import cn.dev33.satoken.util.SaResult;
import com.example.demo12.domain.User;
import com.example.demo12.vo.UserAP;
import com.example.demo12.vo.UserSignForm;

import java.util.List;

public interface ISysUserService {
    SaResult login(UserAP userAP);

    SaResult sign(UserSignForm form);
}
