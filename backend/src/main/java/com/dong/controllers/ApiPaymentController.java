package com.dong.controllers;

import com.dong.DTO.PaymentResDTO;
import com.dong.configs.ConfigVNPay;
import com.dong.pojo.Receipt;
import com.dong.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/api/vnpay")
public class ApiPaymentController {
    @Autowired
    ReceiptService recSerivce;
    @GetMapping("/create_payment")
    public ResponseEntity<PaymentResDTO> create_payment(HttpServletRequest request, @RequestParam long amount, @RequestParam long month ) throws UnsupportedEncodingException, URISyntaxException {
      String receiptId = request.getParameter("receiptId");
      String hashReceiptId = hashString(receiptId);
        String orderType = "other";
//        long amount = Integer.parseInt(req.getParameter("amount")) * 100;
//        String bankCode = req.getParameter("bankCode
                String vnp_IpAddr = ConfigVNPay.getIpAddress(request);
//        long amount= 1000000;
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_TxnRef = ConfigVNPay.getRandomNumber(8);
        String vnp_TmnCode = ConfigVNPay.vnp_TmnCode;
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command",vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "NCB");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", hashReceiptId);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        vnp_Params.put("vnp_OrderType", orderType);

        vnp_Params.put("vnp_ReturnUrl", ConfigVNPay.vnp_ReturnUrl + "/" + receiptId);
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = ConfigVNPay.hmacSHA512(ConfigVNPay.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = ConfigVNPay.vnp_PayUrl + "?" + queryUrl;
        PaymentResDTO paymentResDTO = new PaymentResDTO();
        paymentResDTO.setStatus("OK");
        paymentResDTO.setMessage("Successfully");
        paymentResDTO.setURL(paymentUrl);
        return ResponseEntity.status(HttpStatus.OK).body(paymentResDTO);
    }

    @GetMapping("/return_url/{receiptId}")
    public ResponseEntity return_url(HttpServletRequest request, @PathVariable String receiptId) throws UnsupportedEncodingException {

        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
            String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = ConfigVNPay.hashAllFields(fields);
        String response;


        String hashReceipt =  request.getParameter("vnp_OrderInfo");

        if (signValue.equals(vnp_SecureHash) && "00".equals(request.getParameter("vnp_TransactionStatus")) &&
               checkString(receiptId, hashReceipt)) {
            // thuc hien xuat hoa, thanh toan thanh cong
            // kiem tra dieu kien if xem
            Long amount = Long.valueOf(request.getParameter("vnp_Amount"));
            Optional<Receipt> optionalReceipt = Optional.ofNullable(this.recSerivce.getReceiptById(Integer.valueOf(receiptId)));
            if (optionalReceipt.isPresent()) {
                Receipt receipt = optionalReceipt.get();
                if (receipt.getTotal().compareTo(new BigDecimal(amount)) == 0) {
                    receipt.setIsPay(Boolean.FALSE);
                    this.recSerivce.addOrUpdateReceipt(receipt);
                    response = "Thanh cong";
                } else {
                    response = "So tien khong khop";
                }
            } else {
                response = "Khong tim thay hoa don";
            }
        } else {
            response = "Khong thanh cong";
        }

        return ResponseEntity.ok(response);
    }

    public static String hashString(String rawString) {
        return BCrypt.hashpw(rawString, BCrypt.gensalt());
    }

    public static boolean checkString(String rawString, String hashedString) {
        return BCrypt.checkpw(rawString, hashedString);
    }
}

