package com.slabadniak.task5.command;

public enum EnumCommand {
    LOCALE{
        {
            this.command = new LocalCommand();
        }
    },
    LOGIN{
        {
            this.command = new LogInCommand();
        }
    },
    LOGOFF{
        {
            this.command = new LogOffCommand();
        }
    },
    SIGNIN{
        {
            this.command = new SignInCommand();
        }
    },
    CROSS{
        {
            this.command = new CrossCommand();
        }
    },
    MOVIE{
        {
            this.command = new MovieCimmand();
        }
    },
    GENRE{
        {
            this.command = new GenreCommand();
        }
    },
    COMMENT{
        {
            this.command = new CommentCommand();
        }
    },
    ACTOR{
        {
            this.command = new ActorCommand();
        }
    },
    SETMAINCONTENT{
        {
            this.command = new SetMainContentCommand();
        }
    };

    ICommand command;
    public ICommand getCurrentCommand() {
        return command;
    }
}
