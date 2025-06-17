package com.example.promo.service.Impl;

import com.example.promo.dto.BenefitsDTO;
import com.example.promo.dto.LabTestItemDTO;
import com.example.promo.dto.MedicineItemDTO;
import com.example.promo.model.Promo;
import com.example.promo.repository.PromoRepository;
import com.example.promo.repository.RedemptionLogRepository;
import com.example.promo.service.CouponValidationService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CouponValidationServiceImpl implements CouponValidationService {

    @Autowired
    private PromoRepository promoRepository;

    @Autowired
    private RedemptionLogRepository redemptionRepository;

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public BenefitsDTO validateCoupon(BenefitsDTO requestDTO) {
    	System.out.println("Checking Drug Typee " + requestDTO);
        BenefitsDTO responseDTO = new BenefitsDTO(requestDTO);

        // 1. Check if coupon exists
        Optional<Promo> optionalPromo = promoRepository.findByCouponCode(requestDTO.getCouponCode());
        if (optionalPromo.isEmpty()) {
            responseDTO.setValid(false);
            responseDTO.setMessage("Invalid coupon code.");
            return responseDTO;
        }

        Promo promo = optionalPromo.get();

        // 2. Validate coupon start and end date
        try {
            Date start = sdf.parse(promo.getStartDate());
            Date end = sdf.parse(promo.getEndDate());
            Date today = new Date();
            if (today.before(start) || today.after(end)) {
                responseDTO.setValid(false);
                responseDTO.setMessage("Coupon is not active.");
                return responseDTO;
            }
        } catch (Exception e) {
            responseDTO.setValid(false);
            responseDTO.setMessage("Invalid date format in coupon.");
            return responseDTO;
        }

        // 3. Validate membership requirement
        if (promo.isApplyOnMembership() && (requestDTO.getMembershipCode() == null || requestDTO.getMembershipCode().isEmpty())) {
{
            responseDTO.setValid(false);
            responseDTO.setMessage("Coupon only applicable for members.");
            return responseDTO;
        }
}


        // 6. Validate max redemption per user (if userId exists)
        if (requestDTO != null && requestDTO.getPatientId() != null) {
            String patientId = requestDTO.getPatientId();
            long userRedemptions = redemptionRepository.countByCouponCodeAndPatientId( promo.getCouponCode(),patientId);
            if (userRedemptions >= promo.getRedeemptions().getPerUserRedeemCnt()) {
                responseDTO.setValid(false);
                responseDTO.setMessage("Redemption limit reached for this user.");
                return responseDTO;
            }
        }

        // 7. Validate total redemptions
        long totalRedemptions = redemptionRepository.countByCouponCode(promo.getCouponCode());
        if (totalRedemptions >= promo.getRedeemptions().getTotalRedeemCnt()) {
            responseDTO.setValid(false);
            responseDTO.setMessage("Coupon redemption limit exceeded.");
            return responseDTO;
        }
        
     // 8. Validate discount range
        List<Object> applicableItems = new ArrayList<>();
        double totalApplicableAmount = 0.0;

        if ("MED".equalsIgnoreCase(requestDTO.getCartType())) {
            List<MedicineItemDTO> medicineItems = requestDTO.getMedicineItems();

            if (promo.getBusinessLines() != null && promo.getBusinessLines().getMedicines() != null) {
                List<String> categories = promo.getBusinessLines().getMedicines().getCategory();
                if (categories != null && !categories.isEmpty()) {
                    for (MedicineItemDTO item : medicineItems) {
                    	if (categories.stream().anyMatch(cat -> 
                        "ALL".equalsIgnoreCase(cat) || cat.equalsIgnoreCase(item.getDrugCategory())
                    )) {
                        	System.out.println("Checking Drug Typee " + item.getDrugCategory());
                            applicableItems.add(item);
                            totalApplicableAmount += item.getTotalAmount();
                        }
                    }
                }
            }
        } else if ("LAB".equalsIgnoreCase(requestDTO.getCartType())) {
            List<LabTestItemDTO> labTestItems = requestDTO.getLabTestItems();

            if (promo.getBusinessLines() != null && promo.getBusinessLines().getDiagnostics() != null) {
                List<String> categories = promo.getBusinessLines().getDiagnostics().getCategory();
                if (categories != null && !categories.isEmpty()) {
                    for (LabTestItemDTO item : labTestItems) {
                    	if (categories.stream().anyMatch(cat -> 
                        "ALL".equalsIgnoreCase(cat) || cat.equalsIgnoreCase(item.getLabType())
                    )) {
                            applicableItems.add(item);
                            totalApplicableAmount += item.getTotalAmount();
                        }
                    }
                }
            }
        }


        // Match totalApplicableAmount against discount range
        int matchedDiscountValue = promo.getDiscount().getDiscountValue(totalApplicableAmount);

        if (matchedDiscountValue == 0) {
            responseDTO.setValid(false);
            responseDTO.setMessage("Applicable items' total amount not in discount range.");
            return responseDTO;
        }

        double totalDiscount = 0.0;
        String discountType = promo.getDiscount().getDiscountType();


        if ("percentage".equalsIgnoreCase(discountType)) {
            for (Object obj : applicableItems) {
                double itemAmount = 0.0;
                double itemDiscount = 0.0;

                if (obj instanceof MedicineItemDTO) {
                    MedicineItemDTO item = (MedicineItemDTO) obj;
                    itemAmount = item.getTotalAmount();
                    itemDiscount = (matchedDiscountValue / 100.0) * itemAmount;
                    item.setDiscountAmount(itemDiscount);
                    item.setTotalAmount(itemAmount - itemDiscount); // ✅ Set paidAmount
                    totalDiscount += itemDiscount;

                } else if (obj instanceof LabTestItemDTO) {
                    LabTestItemDTO item = (LabTestItemDTO) obj;
                    itemAmount = item.getTotalAmount();
                    itemDiscount = (matchedDiscountValue / 100.0) * itemAmount;
                    item.setDiscountAmount(itemDiscount);
                    item.setTotalAmount(itemAmount - itemDiscount); // ✅ Set paidAmount
                    totalDiscount += itemDiscount;
                }
            }

        } else if ("flat".equalsIgnoreCase(discountType)) {
            totalDiscount = matchedDiscountValue;
        }

        // ✅ Update cart
//        CartDTO responseCart = requestDTO;
//        responseDTO.setCart(responseCart);
//        responseCart.setDiscountAmount(totalDiscount);
//        responseCart.setPaidAmount(responseCart.getAmount() - totalDiscount);
//        responseCart.setVoucherCode(promo.getCouponCode());
        BeanUtils.copyProperties(requestDTO, responseDTO);
        System.out.println("Checking Drug Typee " + responseDTO);
        responseDTO.setPaidAmount(requestDTO.getAmount() - totalDiscount);
        responseDTO.setDiscountAmount(totalDiscount);
        responseDTO.setValid(true);
        responseDTO.setMessage("Coupon applied successfully.");





        return responseDTO;
    }
}
