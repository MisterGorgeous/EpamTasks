package com.slabadniak.task5.command;


public class CommandFactory {

    private CommandFactory() {
    }


    //DoubleCHECK


    public static ICommand create(String type) {
        //String str = type.toUpperCase();
        ICommand command = null;
        switch (type) {
            case "DOM":
            case "SAX":
            case "StAX":
                return new ParseCommand();
            case "ru_RU":
            case "en_US":
                return new LocalCommand();
            case "UPLOAD":
                return new UploadCommand();
            case "QUERY":
                return new QueryCommand();
            case "TABLE":
                return new TableCommand();
            case "FILM":
                return new FilmCommand();
            default:
                return new EmptyCommand();
        }
    }
}
