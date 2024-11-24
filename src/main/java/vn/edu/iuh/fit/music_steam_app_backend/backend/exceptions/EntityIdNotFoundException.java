package vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions;

public class EntityIdNotFoundException extends  Exception{
    public EntityIdNotFoundException(String message){
        super("The entity id = " + message + " was not found!");
    }
}
