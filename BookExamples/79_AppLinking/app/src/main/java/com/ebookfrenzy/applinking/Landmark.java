package com.ebookfrenzy.applinking;

public class Landmark {
    private String _id;
    private String title;
    private String description;
    private int personal;
    private static final String URLBASE = "http://example.com/landmarks/";

    public Landmark() {

    }

    public Landmark(String id, String title, String description, int personal) {
        this._id = id;
        this.title = title;
        this.description = description;
        this.personal = personal;
    }
    /*
        public Landmark(String title, String description) {
            this.title = title;
            this.description = description;
        }
    */
    public void setID(String id) {
        this._id = id;
    }

    public String getID() {
        return this._id;
    }

    public void setPersonal(int personal) {
        this.personal = personal;
    }

    public int getPersonal() {
        return this.personal;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public String getLandmarkURL() {
        return URLBASE + this._id;
    }
}

