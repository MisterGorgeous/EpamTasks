package com.slabadniak.web.constant;

import com.slabadniak.web.command.*;

public enum EnumCommand {
    EMPTY{
        {
            this.command = new EmptyCommand();
        }
    },
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
    MOVIE{
        {
            this.command = new MovieCommand();
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
    SHOWCOMMENTS{
        {
            this.command = new ShowCommentsCommand();
        }
    },
    UPLOAD{
        {
            this.command = new UploadCommand();
        }
    },
    USERS{
        {
            this.command = new UsersCommand();
        }
    },
    USERBANNED{
        {
            this.command = new UserBannnedCommand();
        }
    },
    CHANGESTATUS{
        {
            this.command = new ChangeStatusCommand();
        }
    },
    ALLGENRES{
        {
            this.command = new AllGenresCommand();
        }
    },
    ADDMOVIE{
        {
            this.command = new AddMovieCommand();
        }
    },
    ADDACTOR{
        {
            this.command = new AddActorCommand();
        }
    },
    PAGINATION{
        {
            this.command = new PaginationCommand();
        }
    },
    CHANGEPROFILE{
        {
            this.command = new ChangeProfileCommand();
        }
    },
    SPECIFICGENRE{
        {
            this.command = new SpecificGenreCommand();
        }
    },
    SEARCHMOVIES{
        {
            this.command = new SearchMoviesCommand();
        }
    },
    SORTMOVIES{
        {
            this.command = new SortMoviesCommand();
        }
    },
    WRITEFEEDBACK{
        {
            this.command = new WriteFeedbackCommand();
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
