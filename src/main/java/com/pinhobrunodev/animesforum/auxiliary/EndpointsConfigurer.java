package com.pinhobrunodev.animesforum.auxiliary;

public class EndpointsConfigurer {


    public static final String [] PUBLIC = {"/oauth/token","/h2-console/**"};
    public static final String [] USER_ENDPOINT = {"/users/**"};
    public static final String [] GENDER_ENDPOINT = {"/genders/**"};
    public static final String [] ANIME_ENDPOINT = {"/animes/**"};
    public static final String [] ANIME_REQUEST_ENDPOINT = {"/anime-request/**"};
    public static final String [] TOPIC_ENDPOINT = {"/topics/**"};
    public static final String [] REPLY_ENDPOINT = {"/replies/**"};
    public static final String [] NOTIFICATION_ENDPOINT = {"/notifications/**"};

}
