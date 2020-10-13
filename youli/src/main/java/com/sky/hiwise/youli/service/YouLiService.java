package com.sky.hiwise.youli.service;

import com.baomidou.mybatisplus.extension.service.IService;

public interface YouLiService extends IService {

    void parseHtml(Integer projectId);
}
