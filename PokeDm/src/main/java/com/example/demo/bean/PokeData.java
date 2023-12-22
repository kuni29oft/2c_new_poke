package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokeData {

    private String type;

    // 引数なしのコンストラクタを定義しないとデシリアライズ（JSON -> Javaオブジェクトへの変換）時にエラーが起きる
    public PokeData() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}