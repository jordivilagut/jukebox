package com.jordivt.jukebox.Controller;

public class ControllerFactory {

    private static ControllerFactory instance;
    private AlbumsController albumsController;

    public static ControllerFactory get() {
        if (instance == null) {
            instance = new ControllerFactory();
        }
        return instance;
    }

    public AlbumsController getAlbumsController() {
        if (albumsController == null) albumsController = new AlbumsController();
        return albumsController;
    }
}