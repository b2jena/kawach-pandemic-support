package com.stackroute.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OTPService {
    // Otp Expiry time,i.e. 4 mins
    private static final Integer EXPIRE_MINS = 4;

    //Importing the LoadingCache to store the patient id and otp,
    // as a key value pair, in the form of cache.
    private LoadingCache<String, Integer> otpCache;

    //method to build the loading cache, with expiry time.
    public OTPService() {
        super();
        otpCache = CacheBuilder.newBuilder().
                expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Integer>() {
                    public Integer load(String key) {
                        return 0;
                    }
                });
    }

    //generate the otp and map it to the patient id.
    public int generateOTP(String key) {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        otpCache.put(key, otp);
        return otp;
    }

    //method to get the otp from the respective Patient id key.
    public int getOtp(String key) {
        try {
            return otpCache.get(key);
        } catch (Exception e) {
            return 0;
        }
    }

    //To clear the key value pair from system.
    public void clearOTP(String key) {
        otpCache.invalidate(key);
    }
}