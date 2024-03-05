package com.rdz.pitufos.business.facade.impl;

import com.rdz.pitufos.business.exception.ResourceExistOnBillException;
import com.rdz.pitufos.business.exception.ResourceNotFoundExcpetion;
import com.rdz.pitufos.business.exception.StockException;
import com.rdz.pitufos.business.facade.IBillDetailFacade;

import com.rdz.pitufos.business.mapper.IBillDetailMapper;
import com.rdz.pitufos.business.service.IBillDetailService;
import com.rdz.pitufos.business.service.IBillService;
import com.rdz.pitufos.business.service.IProductService;
import com.rdz.pitufos.domain.dto.request.BillDetailRequestDto;
import com.rdz.pitufos.domain.dto.response.BillDetailResponseDto;
import com.rdz.pitufos.domain.entity.BillDetailEntity;
import com.rdz.pitufos.domain.entity.BillEntity;
import com.rdz.pitufos.domain.entity.ProductEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillDetailFacadeImpl implements IBillDetailFacade {
    
    private final IBillDetailService iBillDetailService;
    private final IBillService iBillService;
    private final IProductService iProductService;
    private final IBillDetailMapper iBillDetailMapper;

    @Override
    public BillDetailResponseDto findById(long billId, long billDetailId) {
        BillDetailEntity billDetailEntity = iBillDetailService.findById(billDetailId,billId)
                .orElseThrow(ResourceNotFoundExcpetion::new);
        return iBillDetailMapper.toBillDetailResponseDto(billDetailEntity);
    }

    @Override
    public List<BillDetailResponseDto> findAll(long id) {
        return iBillDetailService.findAll(id).stream().map(iBillDetailMapper::toBillDetailResponseDto).toList();
    }

    @Transactional
    @Override
    public boolean save(long billId, List<BillDetailRequestDto> products) {
        BillEntity billEntity = iBillService.findById(billId).orElseThrow(ResourceNotFoundExcpetion::new);

        products.forEach(billDetailRequestDto -> {
            ProductEntity productEntity = validateProduct(billDetailRequestDto,billEntity.getId());
            BillDetailEntity billDetail = iBillDetailMapper.toBillDetailEntity(billDetailRequestDto);
            billDetail.setBill(billEntity);
            billDetail.setProduct(productEntity);
            billDetail.setTotal(productEntity.getPrice().multiply(new BigDecimal(billDetailRequestDto.quantity())));
            billDetail.setPrice(productEntity.getPrice());
            iBillDetailService.save(billDetail);
        });

        this.setTotalBill(billId,billEntity);
        return true;
    }


    @Override
    public BillDetailResponseDto update(long billId, long billDetailId, BillDetailRequestDto billDetailRequestDto) {
        //verifica que exsita el detalle
        BillDetailEntity detail = iBillDetailService.findById(billDetailId,billId)
                .orElseThrow(ResourceNotFoundExcpetion::new);

                //verifica que exista la cuenta
        BillEntity billEntity = iBillService.findById(billId).orElseThrow(ResourceNotFoundExcpetion::new);

        //valida el stock que no sea mas la cantidad
        this.validateStock(billDetailRequestDto.quantity(), detail.getProduct().getStock());
        
        //actualiza el stock del producto 
        if(detail.getQuantity()>billDetailRequestDto.quantity()){
            int newStock = detail.getQuantity() - billDetailRequestDto.quantity();
            updateStock(detail.getProduct(),newStock,false);
        }else{
            int newStock = billDetailRequestDto.quantity() - detail.getQuantity();
            updateStock(detail.getProduct(),newStock,true);
        }

        //actualiza la nueva cantidad del detalle
        detail.setQuantity(billDetailRequestDto.quantity());
        //se actualiza el total del detalle
        detail.setTotal(detail.getProduct().getPrice().multiply(new BigDecimal(detail.getQuantity())));

        //se actualiza el total de la cuenta
        this.setTotalBill(billId,billEntity);
        return iBillDetailMapper.toBillDetailResponseDto(iBillDetailService.save(detail));
    }


    @Override
    public boolean delete(long billId, long billDetailId) {
        BillEntity billEntity = iBillService.findById(billId).orElseThrow(ResourceNotFoundExcpetion::new);
        BillDetailEntity detail = iBillDetailService.findById(billDetailId, billId).orElseThrow(ResourceNotFoundExcpetion::new);
        ProductEntity product = iProductService.findById(detail.getProduct().getId()).orElseThrow(ResourceNotFoundExcpetion::new);

        //restablecer stock
        int restoreQuantity = product.getStock()+detail.getQuantity();
        product.setStock(restoreQuantity);
        iProductService.save(product);

        //actualizar el total
        BigDecimal restoreTotal = billEntity.getTotal().subtract(detail.getTotal());
        billEntity.setTotal(restoreTotal);

        iBillDetailService.delete(billDetailId);
        return true;
    }

    private ProductEntity validateProduct(BillDetailRequestDto billDetailRequestDto,long billId){
        ProductEntity productEntity = iProductService.findById(billDetailRequestDto.product()).orElseThrow(ResourceNotFoundExcpetion::new);
        if(iBillDetailService.existByProductIdAndBillId(productEntity.getId(), billId)) throw new ResourceExistOnBillException();
        validateStock(billDetailRequestDto.quantity(), productEntity.getStock());
        updateStock(productEntity,billDetailRequestDto.quantity(),true);
        return productEntity;
    }

    private void validateStock(int quantity,int stock){
        if(quantity>stock) throw new StockException();
    }

    private void updateStock(ProductEntity productEntity, int quantity,boolean subtractStock){
        if(subtractStock){
            productEntity.setStock(productEntity.getStock() - quantity);
        }else {
            productEntity.setStock(productEntity.getStock() + quantity);
        }
        iProductService.save(productEntity);
    }

    private void setTotalBill(long billId,BillEntity billEntity){
        List<BillDetailEntity> productsFromBill = iBillDetailService.findAll(billId);
        
        final BigDecimal[] total = {new BigDecimal(0)};
        
        productsFromBill.forEach(billDetailEntity -> {
            System.out.println("-------"+billDetailEntity.getProduct().getName());
            System.out.println("-------"+billDetailEntity.getQuantity());
            total[0] = total[0].add(billDetailEntity.getTotal());
        });
        
        billEntity.setTotal(total[0]);
        iBillService.save(billEntity);
    }
}
