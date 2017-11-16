package info.androidhive.materialtabs.constant;

/**
 * Created by yuva on 4/10/17.
 */

public class Constant {


    public static class AppConstant{
        public static final String BASE_URL = "ec2-34-227-225-1.compute-1.amazonaws.com";//"ec2-54-164-4-1.compute-1.amazonaws.com";
        public static final String ANDROID = "android";
        public static final int SPLASH_TIME_OUT = 2000;
    }

    public static class AppHeaderKeysValue{
        public static final String CONTENT_TYPE = "Content-Type";
        public static final String APPLICATION_JS = "application/json";
    }

    public static class APIMethods{
        public static final String SIGN_IN = "signin";
        public static final String FORGET = "forget";
    }

    public static class User{
        public static final String IS_LOGIN = "is_login";
        public static final String USER_INFO = "user_info";
    }

}


