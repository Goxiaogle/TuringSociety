package com.goxiaoge.turingsociety.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goxiaoge.turingsociety.mapper.ApplicationMapper;
import com.goxiaoge.turingsociety.pojo.Application;
import com.goxiaoge.turingsociety.service.ApplicationService;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements ApplicationService {
}
