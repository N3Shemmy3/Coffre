package dev.n3shemmy3.coffre.backend.item;

public class Page {
    private String emoticon;
    private String title;
    private String summary;

    public Page(String emoticon, String title, String summary) {
        this.emoticon = emoticon;
        this.title = title;
        this.summary = summary;
    }

    public String getEmoticon() {
        return emoticon;
    }

    public void setEmoticon(String emoticon) {
        this.emoticon = emoticon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
