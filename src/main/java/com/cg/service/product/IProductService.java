package com.cg.service.product;

import com.cg.model.Product;
import com.cg.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IProductService extends IGeneralService<Product, Long> {

    void updatePrice(Product product);
}
