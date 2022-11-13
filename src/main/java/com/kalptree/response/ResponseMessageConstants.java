package com.kalptree.response;

public class ResponseMessageConstants {

    public static final String success = "SUCCESS, ";
    public static final String fail = "FAILED, ";
    public static final String successAuthenticate = success + "AUTHENTICATION ";
    public static final String successCategoryAdded = success + "CATEGORY ADDED";
    public static final String successCategoryGet = success + "CATEGORY GET";
    public static final String successReactCategoryAdded = success + "REACT ADDED";
    public static final String successUserAdded = success + "USER ADDED ";
    public static final String successBlogAdded = success + "BLOG ADDED";
    public static final String successUserBlogsGet = success + "BLOG OF USER";
    public static final String userAlreadyExist = fail + "USER ALREADY EXIST";
    public static final String userNotFound = fail + "USER NOT FOUND";
    public static final String categoryAlreadyExist = fail + "CATEGORY ALREADY EXIST";
    public static final String reactCategoryAlreadyExist = fail + "REACT ALREADY EXIST";
    public static final String badCredentials = "BAD CREDENTIALS";


}
