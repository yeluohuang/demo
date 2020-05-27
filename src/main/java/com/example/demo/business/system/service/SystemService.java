package com.example.demo.business.system.service;

import com.example.demo.business.system.domain.SystemInfos;

/**
 * @description 系统监控的接口
 * @author zhushj3
 * @date 2020/05/22
 */
public interface SystemService {
    /**
     *  罗列系统和虚拟机的信息
     * @return
     */
    SystemInfos listSystemInfos();
}
