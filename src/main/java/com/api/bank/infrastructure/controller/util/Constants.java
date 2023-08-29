package com.api.bank.infrastructure.controller.util;

public class Constants {
    public static class General {
        public static String ZONE_ID = "America/Bogota";

        private General(){
            throw new UnsupportedOperationException();
        }
    }
    public static class Message {
        public static String MESSAGE_OK = "Successful";
        public static String SERVER_ERROR = "server internal error";

        private Message(){
            throw new UnsupportedOperationException();
        }
    }
    public static class ServiceName {
        public static String GET_PERSON = "getPerson";
        public  static String GET_CUSTOMER = "getCustomer";
        public static String GET_ACCOUNT = "getAccount";
        public static String CREATE_PERSON = "createPerson";
        public static String CREATE_CUSTOMER = "createCustomer";
        public static String CREATE_ACCOUNT = "createAccount";
        public static String EDIT_PERSON = "editPerson";
        public static String EDIT_CUSTOMER = "editCustomer";
        public static String EDIT_ACCOUNT = "editAccount";
        public static String REMOVE_PERSON = "removePerson";
        public static String REMOVE_CUSTOMER = "removeCustomer";
        public static String REMOVE_ACCOUNT = "removeAccount";

        private ServiceName(){
            throw new UnsupportedOperationException();
        }
    }
    public static class Validation {
        public static int DAY_DIFF_REPORT_VALIDATE = -1;
        public static String STAR_TIME_VALIDATE = "00:00";
        public static String STATUS_ACTIVE = "active";
        private Validation(){
            throw new UnsupportedOperationException();
        }
    }
    public static class ConstantsUserB2b {
        public static int EXPIRATION_CODE_OTP = 15;

        private ConstantsUserB2b(){
            throw new UnsupportedOperationException();
        }
    }
    public static class StatusCodeApi {

        public static final int OK = 0;

        public static final int USERNAME_NOT_FOUND = -6;

        public static final int AUTHENTICATE_FAILED = -5;

        public static final int ERROR_BAD_REQUEST = -4;

        public static final int EXPIRED_CODE = -3;

        public static final int ERROR_NO_CONTENT = -2;

        public static final int ERROR_GENERIC = -1;

        public static final int ERROR_PASSWORD_INVALID = 3;

        private StatusCodeApi() {
            throw new UnsupportedOperationException();
        }
    }
    private Constants(){
        throw new UnsupportedOperationException();
    }
}
