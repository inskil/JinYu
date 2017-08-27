package com.example.jinyu.Database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by yyx on 2017/8/26.
 */
@Entity
public class Sentence {
    @Id(autoincrement = true)
    private Long id;
    @Property
    private String content;
    private String sound_url;
    private String analysis;
    @Generated(hash = 263422990)
    public Sentence(Long id, String content, String sound_url, String analysis) {
        this.id = id;
        this.content = content;
        this.sound_url = sound_url;
        this.analysis = analysis;
    }
    @Generated(hash = 300356185)
    public Sentence() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getSound_url() {
        return this.sound_url;
    }
    public void setSound_url(String sound_url) {
        this.sound_url = sound_url;
    }
    public String getAnalysis() {
        return this.analysis;
    }
    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }
    @Keep
    public Sentence clone(){ return new Sentence(this.id,this.content,this.sound_url,this.analysis);}

}
