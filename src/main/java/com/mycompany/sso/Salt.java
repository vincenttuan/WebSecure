package com.mycompany.sso;

import java.security.SecureRandom;

public class Salt {
    public static int getSalt() {
        // SecureRandom.getInstanceStrong() 高強度隨機值 
        SecureRandom sr = new SecureRandom(); // 不可預測的安全隨機數
        return sr.nextInt(10000);
    }
}
