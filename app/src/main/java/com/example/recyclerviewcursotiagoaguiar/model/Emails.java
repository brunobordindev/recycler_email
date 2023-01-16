package com.example.recyclerviewcursotiagoaguiar.model;

import java.util.Arrays;
import java.util.List;

public class Emails {

    public static List<Email> fakeEmails(){
        return Arrays.asList(

                Email.EmailBuilder.builder()
                        .setUser("Facebook")
                        .setSubject("Veja as nossas três dicas principais para aumentar as suas vendas")
                        .setPreview("Olá Bruno, Você precisa ver esse site para")
                        .setDate("5 jun")
                .build(),

                Email.EmailBuilder.builder()
                        .setUser("Facebook")
                        .setSubject("Um amigo quer que você curta a Página dele")
                        .setPreview("Fulano convidou você para curtit  sua Página do Facebook")
                        .setDate("30 mai")
                        .build(),

                Email.EmailBuilder.builder()
                        .setUser("Youtube")
                        .setSubject("Tiago Aguiar acabou de enviar um vídeo")
                        .setPreview("Tiago Aguiar enviou ANDROID: GOOGLE MAPS, LOCATION")
                        .setDate("30 mai")
                        .setStared(true)
                        .setUnread(true)
                        .build(),

                Email.EmailBuilder.builder()
                        .setUser("Instagram")
                        .setSubject("brunobordin.oficial, comecou a seguir seu perfil")
                        .setPreview("Você tem um novo seguidor")
                        .setDate("18 mai")
                        .setStared(false)
                        .build(),

                Email.EmailBuilder.builder()
                        .setUser("Facebook")
                        .setSubject("Veja as nossas três dicas principais para aumentar as suas vendas")
                        .setPreview("Olá Bruno, Você precisa ver esse site para")
                        .setDate("5 jun")
                        .build(),

                Email.EmailBuilder.builder()
                        .setUser("Facebook")
                        .setSubject("Um amigo quer que você curta a Página dele")
                        .setPreview("Fulano convidou você para curtit  sua Página do Facebook")
                        .setDate("30 mai")
                        .build(),

                Email.EmailBuilder.builder()
                        .setUser("Youtube")
                        .setSubject("Tiago Aguiar acabou de enviar um vídeo")
                        .setPreview("Tiago Aguiar enviou ANDROID: GOOGLE MAPS, LOCATION")
                        .setDate("30 mai")
                        .setStared(true)
                        .setUnread(true)
                        .build(),

                Email.EmailBuilder.builder()
                        .setUser("Instagram")
                        .setSubject("brunobordin.oficial, comecou a seguir seu perfil")
                        .setPreview("Você tem um novo seguidor")
                        .setDate("18 mai")
                        .setStared(false)
                        .build(),

                Email.EmailBuilder.builder()
                        .setUser("Instagram")
                        .setSubject("brunobordin.oficial, comecou a seguir seu perfil")
                        .setPreview("Você tem um novo seguidor")
                        .setDate("18 mai")
                        .setStared(false)
                        .build(),

                Email.EmailBuilder.builder()
                        .setUser("Facebook")
                        .setSubject("Veja as nossas três dicas principais para aumentar as suas vendas")
                        .setPreview("Olá Bruno, Você precisa ver esse site para")
                        .setDate("5 jun")
                        .build()
        );
    }
}
