package com.albertmiro.pics500px.data.ws;

import com.albertmiro.pics500px.data.model.Photo;

import org.androidannotations.annotations.EBean;

import java.util.List;

@EBean(scope = EBean.Scope.Singleton)
public class PicturesData {

    private List<Photo> listPictures;

    public void setPictures(List<Photo> pictures) {
        listPictures = pictures;
    }

    public void add(Photo photo) {
        listPictures.add(photo);
    }

    public void add(int pos, Photo photo) {
        listPictures.add(pos, photo);
    }

    public void remove(Photo photo) {
        listPictures.remove(photo);
    }

    public void remove(int pos) {
        listPictures.remove(pos);
    }

    public Photo get(int pos) {
        return listPictures.get(pos);
    }

    public List<Photo> getAll() {
        return listPictures;
    }

    public boolean isEmpty() {
        return listPictures == null || listPictures.size() == 0;
    }
}
