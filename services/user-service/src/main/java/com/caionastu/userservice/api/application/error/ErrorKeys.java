package com.caionastu.userservice.api.application.error;

public class ErrorKeys {

    public static class Common {
        public final static String FAIL_TO_CREATE_ENTITY = "Fail to Create Entity.";
        public final static String FAIL_TO_UPDATE_ENTITY = "Fail to Update Entity.";
        public final static String ENTITY_NOT_FOUND = "Entity not found.";
        public final static String ENTITY_FAIL_REQUIREMENT = "Entity doesn`t meet requirements.";

    }

    public static class User {
        public static String USERNAME_ALREADY_EXISTS = "Username is already in use.";
    }
}
