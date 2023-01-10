package org.hints.common.utils;

import org.nutz.lang.random.R;

/**
 * 包路径: com.gree.gscmservice.util
 * 项目名: scm-api
 * 创建时间: 2021-07-13 10:09
 * 描述: TODO
 **/
public class UUIDUtil {
    /**
     * 获取16位随机字符串
     * @return String
     */
    public static String getUUID()
    {
        String uuid= R.UU16();
        char[] cs=new char[32];
        char c=0;
        for(int i=uuid.length()/2,j=1;i-->0;){
            if((c=uuid.charAt(i))!='-'){
                cs[j++]=c;
            }
        }
        return String.valueOf(cs);
    }

    public static String getNumberUUID()
    {
        long randomtime=System.currentTimeMillis();
        String randomtimestr = Long.toString(randomtime);
        int rannum = (int) (Math.random()*900 + 100);
        String rannumstr = Integer.toString(rannum);
        return randomtimestr+rannumstr;
    }
}
