<header>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <form class="navbar-form navbar-left" action="Controller" method="post">
                    <input type="hidden" name="command" value="setmaincontent">
                    <input type="hidden" name="page" value="path.page.main">
                    <button type="submit" class="btn btn-default"><img src="/img/logo.jpg" alt="" width="40"
                                                                       height="20"></button>
                </form>
            </div>

            <form class="navbar-form navbar-left" name="search" action="Controller" method="post">
                <input type="hidden" name="command" value="searchmovies">
                <input type="hidden" name="page" value="path.page.main">
                <div class="form-group">
                    <input name="searchMovie" type="text" placeholder="Search" pattern="[A-Z a-z\w]{1,56}">
                </div>
                <button type="submit" class="btn btn-primary">Search Movie</button>
            </form>


            <form class="navbar-form navbar-left" name="locale" action="Controller" method="post">
                <input type="hidden" name="command" value="locale">
                <input type="hidden" name="page" value="path.page.main">
                <div class="btn-group">
                    <input type="submit" name="button" class="btn btn-primary"
                           value="EN"/>
                    <input type="submit" name="button" class="btn btn-primary"
                           value="RU"/>
                </div>
            </form>


            <c:if test="${userStatus == 'GUEST'}">
                <form class="navbar-form navbar-left" name="signin" action="Controller" method="post">
                    <input type="hidden" name="command" value="">
                    <input type="hidden" name="page" value="path.page.signin">
                    <input type="submit" name="button" class="btn btn-primary"
                           value="<fmt:message key="main.sigin" bundle="${resourceBundle}"/>"/>
                </form>

                <!--Sign in -->

                <form class="navbar-form navbar-left" action="Controller" method="post">
                    <input class="form-control" type="hidden" name="command"
                           value="login">
                    <input class="form-control" type="hidden" name="page"
                           value="path.page.main">
                    <button id="modalButton" type="button" class="btn btn-primary" data-toggle="modal"
                            data-target="#myModal">
                        <fmt:message key="main.login" bundle="${resourceBundle}"/>
                    </button>
                </form>

                <!-- Modal -->
                <div class="modal fade  pagination-centered text-center" id="myModal" tabindex="-1" role="dialog"
                     aria-labelledby="myModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content ">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title" id="myModalLabel">Sign In</h4>
                            </div>
                            <div class="modal-body">
                                <div class="container">
                                    <div class="row">
                                        <div class="col-sm-6 col-md-4 col-lg-4">
                                            <div class="account-wall">
                                                <img class="profile-img"
                                                     src="/img/photo.png"
                                                     alt="">
                                                <form class="form-signin" title="login" action="Controller"
                                                      method="post">
                                                    <input class="form-control" type="hidden" name="command"
                                                           value="login">
                                                    <input class="form-control" type="hidden" name="page"
                                                           value="path.page.main">
                                                    <input name="login" type="text" class="form-control col-md-8 "
                                                           placeholder="Login" pattern="^[A-Za-z]\w{4,32}$" required
                                                           autofocus>
                                                    <input type="password" class="form-control col-md-8"
                                                           placeholder="Password"
                                                           name="pass"
                                                           pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,32}$"
                                                           required>
                                                    <button id="logon" class="btn btn-lg btn-primary btn-block"
                                                            type="submit">Log on
                                                    </button>
                                                </form>


                                            </div>

                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>


            <c:if test="${userStatus == 'ADMINISTRATOR'}">
                <div class="dropdown navbar-form navbar-left">


                    <button class="btn btn-info dropdown-toggle " type="button" data-toggle="dropdown">Actions
                        <span class="caret"></span></button>

                    <ul class="dropdown-menu">
                        <li class="dropdown-header">Change Users Status</li>
                        <li>
                            <form action="Controller" method="post">
                                <input type="hidden" name="command" value="users">
                                <input type="hidden" name="page" value="path.page.admin">
                                <input class="btn btn-info" type="submit" title="button"
                                       value="<fmt:message key="header.users" bundle="${resourceBundle}"/>"/>
                            </form>
                        </li>
                        <li class="dropdown-header">Add new Movie to DB</li>
                        <li>
                            <form action="Controller" method="post">
                                <input type="hidden" name="command" value="allgenres">
                                <input type="hidden" name="page" value="path.page.addmovie">
                                <input class="btn btn-info" type="submit" title="button"
                                       value="<fmt:message key="header.addmovie" bundle="${resourceBundle}"/>"/>
                            </form>
                        </li>
                        <li class="dropdown-header">Add Actors to Movie</li>
                        <li>
                            <form action="Controller" method="post">
                                <input type="hidden" name="command" value="">
                                <input type="hidden" name="page" value="path.page.addactor">
                                <input class="btn btn-info" type="submit" title="button"
                                       value="<fmt:message key="header.addactor" bundle="${resourceBundle}"/>"/>
                            </form>
                        </li>
                    </ul>
                </div>
            </c:if>


            <c:if test="${feedback.written}">
                <div id="modalInfo" class="navbar navbar-left">
                    <h3><span id="logged" class="label label-warning ">${feedback.message} </span>
                    </h3>
                </div>
            </c:if>

            <c:if test="${userStatus != 'GUEST'}">


                <form class="navbar-form navbar-right" title="logoof" action="Controller" method="post">
                    <input type="hidden" name="command" value="logoff">
                    <input type="hidden" name="page" value="path.page.main">
                    <input class="btn btn-primary" type="submit" title="button"
                           value="<fmt:message key="header.logoff" bundle="${resourceBundle}"/>"/>
                </form>

                <form class="navbar-form navbar-right" title="user" action="Controller" method="post">
                    <input type="hidden" name="command" value="">
                    <input type="hidden" name="page" value="path.page.user">
                    <!--<input class="btn btn-default" type="submit"  title="button" value="user" /> />-->
                    <button type="submit" class="btn btn-default">
                        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                            ${user.login}
                    </button>
                </form>
            </c:if>


            <script src="/js/custom.js"></script>

        </div>
    </nav>

</header>


