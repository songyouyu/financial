package com.imooc.manager.rpc;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.imooc.api.ProductRpc;
import com.imooc.api.domain.ParamInf;
import com.imooc.api.domain.ProductRpcReq;
import com.imooc.entity.Product;
import com.imooc.manager.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * rpc服务实现类
 * Created by songyouyu on 2018/6/14
 */
@Service
@AutoJsonRpcServiceImpl
public class ProductRpcImpl implements ProductRpc {

    private static Logger log = LoggerFactory.getLogger(ProductRpcImpl.class);

    @Autowired
    private ProductService productService;

    /**
     * 查询多个产品
     * @param req
     * @return
     */
    @Override
    public List<Product> query(ParamInf req) {
        log.info("查询多个产品,请求:{}", req);
        Pageable pageable = new PageRequest(0, 100, Sort.Direction.DESC, "rewardRate");
        Page<Product> result = productService.selectProductList(req.getIdList(), req.getMinRewardRate(),
                req.getMaxRewardRate(), req.getStatusList(), pageable);

        log.info("查询多个产品,结果:{}", result);
        return result.getContent();
    }

    /**
     * 查询产品详情
     * @param id
     * @return
     */
    @Override
    public Product findOne(String id) {
        log.info("查询产品详情,请求:{}", id);
        Product result = productService.selectProduct(id);
        log.info("查询产品详情,结果:{}", result);
        return result;
    }
}