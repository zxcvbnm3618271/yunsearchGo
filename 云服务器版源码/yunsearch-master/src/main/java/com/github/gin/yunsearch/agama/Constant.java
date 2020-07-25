package com.github.gin.yunsearch.agama;

import java.util.regex.Pattern;

/**
 * @author GinPonson
 */
public class Constant {

    public static final String FOLLOW_URL = "http://yun.baidu.com/pcloud/friend/getfollowlist?query_uk=%d&limit=%d&start=%d&bdstoken=e6f1efec456b92778e70c55ba5d81c3d&channel=chunlei&clienttype=0&web=1&logid=MTQ3NDA3NDg5NzU4NDAuMzQxNDQyMDY2MjA5NDA4NjU=";

    public static final String FANS_URL = "http://pan.baidu.com/pcloud/friend/getfanslist?query_uk=%d&limit=%d&start=%d&bdstoken=null&channel=chunlei&clienttype=0&web=1&logid=MTQ3NDAzNjQwNzg3OTAuNzM1MzMxMDUyMDczMjYxNA==";

    public static final String YUN_URL = "http://pan.baidu.com/wap/share/home?uk=%d&start=%d&adapt=pc&fr=ftw";

    public static final int LIMIT = 20;

    public static Pattern USER_PATTERN = Pattern.compile("query_uk=(\\d*)&limit=(\\d*)&start=(\\d*)&");

    public static Pattern YUN_PATTERN = Pattern.compile("uk=(\\d*)&start=(\\d*)&");

}
